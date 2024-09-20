<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ include file="/WEB-INF/view/layout/header.jsp"%>

<div style="display: flex;">
<%@ include file="/WEB-INF/view/layout/myPageMenu.jsp"%>


<div style="display: flex; flex-direction: column; align-items: center; justify-content: center; height: 30vh; width: 100vh; text-align: center; ">
    <h1>환전 불가</h1>
    <p>현재 환전 가능한 금액이 없거나, 서브몰 신청이 이루어지지 않았습니다.</p>
    <p>필요한 조건이 충족되지 않았습니다. 나중에 다시 시도해 주세요.</p>
</div>



</div>




<%@ include file="/WEB-INF/view/layout/footer.jsp"%>
