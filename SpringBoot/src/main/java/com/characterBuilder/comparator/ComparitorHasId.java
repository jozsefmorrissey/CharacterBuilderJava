package com.characterBuilder.comparator;

import java.util.Comparator;

import com.characterBuilder.markers.HasId;

public class ComparitorHasId implements Comparator<HasId> {

	@Override
	public int compare(HasId arg0, HasId arg1) {
		long id0 = arg0.getId();
		long id1 = arg1.getId();
		if(id0 == id1)
			return 0;
		else
			return arg1.getId() - arg0.getId() > 0 ? -1 : 1;
	}

}
