<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>    
<link rel="stylesheet" href="/css/findFriend.css">

<!-- 메인 컨테이너 -->
<div class="main-container">
    <!-- 서브헤더 -->
    <div class="sub-header">
        <jsp:include page="/WEB-INF/view/layout/friendHeader.jsp"/>
    </div>

    <!-- 메인 콘텐츠 -->
    <div class="main-content">
        <!-- 검색 박스 -->
        <div class="search-box">
            <form action="/friends/findUser" method="POST">
                <input type="text" placeholder="이름으로 검색" name="name">
                <input type="hidden" value="1" name="pageNum">
            </form>
        </div>

        <!-- 검색 결과 -->
        <div class="search-result">
            <c:forEach var="user" items="${userList}">
                <div class="user-profile" onclick="window.open('http://localhost:8080/chat/profileInfo?userId=${user.userId}')">
                        <c:choose>
                            <c:when test="${user.uploadFileName == null}">
                                <img src="/static/images/defaultProfile.jpeg">
                            </c:when>
                            <c:otherwise>
                                <img src="/images/${user.uploadFileName}" alt="Profile Picture">
                            </c:otherwise>
                        </c:choose>
                    <div class="user-info">
                        <div class="user-name">
                            <span>${user.nickname}</span>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>

        <!-- 페이지 네비게이션 -->
        <div class="page">
            <c:if test="${current} != 1">
                <form action="/friends/findUser?name=${name}&page=${current - 1}" method="post">
                    <button class="previous">이전</button>
                </form>
            </c:if>

            <c:forEach var="i" begin="1" end="${pageSize}">
                <ul class="pagination">
                    <li><a href="javascript:pagePost(${i})"
                           <c:if test="${current == i}">class="current-page"</c:if>>
                           ${i}</a>
                    </li>
                </ul>
            </c:forEach>

            <c:if test="${current} != ${pageSize}">
                <form action="/friends/findUser?name=${name}&page=${current + 1}" method="post">
                    <button class="next">다음</button>
                </form>
            </c:if>
        </div>
    </div>
</div>

<script>
    function pagePost(page) {
        let form = document.createElement('form');
        form.setAttribute('method', 'post');
        form.setAttribute('action', '/friends/findUser');
        
        let pageNum = document.createElement('input');
        pageNum.setAttribute('type', 'hidden');
        pageNum.setAttribute('name', 'pageNum');
        pageNum.setAttribute('value', page);
        
        let name = document.createElement('input');
        name.setAttribute('type', 'hidden');
        name.setAttribute('name', 'name');
        name.setAttribute('value', '${name}');
        
        form.appendChild(pageNum);
        form.appendChild(name);
        document.body.appendChild(form);
        form.submit();
    }
</script>

<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
