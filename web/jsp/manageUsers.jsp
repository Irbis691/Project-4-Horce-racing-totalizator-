<%-- 
    Document   : managUsers
    Created on : 06.06.2015, 15:43:09
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
                            <input type = "hidden" name = "path" value = "path.page.manageUsers" />
                            <input type = "hidden" name = "req" value = "users" />
                            <input type = "hidden" name = "lang" value = "en_US" />
                            <input type="submit" value="<fmt:message key="en"/>" class="button">
                            </form></li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.manageUsers" />
                            <input type = "hidden" name = "req" value = "users" />
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
                <h2><fmt:message key="manage.users"/></h2>
                <center>
                    <table class="bets">
                        <tbody>
                            <tr>
                                <th colspan="3">
                                    <fmt:message key="us.tab.title"/>
                                </th>
                            </tr>
                            <tr>
                                <th><fmt:message key="del.us.title"/></th>
                                <th><fmt:message key="add.adm.title"/></th>
                                <th><fmt:message key="del.boo.title"/></th>
                            </tr>
                            <tr>
                                <td align="center">
                                    <form method="POST" action="./Controller" />
                                    <input type = "hidden" name = "command" value = "deleteUser" />
                                    <select name="login">
                                        <option selected disabled>Choose user</option>
                                        <c:forEach var="elem" items="${users}">
                                            <option>
                                                <c:out value="${elem}" />
                                            </option>
                                        </c:forEach>                            
                                    </select>
                                    <br /><br />
                                    <input type="submit" value="<fmt:message key="del"/>" class="small_button">
                                    </form>
                                </td>
                                <td align="center">
                                    <form method="POST" action="./Controller" />                        
                                    <input type = "hidden" name = "command" value = "registration" />
                                    <input type = "hidden" name = "type" value = "1" />
                                    <p class="small_label"><fmt:message key="inp.login"/></p>                        
                                    <input type="text" name = "login" />
                                    <p class="small_label"><fmt:message key="inp.password"/></p>  
                                    <input type="text" name = "password">
                                    </br></br>
                                    <input type="submit" value="<fmt:message key="add"/>" class="small_button">
                                    </form>
                                </td>                   
                                <td align="center">
                                    <form method="POST" action="./Controller" />                        
                                    <input type = "hidden" name = "command" value = "registration" />
                                    <input type = "hidden" name = "type" value = "2" />
                                    <p class="small_label"><fmt:message key="inp.login"/></p>
                                    <input type="text" name = "login">
                                    <p class="small_label"><fmt:message key="inp.password"/></p>
                                    <input type="text" name = "password">
                                    </br></br>
                                    <input type="submit" value="<fmt:message key="add"/>" class="small_button" />
                                    </form>
                                </td>                   
                            </tr>
                        </tbody>            
                    </table>
                    <p class="message">${loginNotUniqMessage}</p>
                    <p class="message">${emptyInput}</p>
                    <p class="message">${chForDel}</p>
                    <ul id="buttons_list">                        
                        <li>
                            <form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "takeRaces" />
                            <input type="submit" value="<fmt:message key="fix.pl"/>" class="button">
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
