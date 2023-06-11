/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.universitymanagement;


import java.awt.Component;
import java.util.List;

public interface DAO {
    void insertion(User pessenger,Component component);
    void show(Component component);
    void register(User pessenger,Component component);
    boolean available(String UserName,Component component);
    boolean login(String UserName,String Password,Component component);
    public int getSizeApproval(Component comp);
    public List<String> getRolesFromList(Component comp);
    public boolean deleteApproval(int index, Component comp);
    public boolean approvePerson(User nPassenger, String role, Component comp);
    public User searchApproval(int index, Component comp);
    public int searchApproval(String name, Component comp);
    public String checkRole(String userName, Component comp);
}
