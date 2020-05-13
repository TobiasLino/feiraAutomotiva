package lista2;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListaCliente {
	List<Cliente> banco = new ArrayList<Cliente>();
	
	public void cadastraCliente() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o nome: ");
		String nome = sc.nextLine();
		System.out.println("Digite o cpf: ");
		String cpf = sc.nextLine();
		System.out.println("Digite o telefone: ");
		String tel = sc.nextLine();
		System.out.println("Digite a rua: ");
		String rua = sc.nextLine();
		System.out.println("Digite o estado: ");
		String estado = sc.nextLine();
		System.out.println("Digite o numero: ");
		String num = sc.nextLine();
		
		banco.add(new Cliente(nome, tel, cpf, rua, num, estado));
	}
	public void cadastraCarro() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Cpf do cliente: ");
		String cpf = sc.nextLine();
		try {
		procuraCpf(cpf).adicionaCarro();
		}catch(Exception e) {
			System.out.println("CPF Não Encrontrado");
		}
	}
	public Cliente procuraCpf(String cpf) {
		for(Cliente c:banco) {
			if(c.getCpf().equals(cpf)) {
				return c;
			}
		}
		return null;
	}

	public void imprime() {
		for(Cliente c : banco) {
			System.out.println("Nome: "+ c.getNome());
			System.out.println("Cpf: "+ c.getCpf());
			System.out.println("Telefone: "+ c.getTelefone());
			System.out.println("Rua: "+ c.getEnd().getRua());
			System.out.println("Estado: "+ c.getEnd().getEstado());
			System.out.println("Numero: "+c.getEnd().getNumero());
			
			if(!c.getVs().isEmpty()) {
				System.out.println("________________");
				System.out.println("-Carros:");
				for(Veiculo v:c.getVs()) {
					System.out.println("--Placa: "+ v.getPlaca());
					System.out.println("--Ano: "+ v.getAno());
					System.out.println("--Modelo: "+ v.getModelo());
					System.out.println("--Preço: \n" +v.getPreco());
					if(!v.getAg().isEmpty()) {
						System.out.println("________________");
						for(Agend a:v.getAg()) {
							System.out.println("-Serviços");
							System.out.println("--Descrição: "+ a.getNome());
							System.out.println("--Data: "+ a.getData().getDia()+"/"+a.getData().getMes()+"/"+a.getData().getAno()+"\n");
						}
					}
				}
			}
			System.out.println("________________");
		}
	}
	public void cadastraRev() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o cpf: ");
		String cpf = sc.nextLine();
		try {
		procuraCpf(cpf).adicionaAg();
		} catch(Exception e) {
			System.out.println("CPF não Encontrado");
		}
	}
	public void trocaData() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o cpf: ");
		String cpf = sc.nextLine();
		try {
		procuraCpf(cpf).modificaAg();
		} catch(Exception e) {
			System.out.println("CPF não Encontrado");
		}
	}
	public void cancelaRev() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Digite o cpf: ");
		String cpf = sc.nextLine();
		try {
		procuraCpf(cpf).deletaAg();
		} catch(Exception e) {
			System.out.println("CPF não Encontrado");
		}
	}
	public void addCliente(String[] s) {
		banco.add(new Cliente(s[0],s[1],s[2],s[3],s[4],s[5]));
	}
	
	 
	public void update() throws IOException {
		BufferedWriter reset = new BufferedWriter(new FileWriter("clientes.txt"));
		BufferedWriter resett = new BufferedWriter(new FileWriter("carros.txt"));
		BufferedWriter resetti = new BufferedWriter(new FileWriter("agendamentos.txt"));
		reset.write("");
		resett.write("");
		resetti.write("");
		reset.close();
		resett.close();
		resetti.close();
		BufferedWriter writerC = new BufferedWriter(new FileWriter("clientes.txt", true));
		BufferedWriter writerV = new BufferedWriter(new FileWriter("carros.txt", true));
		BufferedWriter writerA = new BufferedWriter(new FileWriter("agendamentos.txt", true));
		for(Cliente c:banco) {			
			writerC.append(c.getNome()+";"+c.getTelefone()+";"+c.getCpf()+";"+c.getEnd().getRua()+";"+c.getEnd().getNumero()+";"+c.getEnd().getEstado()+"\n");
			for(Veiculo v:c.getVs()) {
				writerV.append(c.getCpf()+";"+v.getPlaca()+";"+v.getModelo()+";"+v.getAno()+";"+v.getPreco()+"\n");
				for(Agend a:v.getAg()) {
					writerA.append(c.getCpf()+";"+v.getPlaca()+";"+a.getNome()+";"+a.getData().getDia()+";"+a.getData().getMes()+";"+a.getData().getAno()+"\n");
				}
			}
		}
		writerC.close();
		writerV.close();
		writerA.close();
	}
}
