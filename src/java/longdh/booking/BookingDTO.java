/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.booking;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Dong Long
 */
public class BookingDTO implements Serializable {

    private int id, userId;
    private Date importedDate;
    private float total;
    private String payment;
    private String paymentStatus;
    

    public BookingDTO() {
    }

    public BookingDTO(int userId, Date importedDate, float total) {
        this.userId = userId;
        this.importedDate = importedDate;
        this.total = total;
    }

    public BookingDTO(int id, int userId, Date importedDate, float total,String payment, String paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.importedDate = importedDate;
        this.total = total;
        this.payment = payment;
        this.paymentStatus = paymentStatus;
    }

    public BookingDTO(int userId, Date importedDate) {
        this.userId = userId;
        this.importedDate = importedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(Date importedDate) {
        this.importedDate = importedDate;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    
}
