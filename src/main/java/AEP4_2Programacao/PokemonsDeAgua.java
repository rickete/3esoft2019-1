package AEP4_2Programacao;

import javax.persistence.Entity;

@Entity
public class PokemonsDeAgua<TIPO extends Elemento> extends Pokemon{
	private String nome;
	public PokemonsDeAgua(String nome) {
		this.nome=nome;
	}
	
	public String getNome() {
		return nome;
	}

}
