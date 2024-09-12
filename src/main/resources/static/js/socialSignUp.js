document.addEventListener('DOMContentLoaded', function() {
    let isNicknameChecked = false;  // 닉네임 중복 체크 완료 여부를 확인하는 변수

    // 폼 요소 가져오기
    const form = document.getElementById('login-form');
    
    // 입력 필드 가져오기
    const nickname = form.querySelector('input[name="nickname"]');
    const birthDateInput = form.querySelector('input[name="birthDate"]');
    const emailBodyInput = form.querySelector('input[name="emailBody"]');
    const emailDomainInput = form.querySelector('input[name="emailDomain"]');
    const phoneNumberInput = form.querySelector('input[name="phoneNumber"]');

    // 엔터키 막기
    form.addEventListener('keydown', function(event) {
        if (event.key === "Enter") {
            event.preventDefault(); // 엔터키로 폼 제출 막기
        }
    });

    // 폼 제출 시 유효성 검사
    form.addEventListener('submit', function(event) {
        if (!validateForm() || !isNicknameChecked) {  // 닉네임 중복 체크 여부도 확인
            event.preventDefault(); // 유효성 검사 실패 또는 닉네임 체크 미완료 시 폼 제출 막기
            if (!isNicknameChecked) {
                alert('닉네임 중복 검사를 완료해 주세요.');
            }
        }
    });

    // 유효성 검사 함수
    function validateForm() {
        // 닉네임 검사
        if (nickname.value.trim() === '') {
            alert('닉네임을 입력해주세요.');
            nickname.focus();
            return false;
        }

        // 생년월일 검사
        if (birthDateInput.value.trim() === '' || birthDateInput.value.length !== 8) {
            alert('생년월일을 8자리 숫자로 입력해주세요.');
            birthDateInput.focus();
            return false;
        }

        // 이메일 검사
        if (emailBodyInput.value.trim() === '' || emailDomainInput.value.trim() === '') {
            alert('이메일을 완전히 입력해주세요.');
            emailBodyInput.focus();
            return false;
        }

        // 연락처 검사
        if (phoneNumberInput.value.trim() === '' || phoneNumberInput.value.length < 10 || phoneNumberInput.value.length > 11) {
            alert('연락처를 올바르게 입력해주세요.');
            phoneNumberInput.focus();
            return false;
        }

        return true; // 모든 검사를 통과하면 true 반환
    }

    // 도메인 선택시 자동 입력
    const domainList = document.getElementById('domainList');
    domainList.addEventListener('change', function() {
        if (this.value !== '') {
            emailDomainInput.value = this.value;
        } else {
            emailDomainInput.value = '';
            emailDomainInput.focus();
        }
    });

    // 닉네임 중복 체크 함수
    window.checkNickname = function() {
        const nicknameValue = nickname.value.trim();

        if (nicknameValue === '') {
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
                    isNicknameChecked = true;  // 닉네임 중복 체크 완료 플래그 설정
                } else if (result === 'unavailable') {
                    alert('이미 사용 중인 닉네임입니다.');
                    document.querySelector('.nickname-check').style.color = 'red';
                    document.querySelector('.nickname-check').textContent = '이미 사용 중인 닉네임입니다.';
                    document.querySelector('.nickname-input').focus();
                    isNicknameChecked = false;  // 닉네임 중복 체크 실패 시 플래그 해제
                }
            } else {
                alert('서버 오류가 발생했습니다. 나중에 다시 시도해주세요.');
                isNicknameChecked = false;  // 서버 오류 시에도 플래그 해제
            }
        };

        xhr.send('nickname=' + encodeURIComponent(nicknameValue));
    };
});




