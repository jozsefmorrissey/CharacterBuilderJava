package com.characterBuilder.entities.pureDBEntities;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.characterBuilder.markers.DescriptionAbs;

@Entity
@Table(name = "SKILL_DESCRIPTION")
@AttributeOverrides({
    @AttributeOverride(name = "descId.id", column =
            @Column(name = "SKILL_MAP_ID"))})
public class SkillDescription extends DescriptionAbs{
	public SkillDescription () {}
	public SkillDescription (long id, String description, int position){
		super(id, description, position);
	}
}
