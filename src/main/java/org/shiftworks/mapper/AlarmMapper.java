package org.shiftworks.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.shiftworks.domain.AlarmVO;

public interface AlarmMapper {
	int insertAlarm(AlarmVO alarmVO);
	List<AlarmVO> getAlarm(@Param("emp_id") String emp_id, @Param("today") String today);
	int deleteAlarm(Integer alarm_id);
	int insertDday(AlarmVO alarmVO);
}
