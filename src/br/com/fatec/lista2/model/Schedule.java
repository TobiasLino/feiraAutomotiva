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
         * Adiciona um novo cliente, junto com seu carro
         */
        public void add(Client client) {
                if (clientSchedule.containsKey(client.getName().charAt(0))) {
                        clientSchedule.get(client.getName().charAt(0)).add(client);
                        // ordena
                        clientSchedule.get(client.getName().charAt(0)).sort(Comparator.comparing(Client::getName));
                        // adiciona o veículo do cliente na lista de veículos
                        add(client.getVehicle());
                } else {
                        clientSchedule.put(client.getName().charAt(0), new LinkedList<>());
                        clientSchedule.get(client.getName().charAt(0)).add(client);
                        // não precisa ordenar pois só tem um único cliente.
                        add(client.getVehicle());
                }
        }
        // Adiciona um veículo no map
        public void add(Vehicle vehicle) {
                if (!vehicle.getBrand().equals("")) {
                        if (vehicleSchedule.containsKey(vehicle.getBrand())) {
                                vehicleSchedule.get(vehicle.getBrand()).add(vehicle);
                                // ordena
                                vehicleSchedule.get(vehicle.getBrand()).sort(Comparator.comparing(Vehicle::getModelVersion));
                        } else {
                                vehicleSchedule.put(vehicle.getBrand(), new LinkedList<>());
                                vehicleSchedule.get(vehicle.getBrand()).add(vehicle);
                        }
                }
        }

        public List<Client> getClients(Character key) {
                return clientSchedule.get(key);
        }

        public List<Vehicle> getVehicles(String key) {
                return vehicleSchedule.get(key);
        }
}