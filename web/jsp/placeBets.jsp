<%--
    Document   : placeBets
    Created on : 13.05.2015, 11:33:45
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
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title><fmt:message key="cl.console.title"/></title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div align="right">
                    <ul id="lang">
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.placeBets" />
                            <input type = "hidden" name = "req" value = "races" />
                            <input type = "hidden" name = "lang" value = "en_US" />
                            <input type="submit" value="<fmt:message key="en"/>" class="button">
                            </form></li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.placeBets" />
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
                <center>
                    <h2><fmt:message key="cl.console.title"/></h2>
                    <table class="bets">
                        <thead>
                            <tr>
                                <th><fmt:message key="sel.race"/></th>
                                <th><fmt:message key="sel.horse"/></th>
                                <th><fmt:message key="inp.bet.size"/></th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td align = "center">
                                    <form method="POST" action="./Controller" />
                                    <input type = "hidden" name = "command" value = "takeHorses" />                                    
                                    <select name="race">
                                        <option selected disabled><fmt:message key="def.race"/></option>
                                        <c:forEach var="elem" items="${races}">
                                            <option>
                                                <c:out value="${elem.getRaceName()}, ${elem.getRaceDateTime()}" />
                                            </option>
                                        </c:forEach>
                                    </select>
                                    </br></br>
                                    <input type="submit" value="<fmt:message key="choose"/>" class="small_button"/>
                                    </form>
                                </td>
                        <form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "placeBet" />
                        <input type = "hidden" name = "raceName" value = "${param.race}" />
                        <input type = "hidden" name = "userId" value = "${id}" />
                        <td valign="top">
                            <select name="horseName">
                                <option selected disabled><fmt:message key="def.horse"/></option>
                                <c:forEach var="elem" items="${horses}">
                                    <option>
                                        <c:out value="${elem}" />
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td align = "center">
                            <input type="text" name="betSize">
                            </br></br>
                            <input type="submit" value="<fmt:message key="waste"/>" class="small_button">
                        </td>
                        </form>
                        </tr>
                        </tbody>
                    </table>
                    <p class="message">${chooseRace}</p>
                    <p class="message">${existedBet}</p>
                    <p class="message">${chooseHorse}</p>
                    <p class="message">${notInpBet}</p>
                    <ul id="buttons_list">                        
                        <li>
                            <form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "takeUserBets" />
                            <input type="submit" value="<fmt:message key="my.bets"/>" class="button">
                            </form>
                        </li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "toResDelPage" />
                            <input type="submit" value="<fmt:message key="ch.del.bets"/>" class="button">
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
