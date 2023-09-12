import java.util.ArrayList;

public class Cliente {

	private String email;

	private String nome;

	private String endereco;

	private ArrayList<Entrega> entregas;

	public Cliente (String e, String n, String end){
		email = e;
		nome = n;
		endereco = end;
		entregas = new ArrayList<Entrega>();	}

	public String getEmail(){
		return email;
	} 
	public String getNome(){
		return nome;
	}
	public String getEndereco(){
		return endereco;
	}

	public ArrayList<Entrega> pesquisaEntregas(){
		if(entregas.size()<=0){
			return null;
		}
		else{
			return entregas;
		}
		
	}

	public boolean adicionaEntrega(Entrega entrega) {
		Entrega aux = pesquisaEntrega(entrega.getCodigo());
		if(aux == null){
			return entregas.add(entrega);
		}
		else{
			return false;
		}
	}

	public Entrega pesquisaEntrega(int codigo) {
		for(Entrega e : entregas) {
			if(e.getCodigo()==(codigo))
				return e;
		}
		return null;
	}
}


