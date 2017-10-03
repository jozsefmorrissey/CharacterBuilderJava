package com.characterBuilder.comparator;

import java.util.Comparator;

import com.characterBuilder.markers.HasDateTime;

public class ComparitorDateTime implements Comparator<HasDateTime>
{

	@Override
	public int compare(HasDateTime o1, HasDateTime o2)
	{
		return o1.getDateTime().compareTo(o2.getDateTime());
	}
	
}
