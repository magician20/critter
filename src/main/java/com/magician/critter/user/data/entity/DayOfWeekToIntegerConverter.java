// package com.magician.critter.user.data.entity;

// import java.time.DayOfWeek;

// import javax.persistence.AttributeConverter;
// import javax.persistence.Converter;

// //Not used 
// /**
//  * Converts {@link DayOfWeek} to {@link String} and back again.
//  * Throws {@link DateTimeException} if the latter is not possible.
//  */
// @Converter(autoApply = true)
// public class DayOfWeekToIntegerConverter implements AttributeConverter<DayOfWeek, String> {
// 	@Override
// 	public String convertToDatabaseColumn(DayOfWeek attribute) {
// 		return attribute == null ? null : attribute.toString();
// 	}

// 	@Override
// 	public DayOfWeek convertToEntityAttribute(String dbData) {
// 		return dbData == null ? null : DayOfWeek.valueOf(dbData);
// 	}

// }