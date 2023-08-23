<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <c:import url="../includes/meta.jsp"/>
    <c:import url="../includes/css.jsp"/>
    <s:url value="${fileServerPath}/css" var="css_url"/>
    <s:url value="${fileServerPath}/js" var="js_url"/>
    <link href="${css_url}/case.css" rel="stylesheet"/>
    <script type="text/javascript" src="${js_url}/jquery.min.js"></script>
    <script type="text/javascript" src="${js_url}/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${js_url}/roulette.js"></script>
    <title>Twister</title>
</head>

<body>
<div class="site-wrapper">
    <c:import url="../includes/header.jsp"/>
    <main>
        <div class="case">
            <c:forEach items="${selectedCase.items}" var="slot" varStatus="loop">
                <div class="slot" id="<c:out value="${slot.name}"/>" data-index="${loop.index}">
                    <div class="item <c:out value="${slot.item.type}"/>">
                        <span class="name"><c:out value="${slot.item.name}"/></span>
                    </div>
                </div>
            </c:forEach>
        </div>
    </main>
    <c:import url="../includes/footer.jsp"/>
</div>
</body>
</html>