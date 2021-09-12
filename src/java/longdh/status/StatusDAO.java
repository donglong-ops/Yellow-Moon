/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.status;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.naming.NamingException;
import longdh.utils.MyConnection;

/**
 *
 * @author Dong Long
 */
public class StatusDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public StatusDAO() {
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

    public ArrayList<StatusDTO> getAllStatus() throws SQLException, NamingException { 
        ArrayList<StatusDTO> listStatus = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT S.Id, S.Name FROM Status S";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String name = rs.getString("Name");
                listStatus.add(new StatusDTO(id, name));
            }
        } finally {
            closeConnection();
        }
        return listStatus;
    }
}
