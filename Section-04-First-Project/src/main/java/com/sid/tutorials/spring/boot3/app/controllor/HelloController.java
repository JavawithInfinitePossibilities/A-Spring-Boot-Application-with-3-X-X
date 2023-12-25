/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.controllor;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author kunmu<br/>
 *         URL: http://localhost:8080/hello
 *
 */
@RestController
public class HelloController {

	@RequestMapping("/hello")
	public String helloSpringBoot() {
		return "This is first spring boot application. Hello spring boot.";
	}
}
