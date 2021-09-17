/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.registration;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.naming.NamingException;
import longdh.utils.MyConnection;
import longdh.role.RoleDTO;
import longdh.status.StatusDTO;

/**
 *
 * @author Dong Long
 */
public class RegistrationDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public RegistrationDAO() {
    }

    public void closeConnection() throws SQLException {
        if (rs != null) {
            rs.close();
        }
        if (preStm != null) {
            preStm.close();
        }
        if (conn != null) {
            conn.close();
        }
    }

    public RegistrationDTO checkLogin(String email, String password) throws SQLException, NamingException {
        RegistrationDTO result = null;
        try {
            String SQL = "SELECT Re.ID , Re.Email ,Re.Password, Re.Name, Re.Phone, Re.Address, R.ID  AS RoleID, R.Name AS RoleName "
                    + " FROM Registration Re JOIN Role R ON Re.RoleID  = R.ID "
                    + " WHERE Email = ? And Password = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(SQL);
            preStm.setString(1, email);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String id = String.valueOf(rs.getInt("ID"));
                String name = rs.getString("Name");
                int roleID = rs.getInt("RoleID");
                String roleName = rs.getString("RoleName");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                result = new RegistrationDTO(id, email, password, name, address, new RoleDTO(roleID, roleName), new StatusDTO(1));
                result.setPhone(phone);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int insertAccountWithGoogle(String email, String name, int roleID, int statusID, String avatar) throws SQLException, NamingException {
        int id = -1;
        try {
            String sql = "INSERT INTO Registration (Email, Name, RoleID, StatusID, Avatar) "
                    + " OUTPUT Inserted.ID "
                    + " VALUES (?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, email);
            preStm.setString(2, name);
            preStm.setInt(3, roleID);
            preStm.setInt(4, statusID);
            preStm.setString(5, avatar);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }

        } finally {
            closeConnection();
        }
        return id;
    }

    public RegistrationDTO checkLoginWithGoogle(String emailUser) throws SQLException, NamingException {
        RegistrationDTO result = null;
        try {
            String sql = "SELECT Re.ID , Re.Email ,Re.Password, Re.Name, Re.Phone, Re.Address, R.ID  AS RoleID, R.Name AS RoleName "
                    + " FROM Registration  Re JOIN Role R ON Re.RoleID  = R.ID "
                    + " WHERE Email = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, emailUser);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String id = String.valueOf(rs.getInt("ID"));

                String name = rs.getString("Name");
                int roleID = rs.getInt("RoleID");
                String roleName = rs.getString("RoleName");
                String phone = rs.getString("Phone");
                String address = rs.getString("Address");

                result = new RegistrationDTO(id, emailUser, "null", name, new RoleDTO(roleID, roleName), new StatusDTO(1));
                result.setPhone(phone);
                result.setAddress(address);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public String getUserNameByID(int userId) throws SQLException, NamingException {
        String userName = null;
        try {
            String sql = "select r.Name from Registration r where r.Id = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, userId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                userName = rs.getString("Name");
            }
        } finally {
            closeConnection();
        }
        return userName;
    }

    public int insertAccount(RegistrationDTO dto) throws SQLException, NamingException {
        int id = -1;
        try {
            String sql = "INSERT INTO Registration (Email, Name, Phone, Address, RoleID, StatusID) "
                    + " OUTPUT Inserted.ID "
                    + " VALUES (?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getEmail());
            preStm.setString(2, dto.getName());
            preStm.setString(3, dto.getPhone());
            preStm.setString(4, dto.getAddress());
            preStm.setInt(5, 2);
            preStm.setInt(6, 1);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }

        } finally {
            closeConnection();
        }
        return id;
    }

    public int createAccount(RegistrationDTO dto) throws SQLException, NamingException {
        int id = -1;
        try {
            String sql = "INSERT INTO Registration (Email, Password,Name, Phone, Address, RoleID, StatusID) "
                    + " OUTPUT Inserted.ID "
                    + " VALUES (?, ?, ?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getEmail());
            preStm.setString(2, dto.getPassword());
            preStm.setString(3, dto.getName());
            preStm.setString(4, dto.getPhone());
            preStm.setString(5, dto.getAddress());
            preStm.setInt(6, 2);
            preStm.setInt(7, 1);
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("ID");
            }

        } finally {
            closeConnection();
        }
        return id;
    }

    public void updateInfomationUser(int userId, String phone, String address) throws SQLException, NamingException {
        try {
            conn = MyConnection.getMyConnection();
            String sql = " UPDATE Registration SET Phone = ?, Address = ? WHERE Id = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, phone);
            preStm.setString(2, address);
            preStm.setInt(3, userId);
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public RegistrationDTO getInfoUserLogin(int userId) throws SQLException, NamingException {
        RegistrationDTO result = null;
        try {
            String SQL = "SELECT Re.ID , Re.Email ,Re.Password, Re.Name, Re.Phone, Re.Address, R.ID  AS RoleID, R.Name AS RoleName "
                    + " FROM Registration Re JOIN Role R ON Re.RoleID  = R.ID "
                    + " WHERE Re.ID = ?  ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(SQL);
            preStm.setInt(1, userId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String id = String.valueOf(rs.getInt("ID"));
                String name = rs.getString("Name");
                String email = rs.getString("Name");
                String password = rs.getString("Password");
                int roleID = rs.getInt("RoleID");
                String roleName = rs.getString("RoleName");
                String address = rs.getString("Address");
                String phone = rs.getString("Phone");
                result = new RegistrationDTO(id, email, password, name, address, new RoleDTO(roleID, roleName), new StatusDTO(1));
                result.setPhone(phone);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}
