<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:import url="../layout/app.jsp">
    <c:param name="content">
        <h2>フォローしている従業員</h2>
        <table id = "follow_list">
            <tbody>
                <tr>
                    <th class="follow_code">社員番号</th>
                    <th class="follow_name">氏名</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="follows" items="${ef }" varStatus="status">
                    <tr>
                        <td class="follow_code"><c:out value = "${follows.follow.code}"/></td>
                        <td class="follow_name"><c:out value = "${follows.follow.name}"/></td>
                         <td>
                            <c:choose>
                                <c:when test="${follows.follow.delete_flag == 1}">
                                    （削除済み）
                                </c:when>
                                <c:otherwise>
                                    <a href="<c:url value='/employees/show?id=${follows.follow.id}' />">詳細を表示</a>
                                </c:otherwise>
                            </c:choose>
                        </td>

                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <p><a href="<c:url value='/index.html' />">トップに戻る</a></p>
    </c:param>
</c:import>