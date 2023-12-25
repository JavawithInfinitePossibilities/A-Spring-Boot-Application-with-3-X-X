/**
 *
 */
package com.sid.tutorials.spring.boot3.app.services.impl;

import com.sid.tutorials.spring.boot3.app.repository.IPaymentDAO;
import com.sid.tutorials.spring.boot3.app.services.IPaymentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author kunmu
 *
 */
@Service
public class PaymentServices implements IPaymentServices {

    @Autowired
    private IPaymentDAO paymentDAO;

    public IPaymentDAO getPaymentDAO() {
        return paymentDAO;
    }

    public void setPaymentDAO(IPaymentDAO paymentDAO) {
        this.paymentDAO = paymentDAO;
    }

    @Override
    public void create() {
        paymentDAO.save();
    }

    @Override
    public void update() {
        paymentDAO.update();
    }

    @Override
    public void delete() {
        paymentDAO.delete();
    }

    @Override
    public String get() {
        return paymentDAO.get();
    }

}
