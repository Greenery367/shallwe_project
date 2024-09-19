<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ include file="/WEB-INF/view/layout/header.jsp" %>



<div>
	<b>문의글 작성하기</b>
</div>
<div>
	<form action="post-FAQ" method="post">
		<div>
			<input type="text" name="title" value="문의글 1">
		</div>
		<div>
			<textarea rows="5" name="content" id="editorTxt0"></textarea>
		</div>
		<button type="submit">작성 완료</button>
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
<%@ include file="/WEB-INF/view/layout/footer.jsp" %>