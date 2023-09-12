// Imports
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

/**@author Daniel Lee
 * @version 12 sept. 2023
 * 
 **/

public class ACMEDelivery {
    private Scanner entrada = null;                 // Atributo para entrada de dados
    private PrintStream saidaPadrao = System.out;   // Guarda a saida padrao - tela(console)
	private Clientela clientela;
	private CadastroEntregas cadastroEntregas;
	public int quantasPessoasCadastradas = 0;
	public int quantasEntregasCadastradas = 0;
	public Entrega maisValiosa;

    // Construtor
    public ACMEDelivery() {
        try {
            BufferedReader streamEntrada = new BufferedReader(new FileReader("arquivoentrada.txt"));
            entrada = new Scanner(streamEntrada);   // Usa como entrada um arquivo
            PrintStream streamSaida = new PrintStream(new File("arquivosaida.txt"), Charset.forName("UTF-8"));
            System.setOut(streamSaida);             // Usa como saida um arquivo
        } catch (Exception e) {
            System.out.println(e);
        }
        Locale.setDefault(Locale.ENGLISH);   // Ajusta para ponto decimal
        entrada.useLocale(Locale.ENGLISH);
		clientela = new Clientela();
		cadastroEntregas = new CadastroEntregas(clientela);
	}

        // Implemente aqui o seu codigo adicional do construtor
		public void executa(){
			
		cadastrarClientes();
		cadastrarEntregas();
		numeroDequantasPessoasCadastradas();
		numeroDequantasEntregasCadastradas();
		dadosClientes();
		dadosEntrega();
		entregasDessaPessoa();
		dadosDaMaisValiosa();
		enderecoDessaEntrega();
		somatorioDessaPessoa();
		

		restauraES();

	}

	private void cadastrarClientes() {
		String email;
		String nome;
		String endereco;
		Cliente cliente;
		email = entrada.nextLine();
			while(!email.equals("-1")){
				nome = entrada.nextLine();
				endereco = entrada.nextLine();
				cliente = new Cliente(email, nome, endereco);
				
				if (clientela.cadastraCliente(cliente)==true){
					System.out.println("1;"+email+";"+nome+";"+endereco);
					++quantasPessoasCadastradas;
				}
				email = entrada.nextLine();
			}
	}

	private void cadastrarEntregas(){
		int codigo;
		double valor;
		String descricao;
		String email;
		codigo = entrada.nextInt();
		while (codigo != -1){
			valor = entrada.nextDouble();
			entrada.nextLine();
			descricao = entrada.nextLine();
			email = entrada.nextLine();
			Cliente cliente = clientela.pesquisaCliente(email);
			if(cliente!=null){
			Entrega entrega = new Entrega(codigo, valor, descricao, email, cliente);
			cadastroEntregas.cadastraEntrega(entrega);
			cliente.adicionaEntrega(entrega);
			System.out.println ("2;" + codigo + ";" + valor +";" + descricao + ";" + email);
			++quantasEntregasCadastradas;
			}
			codigo = entrada.nextInt();
		}
	}
	private void numeroDequantasPessoasCadastradas(){
		System.out.println("3;"+quantasPessoasCadastradas);
	}

	private void numeroDequantasEntregasCadastradas(){
		System.out.println("4;"+quantasEntregasCadastradas);
	} 

	private void dadosClientes(){
		String email;
		Cliente cliente; 
		entrada.nextLine();
		email = entrada.nextLine();
		cliente = clientela.pesquisaCliente(email);
		if (clientela.pesquisaCliente(email)==null){
			System.out.println("5;Cliente inexistente");
		}
		else{
			System.out.println("5;"+cliente.getEmail()+";"+cliente.getNome()+";"+cliente.getEndereco());
		}
	}
    
	private void dadosEntrega(){
		int codigo;
		Entrega entrega; 
		codigo = entrada.nextInt();
		entrega = cadastroEntregas.pesquisaEntrega(codigo);
		
		if (cadastroEntregas.pesquisaEntrega(codigo)==null){
			System.out.println("6;Entrega inexistente");
		}
		else{
			System.out.println("6;"+entrega.getCodigo()+";"+entrega.getValor()+";"+entrega.getDescricao()+";"+entrega.getCliente().getEmail()+";"+entrega.getCliente().getNome()+";"+entrega.getCliente().getEndereco());
		}
	}

	private void entregasDessaPessoa(){
		entrada.nextLine();
		String email = entrada.nextLine();
		ArrayList<Entrega> listaDessaPessoa = cadastroEntregas.pesquisaEntrega(email);
		if (listaDessaPessoa == null){
			System.out.println("7;Cliente inexistente");
		}
		else{
			for(int i = 0; i<listaDessaPessoa.size(); i++){
				System.out.println("7;"+email+";"+listaDessaPessoa.get(i).getCodigo()+";"+listaDessaPessoa.get(i).getValor()+";"+listaDessaPessoa.get(i).getDescricao());
			}
			}
		}
	
	private void dadosDaMaisValiosa(){
		int codigo = 0;
		String descricao = "a";
		double maiorValor = 0;
		if(cadastroEntregas.getEntregas()==null){
			System.out.println("8;Entrega Inexistente");
		}
		else{
			for(Entrega e: cadastroEntregas.getEntregas()){
				if(e.getValor()>maiorValor){
					maiorValor=e.getValor();
					codigo=e.getCodigo();
					descricao=e.getDescricao();

				}
			}
			System.out.println("8;"+maiorValor+";"+codigo+";"+descricao);
			
		}
    }

	private void enderecoDessaEntrega(){
		int codigo = entrada.nextInt();
		Entrega entrega = cadastroEntregas.pesquisaEntrega(codigo);
		if(entrega==null){
			System.out.println("9;Entrega Inexistente");
		}
		else{
			Cliente cliente = entrega.getCliente();
			System.out.println("9;"+codigo+";"+cadastroEntregas.pesquisaEntrega(codigo).getValor()+";"+cadastroEntregas.pesquisaEntrega(codigo).getDescricao()+";"+entrega.getCliente().getEndereco());
	}
}
	private void somatorioDessaPessoa(){
		entrada.nextLine();
		String email = entrada.nextLine();
		Cliente cliente = clientela.pesquisaCliente(email);
		double somatorio = 0.0;

		if(cliente==null){
			System.out.println("10;Cliente inexistente");
		}
		ArrayList<Entrega> entregas = cliente.pesquisaEntregas();
		if(entregas==null){
			System.out.println("10;Entrega inexistente");
		}
		else{
			for(int i = 0; i < entregas.size(); i++){
				somatorio +=entregas.get(i).getValor();
			}
			System.out.printf ("10;%s;%s;%.2f\n", email, cliente.getNome(), somatorio);
		}

		
	}

	

    // Restaura E/S padrao de tela(console)/teclado
    private void restauraES() {
        System.setOut(saidaPadrao);
        entrada = new Scanner(System.in);
    }

}

	

	


