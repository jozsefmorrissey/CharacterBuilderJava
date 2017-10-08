package com.characterBuilder.markers;

import java.util.ArrayList;

public interface DescriptionRepoMarker<T>
{
	public void deleteByDescIdId(long id);

	//public void save(ArrayList<T> descs);
	public T save(T newT);
	
}
