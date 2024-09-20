package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.ReportDTO;
import com.example.demo.repository.ReportRepository;
import com.example.demo.repository.model.Report;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportService {

	private final ReportRepository reportRepository;
	
	@Transactional
	public int sendReport(ReportDTO dto) {
		int result = 0;
		result = reportRepository.createReport(dto);
		return result;
	}
	
	public List<Report> getReports(int limit, int offset) {
		List<Report> reportList = null;
		reportList = reportRepository.getReportList(limit, offset);
		return reportList;
	}
	
	public int getReportListSize() {
		int size = reportRepository.getReportSize();
		return size;
	}
	
	public void deleteReport(int id) {
		reportRepository.solvedReport(id);
	}
}
