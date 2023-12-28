package com.sid.tutorials.spring.boot3.app;

import com.sid.tutorials.spring.boot3.app.sender.JmsMessageSender;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author kunmu On 28-12-2023
 */
@SpringBootTest(classes = Section16MessageAndSpringJMS.class)
class Section16MessageAndSpringJMSTest {
    @Autowired
    private JmsMessageSender sender;

    @Test
    void jmsSendAndReceive() {
        sender.send("Hi this is JMS message!!!");
    }
}