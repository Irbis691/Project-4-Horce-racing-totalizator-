/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.User;
import races.resources.ConfigurationManager;

/**
 * Class-command for taking list of users
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class TakeUsersCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.manageUsers");
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        List<User> users = daoFactory.createUserDao().findAll();
        List<String> userLogins = new ArrayList<>();
        for(User u: users) {
            userLogins.add(u.getLogin());
        }
        request.setAttribute("users", userLogins);
        return page;
    }

}
