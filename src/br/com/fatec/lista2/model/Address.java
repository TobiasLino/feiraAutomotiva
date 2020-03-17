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
public class Address {
        private String street;
        private String number;
        private String complement;
        private String neighborhood;
        private String city;
        private String state;

        public Address() {
                street = "";
                number = "";
                complement = "";
                neighborhood = "";
                city = "";
                state = "";
        }
        // Impressão dos dados
        public void print() {
                System.out.println("Endereço\n"
                        + "\tRua:         " + street + "\n"
                        + "\tNúmero:      " + number + "\n"
                        + "\tComplemento: " + complement + "\n"
                        + "\tBairro:      " + neighborhood + "\n"
                        + "\tCidade:      " + city + "\n"
                        + "\tEstado:       " + state
                );
        }
        /*
         * Getters e Setters
         */
        public String getStreet() {
                return street;
        }

        public void setStreet(String street) {
                this.street = street;
        }

        public String getNumber() {
                return number;
        }

        public void setNumber(String number) {
                this.number = number;
        }

        public String getComplement() {
                return complement;
        }

        public void setComplement(String complement) {
                this.complement = complement;
        }

        public String getNeighborhood() {
                return neighborhood;
        }

        public void setNeighborhood(String neighborhood) {
                this.neighborhood = neighborhood;
        }

        public String getCity() {
                return city;
        }

        public void setCity(String city) {
                this.city = city;
        }

        public String getState() {
                return state;
        }

        public void setState(String state) {
                this.state = state;
        }
}
