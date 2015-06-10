<%-- 
    Document   : updDelBets
    Created on : 06.06.2015, 12:01:52
    Author     : Pazynych
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.pagecontent" />
<c:set var="CLIENT_TYPE" value="${3}" />
<c:if test="${empty type or type != CLIENT_TYPE}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title><fmt:message key="cl.console.title"/></title>
    </head>
</head>
<body>
    <div id="wrapper">
        <div id="header">
            <div align="right">
                <ul id="lang">
                    <li><form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "changeLanguage" />
                        <input type = "hidden" name = "path" value = "path.page.chDelBets" />
                        <input type = "hidden" name = "req" value = "none" />
                        <input type = "hidden" name = "lang" value = "en_US" />
                        <input type="submit" value="<fmt:message key="en"/>" class="button">
                        </form></li>
                    <li><form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "changeLanguage" />
                        <input type = "hidden" name = "path" value = "path.page.chDelBets" />
                        <input type = "hidden" name = "req" value = "none" />
                        <input type = "hidden" name = "lang" value = "ru_RU" />
                        <input type="submit" value="<fmt:message key="ru"/>" class="button">
                        </form></li>
                </ul>
            </div>
            <h1><fmt:message key="title"/></h1>
            <p class="description"><fmt:message key="welcome"/></p>
            <img src="logo1.png" alt="horse racing" width="250">
        </div> 
        <ul id="nav">
            <li><a href="index.jsp"><fmt:message key="index"/></a></li>
            <li><a href="registration.jsp"><fmt:message key="registration"/></a></li>
            <li> <a href="login.jsp"><fmt:message key="login"/></a></li>            
        </ul>
        <div id="fullpage">   
            <center>
                <h2><fmt:message key="upd.del.bet"/></h2>
                <table class="bets">
                    <thead>
                        <tr>
                            <th><fmt:message key="upd"/></th>
                            <th><fmt:message key="del"/></th>
                        </tr>
                    <tbody>
                        <tr>
                            <td align = "center">
                                <form method="POST" action="./Controller" />
                                <input type = "hidden" name = "command" value = "updateBetSize" />
                                <p class="small_label"><fmt:message key="inp.b.id"/></p>
                                <input type="text" name="betId"/>                            
                                <p class="small_label"><fmt:message key="inp.b.size"/></p>                        
                                <input type="text" name="betSize"/>
                                </br></br>
                                <input type="submit" value="<fmt:message key="waste"/>" class="small_button">
                                </form>
                            </td>
                            <td align = "center">
                                <form method="POST" action="./Controller" />
                                <input type = "hidden" name = "command" value = "deleteBet" />
                                <p class="small_label"><fmt:message key="inp.b.id"/></p>
                                </br>
                                <input type="text" name="betId"/>
                                </br></br>
                                <input type="submit" value="<fmt:message key="del"/>" class="small_button"/>
                                </form> 
                            </td>
                        </tr>
                    </tbody>
                </table>
                <p class="message">${inpBetId}</p>
                <p class="message">${inpBetSize}</p>
                <p class="message">${delNotOwnBet}</p>
                <ul id="buttons_list">
                    <li>
                        <form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "takeRaces" />
                        <input type="submit" value="<fmt:message key="place.bet"/>" class="button">
                        </form>                        
                    </li>
                    <li>
                        <form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "takeUserBets" />
                        <input type="submit" value="<fmt:message key="my.bets"/>" class="button">
                        </form>
                    </li>
                    <li>
                        <form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "Logout" />
                        <input type="submit" value="<fmt:message key="out"/>" class="button"/>
                        </form>
                    </li>
                </ul>
            </center>
        </div>
    </div>
</body>
</html>
