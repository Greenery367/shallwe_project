package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.BankInfoDTO;
import com.example.demo.repository.model.User;
import com.example.demo.service.BankService;
import com.example.demo.service.MyPageService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/my-page")
public class MyPageController {

	private final MyPageService myPageService;
	private final BankService bankService;
	private final HttpSession httpSession;

	@Autowired
	public MyPageController(MyPageService myPageService, BankService bankService, HttpSession httpSession) {
		this.myPageService = myPageService;
		this.bankService = bankService;
		this.httpSession = httpSession;
	}

	@GetMapping("/{userId}")
	public String myInfo(@PathVariable("userId") Integer userId, Model model) {
		myPageService.readUserDetail(userId);
		User user = (User) httpSession.getAttribute("principal");
		List<BankInfoDTO> banks = bankService.getAllBanks(); // 은행 목록 조회
		System.out.println(user);
		BankDTO bankAccount = bankService.getAccountByUserIdAndBankId(userId);
		
		model.addAttribute("user", user);
		model.addAttribute("userId", userId);
		model.addAttribute("banks", banks); // 모델에 은행 목록 추가
		model.addAttribute("bankAccount", bankAccount);
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

	/**
	 * 유저 이름 변경
	 * 
	 * @param username
	 * @return
	 */
	@PostMapping("/update-username")
	public String updateUsername(@RequestParam("username") String username) {
		User user = (User) httpSession.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updateUsername(userId, username);
		if (updateSuccess) {
			// 세션에 저장된 사용자 정보 업데이트
			user.setUsername(username);
			httpSession.setAttribute("principal", user);
		} else {
			System.out.println("이름 수정에 실패했따!");
		}

		return "redirect:/my-page/" + userId; // 수정 후 마이페이지로 리다이렉트
	}

	/**
	 * 유저 닉네임 변경
	 * 
	 * @param nickname
	 * @return
	 */
	@PostMapping("/update-nickname")
	public String updateNickname(@RequestParam("nickname") String nickname) {
		User user = (User) httpSession.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updateNickname(userId, nickname);
		if (updateSuccess) {
			user.setNickname(nickname);
			httpSession.setAttribute("principal", user);
		} else {
			System.out.println("닉네임 수정에 실패했다.");
		}

		return "redirect:/my-page/" + userId;
	}

	@PostMapping("/update-phone-number")
	public String updatePoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
		User user = (User) httpSession.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updatePhoneNumber(userId, phoneNumber);
		if (updateSuccess) {
			user.setPhoneNumber(phoneNumber);
			httpSession.setAttribute("principal", user);
		} else {
			System.out.println("핸드폰 번호 수정에 실패했다.");
		}

		return "redirect:/my-page/" + userId;
	}

	@PostMapping("/update-email")
	public String updateEmail(@RequestParam("email") String email) {
		User user = (User) httpSession.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updateEmail(userId, email);
		if (updateSuccess) {
			user.setEmail(email);
			httpSession.setAttribute("principal", user);
		} else {
			System.out.println("이메일 수정에 실패했다.");
		}

		return "redirect:/my-page/" + userId;
	}
	
	
	
	@PostMapping("/saveOrUpdateAccount")
    public String saveOrUpdateAccount(
    						@RequestParam("bankId") String bankId, 
    						@RequestParam("accountNumber") String accountNumber, 
    						Model model) {
        User user = (User) httpSession.getAttribute("principal");
        Integer userId = user.getUserId();
        
        if (bankService.doesAccountExist(userId)) {
            // 계좌가 존재하면 업데이트
            bankService.updateAccount(userId, bankId, accountNumber);
        } else {
            // 계좌가 존재하지 않으면 생성
            bankService.createAccount(userId, bankId, accountNumber);
        }
        return "redirect:/my-page/" + userId;
    }

}