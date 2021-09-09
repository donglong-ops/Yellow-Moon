/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.booking;

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
public class BookingDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public BookingDAO() {
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

    public int insertBookingFood(BookingDTO dto) throws SQLException, NamingException {
        int id = -1;
        try {
            String sql = "INSERT INTO Booking (UserId , ImportedDate, Total, PayWith) "
                    + "  OUTPUT Inserted.UserId "
                    + "  VALUES(?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, dto.getUserId());
            preStm.setTimestamp(2, dto.getImportedDate());
            preStm.setFloat(3, dto.getTotal());
            preStm.setString(4, "CASH");
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("UserId");
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public List<BookingDTO> allBookingUser(int userID) throws SQLException, NamingException {
        ArrayList<BookingDTO> list = new ArrayList<>();
        try {
            String sql = " select b.UserId as MaDonHang, b.ImportedDate , b.Total, b.PayWith  "
                    + " from Booking b  "
                    + " where b.UserId = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, userID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int bookingID = rs.getInt("MaDonHang");
                Timestamp importDate = rs.getTimestamp("ImportedDate");
                float total = rs.getFloat("Total");
                String payment = rs.getString("PayWith");
                list.add(new BookingDTO(bookingID, userID, importDate, total, payment));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public BookingDTO getBooking(int bookingID) throws SQLException {
        BookingDTO dto = new BookingDTO();
        try {
            String sql = " select  Id, ImportedDate , Total"
                    + " from Booking where Id = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, bookingID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int bookingId = rs.getInt("Id");
                Timestamp importDate = rs.getTimestamp("ImportedDate");
                float total = rs.getFloat("Total");
                dto = new BookingDTO(bookingId, importDate, total);
            }
        } catch (NamingException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<BookingDTO> searchHis(int userID, Date fromDate, Date toDate) throws SQLException, NamingException {
        ArrayList<BookingDTO> list = new ArrayList<>();
        try {
            String sql = " select b.Id as MaDonHang, b.ImportedDate , b.Total , b.PayWith "
                    + "from Booking b  "
                    + "where b.UserId = ? and b.ImportedDate > ? and b.ImportedDate <= ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, userID);
            preStm.setDate(2, fromDate);
            preStm.setDate(3, toDate);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int bookingID = rs.getInt("MaDonHang");
                Timestamp importDate = rs.getTimestamp("ImportedDate");
                float total = rs.getFloat("Total");
                String payment = rs.getString("PayWith");
                list.add(new BookingDTO(bookingID, userID, importDate, total, payment));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public void updateStatusBooking(int bookingId) throws SQLException, NamingException {
        try {
            conn = MyConnection.getMyConnection();
            String sql = " UPDATE Booking SET PayWith = ? WHERE BookingId = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "MOMO");
            preStm.setInt(2, bookingId);
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }

}
