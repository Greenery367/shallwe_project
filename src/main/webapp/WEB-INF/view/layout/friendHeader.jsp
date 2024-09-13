<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
	<div class="friends_nav">
		<a class="all_friends" href="friends/all">
			<span class="title">나의 친구</span>
			<span id="friends_ct" class="count">${count}</span>
		</a>
		<a class="add_friends" href="friends/findUser">
			<span class="title">친구 추가</span>
		</a>
		<a class="wait_friends" href="friends/wait">
			<span class="title">대기 중인 초대</span>
		</a>
	</div>
