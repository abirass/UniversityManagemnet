 package com.example.universitymanagement;

 import java.awt.*;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

 public class StudentDAO implements DAO {
     public String username="root";
     public String password="";
      
    public StudentDAO()
    {
        
    }
    public Student authenticateStudent(String regno, String password) {
        Student student = null;
        
       
        try {
            String query = "SELECT * FROM studentdata WHERE regno = '"+regno+"' AND password = '"+password+"'"; 
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement", this.username, this.password);// for local database
//            Connection conn=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12625232","sql12625232","fYb5YRs82U"); //online database
            PreparedStatement statement = conn.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                
                String REGNO = resultSet.getString("regno");  
                String PASSWORD = resultSet.getString("password"); 
                student = new Student(REGNO,PASSWORD);//from local variable data is transfering into entity class  
                student.setName(resultSet.getString("name"));
                student.setFatherName(resultSet.getString("fathername"));
                student.setSemester(resultSet.getString("semester"));
                student.setAge(resultSet.getInt("age"));
                student.setEmail(resultSet.getString("email"));
                student.setPhone(resultSet.getString("phone"));
                student.setMajor(resultSet.getString("major"));
                student.setGpa(resultSet.getString("gpa"));
                student.setNo_OF_Awards(resultSet.getString("no_of_awards"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return student;
    }
    
     public Student getStudentData(String registrationno) {
        
        Student student = null;
        try {
            // Establish database connection and execute query
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement", this.username, this.password);//local database
//               Connection conn=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12625232","sql12625232","fYb5YRs82U");
            String query = "SELECT * FROM studentdata WHERE regno = ? LIMIT 1";
            PreparedStatement statement = conn.prepareStatement(query);
            statement.setString(1, registrationno);
            ResultSet resultSet = statement.executeQuery();

            // Retrieve data and create Student object
            if (resultSet.next()) {
                student = new Student();
                student.setRegno(resultSet.getString("regno"));
                student.setName(resultSet.getString("name"));
                student.setFatherName(resultSet.getString("fathername"));
                student.setSemester(resultSet.getString("semester"));
                student.setAge(resultSet.getInt("age"));
                student.setPassword(resultSet.getString("password"));
                
                student.setEmail(resultSet.getString("email"));
                 student.setPhone(resultSet.getString("phone"));
                 student.setMajor(resultSet.getString("major"));
                 student.setGpa(resultSet.getString("gpa"));
                student.setNo_OF_Awards(resultSet.getString("no_of_awards"));
                // Add more attributes if needed
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }  

        return student;
    }
     public void register(Student student, Component component){
         String query = "insert into studentdata(name,fathername,semester,age,regno,password,email,phone,major,no_of_awards,gpa,id) values(?,?,?,?,?,?,?,?,?,?,?,?)"; //change username into regno as u ill make in database 
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/UniversityManagement", this.username, this.password);// for local database
//            Connection conn=DriverManager.getConnection("jdbc:mysql://sql12.freesqldatabase.com:3306/sql12625232","sql12625232","fYb5YRs82U"); //online database
            PreparedStatement statement = conn.prepareCall(query);
            statement.setString(1,student.getName());
            statement.setString(2, student.getFatherName());
            statement.setString(3, student.getSemester());
            statement.setInt(4, student.getAge());
            statement.setString(5, student.getRegno());
            statement.setString(6, student.getPassword());
            statement.setString(7, student.getEmail());
            statement.setString(8, student.getPhone());
            statement.setString(9, student.getMajor());
            statement.setString(10, student.getNo_OF_Awards());
            statement.setString(11, student.getGpa());
            statement.setInt(12, student.getId());
            statement.executeUpdate();
        }
        catch(Exception e){
            
        }
     }
     @Override
     public void insertion(User user, Component component) {

     }

     @Override
     public void show(Component component) {

     }

     @Override
     public void register(User user, Component component) {

     }

     @Override
     public boolean available(String UserName, Component component) {
         return false;
     }

     @Override
     public boolean login(String UserName, String Password, Component component) {
         return false;
     }

     @Override
     public int getSizeApproval(Component comp) {
         return 0;
     }

     @Override
     public List<String> getRolesFromList(Component comp) {
         return null;
     }

     @Override
     public boolean deleteApproval(int index, Component comp) {
         return false;
     }

     @Override
     public boolean approvePerson(User nPassenger, String role, Component comp) {
         return false;
     }

     @Override
     public User searchApproval(int index, Component comp) {
         return null;
     }

     @Override
     public int searchApproval(String name, Component comp) {
         return 0;
     }

     @Override
     public String checkRole(String userName, Component comp) {
         return null;
     }
 }
