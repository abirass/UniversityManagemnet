package com.example.universitymanagement;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.awt.BorderLayout;
import java.awt.Component;
import java.sql.*;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
public class UserDAO implements DAO{
    
    @Override
    public void insertion(User user,Component component)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            String SQL = "insert into User(name,password,role) values(?,?,?)";
            PreparedStatement ptst = conn.prepareCall(SQL);
            ptst.setString(1, user.getUsername());
            ptst.setString(2, user.getPassword());
            ptst.executeUpdate();
            conn.close();
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    @Override
    public void show(Component component)
    {
        try
        {
            JTable table = new JTable();
            String[] col = {"ID", "Username", "Password", "Role"};
            JPanel panel = new JPanel();
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            Statement st = conn.createStatement();
            String query = "select * from User";
            PreparedStatement ptst = conn.prepareStatement(query);
            ResultSet rs = ptst.executeQuery();
            DefaultTableModel model = new DefaultTableModel(col, 0);
            table.setModel(model);
            DefaultTableModel tablemodel = (DefaultTableModel)table.getModel();
            tablemodel.setRowCount(0);
            tablemodel.addRow(col);
            while(rs.next())
            {
                Object object[] = {rs.getInt("id"),rs.getString("name"),rs.getString("password"),rs.getString("role")};
                tablemodel.addRow(object);
            }
            TableColumn column = table.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            panel.add(table);
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            panel.setVisible(true);
            table.setVisible(true);
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    public void show(Component component,String name)
    {
        try
        {
            JTable table = new JTable();
            String[] col = {"ID", "Username", "Password", "Role"};
            JPanel panel = new JPanel();
            JFrame frame = new JFrame();
            frame.setLayout(new BorderLayout());
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            Statement st = conn.createStatement();
            String query = "select * from User";
            PreparedStatement ptst = conn.prepareStatement(query);
            ResultSet rs = ptst.executeQuery();
            DefaultTableModel model = new DefaultTableModel(col, 0);
            table.setModel(model);
            DefaultTableModel tablemodel = (DefaultTableModel)table.getModel();
            tablemodel.setRowCount(0);
            tablemodel.addRow(col);
            while(rs.next())
            {
                String role = rs.getString("role");
                System.out.println(role);
                if(role.equals(name)){
                    
                    Object object[] = {rs.getInt("id"),rs.getString("name"),rs.getString("password"),rs.getString("role")};
                    tablemodel.addRow(object);
                }
            }
            TableColumn column = table.getColumnModel().getColumn(2);
            column.setPreferredWidth(100);
            panel.add(table);
            frame.add(panel, BorderLayout.CENTER);
            frame.pack();
            frame.setVisible(true);
            panel.setVisible(true);
            table.setVisible(true);
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    @Override
    public void register(User user,Component component)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            String SQL = "insert into User(name,password,role) values(?,?,?)";
            PreparedStatement ptst = conn.prepareCall(SQL);
            ptst.setString(1, user.getUsername());
            ptst.setString(2, user.getPassword());
            ptst.setString(3, user.getRole());
            ptst.executeUpdate();
            conn.close();
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    public void update(User user, Component component)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            String SQL = "update User set name ='"+user.getUsername()+"', password= '"+user.getPassword()+"' where id ='"+user.getId()+"'";
            Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
            PreparedStatement ptst = conn.prepareStatement(SQL);
            ptst.executeUpdate();
            conn.close();
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
        }
    }
    @Override
    public boolean available(String UserName,Component component)
    {
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
//            Statement stmt = conn.createStatement();
//            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM Registeration WHERE UserName ='"+UserName+"'");
//            rs.next();
//            int count = rs.getInt(1);
//            return count>0;
//        }
//        catch(Exception E)
//        {
//            JOptionPane.showMessageDialog(component,E);
//            return false;
//        }
        return false;
    }
    public User searchId(int id,Component component)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE id ='"+id+"'");
            if(rs.next()){
                String name = rs.getString("name");
                String password = rs.getString("password");
                String role = rs.getString("role");
                User user = new User(name,password,role,id);
                return user;
            }
            return null;
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
            return null;
        }
    }
    public User searchIdStudent(int id,Component component)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM User WHERE id ='"+id+"'");
            if(rs.next()){
                String name = rs.getString("name");
                String password = rs.getString("password");
                String role = rs.getString("role");
                User user = new User(name,password,role,id);
                if("Student".equals(role)){
                    return user;
                }else{
                    return null;
                }
                
            }
            return null;
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
            return null;
        }
    }
    @Override
    public boolean login(String username,String password,Component component)
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) FROM User WHERE name ='"+username+"' AND password='"+password+"'");
            if(rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        catch(Exception E)
        {
            JOptionPane.showMessageDialog(component,E);
            return false;
        }
        return false;
    }
    @Override
    public int getSizeApproval(Component comp){
//            try{
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
//                String query ="SELECT COUNT(*) FROM Approval";
//                PreparedStatement st = conn.prepareStatement(query);
//                ResultSet rs = st.executeQuery();
//                if(rs.next()){
//                    return rs.getInt(1);
//                }
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(comp, e+" Error in SizeFunction");
//                return -1;
//            }
        return 0;
    }
    @Override
    public List<String> getRolesFromList(Component comp){
//        try{
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
//                String query = "SELECT * FROM Roles";
//                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = st.executeQuery(query);
//                List<String> rolesList = new ArrayList<String>();
//                while(rs.next()){
//                    rolesList.add(rs.getString("Name"));
//                }
//                conn.close();
//                return rolesList;
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(comp,e+" Error in roles list");
//                return null;
//            }
        return null;
    }
    @Override
    public boolean deleteApproval(int index, Component comp){
//        try{
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
//                String query ="SELECT * FROM Approval";
//                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = st.executeQuery(query);
//                rs.absolute(index+1);
//                query = "DELETE FROM Approval WHERE Name='"+rs.getString("Name")+"' AND UserName='"+rs.getString("UserName")+"'";
//                PreparedStatement ptst = conn.prepareCall(query);
//                ptst.execute();
//                conn.close();
//                return true;
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(comp, e);
//                return false;
//            }
return false;
    }
    public boolean deleteUser(User user, Component comp){
        try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
                String query = "DELETE FROM User WHERE id='"+user.getId()+"'";
                PreparedStatement ptst = conn.prepareCall(query);
                ptst.execute();
                conn.close();
                return true;
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp, e);
                return false;
            }
    }
    @Override
    public boolean approvePerson(User nPassenger, String role, Component comp){
//        try
//        {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
//            String SQL = "insert into Registeration values(?,?,?,?,?)";
//            PreparedStatement ptst = conn.prepareCall(SQL);
//            ptst.setString(1, nPassenger.getName());
//            ptst.setString(2, nPassenger.getUserName());
//            ptst.setString(3,nPassenger.getPassword());
//            ptst.setString(4,nPassenger.getDOB());
//            ptst.setString(5, role);
//            ptst.executeUpdate();
//            conn.close();
//            return true;
//        }
//        catch(Exception E)
//        {
//            JOptionPane.showMessageDialog(comp,E);
//            return false;
//        }
return false;
    }
    @Override
    public User searchApproval(int index, Component comp){
//        try{
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
//                String query = "SELECT * FROM Approval";
//                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//                ResultSet rs = st.executeQuery(query);
//                rs.absolute(index+1);
//                User nPassenger = new User();
//                nPassenger.setName(rs.getString("Name"));
//                nPassenger.setUserName(rs.getString("UserName"));
//                nPassenger.setPassword(rs.getString("Password"));
//                nPassenger.setDOB(rs.getString("DOB"));
//                conn.close();
//                return nPassenger;
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(comp,e+" Error in searchApproval");
//                return null;
//            }
return null;
    }
    @Override
    public int searchApproval(String name, Component comp){
//        try{
//                Class.forName("com.mysql.cj.jdbc.Driver");
//                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
//                String query = "SELECT EXISTS(SELECT * FROM Approval WHERE Name = '"+name+"')";
//                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
//                int count = 0;
//                //Check if the spesified person found in the database
//                if (!st.execute(query)) throw new IOException("Passenger Not Found");
//                //if found then return its position index
//                else {
//                    query ="SELECT * FROM Approval";
//                    ResultSet rs = st.executeQuery(query);
//                    while(rs.next()){
//                        if(rs.getString("Name").equals(name)){
//                            conn.close();
//                            break;
//                        }else count++;
//                    }
//                return count;
//                }
//            }catch(Exception e){
//                JOptionPane.showMessageDialog(comp, e);
//                return -1;
//            }
return 0;
    }
    @Override
    public String checkRole(String userName, Component comp){
            try{
                Class.forName("com.mysql.cj.jdbc.Driver");
                Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement","root","");
                String query = "SELECT * FROM User WHERE name = '"+userName+"'";
                Statement st = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
                ResultSet rs = st.executeQuery(query);
                if(rs.next()){
                    return rs.getString("role");
                }
            }catch(Exception e){
                JOptionPane.showMessageDialog(comp, e);
                return "";
            }
        return null;
    }
}
