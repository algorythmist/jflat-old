package com.tecacet.jflat;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.joda.time.LocalDate;
import org.junit.Test;

import com.tecacet.jflat.om.StockPrice;

public class StockPriceReaderTest {

    @Test
    public void testSimpleRead() throws IOException, LineMergerException {
        
        ReaderRowMapper<StockPrice> rowMapper = 
        		new BeanReaderRowMapper<StockPrice>(StockPrice.class, new String[] {
                "date", "openPrice", "closePrice", "volume" }, new String[] { "Date", "Open", "Close", "Volume" });

        // create a normal file reader
        FileReader fr = new FileReader("testdata/prices.csv");
        // finally a CSV reader
        CSVReader<StockPrice> csvReader = new CSVReader<StockPrice>(rowMapper);

        // read all the prices
        List<StockPrice> prices = csvReader.readAll(fr);
        assertEquals(253, prices.size());
        StockPrice price = prices.get(0);
        assertEquals(1550.87, price.getOpenPrice(), 0.0001);
        assertEquals(1577.03, price.getClosePrice(), 2);
        assertEquals(1521220000, price.getVolume());
        LocalDate localDate = new LocalDate( price.getDate());
        assertEquals("2008-12-31",localDate.toString());

        FileWriter fw = new FileWriter("test.out");
        String[] properties = new String[] { "volume", "averagePrice", "date" };
        CSVWriter<StockPrice> csvWriter = new CSVWriter<StockPrice>(fw, StockPrice.class, properties);
        String[] header = new String[] { "Volume", "Average Price", "Date" };
        csvWriter.writeNext(header);
        csvWriter.writeAll(prices);
        csvWriter.close();

        // create a normal file reader
        fr = new FileReader("test.out");
        // finally a CSV reader
        rowMapper = new BeanReaderRowMapper<StockPrice>(StockPrice.class, properties, header);
        csvReader = new CSVReader<StockPrice>(rowMapper);

        prices = csvReader.readAll(fr);
        assertEquals(253, prices.size());
        price = prices.get(0);

        assertEquals(1521220000, price.getVolume());

        File result = new File("test.out");
        assertTrue(result.exists());
        result.delete();

    }
}