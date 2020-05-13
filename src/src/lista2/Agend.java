package lista2;

import java.util.Scanner;

public class Agend {
	private Data data;
	private String nome;
	public Agend(String nome, Data data) {
		this.nome = nome;
		this.data = data;
	}
	public Data getData() {
		return data;
	}
	public String getNome() {
		return nome;
	}
	public void alteraData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o novo dia: ");
		String dia = sc.nextLine();
		System.out.println("Digite o novo mes: ");
		String mes = sc.nextLine();
		System.out.println("Digite o novo ano: ");
		String ano = sc.nextLine();
		
		this.data = new Data(dia, mes, ano);
	}
}
