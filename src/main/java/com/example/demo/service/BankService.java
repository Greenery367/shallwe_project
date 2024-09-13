package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.BankDTO;
import com.example.demo.dto.BankInfoDTO;
import com.example.demo.repository.BankRepository;
import com.example.demo.repository.model.User;

@Service
public class BankService {

	private final BankRepository bankRepository;

	@Autowired
	private BankService(BankRepository bankRepository) {
		this.bankRepository = bankRepository;
	}
	
	
	// 사용자 계좌 생성
    public void createAccount(int userId, String bankId, String accountNumber) {
        bankRepository.createAccount(userId, bankId, accountNumber);
    }
	
    // 사용자 계좌 업데이트
    public void updateAccount(int userId, String bankId, String accountNumber) {
        bankRepository.updateAccount(userId, bankId, accountNumber);
    }
    
    // 모든 은행 목록 조회
    public List<BankInfoDTO> getAllBanks() {
    	return bankRepository.selectAllBanks();
    }

    // 특정 사용자와 은행 ID의 계좌 정보 조회
    public BankDTO getAccountByUserIdAndBankId(int userId) {
        return bankRepository.bankAccountFindByUser(userId);
    }

    
    public boolean doesAccountExist(int userId) {
        BankDTO bankInfo = bankRepository.bankAccountFindByUser(userId);
        return bankInfo != null && bankInfo.getAccountNumber() != null;
    }
	
}