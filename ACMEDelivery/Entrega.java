public class Entrega {

	private int codigo;

	private double valor;

	private String descricao;

	private Cliente cliente;

	private String email;

	public Entrega(int c, double v, String d,String e, Cliente cli) {
		codigo = c;
		valor = v;
		descricao = d;
		email = e;
		cliente = cli;
	}

	public int getCodigo(){
		return codigo;
	}
	public double getValor(){
		return valor;
	}
	public String getDescricao(){
		return descricao;
	}
	public Cliente getCliente(){
		return cliente;
	}
	public String getEmail(){
		return email;
	}
}

