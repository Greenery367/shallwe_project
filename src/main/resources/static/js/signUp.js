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
                document.querySelector('#emailBody').readOnly = true;
                document.querySelector('#emailDomain').readOnly = true;
                document.querySelector('#authNum').readOnly = true;
                document.querySelector('.email-check').style.color = 'green';
                document.querySelector('.email-check').textContent = '이메일 인증 완료.';
                
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
	const reg = /[\{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
	const blank_pattern = /[\s]/g;
	const engnum = /^[a-zA-Z0-9]+$/; 
	if(id.length<6){
		alert('아이디는 6글자 이상 12글자 이내여야 합니다.')
		return;
	}
	if(id.length>12){
		alert('아이디는 6글자 이상 12글자 이내여야 합니다.')
		return;
	}
	if(blank_pattern.test(id)){
		alert('아이디에는 공백이 사용될 수 없습니다.')
		return;
	}
	if(reg.test(id)){
		alert('아이디에는 특수문자를 사용할 수 없습니다.')
		return;
	}
	if(engnum.test(id) == false){
		alert('아이디에는 영문 대소문자, 숫자만 사용 가능합니다.')
		return;
	}	
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

<<<<<<< HEAD

=======
>>>>>>> JY-7
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

// 이메일 중복 체크 함수
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

            if (result === 'available') {
                callback(); // 이메일 중복 체크가 성공적으로 완료되면 콜백 함수 호출
            } else if (result === 'unavailable') {
                alert('이미 가입된 이메일입니다.');
            }
        } else {
            alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
        }
    };

    xhr.send('email=' + encodeURIComponent(email));
}

// 이메일 인증 버튼 클릭 핸들러
function handleEmailBtnClick(event) {
    event.preventDefault(); // 폼 제출 방지

    checkEmail(sendEmailVerification); // 이메일 중복 체크 후 이메일 인증 요청
}

// DOMContentLoaded 이벤트에 대한 처리
document.addEventListener('DOMContentLoaded', function() {
    // 버튼 클릭 이벤트 핸들러 등록
    const idBtn = document.querySelector('.id-btn');
    const nickBtn = document.querySelector('.nickname-btn');
    const emailButton = document.querySelector('#email-btn');
    const authButton = document.querySelector('#email-auth-button');

    // 이벤트 리스너가 중복 등록되지 않도록 확인
    if (idBtn) {
        idBtn.removeEventListener('click', handleIdBtnClick);
        idBtn.addEventListener('click', handleIdBtnClick);
    }

    if (nickBtn) {
        nickBtn.removeEventListener('click', handleNickBtnClick);
        nickBtn.addEventListener('click', handleNickBtnClick);
    }

    if (emailButton) {
        emailButton.removeEventListener('click', handleEmailBtnClick);
        emailButton.addEventListener('click', handleEmailBtnClick);
    }

    if (authButton) {
        authButton.removeEventListener('click', verifyEmailAuthCode);
        authButton.addEventListener('click', verifyEmailAuthCode);
    }

    function handleIdBtnClick(event) {
        event.preventDefault();
        debouncedCheckId();
    }

    function handleNickBtnClick(event) {
        event.preventDefault();
        debouncedCheckNickname();
    }
});

document.addEventListener('DOMContentLoaded', function() {
    const pwInput = document.querySelector("#password");
    const confirmPwInput = document.querySelector("#confirmPassword");
    const pwCheckbox1 = document.querySelector('#pw-show-checkbox-1');
    const pwCheckbox2 = document.querySelector('#pw-show-checkbox-2');

    if (pwCheckbox1) {
        pwCheckbox1.addEventListener('change', function() {
            if (this.checked) {
                pwInput.type = "text";
            } else {
                pwInput.type = "password";
            }
        });
    }

    if (pwCheckbox2) {
        pwCheckbox2.addEventListener('change', function() {
            if (this.checked) {
                confirmPwInput.type = "text";
            } else {
                confirmPwInput.type = "password";
            }
        });
    }
});

// 비밀번호 정규식
const passwordPattern = /^(?=.*[a-zA-Z])(?=.*[0-9]).{8,25}$/; 
// /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,15}$/;

// 비밀번호 확인 및 유효성 검사 함수
function validatePasswords() {
    const password = document.querySelector('#password').value;
    const confirmPassword = document.querySelector('#confirmPassword').value;
    const pwCheckLabel = document.querySelector('#pw-check');

    // 비밀번호 유효성 검사
    if (!passwordPattern.test(password)) {
        pwCheckLabel.textContent = '비밀번호는 8자 이상 15자 이하이며, 영문, 숫자, 특수문자를 포함해야 합니다.';
        pwCheckLabel.style.color = 'red';
        return;
    }

    // 비밀번호 확인
    if (password === confirmPassword) {
        pwCheckLabel.textContent = '비밀번호가 일치합니다.';
        pwCheckLabel.style.color = 'green';
    } else {
        pwCheckLabel.textContent = '비밀번호가 다릅니다.';
        pwCheckLabel.style.color = 'red';
    }
}

// 비밀번호 입력 또는 확인 입력 시 이벤트 핸들러 등록
document.addEventListener('DOMContentLoaded', function() {
    const passwordInput = document.querySelector('#password');
    const confirmPasswordInput = document.querySelector('#confirmPassword');

    if (passwordInput) {
        passwordInput.addEventListener('input', validatePasswords);
    }

    if (confirmPasswordInput) {
        confirmPasswordInput.addEventListener('input', validatePasswords);
    }
});
