/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.mylistener;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author kunmu
 *
 */
@Component
public class MyMessageListener {

	@JmsListener(destination = "${message.sender.queue}")
	public void myMessageListenerParty(String message) {
		System.out.println("My message :" + message);
	}
}
