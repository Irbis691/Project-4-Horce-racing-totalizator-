<%--
    Document   : index
    Created on : 13.05.2015, 11:33:45
    Author     : Pazynych
--%>

<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<fmt:setBundle basename="resources.pagecontent" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="style.css">
        <title><fmt:message key="reg.title"/></title>
    </head>
    <body>
        <div id="wrapper">
            <div id="header"> 
                <div align="right">
                    <ul id="lang">
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.registration" />
                            <input type = "hidden" name = "req" value = "none" />
                            <input type = "hidden" name = "lang" value = "en_US" />
                            <input type="submit" value="<fmt:message key="en"/>" class="button">
                            </form></li>
                        <li><form method="POST" action="./Controller" />
                            <input type = "hidden" name = "command" value = "changeLanguage" />
                            <input type = "hidden" name = "path" value = "path.page.registration" />
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
            <div id="fullpage">
                <h1><fmt:message key="reg.title"/></h1>
                <form method="POST" action="./Controller">
                    <input type = "hidden" name = "command" value = "Registration" />
                    <input type = "hidden" name = "type" value = "3" />
                    <p class="label"><fmt:message key="inp.login"/></p>
                    <input type="text" name="login" />
                    <p class="label"><fmt:message key="inp.password"/></p>
                    <input type="password" name="password" />
                    </br></br>
                    <input type="submit" value="<fmt:message key="reg.button"/>" class="button">
                </form>                
                <p class="message">${loginNotUniqMessage}</p>
                <p class="message">${emptyInput}</p>
            </div>
        </div>
    </body>
</html>
