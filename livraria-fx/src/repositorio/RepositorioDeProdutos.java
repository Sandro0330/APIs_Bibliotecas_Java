package repositorio;

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

public class RepositorioDeProdutos {

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

}
