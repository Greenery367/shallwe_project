package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.repository.model.User;
import com.example.demo.service.MyPageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my-page")
public class MyPageController {

	private final MyPageService myPageService;
	private final HttpSession httpSession;

	@Autowired
	public MyPageController(MyPageService myPageService, HttpSession httpSession) {
		this.myPageService = myPageService;
		this.httpSession = httpSession;
	}

	@GetMapping("/{userId}")
	public String myInfo(@PathVariable("userId") Integer userId, Model model) {
		myPageService.readUserDetail(userId);
		User user = (User) httpSession.getAttribute("principal");
		System.out.println(user);
		model.addAttribute("user", user);
		model.addAttribute("userId", userId);
		return "myPage/info";

	}

	@PostMapping("/update-profile")
	public String updateUserProfile(@RequestParam("file") MultipartFile file) {
		User user = (User) httpSession.getAttribute("principal");
		Integer userId = user.getUserId();
		String uploadDir = "C:\\work_spring\\upload/";
		String[] fileNames = myPageService.uploadFile(file, uploadDir);

		if (fileNames != null) {
			String uploadFileName = fileNames[1];
			boolean updateSuccess = myPageService.updateProfilePicture(userId, uploadFileName);
			if (updateSuccess) {
				// 세션에 저장된 사용자 정보 업데이트
				user.setUploadFileName(uploadFileName);
				httpSession.setAttribute("principal", user);
				return "redirect:/my-page/" + user.getUserId();
			}
		}
		return "redirect:/my-page/" + user.getUserId(); // 실패 시에도 마이페이지로 리다이렉트
	}
	
	@PostMapping("/update-username")
	public String updateUsername(@RequestParam("username") String username) {
	    User user = (User) httpSession.getAttribute("principal");
	    Integer userId = user.getUserId();
	    
	    boolean updateSuccess = myPageService.updateUsername(userId, username);
	    if (updateSuccess) {
	        // 세션에 저장된 사용자 정보 업데이트
	        user.setUsername(username);
	        httpSession.setAttribute("principal", user);
	    }
	    
	    return "redirect:/my-page/" + userId; // 수정 후 마이페이지로 리다이렉트
	}
}