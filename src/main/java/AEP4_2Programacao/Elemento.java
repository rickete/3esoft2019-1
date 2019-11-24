package AEP4_2Programacao;

import javax.persistence.Entity;

@Entity
public abstract class Elemento extends BaseEntity {
	private String elemento;
	
	public Elemento(String elemento) {
		this.elemento = elemento;
	}
	
	public String getElemento() {
		return elemento;
	}
	
	
	
}
