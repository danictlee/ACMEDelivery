import java.util.ArrayList;

public class CadastroEntregas {

	private ArrayList<Entrega> entregas;
	private Clientela clientela;


	public CadastroEntregas(Clientela c){
		entregas = new ArrayList<Entrega>();
		clientela = c;
	}

	public boolean cadastraEntrega(Entrega entrega) {
		Entrega aux = pesquisaEntrega(entrega.getCodigo());
		if (aux == null){
			return entregas.add(entrega);
		}
		else{
		return false;
		}
	}
	

	public Entrega pesquisaEntrega(int codigo) {
		for(Entrega e : entregas) {
			if(e.getCodigo()==codigo){
				return e;
			}
		}
		return null;
	}

	public ArrayList<Entrega> pesquisaEntrega(String email) {
		Cliente cliente = clientela.pesquisaCliente(email);
		if(cliente == null){
			return null;
		}
		ArrayList<Entrega> entregas = cliente.pesquisaEntregas();
		return entregas;
	}

	public int somatorioEntregas(){
		int somatorio = entregas.size();
		return somatorio;
	}

	public ArrayList<Entrega> getEntregas(){
		return entregas;
	}
}



