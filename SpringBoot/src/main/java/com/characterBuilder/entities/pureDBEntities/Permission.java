package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table
public class Permission {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PERMISSION_ID_SEQ")
	@SequenceGenerator(name = "PERMISSION_ID_SEQ", sequenceName = "PERMISSION_ID_SEQ")
	long id;
	
	@Column
	String PermissionLevel;

	public Permission() {
		super();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((PermissionLevel == null) ? 0 : PermissionLevel.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Permission other = (Permission) obj;
		if (PermissionLevel == null) {
			if (other.PermissionLevel != null)
				return false;
		} else if (!PermissionLevel.equals(other.PermissionLevel))
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Permission [id=" + id + ", PermissionLevel=" + PermissionLevel + "]";
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPermissionLevel() {
		return PermissionLevel;
	}

	public void setPermissionLevel(String permissionLevel) {
		PermissionLevel = permissionLevel;
	}
}
