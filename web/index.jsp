<%--
    Document   : index
    Created on : 13.05.2015, 11:33:45
    Author     : Pazynych
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.pagecontent" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title><fmt:message key="title"/></title>
    </head>
    <body>     
        <div id="wrapper">
            <div id="header"> 
                <div align="right">
                    <ul id="lang">
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.index" />
                            <input type = "hidden" name = "req" value = "none" />
                            <input type = "hidden" name = "lang" value = "en_US" />
                            <input type="submit" value="<fmt:message key="en"/>" class="button">
                            </form></li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.index" />
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
                <li><a href="login.jsp"><fmt:message key="login"/></a></li>                           
            </ul>          
            <div id="sidebar">
                <h2><fmt:message key="news"/></h2>
                <p class="news">
                    <fmt:message key="newsText"/>
                    <img src="cute_horse.png" width="200">
                </p>
            </div>
        </div>
    </body>
</html>
