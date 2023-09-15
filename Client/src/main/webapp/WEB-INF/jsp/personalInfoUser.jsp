<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <c:import url="includes/css.jsp"/>
    <s:url value="${fileServerPath}/css" var="css_url">
    </s:url>
    <link href="${css_url}/personalInfoUser.css" rel="stylesheet" />
    <title>${general.nickname}</title>
</head>
<body>
<div class="site-wrapper">
    <c:import url="includes/header.jsp"/>
    <main>
        <h1>Обо мне</h1>
        <h2>Общее</h2>
        <ul class="general_<c:out value="${general.name}"/>">
            <li id="login">
                <div>
                    Логин:<c:out value="${user.preferredUsername}"/>
                </div>
            </li>
            <li id="nickname">
                <div>
                    Никнейм:<c:out value="${general.nickname}"/>
                </div>
            </li>
        </ul>
        <h2>Счета</h2>
        <ul class="accounts">
            <c:forEach items="${accounts.values()}" var="accountList">
                <c:forEach items="${accountList}" var="account">
                    <s:url value="${fileServerPath}/{typePath}" var="icon_url">
                        <s:param name="typePath" value="${account.amount.type.pathToIcon}"/>
                    </s:url>
                    <li id="account_<c:out value="${account.number}"/>">
                        <div class="number">Номер:
                            <s:url value="me/{number}" var="number_url">
                                <s:param name="number" value="${account.number}"/>
                            </s:url>
                            <a href="${number_url}"><c:out value="${account.number}"/></a>
                        </div>
                        <div class="amount">
                            <div class="value"><fmt:formatNumber value="${account.amount.value}"
                                                                      minFractionDigits="0"/></div>
                            <div>
                                <img src="${icon_url}" id="money" class="${account.amount.type.name}"
                                     alt="${account.amount.type.name}">
                            </div>
                        </div>

                    </li>
                </c:forEach>
            </c:forEach>
        </ul>
    </main>
    <c:import url="includes/footer.jsp"/>
</div>
</body>
</html>
