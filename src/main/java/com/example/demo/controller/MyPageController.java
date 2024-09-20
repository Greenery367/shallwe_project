package com.example.demo.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.BankInfoDTO;
import com.example.demo.dto.RecordDTO;
import com.example.demo.repository.OrderRepository;
import com.example.demo.repository.RefundRepository;
import com.example.demo.repository.model.Advertise;
import com.example.demo.repository.model.Category;
import com.example.demo.repository.model.Order;
import com.example.demo.repository.model.Refund;
import com.example.demo.repository.model.User;
import com.example.demo.service.AdminService;
import com.example.demo.service.BankService;
import com.example.demo.service.MyPageService;
import com.example.demo.service.RecordService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Controller
@RequestMapping("/my-page")
@RequiredArgsConstructor
public class MyPageController {

	private final MyPageService myPageService;
	private final BankService bankService;
	private final RecordService recordService;
	private final AdminService adminService;
	private final OrderRepository orderRepository;
	private final RefundRepository refundRepository;
	
	private final HttpSession session;
	
 

	@GetMapping("/")
	public String myInfo(Model model) {
		User user = (User) session.getAttribute("principal");
		List<BankInfoDTO> banks = bankService.getAllBanks(); // 은행 목록 조회
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		BankDTO bankAccount = bankService.getAccountByUserIdAndBankId(user.getUserId());
		
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("user", user);
		model.addAttribute("userId", user.getUserId());
		model.addAttribute("banks", banks); // 모델에 은행 목록 추가
		model.addAttribute("bankAccount", bankAccount);
		return "myPage/info";

	}

	@PostMapping("/update-profile")
	public String updateUserProfile(@RequestParam("file") MultipartFile file) {
		User user = (User) session.getAttribute("principal");
		Integer userId = user.getUserId();
		String uploadDir = "C:\\work_spring\\upload/";
		String[] fileNames = myPageService.uploadFile(file, uploadDir);

		if (fileNames != null) {
			String uploadFileName = fileNames[1];
			boolean updateSuccess = myPageService.updateProfilePicture(userId, uploadFileName);
			if (updateSuccess) {
				// 세션에 저장된 사용자 정보 업데이트
				user.setUploadFileName(uploadFileName);
				session.setAttribute("principal", user);
				return "redirect:/my-page/";
			}
		}
		return "redirect:/my-page/"; // 실패 시에도 마이페이지로 리다이렉트
	}

	/**
	 * 유저 이름 변경
	 * 
	 * @param username
	 * @return
	 */
	@PostMapping("/update-username")
	public String updateUsername(@RequestParam("username") String username) {
		User user = (User) session.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updateUsername(userId, username);
		if (updateSuccess) {
			// 세션에 저장된 사용자 정보 업데이트
			user.setUsername(username);
			session.setAttribute("principal", user);
		} else {
			System.out.println("이름 수정에 실패했따!");
		}

		return "redirect:/my-page/"; // 수정 후 마이페이지로 리다이렉트
	}

	/**
	 * 유저 닉네임 변경
	 * 
	 * @param nickname
	 * @return
	 */
	@PostMapping("/update-nickname")
	public String updateNickname(@RequestParam("nickname") String nickname) {
		User user = (User) session.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updateNickname(userId, nickname);
		if (updateSuccess) {
			user.setNickname(nickname);
			session.setAttribute("principal", user);
		} else {
			System.out.println("닉네임 수정에 실패했다.");
		}

		return "redirect:/my-page/";
	}

	@PostMapping("/update-phone-number")
	public String updatePoneNumber(@RequestParam("phoneNumber") String phoneNumber) {
		User user = (User) session.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updatePhoneNumber(userId, phoneNumber);
		if (updateSuccess) {
			user.setPhoneNumber(phoneNumber);
			session.setAttribute("principal", user);
		} else {
			System.out.println("핸드폰 번호 수정에 실패했다.");
		}

		return "redirect:/my-page/";
	}

	@PostMapping("/update-email")
	public String updateEmail(@RequestParam("email") String email) {
		User user = (User) session.getAttribute("principal");
		Integer userId = user.getUserId();

		boolean updateSuccess = myPageService.updateEmail(userId, email);
		if (updateSuccess) {
			user.setEmail(email);
			session.setAttribute("principal", user);
		} else {
			System.out.println("이메일 수정에 실패했다.");
		}

		return "redirect:/my-page/";
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
        return "redirect:/my-page/";
    }
	
	@GetMapping("/charge-list")
	public String chargeList(
			Model model
			) {
		User user = (User)session.getAttribute("principal");
		System.out.println("쁘아아악!!!!!!!!!!!!!!!!!!");
		List<Order> orders = orderRepository.orderFindById(user.getUserId());
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("user", user);
		model.addAttribute("orders", orders);
		
		return "myPage/chargeRecord";
	}
	
	
	@GetMapping("/spend-list")
	public String spendList(
			Model model
			) {
		User user = (User)session.getAttribute("principal");
		List<RecordDTO> spends = recordService.getSpendBoard(user.getUserId());
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("user", user);
		model.addAttribute("spends", spends);
		
		return "myPage/spendRecord";
	}
	
	@GetMapping("/refund-list")
	public String refundList(
			Model model
			) {
		User user = (User)session.getAttribute("principal");
		List<Refund> refunds = refundRepository.refundFindById(user.getUserId());
		List<Advertise> advertiseListOne = adminService.selectAdvertisePlaceOne();
		List<Advertise> advertiseListTwo = adminService.selectAdvertisePlaceTwo();
		List<Advertise> advertiseListThree = adminService.selectAdvertisePlaceThree();
		List<Category> categoryList = adminService.selectAllCategory();
		
		model.addAttribute("advertiseListOne", advertiseListOne);
		model.addAttribute("advertiseListTwo", advertiseListTwo);
		model.addAttribute("advertiseListThree", advertiseListThree);
		model.addAttribute("categoryList",categoryList);
		model.addAttribute("user", user);
		model.addAttribute("refunds", refunds);
		
		return "myPage/refundRecord";
	}
	
}