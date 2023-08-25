<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<header>
    <nav>
        <ul>
            <li id="title">
                <s:url value="/" var="home_url"/>
                <a href="${home_url}">Twister</a>
            </li>
            <li id="about">
                <ul class="accounts">
                    <s:url value="/logout" var="logout_url"/>
                    <c:choose>
                        <c:when test="${user == null}">
                            <s:url value="/login" var="login_url"/>
                            <li class="entrance"><a href="${login_url}">Войти</a></li>
                        </c:when>
                        <c:when test="${general.name == null}">
                            <s:url value="/setting" var="setting_url"/>
                            <li class="setting"><a href="${setting_url}">Требуется настройка аккаунта</a></li>
                            <li class="exit"><a href="${logout_url}">Выйти</a></li>
                        </c:when>
                        <c:otherwise>
                            <s:url value="/me" var="me_url"/>
                            <s:url value="/setting" var="setting_url"/>
                            <s:url value="/transfers" var="transfers_url"/>
                            <li class="nickname"><span><c:out value="${general.nickname}"/></span>
                                <ul class="dropdown">
                                    <li><a href="${me_url}">Обо мне</a></li>
                                    <li><a href="${setting_url}">Настройки</a></li>
                                    <c:forEach items="${names}" var="inventoryName">
                                        <s:url value="/me/inventory/{inventoryName}" var="inventory_url">
                                            <s:param name="inventoryName" value="${inventoryName.name}"/>
                                        </s:url>
                                        <li><a href="${inventory_url}">Инвентарь</a></li>
                                    </c:forEach>
                                    <li><a href="${transfers_url}">Переводы</a></li>
                                </ul>
                            </li>
                            <c:forEach items="${accounts.values()}" var="account">
                                <s:url value="${fileServerPath}/{typePath}" var="icon_url">
                                    <s:param name="typePath" value="${account.get(0).amount.type.pathToIcon}"/>
                                </s:url>
                                <li id="account_<c:out value="${account.get(0).number}"/>">
                                    <span class="value"><fmt:formatNumber value="${account.get(0).amount.value}"
                                                                          minFractionDigits="0"/></span>
                                    <span>
                                        <img src="${icon_url}" alt="${account.get(0).amount.type.name}">
                                    </span>
                                </li>
                            </c:forEach>
                            <li class="exit"><a href="${logout_url}">Выйти</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </li>
        </ul>
    </nav>
</header>