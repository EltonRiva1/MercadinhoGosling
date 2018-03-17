/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model.DAO;

import Connection.ConnectionFactory;
import Model.Bean.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author notle
 */
public class ProdutoDAO {
    
    public void create(Produto p) {
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("insert into produto(descricao, quantidade, preco)values(?,?,?)");
            ps.setString(1, p.getDescricao());
            ps.setInt(2, p.getQuantidade());
            ps.setDouble(3, p.getPreco());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, ps);
        }
    }
    
    public List<Produto> list() {
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Produto> l = new ArrayList<>();
        try {
            ps = c.prepareStatement("select * from produto");
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                l.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(c, ps, rs);
        }
        return l;
    }
    
    public void update(Produto p) {
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("update produto set descricao = ?, quantidade = ?, preco = ? where id = ?");
            ps.setString(1, p.getDescricao());
            ps.setInt(2, p.getQuantidade());
            ps.setDouble(3, p.getPreco());
            ps.setInt(4, p.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, ps);
        }
    }
    
    public void delete(Produto p) {
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        try {
            ps = c.prepareStatement("delete from produto where id = ?");
            ps.setInt(1, p.getId());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
        } finally {
            ConnectionFactory.closeConnection(c, ps);
        }
    }
    
    public List<Produto> l(String s) {
        Connection c = ConnectionFactory.getConnection();
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Produto> l = new ArrayList<>();
        try {
            ps = c.prepareStatement("select * from produto where descricao like ?");
            ps.setString(1, "%" + s + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setDescricao(rs.getString("descricao"));
                p.setQuantidade(rs.getInt("quantidade"));
                p.setPreco(rs.getDouble("preco"));
                l.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            ConnectionFactory.closeConnection(c, ps, rs);
        }
        return l;
    }
}
