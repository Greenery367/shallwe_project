// 버튼 중복 클릭 방지(디바운싱)
function debounce(func, timeout = 300) {
    let timer;
    return (...args) => {
        if (timer) {
            clearTimeout(timer); // 이전 타이머 제거
        }
        timer = setTimeout(() => {
            func.apply(this, args); // 지정된 시간이 지난 후 함수 호출
        }, timeout);
    };
}

form.addEventListener('keydown', function(event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 엔터키로 폼 제출 막기
        }
    });
// 이메일 존재 체크 함수
function checkEmail(callback) {
    var emailBody = document.querySelector('#emailBody').value;
    var emailDomain = document.querySelector('#emailDomain').value;
    var email = emailBody + '@' + emailDomain;

    if (emailBody.trim() === '') {
        alert('이메일을 입력하세요.');
        return;
    }
    if (emailDomain.trim() === '') {
        alert('도메인을 입력하세요.');
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/user/check-email', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const response = JSON.parse(xhr.responseText);
            const result = response.result;

            if (result === 'unavailable') {
                callback();
            } else if (result === 'available') {
                alert('등록되지 않은 이메일입니다.');
            }
        } else {
            alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
        }
    };

    xhr.send('email=' + encodeURIComponent(email));
}
// 이메일 존재 체크 후 폼 제출
function handleEmailAndSubmit() {
    checkEmail(function() {
        document.getElementById('login-form').submit(); // 이메일이 사용 가능하면 폼 제출
    });
}

// DOMContentLoaded 이벤트에 대한 처리
document.addEventListener('DOMContentLoaded', function() {
    const form = document.getElementById('login-form');

    if (form) {
        form.addEventListener('submit', function(event) {
            event.preventDefault(); // 기본 폼 제출 방지
            handleEmailAndSubmit(); // 이메일 체크 후 폼 제출
        });
    }
});

