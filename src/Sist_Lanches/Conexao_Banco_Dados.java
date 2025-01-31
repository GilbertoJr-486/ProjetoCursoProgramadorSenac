package Sist_Lanches;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Conexao_Banco_Dados {
	
	static String URL = "jdbc:mysql://localhost:3306/senac";
	static String USER = "root";
	static String PASSWORD = "123456";
	
	public static Connection Conexao_Banco_Dados() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
	}
		catch(SQLException e) {
		  System.err.println("ERRO, DESSÁU/FUDEU! :c" + e.getMessage());
		  	e.printStackTrace();
		}
		return null;
	}	
	
	// Método para inserir dados na tabela "produtos"
	public static void InserirDados() throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome do produto:");
		String nome = scanner.nextLine();
		
		System.out.println("Digite o preço do produto:");
		BigDecimal preco = new BigDecimal(scanner.nextLine());
		
		System.out.println("Digite a quantidade do produto:");
		int quantidade = Integer.parseInt(scanner.nextLine());
		
		String sql = "INSERT INTO produtos(nome, preco, quantidade) values (?,?,?);";
		
		try(Connection realizar_conexao = Conexao_Banco_Dados();
		PreparedStatement cursor = realizar_conexao.prepareStatement(sql)){
		
		cursor.setString(1, nome);
		cursor.setBigDecimal(2, preco);
		cursor.setInt(3, quantidade);
		cursor.executeUpdate();
		System.out.println("Inserido com sucesso ");
		}
	}
	
	// Método para consultar dados na tabela "produtos"
	public static void consultarDados() throws SQLException {
		String  sql = "SELECT * FROM produtos";
		
		try (Connection realizar_conexao = Conexao_Banco_Dados();
				Statement cursor = realizar_conexao.createStatement();
				ResultSet resultado_consulta = cursor.executeQuery(sql) ){
				
			while (resultado_consulta.next()) {
				int id = resultado_consulta.getInt("id");
				String nome = resultado_consulta.getString("nome");
				double preco = resultado_consulta.getDouble("preco");
				int quantidade = resultado_consulta.getInt("quantidade");
				
				System.out.printf("ID: %d, Nome: %s, Preco: %.2f, Quantidade: %d%n",
						id, nome, preco, quantidade);
			}
			
			System.out.println(resultado_consulta);
				
		}
	}
	
	// Método para atualizar dados na tabela "produtos"
	public static void AtualizarDados() throws SQLException {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Digite o nome do produto que deseja atualizar:");
		String nome = scanner.nextLine();

		System.out.println("Digite o novo preço do produto:");
		BigDecimal preco = new BigDecimal(scanner.nextLine());

		String sql = "UPDATE produtos SET preco = ? WHERE nome = ?";

		try (Connection conexao = Conexao_Banco_Dados();
				PreparedStatement cursor = conexao.prepareStatement(sql)) {

			cursor.setBigDecimal(1, preco);
			cursor.setString(2, nome);
			int linhasAfetadas = cursor.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Produto/Produtos atualizado com sucesso!");
			} else {
				System.out.println("Produto não encontrado.");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	// Método para deletar dados na tabela "produtos"
	public static void DeletarDados() throws SQLException {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Digite o nome do produto que deseja excluir:");
		String nome = scanner.nextLine();
		
		String  sql = "DELETE FROM produtos WHERE nome = ?";
		
		try (Connection conexao = Conexao_Banco_Dados();
				PreparedStatement cursor = conexao.prepareStatement(sql)){
			
			cursor.setString(1, nome);
			cursor.executeUpdate();
			System.out.println("Excluido com Sucesso!");
		}
	}
	
	public static void main(String[] args) throws SQLException {
		
		while(true) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nEscolha uma opção: \n1. Inserir Dados \n2. Consultar Dados");
		System.out.println("3. Atualizar Dados \n4. Deletar Dados \n5. Sair");
		int opcao = Integer.parseInt(scanner.nextLine());
		
		
		switch(opcao) {
			case 1:
				InserirDados();
				break;
			case 2:
				consultarDados();
				break;
			case 3:
				AtualizarDados();
				break;
			case 4:
				DeletarDados();
				break;
			case 5:
				return;	
		    }
		}
		
		
		
		
		/*Connection conexao = Conexao_Banco_Dados();
		
		
		if (conexao != null) {
			System.out.println("Conexão bem-sucedida!!! =D");
		} 
		else {
			System.out.println("Falha na conexão.");*/
		
		
		
		}
		
	}

//TODO Auto-generated method stub
