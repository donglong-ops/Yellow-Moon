/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package longdh.category;

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
public class CategoryDAO implements Serializable {

    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;

    public CategoryDAO() {
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

    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, NamingException { 
        ArrayList<CategoryDTO> listCate = new ArrayList<>();
        try {
            String sql = "SELECT DISTINCT c.Id, c.Name FROM Category c";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("Id");
                String cateName = rs.getString("Name");
                listCate.add(new CategoryDTO(id, cateName));
            }
        } finally {
            closeConnection();
        }
        return listCate;
    }
}
