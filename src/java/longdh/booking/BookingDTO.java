/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.booking;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author Dong Long
 */
public class BookingDTO implements Serializable {

    private int id, userId;
    private Timestamp importedDate;
    private float total;
    private String payment;
    

    public BookingDTO() {
    }

    public BookingDTO(int userId, Timestamp importedDate, float total) {
        this.userId = userId;
        this.importedDate = importedDate;
        this.total = total;
    }

    public BookingDTO(int id, int userId, Timestamp importedDate, float total,String payment) {
        this.id = id;
        this.userId = userId;
        this.importedDate = importedDate;
        this.total = total;
        this.payment = payment;
    }

    public BookingDTO(int userId, Timestamp importedDate) {
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

    public Timestamp getImportedDate() {
        return importedDate;
    }

    public void setImportedDate(Timestamp importedDate) {
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
    
}
