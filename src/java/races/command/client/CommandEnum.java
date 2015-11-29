/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package races.command.client;

import races.command.ActionCommand;
import races.command.ChangeLanguageCommand;
import races.command.DeleteBetCommand;
import races.command.DeleteUserCommand;
import races.command.FixRaceResult;
import races.command.LoginCommand;
import races.command.LogoutCommand;
import races.command.PlaceBetCommand;
import races.command.RegistrationCommand;
import races.command.TakeHorsesWithCoeffOrPalacesCommand;
import races.command.TakeRacesCommand;
import races.command.TakeUserBetsBalanceCommand;
import races.command.TakeUsersCommand;
import races.command.ToResDelPageCommand;
import races.command.UpdateBetSizeCommand;
import races.command.UpdateHorseCoeffCommand;
import races.command.UpdateHorsePlaceCommand;

/**
 * Container of commands
 *
 * @version 1.0 7 Jun 2015
 * @author Пазинич
 */
public enum CommandEnum {

    /**
     * Enum of anonymous classes that assign some command to variable command
     */
    LOGIN {
                {
                    this.command = new LoginCommand();
                }
            },
    /**
     *
     */
    LOGOUT {
                {
                    this.command = new LogoutCommand();
                }
            },
    /**
     *
     */
    REGISTRATION {
                {
                    this.command = new RegistrationCommand();
                }
            },
    /**
     *
     */
    DELETEUSER {
                {
                    this.command = new DeleteUserCommand();
                }
            },
    /**
     *
     */
    UPDATEHORSEPLACE {
                {
                    this.command = new UpdateHorsePlaceCommand();
                }
            },
    /**
     *
     */
    UPDATEHORSECOEFF {
                {
                    this.command = new UpdateHorseCoeffCommand();
                }
            },
    /**
     *
     */
    PLACEBET {
                {
                    this.command = new PlaceBetCommand();
                }
            },
    /**
     *
     */
    UPDATEBETSIZE {
                {
                    this.command = new UpdateBetSizeCommand();
                }
            },
    /**
     *
     */
    DELETEBET {
                {
                    this.command = new DeleteBetCommand();
                }
            },
    /**
     *
     */
    CHANGELANGUAGE {
                {
                    this.command = new ChangeLanguageCommand();
                }
            },
    /**
     *
     */
    TAKEUSERBETS {
                {
                    this.command = new TakeUserBetsBalanceCommand();
                }
            },
    /**
     *
     */
    TAKERACES {
                {
                    this.command = new TakeRacesCommand();
                }
            },
    /**
     *
     */
    TAKEHORSESWITHCOEFFORPLACES {
                {
                    this.command = new TakeHorsesWithCoeffOrPalacesCommand();
                }
            },
    /**
     *
     */
    TAKEUSERS {
                {
                    this.command = new TakeUsersCommand();
                }
            },
    /**
     *
     */
    TORESDELPAGE {
                {
                    this.command = new ToResDelPageCommand();
                }
            },
    FIXRACERESULTS {
                {
                    this.command = new FixRaceResult();
                }
            };

    /**
     * variable to which is assigned requested command
     */
    ActionCommand command;

    /**
     * @return some command
     */
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
