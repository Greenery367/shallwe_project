package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BankVerificationDTO {

	 // 거래 고유번호 (API)
    private String apiTranId;

    // 거래 일시 (밀리세컨드)
    private String apiTranDtm;

    // 응답 코드 (API)
    private String rspCode;

    // 응답 메시지 (API)
    private String rspMessage;

    // 거래 고유번호 (참가기관)
    private String bankTranId;

    // 거래 일자 (참가기관)
    private String bankTranDate;

    // 응답 코드를 부여한 참가기관 표준코드
    private String bankCodeTran;

    // 응답 코드 (참가기관)
    private String bankRspCode;

    // 응답 메시지 (참가기관)
    private String bankRspMessage;

    // 개설기관 표준코드
    private String bankCodeStd;

    // 개설기관 점별코드
    private String bankCodeSub;

    // 개설기관명
    private String bankName;

    // 개별저축은행명
    private String savingsBankName;

    // 계좌번호
    private String accountNum;

    // 회차번호
    private String accountSeq;

    // 예금주 실명번호 구분코드
    private String accountHolderInfoType;

    // 거래 금액
    private String accountHolderInfo;

    // 예금주 성명
    private String accountHolderName;

    // 계좌 종류 (1: 수시입출금, 2: 예적금, 6: 수익증권)
    private String accountType;

	
}
