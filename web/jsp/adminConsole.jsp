<%-- 
    Document   : adminConsole
    Created on : 13.05.2015, 11:33:28
    Author     : Pazynych
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.pagecontent" />
<c:set var="ADMIN_TYPE" value="${1}" />
<c:if test="${empty type or type != ADMIN_TYPE}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title><fmt:message key="ad.console.title"/></title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div align="right">
                    <ul id="lang">
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.adminConsole" />
                            <input type = "hidden" name = "req" value = "races" />
                            <input type = "hidden" name = "lang" value = "en_US" />
                            <input type="submit" value="<fmt:message key="en"/>" class="button">
                            </form></li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.adminConsole" />
                            <input type = "hidden" name = "req" value = "races" />
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
                <h2><fmt:message key="fix.pl"/></h2>
                <center>
                    <form method="POST" action="./Controller" />
                    <input type = "hidden" name = "command" value = "takeHorsesParam" /> 
                    <input type = "hidden" name = "param" value = "place" /> 
                    <select name="race">
                        <option selected disabled><fmt:message key="def.race"/></option>
                        <c:forEach var="elem" items="${races}">
                            <option>
                                <c:out value="${elem.getRaceName()}, ${elem.getRaceDateTime()}" />
                            </option>
                        </c:forEach>
                    </select>
                    </br></br>
                    <input type="submit" value="<fmt:message key="choose"/>" class="small_button" />
                    </form>
                    <p class="message">${chooseRace}</p>
                    <table class="races">
                        <tbody>
                            <tr>
                                <th><fmt:message key="h.name"/></th>
                                <th><fmt:message key="curr.coeff"/></th>
                            </tr>       
                            <c:forEach var="elem" items="${map.keySet()}">
                                <tr>                                
                                    <td><c:out value="${elem}" /></td>
                                    <td><c:out value="${map.get(elem)}" /></td>                                
                                </tr>         
                            </c:forEach>
                        </tbody>
                    </table>     
                    <table class="bets">
                        <tbody>
                            <tr>
                                <th><fmt:message key="h.name"/></th>
                                <th><fmt:message key="new.pl"/></th>
                            </tr>
                        <form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "updateHorsePlace" />
                        <tr>
                            <td>
                                <select name="name">
                                    <option selected disabled><fmt:message key="def.horse"/></option>
                                    <c:forEach var="elem" items="${map.keySet()}">
                                        <option>
                                            <c:out value="${elem}" />
                                        </option>
                                    </c:forEach>
                                </select>         
                            </td>
                            <td>
                                <input type = "text" name = "place"/>
                            </td>
                        </tr>
                        <tr>
                            <td colspan="2" align = "center">
                                <input type="submit" value="<fmt:message key="upd"/>" class="small_button">
                            </td>
                        </tr>
                        </tbody>
                    </table>
                    </form>
                    <p class="message">${chooseHorse}</p>
                    <p class="message">${inpHorPl}</p>
                    <ul id="buttons_list">                        
                        <li>
                            <form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "takeUsers" />
                            <input type="submit" value="<fmt:message key="manage.users"/>" class="button">
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
