package lista2;

public class Endereco {
	private String rua, estado, numero;
	
	public Endereco(String rua, String estado, String numero) {
		this.rua = rua;
		this.estado = estado;
		this.numero = numero;
	}

	public String getRua() {
		return rua;
	}

	public String getEstado() {
		return estado;
	}

	public String getNumero() {
		return numero;
	}
}
