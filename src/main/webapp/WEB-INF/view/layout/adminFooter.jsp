<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

					<div class="modal-alarm">
						<div class="modal-body">
							<div class="modal_body-content">
								<h2>알림</h2>
								<p>알림내용</p>
								<button class="close-btn-alarm">닫기</button>
							</div>
						</div>
					</div>
	
					<div class="modal-qna">
						<div class="modal-body">
							<h2>Q&A</h2>
							<p>Q&A내용</p>
							<button class="close-btn-qna">닫기</button>
						</div>
					</div>
	
					<div class="modal-report">
						<div class="modal-body">
							<h2>신고</h2>
							<p>신고내용</p>
							<button class="close-btn-report">닫기</button>
						</div>
					</div>
				</div>
			<script>
			    const modalAlarm = document.querySelector('.modal-alarm');
			    const modalQnA = document.querySelector('.modal-qna');
			    const modalReport = document.querySelector('.modal-report');
			
			    const btnOpenModalAlarm = document.querySelector('.btn-open-modal-alarm');
			    const btnOpenModalQnA = document.querySelector('.btn-open-modal-qna');
			    const btnOpenModalReport = document.querySelector('.btn-open-modal-report');
			
			    const btnCloseModalAlarm = document.querySelector('.close-btn-alarm');
			    const btnCloseModalQnA = document.querySelector('.close-btn-qna');
			    const btnCloseModalReport = document.querySelector('.close-btn-report');
			
			    // 모달 열기
			    btnOpenModalAlarm.addEventListener("click", () => {
			        modalAlarm.style.display = "flex";
			    });
			
			    btnOpenModalQnA.addEventListener("click", () => {
			        modalQnA.style.display = "flex";
			    });
			
			    btnOpenModalReport.addEventListener("click", () => {
			        modalReport.style.display = "flex";
			    });
			
			    // 모달 닫기
			    const closeModal = (modal) => {
			        modal.style.display = "none";
			    };
			
			    btnCloseModalAlarm.addEventListener("click", () => {
			        closeModal(modalAlarm);
			    });
			
			    btnCloseModalQnA.addEventListener("click", () => {
			        closeModal(modalQnA);
			    });
			
			    btnCloseModalReport.addEventListener("click", () => {
			        closeModal(modalReport);
			    });
			
			    // 다른곳 클릭시 닫기
			    window.addEventListener("click", (event) => {
			        if (event.target === modalAlarm) {
			            closeModal(modalAlarm);
			        } else if (event.target === modalQnA) {
			            closeModal(modalQnA);
			        } else if (event.target === modalReport) {
			            closeModal(modalReport);
			        }
			    });
			</script>

				
		</div>
		
    
    	<footer>
			<div class="footer">
				<p>footer</p>
			</div>
		</footer>
	</div>
</body>
</html>