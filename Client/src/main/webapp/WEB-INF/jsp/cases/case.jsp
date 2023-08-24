<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<html>
<head>
    <c:import url="../includes/meta.jsp"/>
    <c:import url="../includes/css.jsp"/>
    <s:url value="${fileServerPath}/css" var="css_url"/>
    <s:url value="${fileServerPath}/js" var="js_url"/>
    <link href="${css_url}/case.css" rel="stylesheet"/>
    <link href="${css_url}/item.css" rel="stylesheet"/>
    <script type="text/javascript" src="${js_url}/jquery.min.js"></script>
    <script type="text/javascript" src="${js_url}/jquery-ui.min.js"></script>
    <script type="text/javascript" src="${js_url}/roulette.js"></script>
    <script type="text/javascript" src="${js_url}/twist.js"></script>
    <script type="text/javascript" src="${js_url}/format.js"></script>
    <title>Twister</title>
</head>

<body>
<div class="site-wrapper">
    <c:import url="../includes/header.jsp"/>
    <main>
        <div class="case-form">
            <div class="case-window">
                <div class="case-line">
                    <c:forEach items="${selectedCase.items}" var="slot" varStatus="loop">
                        <div class="slot" id="<c:out value="${slot.name}"/>" data-index="${loop.index}">
                            <div class="item <c:out value="${slot.item.type}"/>">
                                <span class="name"><c:out value="${slot.item.visibleName}"/></span>
                                <c:if test="${slot.quantity > 1}">
                                    <span class="quantity"><c:out value="x${slot.quantity}"/></span>
                                </c:if>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="buttons">
                <s:url value="${fileServerPath}/{typePath}" var="icon_url">
                    <s:param name="typePath" value="${selectedCase.price.type.pathToIcon}"/>
                </s:url>
                <button id="twist-button" class="twist"<c:choose>
                    <c:when test="${general.name == null}"> disabled</c:when>
                    <c:otherwise>data-account-number="<c:out
                            value="${accounts.get(selectedCase.price.type).get(0).number}"/>"
                        data-case-name="<c:out value="${selectedCase.name}"/>"</c:otherwise>
                </c:choose>>
                <span class="price">
                    <span class="value"><fmt:formatNumber value="${selectedCase.price.value}"
                                                          minFractionDigits="0"/></span>
                        <img src="${icon_url}" id="money" class="${selectedCase.price.type.name}"
                             alt="${selectedCase.price.type.name}">
                </span>
                </button>
                <button class="stop" disabled>стоп</button>
            </div>
        </div>
        <c:if test="${selectedCase.cooldown != null and selectedCase.cooldown.seconds > 0}">
            <script>
                let start = Math.floor(new Date().getTime() / 1000);
                let countDown = start + <c:out value="${selectedCase.cooldown.seconds}"/>;
                let id = "twist-button";
                let inner = document.getElementById(id).innerHTML;
                if (countDown - start > 0) {
                    document.getElementById(id).innerHTML = formatSeconds(countDown - start);
                    document.getElementById(id).setAttribute("disabled", "true");
                }
                let x = setInterval(function () {
                    let now = Math.floor(new Date().getTime() / 1000);
                    let distance = countDown - now;
                    if (distance > 0) {
                        document.getElementById(id).innerHTML = formatSeconds(distance);
                    } else {
                        document.getElementById(id).innerHTML = inner;
                        document.getElementById(id).removeAttribute("disabled");
                        clearInterval(x);
                    }
                }, 1000);
            </script>
        </c:if>
    </main>
    <c:import url="../includes/footer.jsp"/>
</div>
</body>
</html>