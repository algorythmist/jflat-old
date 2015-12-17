package com.tecacet.jflat.excel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.tecacet.jflat.BeanWriterRowMapper;
import com.tecacet.jflat.om.Contact;

public class PoiExcelWriterTest {

	@Test
	public void testExport() throws IOException {
		String filename = "contacts.xls";
		String[] fields = new String[] { "firstName", "lastName", "rating", "birthday" };
		BeanWriterRowMapper<Contact> mapper = new BeanWriterRowMapper<>(Contact.class, fields);
		ExcelWriter<Contact> contactWriter = new ExcelWriter<>(filename, mapper);
		Contact contact1 = createContact("Alan", "Bloom", 3, new Date());
		Contact contact2 = createContact("Alice", "Baker", 10, new Date());
		List<Contact> contacts = Arrays.asList(contact1, contact2);
		contactWriter.export(contacts, new String[] { "First", "Last", "Score", "Birthday" });
	
		File file = new File(filename);
		assertTrue(file.exists());
		file.delete();
	}

	private Contact createContact(String first, String last, int rating, Date date) {
		Contact contact = new Contact();
		contact.setFirstName(first);
		contact.setLastName(last);
		contact.setRating(rating);
		contact.setBirthday(date);
		return contact;
	}
}
