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

import br.com.fatec.lista2.model.*;
import br.com.fatec.lista2.view.Menu;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
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

        // Impressão de erros com controle de saída do programa.
        public void error(String message, boolean fatality) {
                System.err.println("ERROR: " + message);
                if (fatality) {
                        System.exit(1);
                }
        }
        // Input com mensagem
        public String getOption(String message) {
                System.out.print(message);
                return scan.nextLine();
        }
        public String getOption() {
                System.out.print("Insira a sua opção: ");
                return scan.nextLine();
        }
        // Para verificar se o valor digitado é número
        public boolean isNumber(String string) {
                return string.matches("[0-9]*");
        }
        // Retorna o valor digitado em inteiro
        public int intOption() {
                String opt = getOption();
                if (!opt.equals("") && isNumber(opt)) {
                        return Integer.parseInt(opt);
                } else {
                        return 0;
                }
        }
        // Retorna o valor digitado em inteiro com mensagem
        public int intOption(String message) {
                String opt = getOption(message);
                if (!opt.equals("") && isNumber(opt)) {
                        return Integer.parseInt(opt);
                } else {
                        return 0;
                }
        }
        // Retorna o valor digitado em double com mensagem
        public double doubleOption(String message) {
                String opt = getOption(message);
                if (!opt.equals("") && isNumber(opt)) {
                        return Double.parseDouble(opt);
                } else {
                        return 0;
                }
        }
        // Para verificar se um objeto é nulo
        <T> boolean isNull(T obj) {
                return obj == null;
        }
        /*
         * Confirmação do usuário
         * Compreendendo as Regras de negócio 04 e 05
         */
        public boolean confirmOption() {
                String opcao = getOption("Confirmar ? (S/n): ");
                return opcao.equals("s") || opcao.equals("S")
                        || opcao.equals(" ");
        }
        /*
         * Adição, remoção e edição dos dados do cliente
         */
        /*
         * Adição de um cliente
         * Compreendendo o requisito Funcional 01
         */
        public void insertClient(Schedule schedule) {
                Client novo = new Client();
                editClientInfos(novo);
                schedule.add(novo);
        }
        /*
         * Remoção de um cliente
         * Compreendendo o Requisito Funcional 02
         */
        public void removeClient(Schedule schedule) {
                Client tmp = schedule.find(getOption("\nInsira o nome do cliente: "));
                if (tmp != null) {
                        if (confirmOption()) {
                                schedule.remove(tmp);
                        }
                } else {
                        System.out.println("Cliente não encontrado");
                }
        }
        /*
         * Edição de um cliente
         */
        public void editClient(Schedule schedule) {
                Client tmp = schedule.find(getOption("\nInsira o nome do cliente: "));
                if (tmp != null) {
                        editClientInfos(tmp);
                } else {
                        System.out.println("Cliente não encontrado");
                }
        }
        /*
         * Edita as informações do cliente temporário
         */
        private void editClientInfos(Client client) {
                boolean opt = true;
                while (opt) {
                        switch (new Menu().editClient(client)) {
                                case 1:
                                        client.setName(getOption("Insira o nome: "));
                                        break;
                                case 2:
                                        client.getPhone().setNumber(insertPhone());
                                        break;
                                case 3:
                                        client.setCpf(getOption("Insira o cpf: "));
                                        break;
                                case 4:
                                        insertAddress(client);
                                        break;
                                case 5:
                                        break;
                                case 6:
                                        if (confirmOption()) {
                                                opt = false;
                                                break;
                                        }
                                default:
                                        System.out.println("\nInsira uma opção válida");
                        }
                }
        }
        // Retorna o número de telefone digitado
        private String insertPhone() {
                String opt = getOption("Insira o telefone: ");
                if (isNumber(opt)) {
                        return opt;
                } else {
                        System.out.println("Insira um valor numérico.");
                        return insertPhone();
                }
        }
        // Insere as informações de endereço do cliente
        private void insertAddress(Client client) {
                Address temp = new Address();
                boolean opt = true;
                while (opt) {
                        switch (new Menu().editClientAddress(temp)) {
                                case 1: temp.setStreet(getOption("Insira a rua: ")); break;
                                case 2: temp.setNumber(getOption("Insira o número: ")); break;
                                case 3: temp.setComplement(getOption("Insira o complemento: ")); break;
                                case 4: temp.setNeighborhood(getOption("Insira o Bairro: ")); break;
                                case 5: temp.setCity(getOption("Insira a cidade: ")); break;
                                case 6:
                                        String state = getOption("Insira a sigla do estado: ");
                                        if (state.length() < 2) {
                                                temp.setState(state);
                                                break;
                                        }
                                case 7: break;
                                case 8:
                                        if (confirmOption()) {
                                                client.setAddress(temp);
                                                opt = false;
                                                break;
                                        }

                        }
                }
        }
        /*
         * Listagem de todos os clientes armazenados
         */
        public void listClients(Schedule schedule) {
                System.out.println("\nLISTANDO TODOS OS CLIENTES\n");
                for (Map.Entry<Character, List<Client>> clients : schedule.getClientMap().entrySet()) {
                        for (Client client : clients.getValue()) {
                                client.print();
                        }
                }
        }
        /*
         * Busca de clientes
         * Compreendendo os requisitos:
         *      Funcionais 10
         *      Complementares 08
         *      De Negócio 08
         */
        public void search(Schedule schedule) {
                String name = getOption("Insira o nome do Cliente [Enter para sair] : ");
                if (!name.equals("")) {
                        Client tmp = schedule.find(name);
                        if (tmp != null) {
                                tmp.print();
                        } else {
                                System.out.println("Cliente não encontrado");
                        }
                }
        }
        /*
         * Adiciona um veículo
         * Compreendendo o requisito funcional 04
         *      e Complementares 05
         *      e de Negócio 03
         */
        public void insertVehicle(Schedule schedule) {
                Client tmp = schedule.find(getOption("Insira o nome do cliente: "));
                if (!isNull(tmp)) {
                        Vehicle tempVehicle = new Vehicle();
                        editVehicleInfos(tempVehicle);
                        tmp.setVehicle(tempVehicle);
                } else {
                        System.out.println("Cliente não encontrado");
                }
        }
        /*
         * Remove o veículo
         * Compreendendo o requisito funcional 03
         */
        public void removeVehicle(Schedule schedule) {
                Client tmp = schedule.find(getOption("Insira o nome do cliente: "));
                if (!isNull(tmp)) {
                        schedule.remove(tmp.getVehicle());
                } else {
                        System.out.println("Cliente não encontrado");
                }
        }
        /*
         * Edita os dados do veículo
         */
        public void editVehicle(Schedule schedule) {
                Client tmp = schedule.find(getOption("Insira o nome do cliente: "));
                if (!isNull(tmp)) {
                        editVehicleInfos(tmp.getVehicle());
                } else {
                        System.out.println("Cliente não encontrado");
                }
        }
        /*
         * Edita os dados do veículo temporário
         */
        private void editVehicleInfos(Vehicle vehicle) {
                Vehicle tmp = new Vehicle();
                boolean exit = false;
                while (!exit) {
                        switch (new Menu().editVehicle(tmp)) {
                                case 1: insertModel(tmp); break;
                                case 2: insertBrand(tmp); break;
                                case 3: insertLicensePlate(tmp); break;
                                case 4: insertYearOfManufature(tmp); break;
                                case 5: insertPurchasePrice(tmp); break;
                                case 6: exit = true; break;
                                case 7:
                                        if (confirmOption()) {
                                                tmp.setClient(vehicle.getClient());
                                                vehicle = tmp;
                                                exit = true;
                                                break;
                                        }
                        }
                }
        }
        void insertModel(Vehicle vehicle) {
                String buff = getOption("Insira o modelo/versão: ");
                vehicle.setModelVersion(buff);
        }
        void insertBrand(Vehicle vehicle) {
                String buff = getOption("Insira a marca: ");
                vehicle.setBrand(buff);
        }
        void insertLicensePlate(Vehicle vehicle) {
                String buff = getOption("Insira a placa: ");
                vehicle.setLicensePlate(buff);
        }
        void insertYearOfManufature(Vehicle vehicle) {
                int buff = intOption("Insira o ano de fabricação");
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, buff);
                vehicle.setYearOfManufacture(cal);
        }
        void insertPurchasePrice(Vehicle vehicle) {
                double buff = doubleOption("Insira o preço de venda");
                vehicle.setPurchasePrice(buff);
        }
        /* ************************************* *
         * Operações com arquivo                 *
         * ************************************* *
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
        /* ******************************************** *
         * Salva os dados no arquivo                    *
         * Compreendendo as Regras de negócio 01 e 07   *
         * ******************************************** */
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
        /* ******************************
         * Recupera os dados do arquivo *
         * Compreendendo os requisitos: *
         *      Complementares 03 04    *
         * **************************** */
        public void recover(Schedule schedule, Revisions revisions) {
                File clientsFile = new File("usr//clients//clients.json");
                JSONParser jsonParser = new JSONParser();
                try {
                        recover(schedule, clientsFile, jsonParser);
                } catch (Exception exp) {
                        exp.printStackTrace();
                }

        }
        // Recuperação dos dados dos clientes
        private void recover(Schedule schedule, File clientsFile, JSONParser jsonParser) throws IOException, ParseException {
                JSONArray jsonArray;
                // Verifica se o arquivo está vazio
                if (fileIsNotEmpty(clientsFile)) {
                        Object obj = parseFromJSON(clientsFile, jsonParser);
                        if (obj instanceof JSONArray) {
                                jsonArray = (JSONArray) obj;
                                for (Object o : jsonArray) {
                                        insertIntoSchedule(schedule, o);
                                }
                        }
                }
        }
        boolean fileIsNotEmpty(File file) {
                return file.length() > 0;
        }
        Object parseFromJSON(File file, JSONParser jsonParser) throws IOException, ParseException {
                FileReader arquivoJSON = new FileReader(file);
                return  jsonParser.parse(arquivoJSON);
        }
        void insertIntoSchedule(Schedule schedule, Object object) {
                JSONObject jsonObject = (JSONObject) object;
                getJSON(jsonObject, schedule);
        }
        private void getJSON(JSONObject jsonObject, Schedule schedule) {
                Client tmp = new Client();
                tmp.setName(jsonObject.get("name").toString());
                tmp.setCpf(jsonObject.get("cpf").toString());
                Phone phone = new Phone();
                phone.setNumber(jsonObject.get("phone").toString());
                tmp.setPhone(phone);
                getAddressFromJSON(jsonObject, tmp);
                getVehicleFromJSON(jsonObject, tmp);

                schedule.add(tmp);
        }
        private void getAddressFromJSON(JSONObject jsonObject, Client client) {
                JSONArray jsonArray = (JSONArray) jsonObject.get("address");
                for (Object o : jsonArray) {
                        JSONObject object = (JSONObject) o;
                        client.getAddress().setStreet(object.get("street").toString());
                        client.getAddress().setNumber(object.get("number").toString());
                        client.getAddress().setComplement(object.get("complement").toString());
                        client.getAddress().setNeighborhood(object.get("neighborhood").toString());
                        client.getAddress().setCity(object.get("city").toString());
                        client.getAddress().setState(object.get("state").toString());
                }
        }
        private void getVehicleFromJSON(JSONObject jsonObject, Client client) {
                JSONArray jsonArray = (JSONArray) jsonObject.get("vehicle");
                for (Object o : jsonArray) {
                        JSONObject object = (JSONObject) o;
                        client.getVehicle().setLicensePlate(object.get("licensePlate").toString());
                        client.getVehicle().setModelVersion(object.get("modelVersion").toString());
                        client.getVehicle().setBrand(object.get("brand").toString());
                        Calendar cal = Calendar.getInstance();
                        cal.set(Calendar.YEAR, Integer.parseInt(object.get("yearOfManufacture").toString()));
                        client.getVehicle().setYearOfManufacture(cal);
                        client.getVehicle().setPurchasePrice(Double.parseDouble(object.get("purchasePrice").toString()));
                }
        }
}
