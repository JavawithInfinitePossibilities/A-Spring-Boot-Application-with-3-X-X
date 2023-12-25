/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.repository.impl;

import com.sid.tutorials.spring.boot3.app.repository.IPaymentDAO;
import org.springframework.stereotype.Repository;


/**
 * @author kunmu
 *
 */
@Repository
public class PaymentDAOImpl implements IPaymentDAO {

	@Override
	public void save() {
		System.out.println("This is a save method");
	}

	@Override
	public void update() {
		System.out.println("This is a update method");
	}

	@Override
	public void delete() {
		System.out.println("This is a delete method");
	}

	@Override
	public String get() {
		System.out.println("This is a get method");
		return "This is a return value for select method";
	}

}
