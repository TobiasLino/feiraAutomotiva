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
package br.com.fatec.lista2.controller;

import br.com.fatec.lista2.model.Client;
import br.com.fatec.lista2.model.Review;
import br.com.fatec.lista2.model.Revisions;
import br.com.fatec.lista2.model.Schedule;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


/*
 * Classe de acesso aos dados e operações com arquivo
 */
public class Controller {
        private Scanner scan;

        public Controller() {
                scan = new Scanner(System.in);
        }
        // Input com mensagem
        public String getOption(String message) {
                System.out.print(message);
                return scan.nextLine();
        }
        // Impressão de erros com controle de saída do programa.
        public void error(String message, boolean fatality) {
                System.err.println("ERROR: " + message);
                if (fatality) {
                        System.exit(1);
                }
        }
        /*
         * Operações com arquivo
         *
         * Verifica se os arquivos existem.      */
        public void fileCheck() {
                File pathToClients = new File("usr//clients");
                File clientsFile = new File("usr//clients//clients.json");
                File pathToRevision = new File("usr//revisions");
                File nextRevisionsFile = new File("usr//revisions//nextRevisions.json");
                File previousRevisionFile = new File("usr//revisions//previousRevisions.json");
                try {
                        verify(pathToClients, clientsFile);
                        verify(pathToRevision, nextRevisionsFile);
                        verify(pathToRevision, previousRevisionFile);
                } catch (Exception exp) {
                        exp.printStackTrace();
                }
        }
        // Verifica e cria as pastas se não existirem
        private void verify(File path, File file) throws IOException {
                if (!path.exists()) {
                        if (!path.mkdirs()) {
                                error("Criação de pasta", true);
                        }
                        if (!file.createNewFile()) {
                                error("Criação de arquivos", true);
                        }
                } else {
                        if (!file.exists()) {
                                if (!file.createNewFile()) {
                                        error("Criação de arquivos", true);
                                }
                        }
                }
        }
        /*
         * Salva os dados no arquivo
         * Compreendendo as Regras de negócio 01 e 07
         */
        public void sync(Schedule schedule, Revisions revisions) {
                try {
                        sync(schedule);
                        sync(revisions);
                } catch (Exception e) {
                        e.printStackTrace();
                }
        }
        /*
         * Insere os dados do cliente no arquivo
         */
        private void sync(Schedule schedule) {
                File clientsFile = new File("usr//clients//clients.json");
                try {
                        // Objeto de escrita
                        FileWriter clientsJSON = new FileWriter(clientsFile);
                        // Array dos objetos json
                        JSONArray clientsArray = new JSONArray();
                        // Insere no array
                        insertClients(schedule, clientsArray);
                        // Escreve
                        clientsJSON.write(clientsArray.toJSONString());
                        clientsJSON.flush();
                        clientsJSON.close();

                } catch (IOException ioE) {
                        Logger.getLogger(schedule.getClass().getName()).log(Level.SEVERE, null, ioE);
                }
        }
        // Inserção da agenda de clientes
        @SuppressWarnings("unchecked")
        private void insertClients(Schedule schedule, JSONArray jsonArray) {
                for (Map.Entry<Character,List<Client>> clientMap : schedule.getClientMap().entrySet()) {
                        for (Client client : clientMap.getValue()) {
                                JSONObject jsonObject = new JSONObject();
                                putClientIntoJSON(client, jsonObject);
                                jsonArray.add(jsonObject);
                        }
                }
        }
        // Insere o cliente no objeto
        @SuppressWarnings("unchecked")
        private void putClientIntoJSON(Client client, JSONObject jsonObject) {
                jsonObject.put("name", client.getName());
                jsonObject.put("cpf", client.getCpf());
                jsonObject.put("phone", client.getPhone().getNumber());
                putClientAddressIntoJSON(jsonObject, client);
                putClientVehicleIntoJSON(jsonObject, client);
                putReviewDateIntoJSON(client.getNextReview(), jsonObject);
        }
        @SuppressWarnings("unchecked")
        private void putClientAddressIntoJSON(JSONObject jsonObject, Client client) {
                // Insere o endereço em um array
                JSONArray jsonArrayAddress = new JSONArray();
                JSONObject address = new JSONObject();
                address.put("street", client.getAddress().getStreet());
                address.put("number", client.getAddress().getNumber());
                address.put("complement", client.getAddress().getComplement());
                address.put("neighborhood", client.getAddress().getNeighborhood());
                address.put("city", client.getAddress().getCity());
                address.put("state", client.getAddress().getState());
                jsonArrayAddress.add(address);
                // Insere o endereço no objeto
                jsonObject.put("address", jsonArrayAddress);
        }
        @SuppressWarnings("unchecked")
        private void putClientVehicleIntoJSON(JSONObject jsonObject, Client client) {
                // Insere os dados do veículo em um array
                JSONArray jsonArrayVehicle = new JSONArray();
                JSONObject vehicle = new JSONObject();
                vehicle.put("licensePlate", client.getVehicle().getLicensePlate());
                vehicle.put("modelVersion", client.getVehicle().getModelVersion());
                vehicle.put("brand", client.getVehicle().getBrand());
                vehicle.put("yearOfManufacture", client.getVehicle().getYearOfManufacture().get(Calendar.YEAR));
                vehicle.put("purchasePrice", client.getVehicle().getPurchasePrice());
                jsonArrayVehicle.add(vehicle);
                // Insere os dados no objeto
                jsonObject.put("vehicle", jsonArrayVehicle);
        }
        @SuppressWarnings("unchecked")
        private void putReviewDateIntoJSON(Review review, JSONObject jsonObject) {
                JSONObject reviewDate = new JSONObject();
                JSONArray date = new JSONArray();
                reviewDate.put("day", review.getReviewDate().get(Calendar.DAY_OF_MONTH));
                reviewDate.put("month", review.getReviewDate().get(Calendar.MONTH));
                reviewDate.put("year", review.getReviewDate().get(Calendar.YEAR));
                date.add(reviewDate);
                jsonObject.put("nextReview", date);
        }
        /*
         * Insere os dados das próximas revisões no array
         */
        private void sync(Revisions revisions) {
                File revisionsFile = new File("usr//revisions//nextRevisions.json");
                try {
                        FileWriter revisionsJSON = new FileWriter(revisionsFile);
                        JSONArray revisionsArray = new JSONArray();
                        insertReview(revisions, revisionsArray);
                        revisionsJSON.write(revisionsArray.toJSONString());
                        revisionsJSON.flush();
                        revisionsJSON.close();
                } catch (IOException ioE) {
                        Logger.getLogger(revisions.getClass().getName()).log(Level.SEVERE, null, ioE);
                }
        }
        // Insere os dados da revisão
        @SuppressWarnings("unchecked")
        private void insertReview(Revisions revisions, JSONArray jsonArray) {
                for (Map.Entry<Client, List<Review>> revisionsMap : revisions.getMap().entrySet()) {
                        for (Review rev : revisionsMap.getValue()) {
                                JSONObject jsonObject = new JSONObject();
                                putReviewIntoJSON(jsonObject, rev);
                                jsonArray.add(jsonObject);
                        }
                }
        }
        // Insere os dados no objeto Json
        private void putReviewIntoJSON(JSONObject jsonObject, Review review) {
                putReviewDateIntoJSON(review, jsonObject);
                putClientIntoReviewJSON(jsonObject, review.getClient());
        }
        // Insere o cliente que tem uma revisão agendada no json
        @SuppressWarnings("unchecked")
        private void putClientIntoReviewJSON(JSONObject jsonObject, Client client) {
                JSONArray reviewClientArray = new JSONArray();
                JSONObject reviewClient = new JSONObject();
                putClientIntoJSON(client, reviewClient);
                reviewClientArray.add(reviewClient);
                jsonObject.put("client",reviewClientArray);
        }
}
