/*
        This file is part of FeiraAutomotiva.

        FeiraAutomotiva is free software: you can redistribute it and/or modify
        it under the terms of the GNU General Public License as published by
        the Free Software Foundation, either version 3 of the License, or
        (at your option) any later version.

        FeiraAutomotiva is distributed in the hope that it will be useful,
        but WITHOUT ANY WARRANTY; without even the implied warranty of
        MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
        GNU General Public License for more details.

        You should have received a copy of the GNU General Public License
        along with Foobar.  If not, see <https://www.gnu.org/licenses/>.

 */
package br.com.fatec.lista2.model;

import br.com.fatec.lista2.controller.Controller;

import java.util.*;

/*
 * Compreendendo o requisito funcional 05
 */
public class Schedule {
        /*
         * O armazenamento dos clientes e automóveis será realizado com Hashmap.
         * Para o map de clientes, a chave é a primeira letra do nome do cliente e o valor é uma
         * lista ligada de clientes cujo nome começa com a letra da chave.
         * Para o map de veículos, a chave é a marca do veículo e o valor é uma lista ligada de
         * veículos cuja marca seja igual à chave.
         */
        private Map<Character, List<Client>> clientSchedule;
        private Map<String, List<Vehicle>> vehicleSchedule;

        public Schedule() {
                clientSchedule = new HashMap<>();
                vehicleSchedule = new HashMap<>();
        }
        /*
         * Retorna a quantidade de elementos dentro da lista
         */
        public int size() {
                int counter = 0;
                for (Map.Entry<Character,List<Client>> i : clientSchedule.entrySet()) {
                        counter += i.getValue().size();
                }
                return counter;
        }

        public boolean isEmpty() {
                return size() <= 0;
        }
        /*
         * Adiciona um novo cliente, junto com seu carro
         * Compreendendo o requisito funcional 01
         */
        public void add(Client client) {
                if (scheduleContainsKey(client)) {
                        addIntoExistingScheduleList(client);
                } else {
                        addIntoNewList(client);
                }
                add(client.getVehicle());
                new Controller().sync(this);
        }

        boolean scheduleContainsKey(Client client) {
                return clientSchedule.containsKey(client.getName().charAt(0));
        }

        void addIntoExistingScheduleList(Client client) {
                clientSchedule.get(client.getName().charAt(0)).add(client);
                // ordena pelo nome do cliente
                clientSchedule.get(client.getName().charAt(0)).sort(Comparator.comparing(Client::getName));
        }

        void addIntoNewList(Client client) {
                clientSchedule.put(client.getName().charAt(0), new LinkedList<>());
                clientSchedule.get(client.getName().charAt(0)).add(client);
        }

        /*
         * Adiciona um veículo no map
         * Compreendendo o requisito funcional 04
         */
        public void add(Vehicle vehicle) {
                if (brandIsNotEmpty(vehicle)) {
                        if (scheduleContainsKey(vehicle)) {
                                addIntoExistingScheduleList(vehicle);
                        } else {
                                addIntoNewList(vehicle);
                        }
                }
        }
        boolean scheduleContainsKey(Vehicle vehicle) {
                return vehicleSchedule.containsKey(vehicle.getBrand());
        }
        boolean brandIsNotEmpty(Vehicle vehicle) {
                return !vehicle.getBrand().equals("");
        }
        void addIntoExistingScheduleList(Vehicle vehicle) {
                vehicleSchedule.get(vehicle.getBrand()).add(vehicle);
                // ordena pela versão
                vehicleSchedule.get(vehicle.getBrand()).sort(Comparator.comparing(Vehicle::getModelVersion));
        }

        void addIntoNewList(Vehicle vehicle) {
                vehicleSchedule.put(vehicle.getBrand(), new LinkedList<>());
                // não precisa ordenar, pois só tem um valor
                vehicleSchedule.get(vehicle.getBrand()).add(vehicle);
        }
        /*
         * Remoção do cliente da lista
         * Compreendendo o requisito funcional 02
         */
        public void remove(Client client) {
                // Como cada cliente terá um veículo, a remoção do cliente também removerá
                // o seu veículo.
                remove(client.getVehicle());
                for (Map.Entry<Character, List<Client>> listEntry : clientSchedule.entrySet()) {
                        listEntry.getValue().removeIf(cli -> cli.getName().equals(client.getName()));
                }
                new Controller().sync(this);
        }
        /*
         * Remoção do veículo da lista
         */
        public void remove(Vehicle vehicle) {
                for (Map.Entry<String, List<Vehicle>> listEntry : vehicleSchedule.entrySet()) {
                        for (Vehicle veh : listEntry.getValue()) {
                                if (veh.equals(vehicle)) {
                                        listEntry.getValue().remove(vehicle);
                                }
                        }
                }
        }
        /*
         * Busca de cliente
         * Compreendendo os requisitos:
         *      Funcional 10
         *      Complementar 08
         *      De negócio 08
         */
        public Client find(String name) {
                for (Client client : getClients(name.charAt(0))) {
                        if (client.getName().equals(name)) {
                                return client;
                        }
                }
                return null;
        }
        /*
         * Getters e Setters
         */
        public List<Client> getClients(Character key) {
                return clientSchedule.get(key);
        }

        public List<Vehicle> getVehicles(String key) {
                return vehicleSchedule.get(key);
        }

        public Map<Character, List<Client>> getClientMap() {
                return clientSchedule;
        }

        public Map<String, List<Vehicle>> getVehicleMap() {
                return vehicleSchedule;
        }
}
