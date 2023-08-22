<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ul class="cases">
    <c:forEach items="${page.values}" var="fCase">
        <s:url value="${fileServerPath}/{typePath}" var="icon_url">
            <s:param name="typePath" value="${fCase.price.type.pathToIcon}"/>
        </s:url>
        <li>
            <s:url value="/cases/{caseName}" var="case_url">
                <s:param name="caseName" value="${fCase.name}"/>
            </s:url>
            <a class="border-case" id="border-case-<c:out value="${fCase.name}"/>" href="${case_url}">
                <span class="case-name"><c:out value="${fCase.visibleName}"/></span>
                <span class="price">
                    <span class="value">Стоимость:<fmt:formatNumber value="${fCase.price.value}"
                                                                    minFractionDigits="0"/></span>
                        <img src="${icon_url}" id="money" class="${fCase.price.type.name}"
                             alt="${fCase.price.type.name}">
                </span>
                <span class="case-description"><c:out value="${fCase.description}"/></span>
                <span class="case-cooldown" id="case-cooldown-<c:out value="${fCase.name}"/>"></span>
            </a>
        </li>
    </c:forEach>
</ul>

<script>
    let start = Math.floor(new Date().getTime() / 1000);
    let elements = new Map();
    <c:forEach items="${page.values}" var="fCase">
    <c:if test="${fCase.cooldown != null}">
    elements.set("case-cooldown-<c:out value="${fCase.name}"/>", start + <c:out value="${fCase.cooldown.seconds}"/>);
    </c:if>
    </c:forEach>
    elements.forEach((countDown, id) => {
        let distance = countDown - start;
        if (distance > 0) {
            document.getElementById(id).innerHTML = formatSeconds(distance);
        }
    });
    let x = setInterval(function () {
        let now = Math.floor(new Date().getTime() / 1000);
        let f = true;
        elements.forEach((countDown, id) => {
            let distance = countDown - now;
            if (distance > 0) {
                document.getElementById(id).innerHTML = formatSeconds(distance);
                f = false;
            } else {
                document.getElementById(id).innerHTML = "";
            }
        });
        if (f) {
            elements.forEach((countDown, id) => {
                document.getElementById(id).innerHTML = "";
            });
            clearInterval(x);
        }
    }, 1000);
</script>
