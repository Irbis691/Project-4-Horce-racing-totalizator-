<%-- 
    Document   : myBets
    Created on : 06.06.2015, 12:01:33
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
                        <input type = "hidden" name = "path" value = "path.page.myBets" />
                        <input type = "hidden" name = "req" value = "bets" />
                        <input type = "hidden" name = "lang" value = "en_US" />
                        <input type="submit" value="<fmt:message key="en"/>" class="button">
                        </form></li>
                    <li><form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "changeLanguage" />
                        <input type = "hidden" name = "path" value = "path.page.myBets" />
                        <input type = "hidden" name = "req" value = "bets" />
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
                <h2><fmt:message key="my.b"/></h2>
                <table class="races">
                    <thead>
                        <tr>
                            <th><fmt:message key="b.id"/></th>
                            <th><fmt:message key="r.name"/></th>
                            <th><fmt:message key="h.name"/></th>
                            <th><fmt:message key="b.size"/></th>
                        </tr>
                    <tbody>
                        <c:forEach var="elem" items="${bets.keySet()}">
                            <tr>                      
                                <td><c:out value="${elem.getBetId()}" /></td>
                                <td><c:out value="${bets.get(elem)}" /></td>
                                <td><c:out value="${elem.getHorseName()}" /></td>
                                <td><c:out value="${elem.getBetSize()}" /></td>                       
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                <ul id="buttons_list">                        
                    <li>
                        <form method="POST" action="./Controller" />
                        <input type = "hidden" name = "command" value = "takeRaces" />
                        <input type="submit" value="<fmt:message key="place.bet"/>" class="button">
                        </form>
                    </li>
                    <li>
                        <form method="POST" action="./Controller" />
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
