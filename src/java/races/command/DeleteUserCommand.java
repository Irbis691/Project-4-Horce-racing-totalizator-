/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.resources.MessageManager;


/**
 * Class-command for deleting user from DB
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class DeleteUserCommand implements ActionCommand {

    /**
     * logger variable 
     */
    private static final Logger logger = Logger.getLogger(DeleteUserCommand.class);
    
    /**
     * String-constant for user's login 
     */
    private static final String PARAM_NAME_LOGIN = "login";
    
    @Override
    public String execute(HttpServletRequest request) {
        String login = request.getParameter(PARAM_NAME_LOGIN);
        if(login == null) {
            logger.error("Admin not choose what user delete");
            request.setAttribute("chForDel",
                    MessageManager.getProperty("message.chForDel"));
            return new TakeUsersCommand().execute(request);
        }
        deleteUser(login);
        return new TakeUsersCommand().execute(request);
    }
    
    /**
     * submethod for interaction with DB
     * @param login 
     */
    private void deleteUser(String login) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        daoFactory.createUserDao().delete(login);
    }
}
