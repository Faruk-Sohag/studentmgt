package student;

import db.MyConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Student {

    Connection con = MyConnection.getConnection();
    PreparedStatement ps;

    
    /*
    
    public static void main(String[] args) {

    }
    private String name;
*/
    
    //get table max row
    public int getMax() {
        int id = 0;
        Statement st;
        try {
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select max(id) from student");
            while (rs.next()) {
                id = rs.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id + 1;

    }
     

    // insert data into student table
    public void insert(int id, String sname, String gender, String email, String phone, String father, String mother, String address1, String address2) {
        String sql = "insert into student values(?,?,?,?,?,?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, sname);
            ps.setString(3, gender);
            ps.setString(4, email);
            ps.setString(5, phone);
            ps.setString(6, father);
            ps.setString(7, mother);
            ps.setString(8, address1);
            ps.setString(9, address2);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New student added successfully");
            }

        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    

    //check student email address is  already exists
    public boolean isEmailExist(String email) {
        try {
            ps = con.prepareStatement("select * from student where email = ?");
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //check student phone number is  already exists
    public boolean isPhoneExist(String phone) {
        try {
            ps = con.prepareStatement("select * from student where phone = ?");
            ps.setString(1, phone);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    //check student id is  already exists
    public boolean isIdExit(int id) {
        try {
            ps = con.prepareStatement("select * from student where id = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    
    
    
    //get all the student values from database student table
    public void getStudentValue(JTable table, String searchValue) {
      //  String sql = "select * from student where concat(id,name,email,phone) like ? order by id desc";
        
        String sql = "select * from student where concat(id,name) like ? order by id desc";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, "%" + searchValue + "%");
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model = (DefaultTableModel) table.getModel();
            Object[] row;
            while (rs.next()) {
                row = new Object[9];
                row[0] = rs.getInt(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                row[8] = rs.getString(9);
                model.addRow(row);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    
    
    
    
    
    
    // update student values
    public void update(int id, String sname, String gender, String email, String phone, String father, String mother, String address1, String address2) {
        String sql = "update student set name = ?,gender = ?, email = ?,phone=?,father_name=?,mother_name=?," + "address1=?,address2=? where id=?";

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, sname);
            ps.setString(2, gender);
            ps.setString(3, email);
            ps.setString(4, phone);
            ps.setString(5, father);
            ps.setString(6, mother);
            ps.setString(7, address1);
            ps.setString(8, address2);
            ps.setInt(9, id);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "student data update successfully");
            }
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //student data delete
    public void delete(int id) {
        int yesNo = JOptionPane.showConfirmDialog(null, "Course and score records will also be deleted", "Student Deleted", JOptionPane.OK_CANCEL_OPTION, 0);
        if (yesNo == JOptionPane.OK_OPTION) {
            try {
                ps = con.prepareStatement("delete from student where id = ?");
                ps.setInt(1, id);
                if (ps.executeUpdate() > 0) {
                    JOptionPane.showMessageDialog(null, "student deleted successfully");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }
    
    
    
    
    
    
    
    
    
    
    

}
