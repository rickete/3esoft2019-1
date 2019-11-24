package AEP4_2Programacao;

import javax.persistence.Entity;

@Entity
public class PokemonsDeFogo<TIPO extends Elemento> extends Pokemon {
	private String nome;
	public PokemonsDeFogo(String nome) {
		this.nome=nome;
	}
	
	public String getNome() {
		return nome;
	}
}
