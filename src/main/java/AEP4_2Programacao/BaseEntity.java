package AEP4_2Programacao;

import java.util.UUID;

import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import net.bytebuddy.implementation.EqualsMethod;

@MappedSuperclass
public abstract class BaseEntity{
	@Id
	private String id;
	
	public BaseEntity() {
		this.id = UUID.randomUUID().toString();
	}
	
	public String getId() {
		return id;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof BaseEntity)) {
			return false;
		}
		BaseEntity other = (BaseEntity) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
}
