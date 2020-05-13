package lista2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Veiculo {
	private String placa, modelo, ano, preco;
	private List<Agend> ag = new ArrayList<Agend>();
	
	public Veiculo(String placa, String modelo, String ano, String preco) {
		this.placa = placa;
		this.modelo = modelo;
		this.ano = ano;
		this.preco = preco;
	}
	public String getPlaca() {
		return placa;
	}
	public String getModelo() {
		return modelo;
	}
	public String getAno() {
		return ano;
	}
	public String getPreco() {
		return preco;
	}
	public List<Agend> getAg() {
		return ag;
	}
	public void addAg() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escreva a descrição: ");
		String desc = sc.nextLine();
		System.out.println("Escreva o dia: ");
		String dia = sc.nextLine();
		System.out.println("Escreva o mes: ");
		String mes = sc.nextLine();
		System.out.println("Escreva o ano: ");
		String ano = sc.nextLine();
		
		ag.add(new Agend(desc, new Data(dia, mes, ano)));
	}
	public void modAg() {
		Scanner sc = new Scanner(System.in);
		int i=1;
		for(Agend a:ag){
			System.out.println(i+" - "+a.getNome()+" Data: "+a.getData().getDia()+"/"+a.getData().getMes()+"/"+a.getData().getAno());
			i++;
		}
		int choice = sc.nextInt();
		ag.get(choice-1).alteraData();
	}
	public void delAg() {
		Scanner sc = new Scanner(System.in);
		int i=1;
		for(Agend a:ag){
			System.out.println(i+" - "+a.getNome()+" Data: "+a.getData().getDia()+"/"+a.getData().getMes()+"/"+a.getData().getAno());
			i++;
		}
		int choice = sc.nextInt();
		ag.remove(choice-1);
	}
	public void addAgend(String s1, String s2, String s3, String s4) {
		ag.add(new Agend(s1,new Data(s2,s3,s4)));
	}
	
	
}
