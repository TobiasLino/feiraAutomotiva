package lista2;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
	public static void main(String[] args) throws IOException {
	     
		List<String> temp2 = new ArrayList<String>();
		ListaCliente banco = new ListaCliente();
		try {
		Files.lines(new File("clientes.txt").toPath()).forEach(temp2::add);
		for(String s:temp2) {
			banco.addCliente(s.split(";"));
		}
		}catch(Exception e) {}
		temp2.clear();
		try {
		Files.lines(new File("carros.txt").toPath()).forEach(temp2::add);
		for(String s:temp2) {
			String[] st = s.split(";");
			banco.procuraCpf(st[0]).addCarro(st[1],st[2],st[3],st[4]);
		}}catch(Exception e) {}
		temp2.clear();
		try {
		Files.lines(new File("agendamentos.txt").toPath()).forEach(temp2::add);
		for(String s:temp2) {
			String[] st = s.split(";");
			banco.procuraCpf(st[0]).procuraPlaca(st[1]).addAgend(st[2], st[3],st[4],st[5]);
		}}catch(Exception e) {}
		
		Scanner sc = new Scanner(System.in);
		boolean k = true;
		
		while(k) {
			System.out.println("Companhia Carros Ltda. Inc. tm");
			System.out.println("Menu:");
			System.out.println("1-Cadastrar Cliente");
			System.out.println("2-Cadastrar Carro");
			System.out.println("3-Agendar Revisão");
			System.out.println("4-Troca de Data");
			System.out.println("5-Cancelar Revisão");
			System.out.println("6-Imprimir Relatório");
			System.out.println("7-Salvar");
			System.out.println("8-Sair");
			switch(sc.nextLine()) {
			case "1":
				banco.cadastraCliente();
				break;
			case "2":
				banco.cadastraCarro();
				break;
			case "3":
				banco.cadastraRev();
				break;
			case "4":
				banco.trocaData();
				break;
			case "5":
				banco.cancelaRev();
				break;
			case "6":
				banco.imprime();
				break;
			case "8":
				sc.close();
				k=false;
				break;
			case "7":
				banco.update();
			case "":
				break;
			default:
				System.out.println("Entrada não reconhecida!");
				break;
			}
		}
	}
}
