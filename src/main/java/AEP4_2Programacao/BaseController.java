package AEP4_2Programacao;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.persistence.MappedSuperclass;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public abstract class BaseController <ENTIDADE  extends BaseEntity, REPOSITORIO extends BaseRepository<ENTIDADE>> {

	@Autowired
	BaseRepository<ENTIDADE> repositorio;
	
	
	@GetMapping
	public List<ENTIDADE> getAll() {
		if(repositorio == null) { //Verifica se o repositório esta vazio
			retornarStatusHTTP(205, "Repositório esta vazio"); 
		}
		retornarStatusHTTP(200, "Ok");
		retornarStatusHTTP(302, "Resultados Encontrados");
		return repositorio.findAll();
		
	}
	
	
	@GetMapping("/{id}")
	public ENTIDADE getById(@PathVariable String id) {
		if(!repositorio.findById(id).isPresent()) {
			retornarStatusHTTP(404,"Não Encontrado");
		}
		retornarStatusHTTP(200, "Ok");
		return repositorio.findById(id).get();
	}
	
	@DeleteMapping("/{id}")
	public void deleteByID(@PathVariable String id) {
		if(!repositorio.findById(id).isPresent()) {
			retornarStatusHTTP(404,"Não Encontrado");
		}
		retornarStatusHTTP(200, "Ok");
		repositorio.deleteById(id);
	}
	
	@PostMapping
	public String postENTIDADE(@RequestBody ENTIDADE entidade) {
		repositorio.save(entidade);
		return entidade.getId();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> putENTIDADE(@PathVariable String id, @RequestBody ENTIDADE e) {
		if (!Objects.equals(id, e.getId())) {     //Se o ID do Body e do path não estiverem iguais ele nem procura no repositório
			return retornarStatusHTTP(400, "Requisiçao inválida! Verifique se os IDs da entidade e parâmetro estão iguais");
		}
		if (!repositorio.findById(id).isPresent()) { //Se o ID passado não existir no repositório retorna Não encontrado
			return retornarStatusHTTP(404);
		}
		repositorio.save(e); // Se o ID passado existir no repositório ele salva
		return retornarStatusHTTP(200, "Ok"); 
	}
	
	private ResponseEntity<String> retornarStatusHTTP(int status, String mensagem) {
		return ResponseEntity.status(status).body(mensagem);
	}
	
	private ResponseEntity<String> retornarStatusHTTP(int status){
		return ResponseEntity.status(status).build();
	}
	
	
}
