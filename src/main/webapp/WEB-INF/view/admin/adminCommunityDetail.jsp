<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
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

        .post-details {
            margin-bottom: 20px;
        }

        .post-details p {
            margin: 5px 0;
        }

        .post-content {
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 4px;
            padding: 15px;
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
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시글 디테일</title>
</head>
<body>
    <header>
        <h1>게시판 관리</h1>
        <nav>
            <ul>
                <li><a href="#">해당 이용자 모든 게시물 보기</a></li>
                <li><a href="#">이용자 정보</a></li>
            </ul>
        </nav>
    </header>

    <main>
        <section class="board">
            <h2>${boardDetail.title}</h2>
            <div class="post-details">
                <p><strong>글 id :</strong> ${boardDetail.id}</p>
                <p><strong>카테고리 이름(id) :</strong> ${boardDetail.categoryId}</p>
                <p><strong>게시글 작성자 :</strong> ${boardDetail.authorId}</p>
                <p><strong>조회수 :</strong> ${boardDetail.viewNum}</p>
                <p><strong>좋아요 :</strong> ${boardDetail.good}</p>
                <p><strong>작성시간 :</strong> ${boardDetail.createdAt}</p>
            </div>
            <div class="post-content">
                <p>
                    ${boardDetail.content}
                </p>
            </div>
            <div>
                <p>댓글리스트</p> 
                <table class="table">
					<thead>
						<tr>
							<th>댓글 작성자id</th>
							<th>내용</th>
							<th>작성시간</th>
							<th>관리</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="comment" items="${boardCommentList}">
							<tr>
								<td>${comment.author}</td>
								<td>${comment.content}</td>
								<td>${comment.createdAt}</td>
								<td>블라인드 /
									<form action="/admin/community/detail/delete-comment" method="post">
									<input type="hidden" id="author" name="author" value="${comment.author}">
									<input type="hidden" id="content" name="content" value="${comment.content}">
									<input type="hidden" id="createdAt" name="createdAt" value="${comment.createdAt}">
									<input type="hidden" id="id" name="id" value="${comment.id}">
									<input type="hidden" id="postId" name="postId" value="${comment.postId}">
									<button type="submit">삭제</button>
									</form>
								</td>
							</tr>
						</c:forEach>
						</tbody>
					</table>
            </div>
        </section>
    </main>

    <footer>
        <p>&copy; footer</p>
    </footer>
</body>
</html>
