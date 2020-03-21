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

/*
 * Compreendendo a regra de negócio 02
 */
public class Client {
        private String name;
        private String cpf;
        private Phone phone;
        private Address address;
        private Vehicle vehicle;
        private Revisions revisions;

        public Client() {
                name = "";
                cpf = "";
                phone = new Phone();
                address = new Address();
                vehicle = new Vehicle();
                vehicle.setClient(this);
                revisions = new Revisions();
        }
        // Impressão dos dados
        public void print() {
                System.out.println("Dados do cliente.\n"
                        + "\tNome: " + name + "\n"
                        + "\tCPF: " + cpf);
                address.print();
                phone.print();
                vehicle.print();
        }
        // Adiciona uma revisão do veículo do cliente
        public void addReview(Review review) {
                this.revisions.add(review);
        }
        /*
         * Getters e Setters
         */
        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getCpf() {
                return cpf;
        }

        public void setCpf(String cpf) {
                this.cpf = cpf;
        }

        public Phone getPhone() {
                return phone;
        }

        public void setPhone(Phone phone) {
                this.phone = phone;
        }

        public Address getAddress() {
                return address;
        }

        public void setAddress(Address address) {
                this.address = address;
        }

        public Vehicle getVehicle() {
                return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
                this.vehicle = vehicle;
                this.vehicle.setClient(this);
        }

        public Revisions getRevisions() {
                return revisions;
        }

        public void setRevisions(Revisions revisions) {
                this.revisions = revisions;
        }
}
