/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.cake;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Dong Long
 */
public class CakeDTO implements Serializable{
    private int cakeId;
    private String cakeName;
    private float cakePrice;
    private int quantity;
    private int categoriID;
    private Date createDate;
    private Date expirationDate;
    private String description;
    private String imageLink;
    private int statusId;
    private Date updateDate;
    private int userId;

    public CakeDTO() {
    }

    public CakeDTO(int cakeId, int statusId, String cakeName, float cakePrice, int quantity, int categoriID, Date createDate, Date expirationDate, String description, String imageLink) {
        this.cakeId = cakeId;
        this.statusId = statusId;
        this.cakeName = cakeName;
        this.cakePrice = cakePrice;
        this.quantity = quantity;
        this.categoriID = categoriID;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.description = description;
        this.imageLink = imageLink;
    }

    public CakeDTO(int cakeId,int statusId, String cakeName, float cakePrice, int quantity, int categoriID, String description, String imageLink, Date updateDate, Date expirationDate, int userId) {
        this.cakeId = cakeId;
        this.statusId = statusId;
        this.cakeName = cakeName;
        this.cakePrice = cakePrice;
        this.quantity = quantity;
        this.categoriID = categoriID;
        this.description = description;
        this.imageLink = imageLink;
        this.updateDate = updateDate;
        this.expirationDate = expirationDate;
        this.userId = userId;
    }

    public CakeDTO(int cakeId,int statusId, String cakeName, float cakePrice, int quantity, int categoriID, Date createDate, Date expirationDate, String description, String imageLink, Date updateDate, int userId) {
        this.cakeId = cakeId;
        this.statusId = statusId;
        this.cakeName = cakeName;
        this.cakePrice = cakePrice;
        this.quantity = quantity;
        this.categoriID = categoriID;
        this.createDate = createDate;
        this.expirationDate = expirationDate;
        this.description = description;
        this.imageLink = imageLink;
        this.updateDate = updateDate;
        this.userId = userId;
    }

    public int getCakeId() {
        return cakeId;
    }

    public void setCakeId(int cakeId) {
        this.cakeId = cakeId;
    }

    public String getCakeName() {
        return cakeName;
    }

    public void setCakeName(String cakeName) {
        this.cakeName = cakeName;
    }

    public float getCakePrice() {
        return cakePrice;
    }

    public void setCakePrice(float cakePrice) {
        this.cakePrice = cakePrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCategoriID() {
        return categoriID;
    }

    public void setCategoriID(int categoriID) {
        this.categoriID = categoriID;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageLink() {
        return imageLink;
    }

    public void setImageLink(String imageLink) {
        this.imageLink = imageLink;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    
    
}
