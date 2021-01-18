package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.com.casadocodigo.livraria.Autor;
import br.com.casadocodigo.livraria.produtos.LivroFisico;
import br.com.casadocodigo.livraria.produtos.Produto;
import db.ConnectionFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProdutoDAO {

	public ObservableList<Produto> lista() {
		ObservableList<Produto> produtos = FXCollections.observableArrayList();
		Connection connection = new ConnectionFactory().getConnection();
		PreparedStatement pstm;
		try {
			pstm = connection.prepareStatement("SELECT * FROM produtos");
			
			ResultSet resultSet = pstm.executeQuery();
			
			while (resultSet.next()) {
				LivroFisico livro = new LivroFisico(new Autor());
				livro.setNome(resultSet.getString("nome"));
				livro.setDescricao(resultSet.getString("descricao"));
				livro.setValor(resultSet.getDouble("valor"));
				livro.setIsbn(resultSet.getString("isbn"));
				produtos.add(livro);
			}
			
			resultSet.close();		
			pstm.close();
			connection.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
		return produtos;
	}
	
	public void adiciona(Produto produto) {
		PreparedStatement pstm;
		
		try (Connection connection = new ConnectionFactory().getConnection()) { 
			pstm = connection.prepareStatement("insert into produtos (nome, "
					+ "descricao, valor, isbn) values (?, ?, ?, ?)");
			
			pstm.setString(1, produto.getNome());
			pstm.setString(2, produto.getDescricao());
			pstm.setDouble(3, produto.getValor());
			pstm.setString(4, produto.getIsbn());
			
			pstm.execute();
			pstm.close();
			
		} catch (SQLException e) {			
				throw new RuntimeException(e);
		} 
	}
	
}
