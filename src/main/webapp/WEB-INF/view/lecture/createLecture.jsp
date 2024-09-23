<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>	
<%@page import="com.example.demo.repository.model.User"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>강좌 개설 페이지</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/createLecture.css">
</head>
<body>
    <div class="container">
        <h1>강좌 개설</h1>
        <form action="/lecture/create-lecture" method="POST">
            <!-- 카테고리 선택 -->
            <div>
                <label for="categoryId">카테고리</label>
                <select id="categoryId" name="categoryId" required>
                    <option value="">카테고리를 선택하세요</option>
                    <c:forEach var="category" items="${categoryList}">
                        <option value="${category.id}">${category.gameName}</option>
                    </c:forEach>
                </select>
            </div>

            <!-- 인풋 필드 히든 타입으로 userId 를 컨트롤러에 전달 -->
            <div>
                <input type="hidden" id="authorId" name="authorId" value="${user.userId}">
            </div>

            <div>
                <label for="title">강의 제목</label>
                <input type="text" id="title" name="title" maxlength="20" required placeholder="강의제목 입력란(20자 이내)">
            </div>
            
            <div>
                <label for="subtitle">강의 부제</label>
                <input type="text" id="subtitle" name="subtitle" maxlength="30" required placeholder="간략한 소개 입력란(30자 제한)">
            </div>
            
            <div>
                <label for="content">강의 내용</label>
                <textarea id="editorTxt0" name="content" rows="5" required placeholder="강의 세부 내용을 서술해 주세요."></textarea>
            </div>

            <!-- 강의 가격 -->
            <div>
                <label for="price">강의 가격 (₩)</label>
                <input type="number" id="price" name="price">
            </div>

            <!-- 총 강의 횟수 -->
            <div>
                <label for="totalNum">총 강의 회차</label>
                <input type="number" id="totalNum" name="totalNum">
            </div>

            <!-- 강의 개설 버튼 -->
            <div class="openbtn">
                <button type="submit">강의 개설</button>
            </div>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.5.1.min.js" crossorigin="anonymous"></script>
    <script type="text/javascript" src="/lib/smarteditor2-2.9.2/workspace/js/service/HuskyEZCreator.js"></script>
    <script>
        let oEditors = [];
        smartEditor = function() {
            nhn.husky.EZCreator.createInIFrame({
                oAppRef: oEditors,
                elPlaceHolder: "editorTxt0", // textarea의 아이디와 동일하게 설정
                sSkinURI: "/lib/smarteditor2-2.9.2/dist/SmartEditor2Skin.html", // 프로젝트에 맞는 경로로 수정
                fCreator: "createSEditor2"
            });
        }

        $(document).ready(function() {
            // 스마트에디터 적용
            smartEditor();

            // 폼 제출 시 에디터 내용을 textarea로 반영
            $("form").submit(function(e) {
                oEditors.getById["editorTxt0"].exec("UPDATE_CONTENTS_FIELD", []);  // 에디터 내용을 textarea로 반영
            });
        });
    </script>
</body>
</html>
