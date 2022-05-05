package com.farmacia.farmacia.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.farmacia.farmacia.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
	//Para fazer uma consulta pelo nome do produto:
	//(método que retorna uma lista da classe Produto)
	//(no nome do método escreve-se exatamente o que a query deverá fazer)
	//(no final coloca-se como argumento qual o tipo de dado e o nome do parâmetro)
	public List<Produto> findAllByNomeContainingIgnoreCase (String nome);
	
	

}
