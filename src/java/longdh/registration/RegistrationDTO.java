/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.registration;

import java.io.Serializable;
import longdh.role.RoleDTO;
import longdh.status.StatusDTO;


/**
 *
 * @author Dong Long
 */
public class RegistrationDTO implements Serializable {

    
    private String id;
    private String email;
    private String name;
    private String password, phone, address;
    private String avatar;
    private RoleDTO role;
    private StatusDTO status;

    public RegistrationDTO() {
    }

    public RegistrationDTO(String name) {
        this.name = name;
    }

    public RegistrationDTO(String email, String name) {
        this.email = email;
        this.name = name;
    }
    

    public RegistrationDTO(String id, String email, String password, String name, String address, RoleDTO role, StatusDTO status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.address = address;
        this.role = role;
        this.status = status;
    }
    
    public RegistrationDTO(String id, String email, String password, String name, RoleDTO role, StatusDTO status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public RegistrationDTO(String email, String password, String name, String phone, String address) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
    }

    public RegistrationDTO(String id, String email, String password, String name, String phone, String address, RoleDTO role, StatusDTO status) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.phone = phone;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public StatusDTO getStatus() {
        return status;
    }

    public void setStatus(StatusDTO status) {
        this.status = status;
    }

    public RoleDTO getRole() {
        return role;
    }

    public void setRole(RoleDTO role) {
        this.role = role;
    }
    

}
