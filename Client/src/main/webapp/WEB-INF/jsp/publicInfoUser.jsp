<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="includes/css.jsp"/>
    <title>${account.nickname}</title>
</head>
<body>
<div class="site-wrapper">
    <c:import url="includes/header.jsp"/>
    <main>
        <a>Никнейм:<c:out value="${account.nickname}" /></a>
    </main>
    <c:import url="includes/footer.jsp"/>
</div>
</body>
</html>
