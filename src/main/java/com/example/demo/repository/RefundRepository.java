package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Refund;


@Mapper
public interface RefundRepository {
	
	public List<Refund> selectAllRefund(@Param("limit") Integer limit,
										@Param("offset")Integer offset);
	
	public List<Refund> refundFindById (@Param("userId") Integer userId);

}
