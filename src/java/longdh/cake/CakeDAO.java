/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.cake;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longdh.utils.MyConnection;


/**
 *
 * @author Dong Long
 */
public class CakeDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public CakeDAO() {
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

    public String getCategoryName(int cate_id) throws SQLException, NamingException {
        String cateName = null;
        try {
            String sql = "SELECT c.Name FROM Category c WHERE c.Id = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cate_id);
            rs = preStm.executeQuery();
            while (rs.next()) {
                cateName = rs.getString("Name");
            }
        } finally {
            closeConnection();
        }
        return cateName;

    }

    public int countTotalCake(String foodname, int categoriID, float toPrice, float fromPrice) throws SQLException, NamingException {
        int count = 2;
        int countPage = 0;
        try {
            String sql = "SELECT COUNT(cakeId) as totalRows FROM Cake C "
                    + "   WHERE C.statusId = 1 and C.quantity > ? And DATEDIFF(day , GETDATE(), C.expirationDate ) > 0 ";
            if (foodname != null) {
                sql += " And C.cakeName like ? ";
            }
            if (categoriID > 0) {
                sql += " And C.categoriId = ? ";
            }
            if (fromPrice >= 0) {
                sql += "And C.cakePrice >= ?  ";
            }
            if (toPrice > 0 && toPrice >= fromPrice) {
                sql += "And C.cakePrice <= ? ";
            }
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 0);
            if (foodname != null) {
                preStm.setString(count, "%" + foodname + "%");
                count++;
            }
            if (categoriID > 0) {
                preStm.setInt(count, categoriID);
                count++;
            }
            if (fromPrice > 0) {
                preStm.setFloat(count, fromPrice);
                count++;
            }
            if (toPrice > 0 && toPrice >= fromPrice) {
                preStm.setFloat(count, toPrice);
                count++;
            }

            rs = preStm.executeQuery();
            if (rs.next()) {
                countPage = rs.getInt("totalRows");
            }
        } finally {
            closeConnection();
        }
        return countPage;
    }

    public int countTotalCake() throws SQLException, NamingException {
        int countPage = 0;
        try {
            String sql = "SELECT COUNT(cakeId) as totalRows FROM Cake C "
                    + " WHERE C.statusId = 1 AND C.quantity >  0 And DATEDIFF(day , GETDATE(), C.expirationDate ) > 0 ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            if (rs.next()) {
                countPage = rs.getInt("totalRows");
            }
        } finally {
            closeConnection();
        }
        return countPage;
    }

    public List<CakeDTO> searchCakePaging(String cakeName, int cate_id, float toPrice, float fromPrice) throws SQLException, NamingException {
        return searchCakePaging(cakeName, cate_id, toPrice, fromPrice, 1, 5);
    }

    public List<CakeDTO> searchCakePaging(String cakeName, int categoriID, float toPrice, float fromPrice, int pageNumber,int pageSize) throws SQLException, NamingException {
        List<CakeDTO> result = new ArrayList<>();
        int count = 2;
        try {
            String sql = "SELECT C.cakeId, C.cakeName , C.cakePrice , C.quantity, C.description, C.createDate ,C.expirationDate ,C.categoriId , C.statusId , C.imageLink "
                    + "    FROM Cake C "
                    + "    WHERE C.statusId = 1 AND C.quantity >  ? And DATEDIFF(day , GETDATE(), C.expirationDate ) > 0";
            if (cakeName != null) {
                sql += "And C.cakeName like ? ";
            }
            if (categoriID > 0) {
                sql += "And C.categoriId = ? ";
            }

            if (fromPrice >= 0) {
                sql += "And C.cakePrice >= ? ";
            }
            if (toPrice > 0 && toPrice >= fromPrice) {
                sql += "And C.cakePrice <= ? ";
            }
            sql += "ORDER BY DATEDIFF(day, GETDATE(), C.expirationDate ) ASC "
                    + "OFFSET ? ROWS "
                    + "FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 0);

            if (cakeName != null) {
                preStm.setString(count, "%" + cakeName + "%");
                count++;
            }
            if (categoriID > 0) {
                preStm.setInt(count, categoriID);
                count++;
            }
            if (fromPrice > 0) {
                preStm.setFloat(count, fromPrice);
                count++;
            }
            if (toPrice > 0 && toPrice >= fromPrice) {
                preStm.setFloat(count, toPrice);
                count++;
            }
            preStm.setInt(count, pageSize * (pageNumber - 1));
            preStm.setInt(count + 1, pageSize);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int cakeId = rs.getInt("cakeId");
                String cakename = rs.getString("cakeName");
                float cakePrice = rs.getFloat("cakePrice");
                int quantity = rs.getInt("quantity");
                String des = rs.getString("description");
                Date createDate = rs.getDate("createDate");
                Date expirationDate = rs.getDate("expirationDate");
                int cate_id = rs.getInt("categoriId");
                int status_id = rs.getInt("statusId");
                String imageLink = rs.getString("imageLink");

                CakeDTO dto = new CakeDTO(cakeId, status_id, cakename, cakePrice, quantity, cate_id, createDate, expirationDate, des, imageLink);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<CakeDTO> cakePaging(int pageNumber, int pageSize) throws SQLException, NamingException {
        List<CakeDTO> result = new ArrayList<>();
        try {
            String sql = "SELECT C.cakeId, C.cakeName , C.cakePrice , C.quantity, C.description, C.createDate, C.expirationDate ,C.categoriId ,C.statusId , C.imageLink "
                    + " From Cake C "
                    + " WHERE C.statusId = 1 AND C.quantity > ? And DATEDIFF(day , GETDATE(), C.expirationDate ) > 0 "
                    + " ORDER BY DATEDIFF(day , GETDATE(), C.expirationDate ) ASC "
                    + " OFFSET ? ROWS "
                    + " FETCH NEXT ? ROWS ONLY";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, 0);
            preStm.setInt(2, pageSize * (pageNumber - 1));
            preStm.setInt(3, pageSize);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int cakeId = rs.getInt("cakeId");
                String cakeName = rs.getString("cakeName");
                float cakePrice = rs.getFloat("cakePrice");
                int quantity = rs.getInt("quantity");
                String des = rs.getString("description");
                Date createDate = rs.getDate("createDate");
                Date expirationDate = rs.getDate("expirationDate");
                int cate_id = rs.getInt("categoriId");
                int status_id = rs.getInt("statusId");
                String imageLink = rs.getString("imageLink");

                CakeDTO dto = new CakeDTO(cakeId, status_id, cakeName, cakePrice, quantity, cate_id, createDate, expirationDate, des, imageLink);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public CakeDTO getCakeByID(int cakeID) throws SQLException, NamingException {
        CakeDTO result = null;
        try {
            String sql = "SELECT C.cakeId, C.cakeName , C.cakePrice , C.quantity, C.description, C.createDate, "
                    + " C.expirationDate, C.categoriId, C.statusId , C.imageLink, C.updateDate, C.userId "
                    + " From Cake C  "
                    + " WHERE C.statusId = 1 and C.cakeId =  ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cakeID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                int cakeId = rs.getInt("cakeId");
                String cakeName = rs.getString("cakeName");
                float cakePrice = rs.getFloat("cakePrice");
                int quantity = rs.getInt("quantity");
                String des = rs.getString("description");
                Date createDate = rs.getDate("createDate");
                Date expirationDate = rs.getDate("expirationDate");
                Date updateDate = rs.getDate("updateDate");
                int userId = rs.getInt("userId");
                int cate_id = rs.getInt("categoriId");
                int statusId = rs.getInt("statusId");
                String imageLink = rs.getString("imageLink");

                result = new CakeDTO(cakeId, statusId, cakeName, cakePrice, quantity, cate_id, createDate, expirationDate, des, imageLink, updateDate, userId);
            }
        } finally {
            closeConnection();
        }
        return result;
    }

    public int getCakeQuantity(int cakeID) throws SQLException, NamingException {
        int res = 0;
        try {
            String sql = "SELECT quantity FROM Cake Where cakeId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cakeID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                res = rs.getInt("quantity");
            }
        } finally {
            closeConnection();
        }
        return res;
    }
    
    public String getCakeName(int cakeID) throws SQLException, NamingException {
        String name = null;
        try {
            String sql = "SELECT cakeName FROM Cake Where cakeId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cakeID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                name = rs.getString("cakeName");
            }
        } finally {
            closeConnection();
        }
        return name;
    }

    public boolean insertCake(CakeDTO dto) throws SQLException, NamingException {
        boolean result = false;
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        try {
            String sql = " INSERT INTO Cake (cakeName, cakePrice, quantity, categoriId, description, createDate, expirationDate, imageLink ,statusId) "
                    + " VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?) ;";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getCakeName());
            preStm.setFloat(2, dto.getCakePrice());
            preStm.setInt(3, dto.getQuantity());
            preStm.setInt(4, dto.getCategoriID());
            preStm.setString(5, dto.getDescription());
            preStm.setTimestamp(6, currentDate);
            preStm.setDate(7, dto.getExpirationDate());
            preStm.setString(8, dto.getImageLink());
            preStm.setInt(9, 1);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean deleteCake(int cakeId, int userID) throws SQLException, NamingException {
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        boolean result = false;
        try {
            String sql = " UPDATE Cake SET statusId = 2 , updateDate = ? , userId = ?  WHERE cakeId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setTimestamp(1, currentDate);
            preStm.setInt(2, userID);
            preStm.setInt(3, cakeId);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public boolean updateCake(CakeDTO dto) throws SQLException, NamingException {
        boolean result = false;
        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        try {
            String sql = "UPDATE Cake SET cakeName = ? , cakePrice = ? , quantity = ? , description = ? , "
                    + " imageLink = ?, categoriId = ? , statusId = ?,expirationDate = ?, updateDate = ?, userId = ? "
                    + " WHERE cakeId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getCakeName());
            preStm.setFloat(2, dto.getCakePrice());
            preStm.setInt(3, dto.getQuantity());
            preStm.setString(4, dto.getDescription());
            preStm.setString(5, dto.getImageLink());
            preStm.setInt(6, dto.getCategoriID());
            preStm.setInt(7, dto.getStatusId());
            preStm.setDate(8, dto.getExpirationDate());
            preStm.setTimestamp(9, currentDate);
            preStm.setInt(10, dto.getUserId());
            preStm.setInt(11, dto.getCakeId());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean updateCakeQuantity(int cakeId, int quantity) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "UPDATE Cake SET quantity = ? WHERE cakeId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, quantity);
            preStm.setInt(2, cakeId);
            
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}
