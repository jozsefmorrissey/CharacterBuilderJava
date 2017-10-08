package com.characterBuilder.abs;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.MappedSuperclass;

import com.characterBuilder.ids.DescriptionId;

@MappedSuperclass
public abstract class DescriptionAbs
{
	@EmbeddedId
	private DescriptionId descId = new DescriptionId();
	
	@Column
	String description;
	
	public DescriptionAbs() {
		super();
	}

	public DescriptionAbs(long id, String description, int position) {
		super();
		this.setId(id);
		this.description = description;
		this.setPostion(position);
	}
	
	public abstract DescriptionAbs create(long id, String description, int postion);
	
	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((descId == null) ? 0 : descId.hashCode());
		result = prime * result + ((description == null) ? 0 : description.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DescriptionAbs other = (DescriptionAbs) obj;
		if (descId == null)
		{
			if (other.descId != null)
				return false;
		} else if (!descId.equals(other.descId))
			return false;
		if (description == null)
		{
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		return true;
	}

	@Override
	public String toString()
	{
		return "DescriptionAbs [descId=" + descId + ", description=" + description + "]";
	}

	public long getId() {
		return descId.getId();
	}
	
	public void setId(long id) {
		this.descId.setId(id);
	}
	
	public long getPosition() {
		return descId.getPosition();
	}
	
	public void setPostion(int postion) {
		this.descId.setPosition(postion);
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
}
