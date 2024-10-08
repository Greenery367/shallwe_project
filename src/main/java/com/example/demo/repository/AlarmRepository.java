package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Alarm;

@Mapper
public interface AlarmRepository {
	
	public int sendAlarm(Alarm alarm);
	public List<Alarm> findAlarmAll(@Param("userId")int userId);
	public int changeStatusBatch(@Param("alarmIdList")List<Integer> alarmIdList);
	public int deleteAlarmBatch(@Param("alarmIdList")List<Integer> alarmIdList);
	public int deleteAlarmAll(@Param("userId")int userId);
	public List<Integer> checkStatusAlarm(@Param("userId")int userId,@Param("opponentId")int opponentId);
	
}
