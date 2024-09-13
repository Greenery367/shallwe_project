package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.dto.ReportDTO;
import com.example.demo.repository.model.Report;

@Mapper
public interface ReportRepository {

	public int createReport(ReportDTO report);
	public List<Report> getReportList(@Param("limit")int limit,@Param("offset")int offset);
	public int solvedReport(int id);
	public int getReportSize();
	
}
