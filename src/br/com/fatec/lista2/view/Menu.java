package br.com.fatec.lista2.view;

import br.com.fatec.lista2.controller.Controller;
import br.com.fatec.lista2.model.Client;
import br.com.fatec.lista2.model.Phone;

public class Menu {
        /*
         * Menu Principal
         * Compreendendo os requisitos Complementares 01 e 02
         */
        public int mainMenu() {
                System.out.println("\nInsira a opção correspondente\n"
                        + "\t1.  Cadastrar Cliente\n"
                        + "\t2.  Remover Cliente\n"
                        + "\t3.  Editar Cliente\n"
                        + "\t4.  Listar Clientes\n"
                        + "\t5.  Buscar Clientes\n"
                        + "\t6.  Cadastrar Veículo\n"
                        + "\t7.  Remover Veículo\n"
                        + "\t8.  Editar Veículo\n"
                        + "\t9.  Agendar Revisão\n"
                        + "\t10. Cancelar Revisão\n"
                        + "\t11. Editar Revisão\n"
                        + "\t12. Gerar relatórios\n"
                        + "\t13. Buscar Revisão\n"
                        + "\t14. Sair\n");
                return new Controller().intOption();
        }
        /*
         * Menu de Cadastro
         * Compreendendo os requisitos:
         *      Funcional 02
         *      Complementares 01 02 05 06
         *      De Negócio 01 02
         */
        public int editClient(Client client) {
                // Informações temporárias do cliente
                client.print();
                System.out.println("\nInsira a opção correspondente\n"
                        + "\t1. Inserir Nome\n"
                        + "\t2. Inserir Telefone\n"
                        + "\t3. Inserir cpf\n"
                        + "\t4. Inserir Endereço\n"
                        + "\t5. Cancelar\n"
                        + "\t6. Salvar\n");
                return new Controller().intOption();
        }
        /*
         * Menu de inserção/edição de endereço
         */
        public int editClientAddress(Client client) {
                // Informações temporárias de endereço
                client.getAddress().print();
                System.out.println("\nInsira a opção correspoondente\n"
                        + "\t1. Inserir rua\n"
                        + "\t2. Inserir Número\n"
                        + "\t3. Inserir Complemento\n"
                        + "\t4. Inserir Bairro\n"
                        + "\t5. Inserir Cidade\n"
                        + "\t6. Inserir Estado\n"
                        + "\t7. Cancelar\n"
                        + "\t8. Salvar\n");
                return new Controller().intOption();
        }
}
