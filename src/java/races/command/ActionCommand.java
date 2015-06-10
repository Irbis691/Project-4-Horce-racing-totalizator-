/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import javax.servlet.http.HttpServletRequest;

/**
 * Interface of command pattern
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public interface ActionCommand {
    /**
     * execute some actions (with DB for example) and forms request to next page
     * 
     * @param request
     * @return string that specified page to go
     */
    String execute(HttpServletRequest request);
}
