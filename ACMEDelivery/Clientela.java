import java.util.ArrayList;

public class Clientela {

	private ArrayList<Cliente> clientes;

	public Clientela(){
		clientes = new ArrayList<Cliente>();
	}

	public boolean cadastraCliente(Cliente cliente) {
		for(Cliente c: clientes){
			if(c.getEmail().equals(cliente.getEmail())){
				return false;
			}
		}
		return clientes.add(cliente);
	}

	public Cliente pesquisaCliente(String email) {
		for(Cliente c: clientes){
			if(c.getEmail().equals(email))
				return c;	
		}
		return null;
	}


	public ArrayList<Cliente> getClientes(){
		return clientes;
	}

}
