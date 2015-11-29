/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.dao.connection.JdbcConnection;
import races.entities.User;
import races.resources.ConfigurationManager;
import races.resources.MessageManager;

/**
 * Class-command for login user
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class LoginCommand implements ActionCommand {
    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(LoginCommand.class);

    /**
     * String-constant for user's login 
     */
    private static final String PARAM_NAME_LOGIN = "login";
    /**
     * String-constant for user's password 
     */
    private static final String PARAM_NAME_PASSWORD = "password";

    private static final int NOT_FINDED = 0;
    
    @Override
    public String execute(HttpServletRequest request) {
        String page;
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        if (checkLogin(login)) {
            if (checkPass(pass)) {
                HttpSession session = request.getSession();
                session.setAttribute("id" , getId(login));
                session.setAttribute("type" , getType(login));
                page = new TakeRacesCommand().execute(request);
            } else {
                logger.error("User input wrong password");
                request.setAttribute("errorPassMessage",
                        MessageManager.getProperty("message.passError"));
                page = ConfigurationManager.getProperty("path.page.login");
            }
        } else {
            logger.error("User input wrong login");
            request.setAttribute("errorLoginMessage",
                    MessageManager.getProperty("message.loginError"));
            page = ConfigurationManager.getProperty("path.page.login");
        }
        return page;
    }
    
    /**
     * method for checking is such login exist
     * 
     * @param enterLogin
     * @return true if such login is in DB, false otherwith
     */
    private boolean checkLogin(String enterLogin) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        List<User> users = daoFactory.createUserDao().findAll();
        for (User user : users) {
            if (user.getLogin().equals(enterLogin)) {
                return true;
            }
        }
        return false;
    }

    /**
     * method for checking is such password have this user
     * 
     * @param enterPass
     * @return true if such password have this user, false otherwith
     */
    private boolean checkPass(String enterPass) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        List<User> users = daoFactory.createUserDao().findAll();
        for (User user : users) {
            if (user.getPasswordHash() == enterPass.hashCode()) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * @param login
     * @return user's type
     */
    private int getType(String login) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        List<User> users = daoFactory.createUserDao().findAll();
        for(User user: users) {
            if(user.getLogin().equals(login)) {
                return user.getUserType();
            }
        }
        return NOT_FINDED;
    }

    /**
     * @param login
     * @return user's id
     */
    private int getId(String login) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        List<User> users = daoFactory.createUserDao().findAll();
        for(User user: users) {
            if(user.getLogin().equals(login)) {
                return user.getUserId();
            }
        }
        return NOT_FINDED;
    }

}
