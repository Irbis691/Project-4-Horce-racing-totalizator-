/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command;

import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.jstl.core.Config;
import races.resources.ConfigurationManager;

/**
 * Class-command for switching language
 * 
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public class ChangeLanguageCommand implements ActionCommand {

    /**
     * String-constant for language 
     */
    private static final String PARAM_NAME_LANG = "lang";
    /**
     * String-constant for redirect 
     */
    private static final String PARAM_NAME_PATH = "path";
    /**
     * String-constant for additional command (what put to request)
     */
    private static final String PARAM_NAME_REQ = "req";
    
    private static final String TAKE_BETS = "bets";
    
    private static final String TAKE_RACES = "races";
    
    private static final String TAKE_USERS = "users";
    
    private static final String TAKE_HORSES = "horses";
    
    /**
     * @param request
     * @return where to go 
     */
    @Override
    public String execute(HttpServletRequest request) {                
        String lang = request.getParameter(PARAM_NAME_LANG);
        String path = request.getParameter(PARAM_NAME_PATH);
        String req = request.getParameter(PARAM_NAME_REQ);
        whatAddToRequest(req, request);        
        String page = ConfigurationManager.getProperty(path);
        Locale locale = new Locale(lang);
        Config.set(request.getSession(), Config.FMT_LOCALE, locale);       
        return page;
    }

    private void whatAddToRequest(String req, HttpServletRequest request) {
        if (req != null) {
            switch (req) {
                case TAKE_BETS:
                    new TakeUserBetsBalanceCommand().execute(request);
                    break;
                case TAKE_RACES:
                    new TakeRacesCommand().execute(request);
                    break;
                case TAKE_USERS:
                    new TakeUsersCommand().execute(request);
                    break;
                case TAKE_HORSES:
                    new TakeHorsesWithCoeffOrPalacesCommand().execute(request);
                    break;
                default:
                    break;
            }
        }
    }
    
}
