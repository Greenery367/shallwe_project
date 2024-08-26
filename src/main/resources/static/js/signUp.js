const domainListEl = document.querySelector('#domainList')
const domainInputEl = document.querySelector('#emailDomain')
// select 옵션 변경 시
domainListEl.addEventListener('change', function () {
  // option에 있는 도메인 선택 시
  if(domainListEl.value !== "") {
    // 선택한 도메인을 input에 입력하고 disabled
    domainInputEl.value = domainListEl.value
    domainInputEl.disabled = true
  } else { // 직접 입력 시
    // input 내용 초기화 & 입력 가능하도록 변경
    domainInputEl.value = ""
    domainInputEl.disabled = false
  }
})

function checkid(){
	const id = document.getElementById("id").value;
	if(id == "" || id.length < 0){
		alert('아이디를 입력하세요.');
		id.focus();
		return;
	} else {
		window.open("idCheck?id="+id,"","width=500, height=300");
	}
}
 