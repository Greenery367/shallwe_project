<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
	/* 기본 스타일 설정 */
body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    margin: 0;
    padding: 0;
}

header {
    background-color: #333;
    color: #fff;
    padding: 10px 20px;
    text-align: center;
}

header h1 {
    margin: 0;
}

nav ul {
    list-style: none;
    padding: 0;
}

nav ul li {
    display: inline;
    margin-right: 10px;
}

nav ul li a {
    color: #fff;
    text-decoration: none;
}

nav ul li a:hover {
    text-decoration: underline;
}

main {
    padding: 20px;
}

.board {
    background-color: #fff;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
    padding: 20px;
}

.board h2 {
    margin-top: 0;
}

table {
    width: 100%;
    border-collapse: collapse;
}

table, th, td {
    border: 1px solid #ddd;
}

th, td {
    padding: 10px;
    text-align: left;
}

th {
    background-color: #f4f4f4;
}

td a {
    text-decoration: none;
    color: #333;
}

td a:hover {
    text-decoration: underline;
}

footer {
    background-color: #333;
    color: #fff;
    text-align: center;
    padding: 10px;
    position: absolute;
    bottom: 0;
    width: 100%;
}
	
</style>
<meta charset="UTF-8">
<title>관리자 - 게시판 / 댓글 관리</title>
</head>
<body>
    <header>
        <h1>관리자 게시판</h1>
        <nav>
            <ul>
                <li><a href="#">홈</a></li>
                <li><a href="#">사용자 관리</a></li>
                <li><a href="#">게시글 관리</a></li>
                <li><a href="#">로그아웃</a></li>
            </ul>
        </nav>
    </header>
    
    <main>
        <section class="board">
            <h2>게시글 목록</h2>
            <table>
                <thead>
                    <tr>
                        <th>번호</th>
                        <th>제목</th>
                        <th>작성자</th>
                        <th>작성일</th>
                        <th>조회수</th>
                    </tr>
                </thead>
                <tbody>
                    <!-- JSP 코드로 데이터가 동적으로 삽입됩니다. -->
                    <tr>
                        <td>1</td>
                        <td><a href="#">게시글 제목</a></td>
                        <td>작성자</td>
                        <td>2024-09-11</td>
                        <td>123</td>
                    </tr>
                    <!-- 추가 게시글은 서버에서 동적으로 삽입됩니다. -->
                </tbody>
            </table>
        </section>
    </main>
    
    <footer>
        <p>&copy; 2024 관리자 게시판. 모든 권리 보유.</p>
    </footer>
</body>
</html>