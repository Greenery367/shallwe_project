document.addEventListener('DOMContentLoaded', function() {
    document.querySelector('.id-btn').addEventListener('click', function(event) {
        event.preventDefault(); // 폼 제출 방지
        checkId(); // 아이디 중복 체크 함수 호출
    });
});

function checkId() {
    // 아이디 입력 필드의 값 가져오기
    const id = document.getElementById('id').value;

    // 아이디가 빈 경우 처리
    if (id.trim() === '') {
        alert('아이디를 입력해주세요.');
        return;
    }

    // AJAX 요청 생성
    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/user/checkId', true); // '/checkId'는 서버에서 아이디 중복 여부를 확인하는 엔드포인트
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    // 요청 처리
    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const response = JSON.parse(xhr.responseText);
            const result = response.result;

            if (result === 'available') {
                alert('사용 가능한 아이디입니다.');
                document.querySelector('.id-check').style.color = 'green';
                document.querySelector('.id-check').textContent = '사용 가능한 아이디입니다.';
                document.querySelector('.id-input').readOnly = true;
            } else if (result === 'unavailable') {
                alert('이미 사용 중인 아이디입니다.');
                document.querySelector('.id-check').style.color = 'red';
                document.querySelector('.id-check').textContent = '이미 사용 중인 아이디입니다.';
                document.querySelector('.id-input').focus();
            }
        } else {
            alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
        }
    };

    // 요청 보내기
    xhr.send('id=' + encodeURIComponent(id));
}