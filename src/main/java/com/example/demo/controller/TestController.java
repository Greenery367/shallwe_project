package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.repository.QuestionRepository;
import com.example.demo.repository.model.Answer;
import com.example.demo.repository.model.Mbti;
import com.example.demo.repository.model.News;
import com.example.demo.repository.model.Notice;
import com.example.demo.repository.model.Question;
import com.example.demo.repository.model.User;
import com.example.demo.service.MbtiService;
import com.example.demo.service.NoticeService;
import com.example.demo.service.QuestionService;
import com.mysql.cj.Session;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
	
	@Autowired
	private HttpSession httpSession;
	
	@Autowired
	private QuestionService questionService;
	
	@Autowired 
	private NoticeService noticeService;
	
	@Autowired
	private MbtiService mbtiService;
	private int progressNumber;
	
	// DI
	public TestController(HttpSession httpSession, QuestionService questionService, NoticeService noticeService){
		this.httpSession  = httpSession;
		this.questionService = questionService;
		this.noticeService = noticeService;
		progressNumber=1;
	}

	/**
	 * 메인 페이지 이동
	 * @return
	 */
	// http://localhost:8080/test/main
	@GetMapping("/main")
	public String mainPage(Model model) {
		
		User user = (User)httpSession.getAttribute("principal");
		
		// 최신 공지사항 글 
		List<Notice> noticeList = noticeService.getAllNotice(0);
		List<News> newsList = noticeService.getAllnews();
		
		// 최신 자유게시판 글 5개
		System.out.println("----------노티스리스트"+noticeList);
		System.out.println("----------뉴스리스트"+newsList);
		
		model.addAttribute("noticeList", noticeList);
		model.addAttribute("newsList", newsList);
		model.addAttribute("user", user);
		
		return "mainPage";
	}
	
	/**
	 * MBTI 테스트 페이지 이동
	 * @param model
	 * @return
	 */
	// http://localhost:8080/test/start-test
	@GetMapping("/start-test")
	public String testPage(Model model) {

		User user = (User)httpSession.getAttribute("principal");
		List<Question> questionList = questionService.getAllQuestion();
		int progressNumber = 0;
		model.addAttribute("questionList", questionList);
		model.addAttribute("progressNumber", progressNumber);
		model.addAttribute("user", user);
		return "test/startTestPage";
	}
	
	/**
	 * MBTI 결과 페이지 송출
	 * @param entity
	 * @return
	 */
	// http://localhost:8080/test/show-result
	@PostMapping("/show-result")
	public String postMethodName(@RequestParam("answerArray") List<String> answerArray,
			Model model) {
		// 로깅
    	//answers.forEach(answer -> {
        //    System.out.println(" Answer: " + answer.getAnswer());
        // });
    	
    	int sNum=0; // 외향형
    	int nNum=0; // 내향형
    	int qNum=0; // 낭만형
    	int mNum=0; // 효율형
    	int rNum=0; // 리더형
    	int eNum=0; // 개인형
    	int tNum=0; // 즐기는 자형
    	int cNum=0; // 승부욕 형
    	
    	// 외향-내향형 가르기
    	for(int i=0; i<3; i++) {
    		if(answerArray.get(i).contains("no")) {
    			sNum++;
    		} else if(answerArray.get(i).contains("yes")){
        		nNum++;
    		}
    	}
    	
    	// 낭만-효율형 가르기
    	for(int i=3; i<6; i++) {
    		if(answerArray.get(i).contains("no")) {
    			qNum++;
    		} else if(answerArray.get(i).contains("yes")) {
        		mNum++;
    		}
    	}
    	
    	// 리더-개인형 가르기
    	for(int i=6; i<9; i++) {
    		if(answerArray.get(i).contains("no")) {
    			eNum++;
    		} else if(answerArray.get(i).contains("yes")) {
    			rNum++;
    		}
    	}
    	
    	// 즐기는 자-승부욕 형 가르기
    	for(int i=9; i<11; i++) {
    		if(answerArray.get(i).contains("no")) {
    			tNum++;
    		} else if(answerArray.get(i).contains("yes")) {
    			cNum++;
    		}
    	}
    	
    	// 키워드 뽑기
    	String first = sNum>nNum ? "S" : "N";
    	String second = qNum>mNum ? "Q" : "M";
    	String third = eNum>rNum ? "E" : "R";
    	String fourth = tNum>eNum ? "T" : "C";
    	
    	// 키워드 합치기
    	String result=first+second+third+fourth;
    	
    	System.out.println("~~~~~~~~~~~~키워드"+result);
    	
    	// 키워드를 통해 결과 탐색
    	Mbti resultMbti = mbtiService.selectMbtiByName(result);
    	
    	System.out.println("~~~~~~~~~~~myMBTI"+resultMbti);
    	System.out.println("~~~~~~~~~~~myMBTI"+resultMbti.getId());
    	
    	// 키워드를 통해 궁합 탐색
    	Mbti goodMatchedMbti = mbtiService.selectMbtiByCompatibility(resultMbti.getId(),100);
    	Mbti badMatchedMbti = mbtiService.selectMbtiByCompatibility(resultMbti.getId(),0);

    	System.out.println("~~~~~~~~~~굿"+goodMatchedMbti);
    	System.out.println("~~~~~~~~~~뱃"+badMatchedMbti);
    	
    	// 세션에 데이터 저장
    	model.addAttribute("resultMbti", resultMbti);
    	httpSession.setAttribute("testResult", resultMbti);
    	model.addAttribute("goodMatchedMbti", goodMatchedMbti);
    	model.addAttribute("badMatchedMbti", badMatchedMbti);
    	
    	// 로그인 상태 확인
    	User user = (User)httpSession.getAttribute("principal");
    	
    	// 만약 로그인 중이라면 유저-mbti_tb에 mbti 저장
    	if(user != null) {
    		mbtiService.setMbtiAndUserInfo(user.getUserId(), resultMbti.getId());
    		user.setMbti(resultMbti.getId());
    		httpSession.setAttribute("principal", user);
    		System.out.println(httpSession.getAttribute("principal"));
    	}
    	
    	
    	model.addAttribute("user", user);
        // 페이지 리디렉션
        return "/test/resultTest";
	}
	
	
}