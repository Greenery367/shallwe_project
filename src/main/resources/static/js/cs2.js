document.addEventListener('DOMContentLoaded', function () {
    // 테이블의 각 행에 클릭 이벤트를 추가
    const rows = document.querySelectorAll('.qna-table tbody tr');
    
    rows.forEach(function(row) {
        row.addEventListener('click', function() {
            const writer = this.querySelector('td:nth-child(3)').innerText; // 작성자
            const id = this.querySelector('input[name="id"]').value; // 히든 필드의 id 값 가져오기

            // detail 컨트롤러로 이동 (GET 요청)
            window.location.href = `/cs/detailFreq?writer=${encodeURIComponent(writer)}&id=${encodeURIComponent(id)}`;
        });
    });
});
