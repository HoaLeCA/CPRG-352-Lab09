/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dataaccess;

import java.sql.*;
import java.util.*;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import models.Role;
import models.User;

/**
 *
 * @author levan
 */
public class UserDB {
    
    
    public List <User> getAll () throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
         try{
           List <User> fullUsers = em.createNamedQuery("User.findAll", User.class).getResultList();
           return fullUsers;
       }finally{
           em.close();
       }
        
    }
    
    public User get(String email) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        
        try{
            User user = em.find(User.class, email);
            return user;
        }finally{
            em.close();
        }
    }
    
    public void insert (User user) throws Exception {
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
        EntityTransaction trans = em.getTransaction();
        try{
            Role role = user.getRole();
            role.getUserList().add(user);
            trans.begin();
            em.persist(user);
            trans.commit();
        }catch (Exception ex){
            trans.rollback();
        }finally{
            em.close();
        }
                     
    }
    
    public void update(User user) throws Exception{
        EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction trans = em.getTransaction();
       try {
           trans.begin();
           em.merge(user);
           trans.commit();
       }catch (Exception e){
           trans.rollback();
       }finally{
           em.close();
       } 
        
    }
    
    public void delete(User user) throws Exception {
       EntityManager em = DBUtil.getEmFactory().createEntityManager();
       EntityTransaction trans = em.getTransaction();
       try {
           Role role = user.getRole();
           role.getUserList().remove(user);
           trans.begin();
           em.merge(user);
           em.remove(em.merge(user));
           trans.commit();
       }catch (Exception e){
           trans.rollback();
       }finally{
           em.close();
       } 
    }
    
}
