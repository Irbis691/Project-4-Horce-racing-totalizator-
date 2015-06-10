/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.ArrayList;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;
import races.dao.connection.JdbcConnection;
import races.dao.factory.DaoFactory;
import races.dao.factory.RealDaoFactory;
import races.entities.User;
import races.resources.ConfigurationManager;
import races.resources.MessageManager;

/**
 * Class-command for user registration
 *
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class RegistrationCommand implements ActionCommand {

    /**
     * logger variable
     */
    private static final Logger logger = Logger.getLogger(RegistrationCommand.class);

    /**
     * String-constant for user's login
     */
    private static final String PARAM_NAME_LOGIN = "login";
    /**
     * String-constant for user's password
     */
    private static final String PARAM_NAME_PASSWORD = "password";
    /**
     * String-constant for user's type
     */
    private static final String PARAM_NAME_TYPE = "type";
    private static final int ADMIN_TYPE = 1;
    private static final int BOOKIE_TYPE = 2;
    private static final int CLIENT_TYPE = 3;

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.successRegPage");
        String login = request.getParameter(PARAM_NAME_LOGIN);
        String pass = request.getParameter(PARAM_NAME_PASSWORD);
        int type = Integer.parseInt(request.getParameter(PARAM_NAME_TYPE));
        if (!(login.isEmpty() || pass.isEmpty())) {
            if (checkLoginUniq(login)) {
                setUserAttributes(type, login, pass, request);
            } else {
                logger.error("User input already existing login");
                request.setAttribute("loginNotUniqMessage",
                        MessageManager.getProperty("message.loginNotUniqMessage"));
                page = whereTo(type, request);
            }
        } else {
            logger.error("Empty input in registration");
            request.setAttribute("emptyInput",
                    MessageManager.getProperty("message.emptyInput"));
            page = whereTo(type, request);
        }
        return page;
    }

    /**
     * submethod for redirecting if some errors occured
     *
     * @param type
     * @param request
     * @return
     */
    private String whereTo(int type, HttpServletRequest request) {
        String page;
        if (type != 3) {
            page = new TakeUsersCommand().execute(request);
        } else {
            page = ConfigurationManager.getProperty("path.page.registration");
        }
        return page;
    }

    /**
     * submethod for setting to session needed attributes
     *
     * @param type
     * @param link
     * @param login
     * @param pass
     * @param request
     */
    private void setUserAttributes(int type, String login,
            String pass, HttpServletRequest request) {
        int id = regNewUser(login, pass, type);
        HttpSession session = request.getSession();
        session.setAttribute("id", id);
        session.setAttribute("type", type);
        request.setAttribute("user", login);
    }

    /**
     * submethod for checking login uniques
     *
     * @param enterLogin
     * @return
     */
    private boolean checkLoginUniq(String enterLogin) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        ArrayList<String> loginList = (ArrayList<String>) daoFactory.createUserDao().findLogins();
        for (String login : loginList) {
            if (login.equals(enterLogin)) {
                return false;
            }
        }
        return true;
    }

    /**
     * submethod for interaction with DB
     *
     * @param enterLogin
     * @param enterPass
     * @param type
     * @return
     */
    private int regNewUser(String enterLogin, String enterPass, int type) {
        JdbcConnection connection = JdbcConnection.getInstance();
        DaoFactory daoFactory = new RealDaoFactory(connection);
        User user = new User(enterLogin, enterPass.hashCode(), type);
        return daoFactory.createUserDao().insert(user);
    }
}
