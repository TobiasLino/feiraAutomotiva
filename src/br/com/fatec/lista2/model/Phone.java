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
public class Phone {
        private String number;
        public Phone() {
                this.number = "";
        }
        // Impressão dos dados
        public void print() {
                System.out.println("Telefone.\n"
                        + "\tNúmero: " + number);
        }
        /*
         * Getters e Setters
         */
        public void setNumber(String number) {
                this.number = number;
        }
        public String getNumber() {
                return number;
        }
}
