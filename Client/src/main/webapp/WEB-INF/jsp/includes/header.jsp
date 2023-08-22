<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header>
    <article id="title">
        <s:url value="/" var="home_url"/>
        <a href="${home_url}">Twister</a>
    </article>
    <article id="about">
        <c:choose>
            <c:when test="${user == null}">
                <s:url value="/login" var="login_url"/>
                <a href="${login_url}">Войти</a>
            </c:when>
            <c:when test="${general.name == null}">
                <s:url value="/setting" var="setting_url"/>
                <a href="${setting_url}">Требуется настройка аккаунта</a>
            </c:when>
            <c:otherwise>
                <s:url value="/logout" var="logout_url"/>
                <s:url value="/me" var="me_url"/>
                <a href="${me_url}"><c:out value="${general.nickname}"/></a>
                <ul class="accounts">
                    <c:forEach items="${accounts.values()}" var="account">
                        <s:url value="${fileServerPath}/{typePath}" var="icon_url">
                            <s:param name="typePath" value="${account.get(0).amount.type.pathToIcon}"/>
                        </s:url>
                        <li id="account_<c:out value="${account.get(0).number}"/>">
                            <div class="value"><fmt:formatNumber value="${account.get(0).amount.value}" minFractionDigits="0"/></div>
                            <div>
                                <img src="${icon_url}" id="money" class="${account.get(0).amount.type.name}"
                                     alt="${account.get(0).amount.type.name}">
                            </div>
                        </li>
                    </c:forEach>
                </ul>
                <a href="${logout_url}">Выйти</a>
            </c:otherwise>
        </c:choose>
    </article>
</header>