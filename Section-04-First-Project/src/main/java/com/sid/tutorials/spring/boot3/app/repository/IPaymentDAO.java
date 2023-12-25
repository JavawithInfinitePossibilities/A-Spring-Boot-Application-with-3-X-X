/**
 * 
 */
package com.sid.tutorials.spring.boot3.app.repository;

/**
 * @author kunmu
 *
 */
public interface IPaymentDAO {
	void save();

	void update();

	void delete();

	String get();
}
