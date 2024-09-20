package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.repository.AlarmRepository;
import com.example.demo.repository.model.Alarm;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AlarmService {
	
	private final AlarmRepository alarmRepository;
	
	@Transactional
	public int sendAlarm(Alarm alarm) {
		int result = 0;
		result = alarmRepository.sendAlarm(alarm);
		return result;
	};
	
	public List<Alarm> findAlarmAll(int userId) {
		List<Alarm>alarmList = null;
		alarmList = alarmRepository.findAlarmAll(userId);
		return alarmList;
	}
	
	public int changeStatus(int id) {
		int result = 0;
		result = alarmRepository.changeStatus(id);
		return result;
	}
	
	public int deleteAlarm(int id) {
		int result = 0;
		result = alarmRepository.deleteAlarm(id);
		return result;
	}
	
	public int deleteAlarmAll(int userId) {
		int result = 0;
		result = alarmRepository.deleteAlarmAll(userId);
		return result;
	}
}
