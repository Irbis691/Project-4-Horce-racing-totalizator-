/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import javax.servlet.http.HttpServletRequest;
import races.resources.ConfigurationManager;

/**
 * Class-command for logout user
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class LogoutCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        String page = ConfigurationManager.getProperty("path.page.index");
        request.getSession().setAttribute("id", null);
        request.getSession().setAttribute("type", null);
        return page;
    }

}
