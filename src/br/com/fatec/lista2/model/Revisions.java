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
 * Lista com todas as revisões agendadas.
 * Compreendendo os requisitos:
 *      Funcionais 06 05 07 08 11
 *      De Negócio 07
 */
public class Revisions {
        private Map<Client, List<Review>> revisions;

        public Revisions() {
                revisions = new HashMap<>();
        }
        /*
         * Adição de uma revisão
         * Compreendendo o requisito funcional 06
         */
        public void add(Review review) {
                // Verifica se a data da revisão está marcada para depois do dia atual.
                if (review.getReviewDate().getTime().after(new Date())) {
                        if (revisions.containsKey(review.getClient())) {
                                revisions.get(review.getClient()).add(review);
                                // Ordena por data
                                revisions.get(review.getClient()).sort(Comparator.comparing(Review::getReviewDate));
                        } else {
                                revisions.put(review.getClient(), new LinkedList<>());
                                revisions.get(review.getClient()).add(review);
                        }
                }
        }

        /*
         * Getters e Setters
         */
        public Map<Client, List<Review>> getRevisions() {
                return revisions;
        }

        public Map<Client, List<Review>> getMap() {
                return revisions;
        }
}
