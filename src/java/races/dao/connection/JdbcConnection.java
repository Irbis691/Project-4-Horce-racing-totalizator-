/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.dao.connection;

import java.sql.Connection;
import java.sql.SQLException;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 * Singleton provider connection to DB
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class JdbcConnection { 
    
    /**
     * logger-variable
     */
    private static final Logger logger = Logger.getLogger(JdbcConnection.class);

    /**
     * variable for connection to DB
     */
    private static JdbcConnection instance;

    /**
     * default constructor
     *
     * @param connection
     */
    private JdbcConnection() {
    }

    /**     
     * @return instance of singleton
     */
    public static JdbcConnection getInstance() {
        if (instance == null) {
            instance = new JdbcConnection();
        }
        return instance;
    }

    /**
     * method fot connetion with resource pull
     * @return connection to resource pull
     */
    public Connection getConnection() {
        try {
            InitialContext context = new InitialContext();
            DataSource ds = (DataSource) context.lookup("jdbc/mydb");
            return ds.getConnection();
        } catch (NamingException | SQLException ex) {
            logger.error("Connection error: " + ex);
        }
        return null;
    }

}
