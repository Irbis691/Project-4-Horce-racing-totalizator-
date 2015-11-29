<%-- 
    Document   : races
    Created on : 21.11.2015, 16:02:38
    Author     : Pazynych
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.pagecontent" />
<c:set var="ADMIN_TYPE" value="${1}" />
<c:set var="CLIENT_TYPE" value="${3}" />
<c:if test="${empty type}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title><fmt:message key="races.title"/></title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div align="right">
                    <ul id="lang">
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.races" />
                            <input type = "hidden" name = "req" value = "races" />
                            <input type = "hidden" name = "lang" value = "en_US" />
                            <input type="submit" value="<fmt:message key="en"/>" class="button">
                            </form></li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.races" />
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
                    <h2><fmt:message key="races.title"/></h2>
                    <table class="bets">
                        <tbody>
                            <c:forEach var="elem" items="${races}">
                                <tr>
                                    <td>                                    
                                        <form method="POST" action="./Controller">
                                            <input type = "hidden" name = "command" value = "takeHorsesWithCoeffOrPlaces" />
                                            <input type = "hidden" name = "raceName" value = "${elem.getRaceName()}" />
                                            <input type="submit" value="<c:out value="${elem.getRaceName()}, ${elem.getRaceDateTime()}" />" class="button">                                            
                                        </form>
                                    </td>
                                </tr>
                            </c:forEach>                             
                        </tbody>
                    </table>
                    <ul id="buttons_list">   
                        <c:if test="${type == CLIENT_TYPE}">
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
                        </c:if>
                        <c:if test="${type == ADMIN_TYPE}">
                            <li>
                                <form method="POST" action="./Controller" />
                                <input type = "hidden" name = "command" value = "takeUsers" />
                                <input type="submit" value="<fmt:message key="manage.users"/>" class="button">
                                </form>
                            </li>
                        </c:if>
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
