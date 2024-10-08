<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
body {
	width: 100%;
	height: 100vh;
	display: flex;
	justify-content: center;
	align-items: center;
}

.title {
	height: 300px;
	display: flex;
	align-items: center;
	flex-direction: column;
}

input {
	margin-top: 8px;
}
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="title">
	<form action = "sign-in" method="POST" enctype="multipart/form-data">
	<br>
	아이디를 입력해주세요
	<input type="number" name="id">
	닉네임을 입력해주세요
	<br>
	<input type="text" name="nickname">
	<br>
	프로필 사진을 등록해주세요
	<br>
	<input type="file" name="fileName">
	<br>
	MBTI를 등록해주세요 (숫자로 입력)
	<br>
	<input type="number" name="mbti">
	<br>
	<input type="submit" value="채팅방 입장">
	<br>
	<p>mbti 목록</p>
	<p>1 :	('SQRT','용사','팀원들과의 소통을중시하고 길잡이가 되어 팀원들을 이끄는걸 좋아한다 하지만 승부의 결과에 연연하지 않고 결과보단 내용을 중시 재미가 있었다면 OK!!'),</p>
	<p>2 :	('SQRC','장군 ','팀원들을 조율하고 직접 오더를 내리는것을 좋아하며 남들과 똑같은 방식으로 이기는것보다 자신의 팀만의 색깔로 이기는것이 중요함!!'),</p>
	<p>3 :	('SQET','음유시인','게임의 승부에 크게 욕심이 없고 팀을 책임지고 이끄는것보단 게임 자체를 즐기는것에 목적을 의의를 두는 자유로운 영혼!!'),</p>
	<p>4 :	('SQEC','예술가','게임에서 비주류 픽들로 이기고싶은 욕망이있으며 누구나 다 사용하는 캐릭터,아이템으로 이기는것은 가치가 없음!!'),</p>
	<p>5 :	('SMRT','책략가','최대한 게임을 이기는데에 집중하지만 결과에는 연연하지않음 내용을 바탕으로 다음에는 이기기위한 연구를 다시 시작함!!'),</p>
	<p>6 :	('SMRC','마왕','이기기위해 모든 최선을 다하며 팀원들을 승리의 길로 이끈다!!'),</p>
	<p>7 :	('SMET','상인','계산적이며 게임의 정보를 공부하는것을 좋아함 하지만 승부에 연연하지는 않는 지식을 탐구하는자!!'),</p>
	<p>8 :	('SMEC','광전사','승리를 위해 수단과 방법을 가리지 않으며 필요하다면 단독행동도 서슴치않는 망나니!!'),</p>
	<p>9 :	('NQRT','탐험가','혼자서 게임 곳곳의 숨겨진 요소들을 즐기는 걸 좋아하는 사람. 승패에 연연하지 않고 게임 자체를 적극적으로 즐기길 좋아한다!!'),</p>
	<p>10 :('NQRC','용병','혼자서 게임을 즐기지만, 이겨야 할 때는 사람들을 이끌기도 하는 적극적인 사람. 플레이 스타일이 확고하지만 경쟁 상대가 생기면 리더쉽을 보여준다!!'),</p>
	<p>11 :('NQET','농부','다른 사람들이 어떤 걸 하든 무슨 상관? 내가 하고 싶은 대로 유유자적 플레이하는 낭만가 스타일!!'),</p>
	<p>12 :('NQEC','고고학자','승부욕이 남다른 사람! 게임 속에 숨겨진 콘텐츠와 떡밥들을 찾아내길 좋아하는 탐구가! 남들이 모르고 있던 이스터에그를 발견하기도 한다!!'),</p>
	<p>13 :('NMRT','도적','홀로 빠르게 게임 컨텐츠를 정복하는 사람! 효율을 추구하며, 누구보다 빠르게 공략법을 찾아낸다!'),</p>
	<p>14 :('NMRC','길드장','누구보다 효율을 추구하는 조용한 리더! 효율적으로 업무를 분담하며 팀플레이를 해결해낸다!'),</p>
	<p>15 :('NMET','메카닉','효율적인 방식으로 원하는 콘텐츠들을 정복해나가는 사람! 효율적인 모습들은 내가 좋아하는 컨텐츠들을 더욱 빠르고, 잘 즐기기 위해서이다!'),</p>
	<p>16 :('NMEC','흑마법사','승리를 위해서라면 어떤 수단과 방법을 가리지 않는다! 혼자서 몇 인분의 일을 해내는 서포터! ');</p>
	</form>
	</div>
</body>
</html>