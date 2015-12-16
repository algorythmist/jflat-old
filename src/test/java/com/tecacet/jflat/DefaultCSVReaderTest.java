package com.tecacet.jflat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.tecacet.jflat.conversion.ConverterRegistry;
import com.tecacet.jflat.conversion.jodd.JoddConverterRegistry;

public class DefaultCSVReaderTest {

	Reader reader;

    /**
     * Setup the test.
     */
    @Before
    public void setUp() throws Exception {
        StringBuffer sb = new StringBuffer();
        sb.append("a,b,c").append("\n"); // standard case
        sb.append("a,\"b,b,b\",c").append("\n"); // quoted elements
        sb.append(",,").append("\n"); // empty elements
        sb.append("a,\"PO Box 123,\nKippax,ACT. 2615.\nAustralia\",d.\n");
        sb.append("\"Glen \"\"The Man\"\" Smith\",Athlete,Developer\n"); // Test
        // quoted
        // quote
        // chars
        sb.append("\"\"\"\"\"\",\"test\"\n"); // """""","test" representing:
        // "", test
        sb.append("\"a\nb\",b,\"\nd\",e\n");
        reader = new StringReader(sb.toString());
    }

    /**
     * Tests iterating over a reader.
     * 
     * @throws IOException
     *             if the reader fails.
     */
    @Test
    public void testParseLine() throws IOException {
    	CSVReader<String[]> csvReader = new DefaultCSVReader();
    	List<String[]> lines = csvReader.readAll(reader);
        // test normal case
        String[] nextLine = lines.get(0);
        assertEquals("a", nextLine[0]);
        assertEquals("b", nextLine[1]);
        assertEquals("c", nextLine[2]);

        // test quoted commas
        nextLine = lines.get(1);
        assertEquals("a", nextLine[0]);
        assertEquals("b,b,b", nextLine[1]);
        assertEquals("c", nextLine[2]);

        // test empty elements
        nextLine = lines.get(2);
        assertEquals(3, nextLine.length);
        assertNull(nextLine[0]);
        assertNull(nextLine[1]);
        assertNull(nextLine[2]);
        
        // test multiline quoted
        nextLine = lines.get(3);
        assertEquals(3, nextLine.length);
        assertEquals("a", nextLine[0]);
        assertEquals("PO Box 123,\nKippax,ACT. 2615.\nAustralia", nextLine[1]);
        assertEquals("d.", nextLine[2]);

        // test quoted quote chars
        nextLine = lines.get(4);
        assertEquals("Glen \"The Man\" Smith", nextLine[0]);

        nextLine = lines.get(5);
        assertTrue(nextLine[0].equals("\"\"")); // check the tricky situation
        assertTrue(nextLine[1].equals("test")); // make sure we didn't ruin the
        // next field..

        nextLine = lines.get(6);
        assertEquals(4, nextLine.length);
        assertEquals("a\nb", nextLine[0]);
        assertEquals("b", nextLine[1]);
        assertEquals("\nd", nextLine[2]);
        assertEquals("e", nextLine[3]);
     
        assertEquals(7, lines.size());
    }

    

    /**
     * Tests constructors with optional delimiters and optional quote char.
     * 
     * @throws IOException
     *             if the reader fails.
     */
    @Test
    public void testOptionalConstructors() throws IOException {

        StringBuffer sb = new StringBuffer();
        sb.append("a\tb\tc").append("\n"); // tab separated case
        sb.append("a\t'b\tb\tb'\tc").append("\n"); // single quoted elements
        CSVReader<String[]> csvReader = new DefaultCSVReader();
        csvReader.setQuotechar('\'');
        csvReader.setDelimiter('\t');
        
        List<String[]> lines = csvReader.readAll(new StringReader(sb.toString()));
        String[] nextLine = lines.get(0);
        assertEquals(3, nextLine.length);

        nextLine = lines.get(1);
        assertEquals(3, nextLine.length);

    }

    /**
     * Tests option to skip the first few lines of a file.
     * 
     * @throws IOException
     *             if bad things happen
     */
    @Test
    public void testSkippingLines() throws IOException {

        StringBuffer sb = new StringBuffer();
        sb.append("Skip this line\t with tab").append("\n"); // should skip
        // this
        sb.append("And this line too").append("\n"); // and this
        sb.append("a\t'b\tb\tb'\tc").append("\n"); // single quoted elements
        CSVReader<String[]> reader = new DefaultCSVReader();
        reader.setQuotechar('\'');
        reader.setDelimiter('\t');
        reader.setSkipLines(2);

        List<String[]> lines = reader.readAll(new StringReader(sb.toString()));
        String[] nextLine = lines.get(0);
        assertEquals(3, nextLine.length);
        assertEquals("a", nextLine[0]);
    }

    /**
     * Tests quotes in the middle of an element.
     * 
     * @throws IOException
     *             if bad things happen
     */
    @Test
    public void testParsedLineWithInternalQuota() throws IOException {

        StringBuffer sb = new StringBuffer();

        sb.append("a,123\"4\"567,c").append("\n");// a,123"4",c

        CSVReader<String[]> csvReader = new DefaultCSVReader();
        List<String[]> lines = csvReader.readAll(new StringReader(sb.toString()));
        String[] nextLine =lines.get(0);
        assertEquals(3, nextLine.length);
        assertEquals("123\"4\"567", nextLine[1]);
    }

    @Test
    public void testLineCount() throws IOException {
        StringBuffer sb = new StringBuffer();
        sb.append("a,b,c").append("\n"); // standard case
        sb.append("a,\"b,b,b\",c").append("\n"); // quoted elements
        sb.append(",,").append("\n"); // empty elements
        CSVReader<String[]> reader = new CSVReader<String[]>(
                new ReaderRowMapper<String[]>() {
                    int i = 1;

                    public String[] getRow(String[] row, int rowNumber) {
                        assertEquals(i, rowNumber);
                        i++;
                        return row;
                    }

					@Override
					public ConverterRegistry getConverterRegistry() {
						return new JoddConverterRegistry();
					}

                });
        reader.readAll(new StringReader(sb.toString()));

    }

    @Test
    public void testReadIntoString() throws IOException {
        FileReader fr = new FileReader("testdata/prices.csv");
        DefaultCSVReader reader = new DefaultCSVReader();
        List<String[]> lines = reader.readAll(fr);
        assertEquals(254, lines.size());
    }
}
