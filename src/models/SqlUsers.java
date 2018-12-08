/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import Formulario.IngresoSistema;
import Formulario.Principal;
import Formulario.RegisterUser;
import com.mysql.jdbc.PreparedStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author matias
 */
public class SqlUsers extends Conexion {
    Conexion cc= new Conexion();
Connection cn = cc.conexion();

    public Integer totalregistros;

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;

        String[] titulos = {"id", "Nombres", "Usuario", "Email", "Acceso", "Estado", "Fecha_Ingreso"};
        String[] registros = new String[13];
        totalregistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        String sql = "select * from users where user like '%" + buscar + "%' order by id desc";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                registros[0] = rs.getString("id");
                registros[1] = rs.getString("description");
                registros[2] = rs.getString("user");
                registros[3] = rs.getString("email");
                registros[4] = rs.getString("user_type");
                registros[5] = rs.getString("status");
                registros[6] = rs.getString("created_at");
                
                totalregistros = totalregistros + 1;
                modelo.addRow(registros);
            }
            return modelo;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

      
      public boolean registrar (users usr) throws SQLException{
            PreparedStatement ps = null;
            Connection con = (Connection) conexion();
            
            String sql = "insert into users (user,password,description,email,user_type,created_at,status) values (?,?,?,?,?,?,?)";
            try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setString(1, usr.getUser());
            ps.setString(2, usr.getPassword());
            ps.setString(3, usr.getDescription());
            ps.setString(4, usr.getEmail());
            ps.setInt(5, usr.getId_type());
            ps.setDate(6, (Date) usr.getFecha());
            ps.setInt(7, usr.getStatus());
            ps.execute();
            return true;
            } catch (SQLException ex){
                  Logger.getLogger(SqlUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
      }
      
      public boolean update(users usr) {
        String sql = "update  users set user = ?, password = ?, description = ?, email = ?, user_type = ?, status = ? "
                + "where id=?";
        try {

            java.sql.PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, usr.getUser());
            pst.setString(2, usr.getPassword());
            pst.setString(3, usr.getDescription());
            pst.setString(4, usr.getEmail());
            pst.setInt(5, usr.getId_type());
            pst.setInt(6, usr.getStatus());
            pst.setInt(7, usr.getId());
            
            int n = pst.executeUpdate();

            if (n != 0) {

                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
        
      }
    
    /**
     *
     * @param usr
     */
    public void eliminar(users usr){
      
         PreparedStatement ps = null;
            Connection con = (Connection) conexion();
            
            String sql = "delete from users where id = ?";
            try{
            ps = (PreparedStatement) con.prepareStatement(sql);
            ps.setInt(1, usr.getId());
            
            ps.executeUpdate();
            
            } catch (SQLException ex){
                  Logger.getLogger(SqlUsers.class.getName()).log(Level.SEVERE, null, ex);
            }
            
      
      }
      
      
      

      public boolean login(users usr) {
            java.sql.PreparedStatement ps = null;
            ResultSet rs = null;
            Principal p = new Principal();

            String query = "select u.id,u.user,u.password,u.description,u.user_type, t.description from users as u inner join types_users t on u.user_type = t.id where u.user = ?";

            try {
                  ps = cn.prepareStatement(query);
                  ps.setString(1, usr.getUser());

              
                  rs = ps.executeQuery();

                  if (rs.next()) {
                        if (usr.getPassword().equals(rs.getString(3))) {
                              usr.setId(rs.getInt(1));
                              usr.setDescription(rs.getString(4));
                              usr.setType_user(rs.getString(5));
                              usr.setType_user(rs.getString(6));
                              return true;

                        } else {
                              return false;
                        }
                  }
                /*    boolean validate = ValidateTypeUser(usr);
                  if (validate == true){
                      p.MenuMant.setEnabled(false);
                  }*/
                  return false;
            } catch (SQLException ex) {
                  Logger.getLogger(IngresoSistema.class.getName()).log(Level.SEVERE, null, ex);
                  return false;
            }

      }
       public int existeUsuario(String usuario) {
        java.sql.PreparedStatement ps = null;
        ResultSet rs = null;
        Connection con = cc.conexion();

        String sql = "SELECT count(id) FROM usuarios WHERE usuario = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            rs = ps.executeQuery();

            if (rs.next()) {
                return rs.getInt(1);
            }

            return 1;

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e.toString());
            return 1;
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, e.toString());
            }
        }
    }

    public boolean esEmail(String correo) {

        // Patrón para validar el email
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");

        Matcher mather = pattern.matcher(correo);

        return mather.find();

    }
    
           public boolean checkUsername (String username)
    {
        java.sql.PreparedStatement ps;
        ResultSet rs;
        boolean checkUser = false;
        String query = "select * from users where user = ?";
        
            try {
            ps = cn. prepareStatement (query);
            ps.setString (1, username);
            
            rs = ps.executeQuery ();
            
            if (rs.next ())
            {
                checkUser = true;
            }
        } catch (SQLException ex) {
            Logger.getLogger (RegisterUser.class.getName ()) .log (Level.SEVERE, null, ex);
        }
         return checkUser;
    }
}
