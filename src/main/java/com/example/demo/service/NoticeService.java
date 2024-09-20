package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.repository.NewsRepository;
import com.example.demo.repository.NoticeRepository;
import com.example.demo.repository.model.News;
import com.example.demo.repository.model.Notice;

@Service
public class NoticeService {
	
	@Autowired
	public NoticeRepository noticeRepository;
	
	@Autowired
	public NewsRepository newsRepository;
	
	public NoticeService(NoticeRepository noticeRepository,NewsRepository newsRepository) {
		this.noticeRepository = noticeRepository;
		this.newsRepository = newsRepository;
	}
	
	// 모든 공지 가져오기
	public List<Notice> getAllNotice(int offset){
		List<Notice> noticeList = noticeRepository.selectAllNotice(offset);
		return noticeList;
	}
	// 한 공지 가져오기
	public Notice getNoticeById(Integer id){
		Notice notice = noticeRepository.selectNoticeById(id);
		return notice;
	}
	
	// 모든 뉴스 가져오기
	// 모든 공지 가져오기
	public List<News> getAllnews(){
		List<News> newsList = newsRepository.selectAllNews();
		return newsList;
	}
	
	// 한 뉴스 가져오기
	public News getNewsById(Integer id){
		News news = newsRepository.selectNewsById(id);
		return news;
	}
}
