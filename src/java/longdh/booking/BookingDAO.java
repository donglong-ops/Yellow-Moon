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
import java.util.ArrayList;
import java.util.List;
import javax.naming.NamingException;
import longdh.registration.RegistrationDTO;
import longdh.role.RoleDTO;
import longdh.status.StatusDTO;
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

    public int insertBookingCake(BookingDTO dto) throws SQLException, NamingException {
        int id = -1;
        try {
            String sql = "INSERT INTO Booking (UserId , ImportedDate, Total, PayWith, PaymentStatus) "
                    + "  OUTPUT Inserted.BookingId "
                    + "  VALUES(?, ?, ?, ?, ?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, dto.getUserId());
            preStm.setDate(2, dto.getImportedDate());
            preStm.setFloat(3, dto.getTotal());
            preStm.setString(4, "CASH");
            preStm.setString(5, "Delivery Success");
            rs = preStm.executeQuery();
            if (rs.next()) {
                id = rs.getInt("BookingId");
            }
        } finally {
            closeConnection();
        }
        return id;
    }

    public List<BookingDTO> allBookingUser(int userID) throws SQLException, NamingException {
        ArrayList<BookingDTO> list = new ArrayList<>();
        try {
            String sql = " select b.BookingId, b.ImportedDate , b.Total, b.PayWith, b.PaymentStatus  "
                    + " from Booking b  "
                    + " where b.UserId = ? order by b.ImportedDate ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, userID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int bookingID = rs.getInt("BookingId");
                Date importDate = rs.getDate("ImportedDate");
                float total = rs.getFloat("Total");
                String payment = rs.getString("PayWith");
                String paymentStatus = rs.getString("PaymentStatus");
                list.add(new BookingDTO(bookingID, userID, importDate, total, payment, paymentStatus));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public BookingDTO getBooking(int bookingID) throws SQLException, NamingException {
        BookingDTO dto = new BookingDTO();
        try {
            String sql = " SELECT BookingId, ImportedDate , Total"
                    + " FROM Booking where BookingId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, bookingID);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int bookingId = rs.getInt("BookingId");
                Date importDate = rs.getDate("ImportedDate");
                float total = rs.getFloat("Total");
                dto = new BookingDTO(bookingId, importDate, total);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }

    public List<BookingDTO> searchHis(int userID, Date fromDate, Date toDate) throws SQLException, NamingException {
        ArrayList<BookingDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT b.BookingId, b.ImportedDate , b.Total , b.PayWith, b.PaymentStatus "
                    + "FROM Booking b  "
                    + "WHERE b.UserId = ? and b.ImportedDate >= ? and b.ImportedDate <= ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, userID);
            preStm.setDate(2, fromDate);
            preStm.setDate(3, toDate);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int bookingID = rs.getInt("BookingId");
                Date importDate = rs.getDate("ImportedDate");
                float total = rs.getFloat("Total");
                String payment = rs.getString("PayWith");
                String paymentStatus = rs.getString("PaymentStatus");
                list.add(new BookingDTO(bookingID, userID, importDate, total, payment, paymentStatus));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public List<BookingDTO> searchHisByBookingID(int userID, int bookingid) throws SQLException, NamingException {
        ArrayList<BookingDTO> list = new ArrayList<>();
        try {
            String sql = "SELECT b.BookingId, b.ImportedDate , b.Total , b.PayWith, b.PaymentStatus "
                    + "FROM Booking b  "
                    + "WHERE b.UserId = ? and b.BookingId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);

            preStm.setInt(1, userID);
            preStm.setInt(2, bookingid);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int bookingID = rs.getInt("BookingId");
                Date importDate = rs.getDate("ImportedDate");
                float total = rs.getFloat("Total");
                String payment = rs.getString("PayWith");
                String paymentStatus = rs.getString("PaymentStatus");
                list.add(new BookingDTO(bookingID, userID, importDate, total, payment, paymentStatus));
            }
        } finally {
            closeConnection();
        }
        return list;
    }

    public void updateStatusBooking(int bookingId, String momoCode, String payment) throws SQLException, NamingException {
        try {
            conn = MyConnection.getMyConnection();
            String sql = " UPDATE Booking SET PayWith = ?, MomoCode = ? WHERE BookingId = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, payment);
            preStm.setString(2, momoCode);
            preStm.setInt(3, bookingId);
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public void updateStatusBookingPaypal(int bookingId, String payment) throws SQLException, NamingException {
        try {
            conn = MyConnection.getMyConnection();
            String sql = " UPDATE Booking SET PayWith = ? WHERE BookingId = ? ";
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, payment);
            preStm.setInt(2, bookingId);
            preStm.executeUpdate();
        } finally {
            closeConnection();
        }
    }

    public RegistrationDTO getUserByBookingID(int bookingID) throws SQLException, NamingException {
        RegistrationDTO result = null;
        try {
            String sql = "SELECT r.Id, r.Email, r.Name, r.Address,r.RoleID, rl.Name AS RoleName "
                    + " FROM Booking b , Registration r , Role rl "
                    + " WHERE r.Id = b.UserId and rl.Id = r.RoleID and b.BookingId = ? ";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, bookingID);
            rs = preStm.executeQuery();
            if (rs.next()) {
                String Id = String.valueOf(rs.getInt("Id"));
                String name = rs.getString("Name");
                String email = rs.getString("Email");
                int roleID = rs.getInt("RoleID");
                String roleName = rs.getString("RoleName");
                String address = rs.getString("Address");
                result = new RegistrationDTO(Id, email, name, address, new RoleDTO(roleID, roleName), new StatusDTO(1));
            }
        } finally {
            closeConnection();
        }
        return result;
    }

}
