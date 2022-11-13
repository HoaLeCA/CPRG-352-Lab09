/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.*;
import java.util.*;
import javax.persistence.EntityManager;
import models.Role;
import models.User;

/**
 *
 * @author levan
 */
public class RoleDB {
    
   public List <Role> getAll() throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       
       try{
           List <Role> fullRoles = em.createNamedQuery("Role.findAll", Role.class).getResultList();
           return fullRoles;
       }finally{
           em.close();
       }
       
   }
    public Role getRole(int id) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            Role role = em.find(Role.class, id);
            return role;
        } finally {
            em.close();
        }
    }
 
}
