package com.tecacet.jflat;

import static org.junit.Assert.*;

import org.junit.Test;

public class ColumnPositionMappingTest {

	@Test
	public void test() {
		ColumnMapping positionMapping = new ColumnPositionMapping(new String[] { "firstName", "lastName", "identifier" });
		assertEquals("lastName",positionMapping.getProperty(1));
		assertFalse(positionMapping.requiresHeaderRow());
	}

}
