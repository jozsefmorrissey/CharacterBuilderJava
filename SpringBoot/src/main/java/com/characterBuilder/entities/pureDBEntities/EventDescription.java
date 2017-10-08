package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.characterBuilder.abs.DescriptionAbs;

@Entity
@Table(name = "EVENT_DESCRIPTION")
@AttributeOverrides({
    @AttributeOverride(name = "descId.id", column =
            @Column(name = "EVENT_ID"))})
public class EventDescription extends DescriptionAbs {
	public EventDescription () {}
	public EventDescription (long id, String description, int position){
		super(id, description, position);
	}
	public DescriptionAbs create(long id, String description, int postion) {
		return new EventDescription(id, description, postion);
	}
}
