package lista2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Cliente {
	private String nome, telefone, cpf;
	private Endereco end;
	private List<Veiculo> vs = new ArrayList<Veiculo>();
	
	public Cliente(String nome, String telefone, String cpf, String rua, String numero, String estado) {
		this.nome = nome;
		this.telefone = telefone;
		this.cpf = cpf;
		this.end = new Endereco(rua, estado, numero);
		
		
	}

	public String getNome() {
		return nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public String getCpf() {
		return cpf;
	}

	public Endereco getEnd() {
		return end;
	}

	public List<Veiculo> getVs() {
		return vs;
	}
	
	public void adicionaCarro() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Escreva a placa: ");
		String placa = sc.nextLine();
		System.out.println("Escreva o modelo: ");
		String modelo = sc.nextLine();
		System.out.println("Escreva o ano: ");
		String ano = sc.nextLine();
		System.out.println("Escreva o preço: ");
		String preco = sc.nextLine();
		
		vs.add(new Veiculo(placa, modelo, ano, preco));
	}

	public void adicionaAg() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a Placa: ");
		String placa = sc.nextLine();
		try {
			procuraPlaca(placa).addAg();
		}catch(Exception e) {
			System.out.println("Placa não Encontrada!");
		}
	}
	public Veiculo procuraPlaca(String placa) {
		for(Veiculo v: vs) {
			if(v.getPlaca().equals(placa))
				return v;
		}
		return null;
	}

	public void modificaAg() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a Placa: ");
		String placa = sc.nextLine();
		try {
			procuraPlaca(placa).modAg();
		}catch(Exception e) {
			System.out.println("Placa não Encontrada!");
		}
	}

	public void deletaAg() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite a Placa: ");
		String placa = sc.nextLine();
		try {
			procuraPlaca(placa).delAg();
		}catch(Exception e) {
			System.out.println("Placa não Encontrada!");
		}
	}

	public void addCarro(String s1, String s2, String s3, String s4) {
		vs.add(new Veiculo(s1,s2,s3,s4));
	}
}
