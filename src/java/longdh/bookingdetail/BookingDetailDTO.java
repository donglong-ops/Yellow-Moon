/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.bookingdetail;

import java.io.Serializable;

/**
 *
 * @author Dong Long
 */
public class BookingDetailDTO implements Serializable {

    private int id, bookingId, cakeId, amount;
    private String cakeName;
    private String imageLink;

    public BookingDetailDTO() {
    }

    public BookingDetailDTO(int id, int bookingId, int cakeId, int amount) {
        this.id = id;
        this.bookingId = bookingId;
        this.cakeId = cakeId;
        this.amount = amount;
    }

    public BookingDetailDTO(int bookingId, int cakeId, int amount) {
        this.bookingId = bookingId;
        this.cakeId = cakeId;
        this.amount = amount;
    }

    public BookingDetailDTO(int cakeId, int amount) {
        this.cakeId = cakeId;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setFoodId(int cakeId) {
        this.cakeId = cakeId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

}
