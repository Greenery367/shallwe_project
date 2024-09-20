package com.example.demo.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.demo.repository.model.Alarm;

@Mapper
public interface AlarmRepository {
	
	public int sendAlarm(Alarm alarm);
	public List<Alarm> findAlarmAll(@Param("userId")int userId);
	public int changeStatus(@Param("id")int id);
	public int deleteAlarm(@Param("id")int id);
	public int deleteAlarmAll(@Param("userId")int userId);
	
}
