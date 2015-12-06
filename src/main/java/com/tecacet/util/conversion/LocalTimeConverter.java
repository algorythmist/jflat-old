package com.tecacet.util.conversion;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.joda.time.LocalTime;

//TODO use joda formatter
public class LocalTimeConverter implements DataConverter<String, LocalTime> {

	private final DateFormat format;

	public LocalTimeConverter(final String format) {
		super();
		this.format = new SimpleDateFormat(format);
	}

	@Override
	public LocalTime convert(String from) {
		if (isEmpty(from)) {
			return null;
		}
		Date date;

		try {
			date = format.parse(from.trim());
		} catch (ParseException e) {
			throw new RuntimeException(e); //TODO
		}

		return new LocalTime(date);
	}

	private boolean isEmpty(String s) {
		return s == null || s.trim().equals("");
	}

}
