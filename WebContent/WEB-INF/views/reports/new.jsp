<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>日報新規登録ページ</h2>

        <form method="post" action="<c:url value='/reports/create' />">
            <c:import url="_form.jsp"/>
        </form>

        <p><a href="<c:url value='/reports/index'/>">日報一覧に戻る</a></p>
    </c:param>
</c:import>