package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.RegisterExchangeDTO;
import com.example.demo.dto.RegisterSubmallDTO;
import com.example.demo.repository.model.Advertise;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.RegisterSubmall;
import com.example.demo.repository.model.Submall;
import com.example.demo.repository.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.BankService;
import com.example.demo.service.RegisterExchangeService;
import com.example.demo.service.RegisterSubmallService;
import com.example.demo.service.UserService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/my-page/exchange-list")
@RequiredArgsConstructor
public class UserExchangeController {

	private final RegisterExchangeService registerExchangeService;
	private final RegisterSubmallService registerSubmallService;
	private final UserService userService;
	private final BankService bankService;
	private final AdminService adminService;
	private final HttpSession session;

	@GetMapping("/")
	public String exchangePage(Model model, HttpSession session) {
		System.out.println("호에에에");
		User user = (User) session.getAttribute("principal"); // 세션에서 유저 가져오기
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		List<RegisterExchangeDTO> exchangeList = registerExchangeService.getExchangeHistory(user.getUserId());
		BankDTO bankAccount = bankService.getAccountByUserIdAndBankId(user.getUserId());

		if (user == null) {
			return "redirect:/user/sign-in";

		} else {
			long lectureMoney = userService.getLectureCash(user.getUserId()); // 렉처머니 조회

			boolean registerSubMall = registerSubmallService.isSubmallRegistered(user.getUserId());
			System.out.println("-------------" + registerSubMall);
			Submall isSubMall = registerSubmallService.isSubmallId(user.getUserId());
			System.out.println("++++++++++++++++++++" + isSubMall);
			if (lectureMoney >= 50000 || isSubMall != null) { // 렉처머니 5만원 이상인지 확인

				if (registerSubMall == true && isSubMall != null) { // 섭몰과 신청 둘 다 있으면 환전 내역 페이지로 이동
					model.addAttribute("exchangeList", exchangeList);
					model.addAttribute("advertiseListOne", advertiseListOne);
					model.addAttribute("advertiseListTwo", advertiseListTwo);
					model.addAttribute("advertiseListThree", advertiseListThree);
					model.addAttribute("user", user);
					model.addAttribute("categoryList", categoryList);
					return "/myPage/userExchange/exchangeRecord";
				} else if (registerSubMall == true && isSubMall == null) { // 신청만 있으면 대기 상태 페이지로 이동
					model.addAttribute("advertiseListOne", advertiseListOne);
					model.addAttribute("advertiseListTwo", advertiseListTwo);
					model.addAttribute("advertiseListThree", advertiseListThree);
					model.addAttribute("categoryList", categoryList);
					return "/myPage/userExchange/responseSubmall";
				} else { // 신청도 없으면 신청 페이지로 이동
					model.addAttribute("advertiseListOne", advertiseListOne);
					model.addAttribute("advertiseListTwo", advertiseListTwo);
					model.addAttribute("advertiseListThree", advertiseListThree);
					model.addAttribute("categoryList", categoryList);
					model.addAttribute("banks", bankService.getAllBanks()); // 은행 목록 추가
					model.addAttribute("bankAccount", bankAccount);

					return "/myPage/userExchange/registerSubmall";
				}
			} else {
				model.addAttribute("advertiseListOne", advertiseListOne);
				model.addAttribute("advertiseListTwo", advertiseListTwo);
				model.addAttribute("advertiseListThree", advertiseListThree);
				model.addAttribute("categoryList", categoryList);
				return "/myPage/userExchange/denyLecture"; // 렉처머니 부족 시 에러 페이지
			}
		}

	}

	 
	/**
	 * 계좌 수정
	 * @param bankId
	 * @param accountNumber
	 * @param model
	 * @return
	 */
	@PostMapping("/saveOrUpdateAccount")
    public String saveOrUpdateAccount(
    						@RequestParam("bankId") String bankId, 
    						@RequestParam("accountNumber") String accountNumber, 
    						Model model) {
        User user = (User) session.getAttribute("principal");
        Integer userId = user.getUserId();
        
        if (bankService.doesAccountExist(userId)) {
            // 계좌가 존재하면 업데이트
            bankService.updateAccount(userId, bankId, accountNumber);
        } else {
            // 계좌가 존재하지 않으면 생성
            bankService.createAccount(userId, bankId, accountNumber);
        }
        return "redirect:/my-page/exchange-list/";
    }
	
	@PostMapping("/registerSubmall")
	public String registerSubmall(@RequestParam("bankId") Integer bankId, Model model) {
	    User user = (User) session.getAttribute("principal");
	    Integer userId = user.getUserId();
	    
	    // 계좌가 존재하는지 확인
	    if (bankService.doesAccountExist(userId)) {
	        // 서브몰 신청 처리
	        registerSubmallService.registerSubmall(userId, bankId);
	        // 성공 메시지 추가
	        model.addAttribute("message", "서브몰 신청이 완료되었습니다.");
	    } else {
	        // 오류 메시지 추가
	        model.addAttribute("error", "계좌가 존재하지 않습니다. 먼저 계좌를 등록해주세요.");
	    }
	    
	    return "redirect:/my-page/exchange-list/";
	}
	
	@PostMapping("/registerExchange")
	public String registerExchange(@RequestParam("amount") Long amount,  
	                               Model model) {
	    User user = (User) session.getAttribute("principal");
	    Integer userId = user.getUserId();

	    // 렉처머니가 충분한지 확인
	    long lectureMoney = userService.getLectureCash(userId);
	    if (lectureMoney < amount) {
	        model.addAttribute("error", "잔액이 부족합니다.");
	        return "redirect:/my-page/exchange-list/";  // 잔액이 부족한 경우 리다이렉트
	    }

	    // 서브몰 ID 찾기
	    String submallId = registerExchangeService.getSubmallFindById(userId);

	    // 환전 신청 처리
	    RegisterExchangeDTO registerExchangeDTO = RegisterExchangeDTO.builder()
	        .userId(userId)
	        .submallId(submallId)
	        .amount(amount)
	        .build();

	    registerExchangeService.registerExchange(registerExchangeDTO);
	    
	    return "redirect:/my-page/exchange-list/";
	}

}
