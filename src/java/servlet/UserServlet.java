/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Role;
import models.User;
import services.RoleService;
import services.UserService;

/**
 *
 * @author levan
 */
public class UserServlet extends HttpServlet {
    private final String PATH = "/WEB-INF/";
    private final String ERROR = "Invalid";
    private final String DEL_ERROR = "Unable to delete user";
    private final String DEL_SUCC = "One user is deleted";
    private final String UPDATE_SUCC = "One user is ";
    private final String CAL_SUCC = "Your selected is canceled";
    private final String ERROR_MSS = "Please fill in all require information before SAVE";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");
        if(action != null){
            if(action.equals("view")){
                String email = request.getParameter("email");
                try{
                    User user = us.get(email);
                    request.setAttribute("selectedUser", user);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
            }else if(action.equals("delete")){
                String email = request.getParameter("email");
                    try{
                        us.delete(email);
                        request.setAttribute("message", DEL_SUCC);
                    } catch (Exception ex) {
                        Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
                        request.setAttribute("message", DEL_ERROR);
                    }
            }
                    
        }
    
        try{
            List <User> users = us.getAll();
            List <Role> roles = rs.getAll();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);

        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", ERROR);
        }
       
        getServletContext().getRequestDispatcher(PATH + "users.jsp").forward(request, response);
        return;
    }

   
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        UserService us = new UserService();
        RoleService rs = new RoleService();
        String action = request.getParameter("action");
        String email = request.getParameter("email");
        boolean active = request.getParameter("active") != null;
        String fname = request.getParameter("fname");
        String lname = request.getParameter("lname");
        String password = request.getParameter("password");
        String role = request.getParameter("role");
        
        
        
        try{
            switch(action){
                case "add":
                    us.insert(email, active, fname, lname, password, role);
                    request.setAttribute("message", UPDATE_SUCC + action);
                    break;
                case "update":
                    us.update(email, active, fname, lname, password, role);
                    request.setAttribute("message", UPDATE_SUCC + action);
                    break; 
                case "cancel":{
                    request.setAttribute("message", CAL_SUCC);
                }
                    
            }
           
           
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", ERROR_MSS );
        }
        try{
            List <User> users = us.getAll();
            List <Role> roles = rs.getAll();
            request.setAttribute("users", users);
            request.setAttribute("roles", roles);
        } catch (Exception ex) {
            Logger.getLogger(UserServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("message", ERROR);
        }  
        getServletContext().getRequestDispatcher(PATH + "users.jsp").forward(request, response);
        return;

    }

}
