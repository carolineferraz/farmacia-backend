package com.farmacia.farmacia.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.farmacia.farmacia.model.Produto;
import com.farmacia.farmacia.repository.ProdutoRepository;

@RestController
@RequestMapping("/tb_produtos")
@CrossOrigin("*")
public class ProdutoController {
	
	//Anotação para poder "instanciar uma interface" através da propriedade de injeção de dependência do Spring:
	@Autowired
	//Para injetar a interface ProdutoRepository aqui no controller:
	private ProdutoRepository repository;
	
	//Criando os métodos do controller:
	
	@GetMapping
	//Método de nome GetAll() do tipo ResponseEntity que retornará uma lista referente à classe Produto:
	//Como retorno o método trará um objeto do tipo ResponseEntity passando ok como resposta http e dentro da body se farão as requisições das postagens
	public ResponseEntity<List<Produto>> GetAll() {
		return ResponseEntity.ok(repository.findAll());
	}
	
	//.buil indica que a resposta deverá ser renderizada na body
	@GetMapping("/{id}") 
	public ResponseEntity<Produto> GetById(@PathVariable long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//.ok é o tipo de status http que teremos como resposta, uma resposta de sucesso de código 200
	@GetMapping("/nome/{nome}")
	public ResponseEntity<List<Produto>> GetByNome(@PathVariable String nome) {
		return ResponseEntity.ok(repository.findAllByNomeContainingIgnoreCase(nome));
	}
	
	//.CREATED é o tipo de status http que teremos como resposta, uma resposta de sucesso de código 201 
	//.body(repository.save(produto) serve para salvar o dado (que será enviado via body) na base de dados
	@PostMapping
	public ResponseEntity<Produto> post (@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(produto));
	}
	
	@PutMapping
	public ResponseEntity<Produto> put (@RequestBody Produto produto) {
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(produto));
	}
	//PS: ResponseEntity<Produto> significa que o método é do ResponseEntity e que vai retornar um recurso do tipo Produto
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		repository.deleteById(id);
	}

}
