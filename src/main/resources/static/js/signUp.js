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

// 이메일 인증 요청 함수
function sendEmailVerification() {
    var emailBody = document.querySelector('#emailBody').value;
    var emailDomain = document.querySelector('#emailDomain').value;
    var email = emailBody + '@' + emailDomain;
    var emailRequestDto = { email: email };

    $.ajax({
        type: "POST",
        url: "/signup/email",
        contentType: "application/json",
        data: JSON.stringify(emailRequestDto),
        success: function (code) {
            if (code) {
                alert("입력하신 메일로 인증번호가 전송되었습니다.");
            } else {
                alert("인증번호를 받을 수 없습니다.");
            }
        },
        error: function () {
            alert("오류가 발생했습니다. 잠시 후 다시 시도해주세요.");
        }
    });
}

// 이메일 인증 번호 검증 함수
function verifyEmailAuthCode() {
    var emailBody = document.querySelector('#emailBody').value;
    var emailDomain = document.querySelector('#emailDomain').value;
    var email = emailBody + '@' + emailDomain;
    var authNum = document.querySelector('#authNum').value;
    var emailCheckDto = { email: email, authNum: authNum };

    $.ajax({
        type: "POST",
        url: "/signup/emailAuth",
        contentType: "application/json",
        data: JSON.stringify(emailCheckDto),
        success: function (message) {
            if (message) {
                alert("이메일 인증에 성공하였습니다.");
            } else {
                alert("인증에 실패하였습니다.");
            }
        },
        error: function(xhr, status, error) {
            if (xhr.status == 500) {
                alert("잘못된 인증번호이거나 시간 초과로 인증번호가 만료되었습니다.");
            } else {
                alert("오류가 발생했습니다: " + status);
            }
        }
    });
}

// 디바운스를 적용한 아이디 중복 체크
const debouncedCheckId = debounce(checkId, 300);

// 디바운스를 적용한 닉네임 중복 체크
const debouncedCheckNickname = debounce(checkNickname, 300);

// ID 중복 체크 함수
function checkId() {
    const id = document.getElementById('id').value;

    if (id.trim() === '') {
        alert('아이디를 입력해주세요.');
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/user/check-id', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

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

    xhr.send('id=' + encodeURIComponent(id));
}

// 닉네임 중복 체크 함수
function checkNickname() {
    const nickname = document.getElementById('nickname').value;

    if (nickname.trim() === '') {
        alert('닉네임을 입력해주세요.');
        return;
    }

    const xhr = new XMLHttpRequest();
    xhr.open('POST', '/user/check-nickname', true);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');

    xhr.onload = function() {
        if (xhr.status >= 200 && xhr.status < 300) {
            const response = JSON.parse(xhr.responseText);
            const result = response.result;

            if (result === 'available') {
                alert('사용 가능한 닉네임입니다.');
                document.querySelector('.nickname-check').style.color = 'green';
                document.querySelector('.nickname-check').textContent = '사용 가능한 닉네임입니다.';
                document.querySelector('.nickname-input').readOnly = true;
            } else if (result === 'unavailable') {
                alert('이미 사용 중인 닉네임입니다.');
                document.querySelector('.nickname-check').style.color = 'red';
                document.querySelector('.nickname-check').textContent = '이미 사용 중인 닉네임입니다.';
                document.querySelector('.nickname-input').focus();
            }
        } else {
            alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
        }
    };

    xhr.send('nickname=' + encodeURIComponent(nickname));
}

// DOMContentLoaded 이벤트에 대한 처리
document.addEventListener('DOMContentLoaded', function() {
    // 버튼 클릭 이벤트 핸들러 등록
    const idBtn = document.querySelector('.id-btn');
    const nickBtn = document.querySelector('.nickname-btn'); // 수정된 클래스명
    const emailButton = document.querySelector('#email-btn'); 
    const authButton = document.querySelector('#email-auth-button'); 

    // 이벤트 리스너가 중복 등록되지 않도록 확인
    if (idBtn) {
        idBtn.removeEventListener('click', handleIdBtnClick); // 중복 리스너 방지
        idBtn.addEventListener('click', handleIdBtnClick);
    }

    if (nickBtn) {
        nickBtn.removeEventListener('click', handleNickBtnClick); // 중복 리스너 방지
        nickBtn.addEventListener('click', handleNickBtnClick);
    }

    if (emailButton) { // .length 체크를 제거하고 그냥 존재 확인
        emailButton.removeEventListener('click', sendEmailVerification); // 중복 리스너 방지
        emailButton.addEventListener('click', sendEmailVerification);
    }

    if (authButton) { // .length 체크를 제거하고 그냥 존재 확인
        authButton.removeEventListener('click', verifyEmailAuthCode); // 중복 리스너 방지
        authButton.addEventListener('click', verifyEmailAuthCode);
    }

    function handleIdBtnClick(event) {
        event.preventDefault(); // 폼 제출 방지
        debouncedCheckId(); // 디바운스된 아이디 중복 체크 함수 호출
    }

    function handleNickBtnClick(event) {
        event.preventDefault(); // 폼 제출 방지
        debouncedCheckNickname(); // 디바운스된 닉네임 중복 체크 함수 호출
    }
});
