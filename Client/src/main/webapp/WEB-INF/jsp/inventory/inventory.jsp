<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<html>
<head>
    <c:import url="../includes/meta.jsp"/>
    <c:import url="../includes/css.jsp"/>
    <s:url value="${fileServerPath}/css" var="css_url"/>
    <s:url value="${fileServerPath}/js" var="js_url"/>
    <link href="${css_url}/item.css" rel="stylesheet"/>
    <link href="${css_url}/inventory.css" rel="stylesheet"/>
    <title>Инвентарь</title>
</head>
<body>
<c:import url="../includes/header.jsp"/>
<main>
    <div class="inventory">
        <c:forEach items="${inventory.slots}" var="slot">
            <c:if test="${slot.quantity > 0}">
                <div class="slot">
                    <div class="item <c:out value="${slot.item.type}"/>">
                        <span class="name"><c:out value="${slot.item.visibleName}"/></span>
                    </div>
                    <c:if test="${slot.item.type == moneyItem}">
                        <s:url value="/me/inventory/{nameInventory}/sell/{nameItem}/{numberAccount}" var="form_url">
                            <s:param name="nameInventory" value="${names.get(0).name}"/>
                            <s:param name="nameItem" value="${slot.item.name}"/>
                            <s:param name="numberAccount" value="${accounts.get(slot.item.cost.type).get(0).number}"/>
                        </s:url>
                        <sf:form action="${form_url}" method="POST" enctype="multipart/form-data" modelAttribute="sell">
                            <sf:errors element="div" cssClass="errors"/>
                            <sf:label path="quantity" type="quantity"
                                      cssErrorClass="error">Количевство</sf:label>:
                            <sf:input path="quantity" type="range" min="0" max="${slot.quantity}" class="slider"/>
                            <form:button>Продать</form:button>
                        </sf:form>
                    </c:if>
                </div>
            </c:if>
        </c:forEach>
    </div>
</main>
<c:import url="../includes/footer.jsp"/>
</body>
</html>
