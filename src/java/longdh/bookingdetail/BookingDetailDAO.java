/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.bookingdetail;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longdh.utils.MyConnection;

/**
 *
 * @author Dong Long
 */
public class BookingDetailDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BookingDetailDAO() {
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

    public int countTotalBookedCake(int cakeId) throws SQLException, NamingException {
        String sql = "SELECT SUM(b.Amount) as TotalBooked FROM BookingDetail b WHERE b.CakeId = ? ";
        int totalBooked = 0;
        try {
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cakeId);
            rs = preStm.executeQuery();
            if (rs.next()) {
                totalBooked = rs.getInt("TotalBooked");
            }
        } finally {
            closeConnection();
        }
        return totalBooked;
    }

    public boolean insertBookingItem(BookingDetailDTO dto) throws SQLException, NamingException {
        boolean result = false;
        try {
            String sql = "INSERT INTO BookingDetail (BookingId, CakeId, Amount) VALUES(?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getBookingId());
            preStm.setInt(2, dto.getCakeId());
            preStm.setInt(3, dto.getAmount());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }

    public List<BookingDetailDTO> itemByBookingID(int bookingID) throws SQLException, NamingException {
        ArrayList<BookingDetailDTO> list = new ArrayList<>();
        try {
            String sql = "  select b.CakeId , b.Amount as soLuong "
                    + " from BookingDetail b ,Cake c "
                    + " where c.cakeId = b.CakeId and b.BookingId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, bookingID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int CakeId = rs.getInt("CakeId");
                int quan = rs.getInt("soLuong");

                list.add(new BookingDetailDTO(CakeId, quan));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public String getCakeName(int cakeId) throws SQLException, NamingException {
        String cakeName = null;
        try {
            String sql = " select cakeName from Cake  where cakeId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, cakeId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                cakeName = rs.getString("cakeName");
            }
        } finally {
            closeConnection();
        }
        return cakeName;

    }

    String getCakeImage(int foodId) throws SQLException, NamingException {
        String image = null;
        try {
            String sql = " select imageLink from Cake where cakeId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, foodId);
            rs = preStm.executeQuery();
            while (rs.next()) {
                image = rs.getString("imageLink");
            }
        } finally {
            closeConnection();
        }
        return image;
    }

}
