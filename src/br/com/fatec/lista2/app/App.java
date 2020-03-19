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
package br.com.fatec.lista2.app;

import br.com.fatec.lista2.controller.Controller;
import br.com.fatec.lista2.model.Revisions;
import br.com.fatec.lista2.model.Schedule;
import br.com.fatec.lista2.view.Menu;

public class App {
        public static void main(String[] args) {
                // Nota de copyright da GPL
                System.out.println("FeiraAutomotiva  Copyright (C) 2020  Tobias da Silva Lino\n" +
                        "    This program comes with ABSOLUTELY NO WARRANTY.\n" +
                        "    This is free software, and you are welcome to redistribute it\n" +
                        "    under certain conditions.\n");
                Schedule schedule = new Schedule();
                Revisions revisions = new Revisions();
                Controller ctrl = new Controller();
                // Verifica os arquivos de dados
                ctrl.fileCheck();
                // menu principal
                while (true) {
                        switch (new Menu().mainMenu()) {
                                case 1: ctrl.insertClient(schedule); break;
                                case 2: ctrl.removeClient(schedule); break;
                                case 3: ctrl.editClient(schedule); break;
                                case 4: break;
                                case 5: break;
                                case 6: break;
                                case 7: break;
                                case 8: break;
                                case 9: break;
                                case 10: break;
                                case 11: break;
                                case 12: break;
                                case 13: break;
                                case 14: System.exit(0);
                                default:
                                        System.out.println("Insira uma opção válida");
                                        break;
                        }
                }
        }
}
