package com.example.demo.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BoardRepository {
	
	public int insert(@Param("title")String title,@Param("content") String content,@Param("author") String author);
	
	public int updateBoard(@Param("id")Integer id,@Param("title")String title, @Param("content")String content);
	
	public int deleteById(Integer id);
	
}
