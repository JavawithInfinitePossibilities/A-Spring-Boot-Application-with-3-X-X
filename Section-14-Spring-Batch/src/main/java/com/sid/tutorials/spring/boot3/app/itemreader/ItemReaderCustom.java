/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.itemreader;

import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**
 * @author kunmu
 *
 */
public class ItemReaderCustom implements ItemReader<String> {

	private String[] courses = { "Java", "Spring", "Spring boot", "Spring batch" };
	private int count;

	@Override
	public String read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		System.out.println("Inside the reader class");
		if (count < courses.length) {
			return courses[count++];
		} else {
			count = 0;
		}
		return null;
	}

}
