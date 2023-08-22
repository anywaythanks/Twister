<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page session="false" %>
<html>
<head>
    <c:import url="includes/css.jsp"/>
    <title>Types</title>
    <link rel="stylesheet"
          type="text/css"
          href="<c:url value="/resources/style.css" />">
</head>
<body>
<c:forEach items="${types}" var="type">
    <s:url value="types/{typeName}" var="icon_url">
        <s:param name="typeName" value="${type.name}"/>
    </s:url>
    <a href="${icon_url}">${type.name}</a>
</c:forEach>
</body>
</html>