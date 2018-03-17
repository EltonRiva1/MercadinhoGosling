/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author notle
 */
public class UsuarioDAO {

    public boolean list(String login, String senha) {
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            ps = c.prepareStatement("select * from usuario where login = ? and senha = ?");
            ps.setString(1, login);
            ps.setString(2, senha);
            rs = ps.executeQuery();
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(c, ps, rs);
        }
        return b;
    }
}
