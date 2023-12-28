/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.processer;

import org.springframework.batch.item.ItemProcessor;

/**
 * @author kunmu
 *
 */
public class ItemProcesserCustom implements ItemProcessor<String, String> {

	@Override
	public String process(String item) throws Exception {
		System.out.println("Item we are processing : " + item);
		return "Processed " + item.toUpperCase();
	}

}
