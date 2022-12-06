package org.shiftworks.controller;

import java.util.List;

import org.shiftworks.domain.ReplyVO;
import org.shiftworks.service.ReplyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/reply/*")
@RestController
@Log4j
@AllArgsConstructor
public class ReplyController {
	
	private ReplyService service;
	
	@PostMapping("/new")
	public ResponseEntity<String> registerReply(@RequestBody ReplyVO vo, Authentication auth){
		
		//로그인한 사람의 emp_id 구하기
		UserDetails ud = (UserDetails)auth.getPrincipal();
		//log.info(ud.getUsername());
		String emp_id = ud.getUsername();
		vo.setR_writer(emp_id);
		
		
		int insertCount = service.insertReply(vo);
		
		return insertCount ==1
		? new ResponseEntity<String>("success",HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
				
	}
	
	@GetMapping("/{post_id}")
	public ResponseEntity<List<ReplyVO>> getListReply(@PathVariable("post_id") int post_id){
		
		//log.info("reply......");
		return new ResponseEntity<>(service.getReply(post_id),HttpStatus.OK);
		
	}

}
