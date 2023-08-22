<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div class="paginator_full">
    <nav>
        <ul class="pagination">
            <c:choose>
                <c:when test="${page.page <= 1}">
                    <li class="page-item disabled">
                        <span class="page-link">&lt;</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <s:url value="${pageContext.request.contextPath}" var="page_url">
                            <s:param name="page" value="${page.page-1}"/>
                        </s:url>
                        <a class="page-link" href="${page_url}">&lt;</a>
                    </li>
                </c:otherwise>
            </c:choose>
            <c:forEach begin="1" end="${startQ}" var="i">
                <c:if test="${i <= page.totalPages}">
                    <li class="page-item<c:if test="${page.page == i}"> active</c:if>">
                        <s:url value="${pageContext.request.contextPath}" var="page_url">
                            <s:param name="page" value="${i}"/>
                        </s:url>
                        <a class="page-link" href="${page_url}"><c:out value="${i}"/></a>
                    </li>
                </c:if>
            </c:forEach>
            <c:if test="${page.page > startQ + windowQ2 + 1 and page.totalPages > endQ + startQ}">
                <li class="page-item disabled">
                    <span class="page-link">...</span>
                </li>
            </c:if>
            <c:if test="${page.totalPages-endQ > 0}">
                <%--                <c:out value="${Math.max(startQ + 1, page.page-windowQ2)}-${Math.min(page.totalPages-endQ, page.page+windowQ2)}"/>--%>
                <c:forEach begin="${Math.max(startQ + 1, page.page-windowQ2)}"
                           end="${Math.min(page.totalPages-endQ, page.page+windowQ2)}"
                           var="i">
                    <c:if test="${i <= page.totalPages}">
                        <li class="page-item<c:if test="${page.page == i}"> active</c:if>">
                            <s:url value="${pageContext.request.contextPath}" var="page_url">
                                <s:param name="page" value="${i}"/>
                            </s:url>
                            <a class="page-link" href="${page_url}"><c:out value="${i}"/></a>
                        </li>
                    </c:if>
                </c:forEach>

                <c:if test="${page.totalPages - (endQ + windowQ2) > page.page and page.totalPages > endQ + startQ}">
                    <li class="page-item disabled">
                        <span class="page-link">...</span>
                    </li>
                </c:if>
                <c:forEach
                        begin="${Math.max(startQ + 1, page.totalPages-endQ+1)}"
                        end="${page.totalPages}" var="i">
                    <c:if test="${i <= page.totalPages}">
                        <li class="page-item<c:if test="${page.page == i}"> active</c:if>">
                            <s:url value="${pageContext.request.contextPath}" var="page_url">
                                <s:param name="page" value="${i}"/>
                            </s:url>
                            <a class="page-link" href="${page_url}"><c:out value="${i}"/></a>
                        </li>
                    </c:if>
                </c:forEach>
            </c:if>
            <c:choose>
                <c:when test="${page.page >= page.totalPages}">
                    <li class="page-item disabled">
                        <span class="page-link">&gt;</span>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <s:url value="${pageContext.request.contextPath}" var="page_url">
                            <s:param name="page" value="${page.page+1}"/>
                        </s:url>
                        <a class="page-link" href="${page_url}">&gt;</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </ul>
    </nav>
</div>