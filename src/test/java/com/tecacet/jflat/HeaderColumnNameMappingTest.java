package com.tecacet.jflat;

import static org.junit.Assert.*;

import org.junit.Test;

public class HeaderColumnNameMappingTest {

	@Test(expected = IllegalStateException.class)
	public void getPropertyWithNoHeader() {
		ColumnMapping columnMapping = new HeaderColumnMapping(new String[] { "low", "high" },
				new String[] { "Up", "Down" });
		columnMapping.getProperty(0);
	}

	@Test
	public void getProperty() {
		HeaderColumnMapping columnMapping = new HeaderColumnMapping(new String[] { "low", "high" },
				new String[] { "Down", "Up" });
		columnMapping.setHeaderRow(new String[] { "  Up  ", "Down" });
		assertNull(columnMapping.getProperty(3));
		assertEquals("high", columnMapping.getProperty(0));
		assertEquals("low", columnMapping.getProperty(1));
		assertFalse(columnMapping.isConvertToLowerCase());
		assertTrue(columnMapping.isTrimWhiteSpace());

	}

	@Test
	public void notTrimmingWhitespace() {
		HeaderColumnMapping columnMapping = new HeaderColumnMapping(new String[] { "low", "high" },
				new String[] { "Down", "Up" });
		columnMapping.setTrimWhiteSpace(false);
		columnMapping.setHeaderRow(new String[] { "  Up  ", "Down" });
		assertNull(columnMapping.getProperty(0));
		assertEquals("low", columnMapping.getProperty(1));
	}

	@Test
	public void testConvertToLowercase() {
		HeaderColumnMapping columnMapping = new HeaderColumnMapping(new String[] { "low", "high" },
				new String[] { "down", "up" });
		columnMapping.setConvertToLowerCase(true);
		columnMapping.setHeaderRow(new String[] { "UP  ", "   dowN" });
		assertEquals("high", columnMapping.getProperty(0));
		assertEquals("low", columnMapping.getProperty(1));
	}
}
