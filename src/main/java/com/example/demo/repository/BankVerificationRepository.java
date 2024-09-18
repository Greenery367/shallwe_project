package com.example.demo.repository;

import java.net.http.HttpHeaders;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.dto.BankVerificationDTO;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface BankVerificationRepository {
	
	public BankVerificationDTO findAccountByBankCodeAndAccountNum(@Param("bankCodeStd") String bankCodeStd, 
            @Param("accountNum") String accountNum);
    
    public HttpHeaders getHeaders();
	
}
