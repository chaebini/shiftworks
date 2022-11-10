package org.shiftworks.controller;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.shiftworks.domain.AlarmVO;
import org.shiftworks.domain.ApprovalCriteria;
import org.shiftworks.domain.BookingVO;
import org.shiftworks.mapper.AlarmMapper;
import org.shiftworks.service.ApprovalService;
import org.shiftworks.service.BookingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.AllArgsConstructor;

/**
 * Handles requests for the application home page.
 */
@Controller
@AllArgsConstructor
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	private BookingService bookingService;
	private ApprovalService appService;
	private AlarmMapper alarmMapper;
	/**
	 * Simply selects the home view to render by returning its name.
	 */
//	@RequestMapping(value = "/", method = RequestMethod.GET)
//	public String home(Locale locale, Model model) {
//		logger.info("Welcome home! The client locale is {}.", locale);
//		
//		Date date = new Date();
//		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
//		
//		String formattedDate = dateFormat.format(date);
//		
//		model.addAttribute("serverTime", formattedDate );
//		
//		return "home";
//	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String homeContent(Model model) {
		List<BookingVO> eventList = new ArrayList<BookingVO>();
		List<BookingVO> list = bookingService.getList();

		
		if(list == null) {
			return "예약이 없습니다";
		}else {
			for(int i=0;i<list.size();i++) {
				eventList.add(list.get(i));
			}
		}
		
		
		//예약 리스트(게시판 형태)
		//model.addAttribute("event", bookingService.getList());
		model.addAttribute("event", eventList);
		
		//model.addAttribute("get", bookingService.getBooking(205));
		//model.addAttribute("event2", sservice.getList());
		
		//model.addAttribute("board", service.boardList());
		//게시판 글목록 가져오기
		//model.addAttribute("board", service.boardList());
		
		
		return "home"; 
	}
	
	
	@GetMapping(value="/alarm/{emp_id}/{today}",
				produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody List<AlarmVO> getAlarm(@PathVariable String emp_id, @PathVariable String today) {
		List<AlarmVO> list = alarmMapper.getAlarm(emp_id, today);
		
		return list;
	}
	
	
	
}
