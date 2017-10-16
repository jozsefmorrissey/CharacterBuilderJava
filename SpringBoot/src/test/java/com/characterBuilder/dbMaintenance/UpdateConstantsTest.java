package com.characterBuilder.dbMaintenance;

import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Appender;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.characterBuilder.appender.LastN;
import com.characterBuilder.entities.abs.ConstantAbs;
import com.characterBuilder.srvc.impl.ConstantSrvcAbs;

public abstract class UpdateConstantsTest<T extends ConstantAbs, E extends Enum<E>>
{
	Logger log = Logger.getRootLogger();
	@Autowired
	ConstantSrvcAbs<T> constSrvc;
	
	@Autowired
	EnumUpdateAbs<?, T> evFlgUpdate;
	
	protected abstract Class<E> getEnum();
	
	@Test
	public void testUpdate() {
		//DbConstantsUtil.print();
		System.out.println(constSrvc.getAll());
		System.out.println(Arrays.toString(getEnum().getEnumConstants()));
		assert(!allAccountedFor());
		//log.addAppender((Appender) new LastN());
		evFlgUpdate.update();
		assert(allAccountedFor());
		removeAddedConstants();
		assert(!allAccountedFor());
	}
	
	private boolean allAccountedFor() {
		List<T> dbEventFlags = constSrvc.getAll();
		E[] enumFlags = getEnum().getEnumConstants();
		for(E enumFlg : enumFlags) {
			boolean found = false;
			for(T dbEvFlg : dbEventFlags) {
				if(enumFlg.toString().equals(dbEvFlg.getValue())) {
					found = true;
					break;
				}
			}
			if(!found){
				return false;
			}
		}
		return true;
	}
	
	private void removeAddedConstants() {
		List<T> dbEventFlags = constSrvc.getAll();

		for(T dbEvFlg : dbEventFlags) {
			if(dbEvFlg.getId() > 3) {
				constSrvc.remove(dbEvFlg);
			}
		}
	}
}
