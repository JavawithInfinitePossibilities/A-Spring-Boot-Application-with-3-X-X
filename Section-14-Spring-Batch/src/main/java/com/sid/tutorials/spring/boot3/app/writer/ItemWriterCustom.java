/**
 *
 */
package com.sid.tutorials.spring.boot3.app.writer;

import java.util.List;

import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;

/**
 * @author kunmu
 *
 */
public class ItemWriterCustom implements ItemWriter<String> {


    @Override
    public void write(Chunk<? extends String> items) throws Exception {
        System.out.println("Inside writer");
        items.getItems().stream().forEach(item -> System.out.println("Write : " + item));

    }
}
