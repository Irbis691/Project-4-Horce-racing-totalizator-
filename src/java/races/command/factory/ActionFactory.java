/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command.factory;

import javax.servlet.http.HttpServletRequest;
import races.command.ActionCommand;
import races.command.EmptyCommand;
import races.command.client.CommandEnum;

/**
 * Factory of commands
 *
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class ActionFactory {

    /**
     * method define what command is requested by string from request and return
     * it
     * 
     * @param request
     * @return requested command
     */
    public ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current = new EmptyCommand();
        String action = request.getParameter("command");
        if (action == null || action.isEmpty()) {
            return current;
        }
        try {
            CommandEnum currentEnum = CommandEnum.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
        }
        return current;
    }
}
