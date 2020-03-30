<%@ page contentType="text/html" pageEncoding="UTF-8"%>
<!doctype html>
<html>
    <head>
        <title>我的投資組合</title>
        <!-- head -->
        <%@include file="/WEB-INF/jsp/include/head.jspf"  %>
    </head>
    <body>
        <div id="layout">
            <!-- Menu toggle -->
            <%@include file="/WEB-INF/jsp/include/toggle.jspf"  %>

            <!-- Menu -->
            <%@include file="/WEB-INF/jsp/include/menu.jspf"  %>
            
            <div id="main">
                <div class="header">
                    <h1>我的投資組合</h1>
                    <h2>Portfolio Tables</h2>
                </div>
                
                <img src="/SpringMVC/portfolio/images/portfolio.png" width="100%">
                
            </div>
        </div>
        <!-- Foot -->
        <%@include file="/WEB-INF/jsp/include/foot.jspf"  %>   
    </body>
</html>
