<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="../includes/meta.jsp"/>
    <c:import url="../includes/css.jsp"/>
    <s:url value="${fileServerPath}/css" var="css_url"/>
    <s:url value="${fileServerPath}/js" var="js_url"/>
    <link href="${css_url}/item.css" rel="stylesheet"/>
    <link href="${css_url}/slots.css" rel="stylesheet"/>
    <link href="${css_url}/inventory.css" rel="stylesheet"/>
    <link href="${css_url}/button.css" rel="stylesheet"/>
    <script type="text/javascript" src="${js_url}/change.js"></script>
    <title>Инвентарь</title>
</head>
<body>
<div class="site-wrapper">
    <c:import url="../includes/header.jsp"/>
    <main>
        <div class="slots">
            <c:forEach items="${inventory.slots}" var="slot" varStatus="loop">
            <c:if test="${slot.quantity > 0}">
            <div class="slot">
                <div class="item <c:out value="${slot.item.type}"/>">
                    <span class="name"><c:out value="${slot.item.visibleName}"/></span>
                    <span class="quantity"><c:out value="x${slot.quantity}"/></span>
                </div>
                <c:if test="${slot.item.type == moneyItem}">
                    <s:url value="/me/inventory/{nameInventory}/sell/{nameItem}/{numberAccount}" var="form_url">
                        <s:param name="nameInventory" value="${names.get(0).name}"/>
                        <s:param name="nameItem" value="${slot.item.name}"/>
                        <s:param name="numberAccount"
                                 value="${accounts.get(slot.item.cost.type).get(0).number}"/>
                    </s:url>
                    <sf:form id="form-${loop.index}" action="${form_url}" method="POST"
                             enctype="multipart/form-data" modelAttribute="sell">

                        <c:set var="sliderId" value="slider-quantity-${loop.index}" scope="request"/>
                        <c:set var="buttonId" value="button-quantity-${loop.index}" scope="request"/>
                        <c:set var="numberId" value="number-quantity-${loop.index}" scope="request"/>
                        <s:url value="${fileServerPath}/{typePath}" var="icon_url">
                            <s:param name="typePath" value="${slot.item.cost.type.pathToIcon}"/>
                        </s:url>
                        <div>
                            <sf:errors element="div" cssClass="errors"/>
                            <sf:label for="${numberId}" path="quantity" type="quantity"
                                      cssErrorClass="error">Количество</sf:label>:
                            <sf:input id="${numberId}" path="quantity" type="number" min="1"
                                      max="${slot.quantity}" placeholder="${slot.quantity}" value="" required="true"
                                      oninput="changeValues(this.value, '${sliderId}', '${buttonId}')"/>
                        </div>
                        <input id="<c:out value="${sliderId}"/>" name="number"
                               min="0" max="<c:out value="${slot.quantity}"/>" type="range" class="slider"
                               value="0" oninput="changeValues(this.value, '<c:out value="${numberId}"/>',
                                '<c:out value="${buttonId}"/>')">
                        <form:button name="button-ui">
                            <div class="cost"><span data-multiplicator="${slot.item.cost.value}"
                                                    data-min="1" data-max="<c:out value="${slot.quantity}"/>"
                                                    id="<c:out value="${buttonId}"/>"
                                                    class="value"><fmt:formatNumber value="${slot.item.cost.value}"
                                                                                    minFractionDigits="0"/></span>
                                <img src="${icon_url}" alt="${slot.item.cost.type.name}"></div>
                        </form:button>
                    </sf:form>
                </c:if>
            </div>
            </c:if>
            </c:forEach>
    </main>
    <c:import url="../includes/footer.jsp"/>
</div>
</body>
</html>
