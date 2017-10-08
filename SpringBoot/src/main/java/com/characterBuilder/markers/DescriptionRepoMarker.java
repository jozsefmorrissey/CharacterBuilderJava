package com.characterBuilder.markers;

public interface DescriptionRepoMarker<T>
{
	public void deleteByDescIdId(long id);

	//public List<T> save(Iterable<T> descs);
	public T save(T newT);
	
}
