package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.characterBuilder.abs.DescriptionAbs;

@Entity
@Table(name = "USER_DESCRIPTION")
@AttributeOverrides({
    @AttributeOverride(name = "descId.id", column =
            @Column(name = "USER_ID"))})
public class UserDescription extends DescriptionAbs{
	public UserDescription () {}
	public UserDescription (long id, String description, int position){
		super(id, description, position);
	}
	public DescriptionAbs create(long id, String description, int postion) {
		return new UserDescription(id, description, postion);
	}
}
