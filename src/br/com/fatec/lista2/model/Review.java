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

import java.text.SimpleDateFormat;
import java.util.Calendar;

/*
 * Agendamento de revisões.
 * Compreendendo o requisito funcional 06
 */
public class Review {
        private Client client;
        private Vehicle vehicle;
        private Calendar reviewDate;

        public Review() {
                client = new Client();
                vehicle = client.getVehicle();
                reviewDate = Calendar.getInstance();
        }
        /*
         * Impressão da revisão
         */
        public void print() {
                System.out.println("Dados da Revisão.\n"
                        + "\tData da revisão: " + reviewDate.get(Calendar.DAY_OF_MONTH) + "/"
                        + reviewDate.get(Calendar.MONTH) + "/"
                        + reviewDate.get(Calendar.YEAR)
                );
                client.print();
        }
        /*
         * Getters e Setters
         */
        public Vehicle getVehicle() {
                return vehicle;
        }

        public void setVehicle(Vehicle vehicle) {
                this.vehicle = vehicle;
        }

        public Calendar getReviewDate() {
                return reviewDate;
        }
        /*
         * Alteração da data de revisão,
         * Compreendendo o requisito funcional 07
         */
        public void setReviewDate(Calendar reviewDate) {
                this.reviewDate = reviewDate;
        }

        public Client getClient() {
                return client;
        }

        public void setClient(Client client) {
                this.client = client;
                setVehicle(client.getVehicle());
        }
}
