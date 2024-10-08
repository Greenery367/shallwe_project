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
	
	public void changeStatusBatch(List<Integer> alarmIdList) {
        alarmRepository.changeStatusBatch(alarmIdList);
    }
	
	public int deleteAlarm(List<Integer>alarmIdList){
		int result = 0;
		result = alarmRepository.deleteAlarmBatch(alarmIdList);
		return result;
	}
	
	public int deleteAlarmAll(int userId) {
		int result = 0;
		result = alarmRepository.deleteAlarmAll(userId);
		return result;
	}
	
	public List<Integer>checkStatusAlarm(int userId,int opponentId) {
		List<Integer>statusList = null;
		statusList = alarmRepository.checkStatusAlarm(userId, opponentId);
		return statusList;
	}
}
