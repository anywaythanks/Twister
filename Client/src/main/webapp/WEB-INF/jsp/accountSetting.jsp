<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <c:import url="includes/css.jsp"/>
    <s:url value="${fileServerPath}/css" var="css_url"/>
    <link href="${css_url}/accountSetting.css" rel="stylesheet" />
    <title>Account setting</title>
</head>
<body>
<div class="site-wrapper">

    <c:import url="includes/header.jsp"/>
    <main>
        <h1>Общая настройка</h1>
        <sf:form action="setting" method="POST" enctype="multipart/form-data" modelAttribute="general">
            <sf:errors element="div" cssClass="errors"/>
            <sf:label path="nickname" type="nickname"
                      cssErrorClass="error">Никнейм</sf:label>:
            <sf:input path="nickname" cssErrorClass="error" minlength="3" maxlength="64"/><br/>
            <form:button>Сохранить</form:button>
        </sf:form>
    </main>
    <c:import url="includes/footer.jsp"/>
</div>
</body>
</html>
