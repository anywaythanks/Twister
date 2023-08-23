<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:import url="includes/meta.jsp"/>
    <c:import url="includes/css.jsp"/>
    <s:url value="${fileServerPath}/css" var="css_url"/>
    <s:url value="${fileServerPath}/js" var="js_url"/>
    <link href="${css_url}/home.css" rel="stylesheet" />
    <link href="${css_url}/caseList.css" rel="stylesheet" />
    <link href="${css_url}/pagination.css" rel="stylesheet" />
    <script type="text/javascript" src="${js_url}/format.js"></script>
    <title>Twister</title>
</head>

<body>
<div class="site-wrapper">
    <c:import url="includes/header.jsp"/>
    <main>
        <div class="home-content">

        </div>
        <div class="cases-list">
            <c:import url="includes/caseList.jsp"/>
            <c:set var="startQ" value="2" scope="request"/>
            <c:set var="endQ" value="2" scope="request"/>
            <c:set var="windowQ2" value="1" scope="request"/>
            <c:import url="includes/pagination.jsp"/>
        </div>
    </main>
    <c:import url="includes/footer.jsp"/>
</div>
</body>
</html>