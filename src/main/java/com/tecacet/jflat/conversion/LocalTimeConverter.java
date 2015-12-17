package com.tecacet.jflat.conversion;

import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocalTimeConverter implements DataConverter<String, LocalTime> {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private final DateTimeFormatter[] formatters;

	public LocalTimeConverter(String dateFormatString) {
		super();
		this.formatters = new DateTimeFormatter[1];
		this.formatters[0] = DateTimeFormat.forPattern(dateFormatString);
	}

	public LocalTimeConverter(String[] dateFormatStrings) {
		super();
		this.formatters = new DateTimeFormatter[dateFormatStrings.length];
		for (int i = 0; i < dateFormatStrings.length; i++) {
			formatters[i] = DateTimeFormat.forPattern(dateFormatStrings[i]);
		}
	}

	@Override
	public LocalTime convert(String from) {
		if (from == null) {
			return null;
		}
		for (DateTimeFormatter formatter : formatters) {
			try {
				return LocalTime.parse(from, formatter);
			} catch (Exception e) {
				log.trace(e.getMessage());
			}
		}
		String message = String.format("Error converting %s to LocalDate", from);
		throw new IllegalArgumentException(message);
	}

}
