<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/view/layout/header.jsp" %>
<meta charset="UTF-8">
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/detailFAQ.css">
<title>게시글 상세 보기</title>
</head>
<body>
	<main class="content-wrapper">
		<!-- 제목 섹션 -->
		<section class="post-header">
			<h1>${FAQ.title}</h1>
		</section>

		<!-- 작성자와 작성시간 -->
		<section class="meta-info">
			<span>작성자: ${FAQ.writer}</span>
			<span>작성시간: ${FAQ.createdAt}</span>
		</section>

		<!-- 구분선 -->
		<div class="separator"></div>

		<!-- 본문 내용 -->
		<section class="post-content">
			<pre>${FAQ.content}</pre>
		</section>

		<!-- 숨겨진 ID 필드 추가 -->
		<input type="hidden" id="id" value="${FAQ.id}" />

		<!-- 버튼 영역 -->
		<div class="btn-area">
			<% int status = Integer.parseInt(request.getParameter("status")); %>
			<c:if test="${status == 0}">
				<button type="button" class="update-button">수정</button>
				
				<!-- 삭제 버튼 (폼 전송 방식) -->
				<form id="deleteForm" action="/cs/delete" method="GET">
					<input type="hidden" name="id" value="${FAQ.id}" />
					<button class="btn-btn"type="button" class="delete-button" onclick="removeCheck()">삭제</button>
				</form>
			</c:if>
		</div>
	</main>

	<script type="text/javascript">
		document.addEventListener('DOMContentLoaded', function () {
			// ID 값이 페이지에 있는지 확인 후 가져옴
			const id = document.getElementById('id') ? document.getElementById('id').value : null;

			if (id) {
				const updateButton = document.querySelector('.update-button');
				updateButton.addEventListener('click', function () {
					window.location.href = "/cs/update?id=" + id;
				});
			} else {
				console.error('ID 값이 정의되지 않았습니다.');
			}
		});
	</script>

	<script>
	function removeCheck() {
	    if (confirm("정말 삭제하시겠습니까?")) {  
	        // 확인을 누르면 해당 폼 제출
	        document.getElementById("deleteForm").submit();
	    } else {
	        return false;  // 취소를 누르면 아무런 동작도 하지 않음
	    }
	}
	</script>

	<%@ include file="/WEB-INF/view/layout/footer.jsp" %>
</body>
</html>
