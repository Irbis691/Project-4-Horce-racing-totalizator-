<%-- 
    Document   : horses
    Created on : 21.11.2015, 16:53:46
    Author     : Pazynych
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.pagecontent" />
<c:if test="${empty type}">
    <c:redirect url="/index.jsp"/>
</c:if>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title><fmt:message key="place.bet"/></title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header">
                <div align="right">
                    <ul id="lang">
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.placeBets" />
                            <input type = "hidden" name = "req" value = "horses" />
                            <input type = "hidden" name = "lang" value = "en_US" />
                            <input type="submit" value="<fmt:message key="en"/>" class="button">
                            </form></li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.placeBets" />
                            <input type = "hidden" name = "req" value = "horses" />
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
                    <h2><fmt:message key="place.bet"/></h2>
                    <table class="bets">
                        <thead>
                            <tr>
                                <th><fmt:message key="inp.bet.size"/></th>
                                <th><fmt:message key="choose.horse"/></th>
                            </tr>                        
                        </thead>  
                        <tbody>
                            <c:forEach var="elem" items="${horses.keySet()}">
                            <form method="POST" action="./Controller">
                                <tr>
                                    <td>
                                        <input type = "text" name = "betSize">
                                    </td>
                                    <td>
                                        <input type = "hidden" name = "command" value = "placeBet" />
                                        <input type = "hidden" name = "horseName" value = "${elem}" />
                                        <input type="submit" value="<c:out value="${elem}" /><fmt:message key="curr.coeff"/><c:out value="${horses.get(elem)}" />" class="button">
                                    </td>
                                </tr>
                            </form>
                        </c:forEach>  
                        </tbody>
                    </table>
                    <p class="message">${notInpBet}</p>
                    <p class="message">${existedBet}</p>
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
                            <input type = "hidden" name = "command" value = "TakeRaces" />
                            <input type="submit" value="<fmt:message key="races"/>" class="button"/>
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