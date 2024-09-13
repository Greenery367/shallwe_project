package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.demo.repository.model.Notice;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface NoticeRepository {
	
	// 모든 공지 가져오기
	public List<Notice> selectAllNotice(@Param("limit")int limit);

	// 특정 공지만 가져오기
	public Notice selectNoticeById(Integer id);

}
