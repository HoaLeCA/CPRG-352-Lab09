
package dataaccess;

import java.sql.*;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


/**
 *
 * @author levan
 */
public class DBUtil {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("Lab9PU");
    
    public static EntityManagerFactory getEmFactory(){
        return emf;
    }
    
}
