package com.characterBuilder.converter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class NullToZeroConverter implements AttributeConverter<Long, Long>
{
	@Override
	public Long convertToDatabaseColumn(Long value)
	{
		if (value == 0l)
			return null;

		return value;
	}

	@Override
	public Long convertToEntityAttribute(Long value)
	{
		if (value == null)
			return 0l;

		return value;
	}
}
