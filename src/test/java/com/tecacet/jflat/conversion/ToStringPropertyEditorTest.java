package com.tecacet.jflat.conversion;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.tecacet.jflat.conversion.DoubleToStringConverter;
import com.tecacet.jflat.conversion.ToStringPropertyEditor;

public class ToStringPropertyEditorTest {

	@SuppressWarnings({ "unchecked", "rawtypes" })
    @Test
	public void testGetAsText() {
		DoubleToStringConverter converter = new DoubleToStringConverter("%.3f");
		ToStringPropertyEditor editor = new ToStringPropertyEditor(converter);
		editor.setValue(100.0);
		assertEquals("100.000", editor.getAsText());
	}

}
