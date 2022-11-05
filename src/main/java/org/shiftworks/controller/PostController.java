package org.shiftworks.controller;


import java.io.File;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.shiftworks.domain.BoardCriteria;
import org.shiftworks.domain.HistoryVO;
import org.shiftworks.domain.BoardPageDTO;
import org.shiftworks.domain.EmployeeVO;
import org.shiftworks.domain.PostVO;
import org.shiftworks.domain.ScrapVO;
import org.shiftworks.domain.Temp_BoardVO;
import org.shiftworks.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@RequestMapping("/board/*")
@RestController
@Log4j
@AllArgsConstructor
public class PostController {
	
	
	private PostService service;
	
	//register form 이동
	@GetMapping(value = "/new")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView register() throws Exception{
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/BOA_register");
		return mav;
	}
	
	//register form에서 받아온 값 db에 넣기
	@PostMapping(value = "/new")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> register(@RequestBody PostVO vo){
		log.info("register......");
		int insertCount = service.insertPost(vo);
		
		return insertCount ==1
		? new ResponseEntity<String>("success", HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	//파일 업로드
	@PostMapping("/uploadAjaxAction")
	public void uploadAjaxPost(MultipartFile[] uploadFile) {
	
		log.info("update ajax post.........");
	
		String uploadFolder = "C:\\upload";
	
		for (MultipartFile multipartFile : uploadFile) {
	
			log.info("-------------------------------------");
			log.info("Upload File Name: " + multipartFile.getOriginalFilename());
			log.info("Upload File Size: " + multipartFile.getSize());
	
			String uploadFileName = multipartFile.getOriginalFilename();
	

	
			File saveFile = new File(uploadFolder, uploadFileName);
	
			try {
				multipartFile.transferTo(saveFile);
			} catch (Exception e) {
				log.error(e.getMessage());
			}
		}
	}
	
		//게시판 번호에 맞는 리스트 호출
		@GetMapping(value = "/list")
		@PreAuthorize("isAuthenticated()")
		public ModelAndView getList(BoardCriteria cri, Authentication auth) {
			
			log.info("getList..........");
			ModelAndView mav = new ModelAndView();
			
			
			mav.setViewName("/board/BOA_list");
			
			mav.addObject("pageMaker", service.getListSearch(cri));
		
			return mav;
		}

	
	//js에 리스트 전달해주기 
	@GetMapping(value = "/listEntity/{pageNum}/{type}/{keyword}")
	public ResponseEntity<BoardPageDTO> getListEntity(@PathVariable("pageNum")int pageNum,
																			@PathVariable("type") String type,
																			@PathVariable("keyword") String keyword){
		
		BoardCriteria cri = new BoardCriteria();
		cri.setPageNum(pageNum);
		if(!type.equals("1")) {
			cri.setType(type);
		}
		if(!keyword.equals("1")) {
			cri.setKeyword(keyword);
		}
		
		log.info("getListEntity......");
		
		return new ResponseEntity<BoardPageDTO>(service.getListSearch(cri),HttpStatus.OK);
		
	}
	
	
	//글번호 클릭 시 BOA_get.jsp로 이동
	@GetMapping(value = "/get")
	@PreAuthorize("isAuthenticated()")
	public ModelAndView getPost(@RequestParam("post_id") int post_id, 
												@ModelAttribute("cri") BoardCriteria cri) throws Exception{
		log.info("get.........");
		ModelAndView mav = new ModelAndView();
		mav.setViewName("/board/BOA_get");
		mav.addObject("post", service.getPost(post_id));
		return mav;
	}
	
	

	
	//수정 클릭 시 BOA_modify.jsp로 이동
	@GetMapping(value = "/modify")
	@PreAuthorize("isAuthenticated()")
		public ModelAndView modify(@RequestParam("post_id") int post_id, 
								  					@ModelAttribute("cri") BoardCriteria cri) throws Exception{
			log.info("modify.........");
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/board/BOA_modify");
			mav.addObject("post", service.getPost(post_id));
			return mav;
		}
	
	// 수정 데이터 값을 db 넣기
	@PostMapping(value = "/modify")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> modify(@RequestBody PostVO post){
		
		log.info("modify..................");
		return service.updatePost(post)==1
		? new ResponseEntity<String>("success",HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);

	}
	
	//삭제하기
	@DeleteMapping(value = "/{post_id}")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> deletePost(@PathVariable("post_id")int post_id){
		
		 return service.deletePost(post_id) ==1
		? new ResponseEntity<String>("success", HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//스크랩하기
	@PostMapping(value="/scrap")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> scrapPost(@RequestBody ScrapVO vo){
		log.info("scrap..........");
	
		
		return service.scrapPost(vo)==1
		? new ResponseEntity<String>("success", HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		
	}
	
	//임시저장/업데이트하기 
	@PostMapping(value = "/temporal")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<String> temporalPost(@RequestBody Temp_BoardVO vo){
		log.info("temporal......");
		
		return service.temporalPost(vo)==1
		? new ResponseEntity<String>("success",HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//임시저장 불러오기 
	@GetMapping(value = "/temporal")
	@PreAuthorize("isAuthenticated()")
	public ResponseEntity<Temp_BoardVO> temporalSelect(Authentication auth){
		log.info("temporalSelect.....");
		
		//로그인한 사람의 emp_id 구하기
		UserDetails ud = (UserDetails)auth.getPrincipal();
		log.info(ud.getUsername());
		String emp_id = ud.getUsername();
		
		
		return new ResponseEntity<Temp_BoardVO>(service.temporalSelect(emp_id),HttpStatus.OK);
	}
	
	
	
		//임시저장 뷰
		@GetMapping(value = "/temporalview")
		public ModelAndView temporalview(Authentication auth){
			log.info("temporalSelect.....");
			
			//로그인한 사람의 emp_id 구하기
			UserDetails ud = (UserDetails)auth.getPrincipal();
			log.info(ud.getUsername());
			String emp_id = ud.getUsername();

			
			ModelAndView mav = new ModelAndView();
			mav.setViewName("/board/BOA_register");
			mav.addObject("post", service.temporalSelect(emp_id));
			return mav;
		}
	
	
	//게시글 클릭 시 history 테이블에 추가하기
	@PostMapping(value = "/history/{post_id}")
	public ResponseEntity<String> insertHistory(@PathVariable("post_id") int post_id, Authentication auth){
		
		log.info("history.......");
		
		//로그인한 사람의 emp_id 구하기
		UserDetails ud = (UserDetails)auth.getPrincipal();
		log.info(ud.getUsername());
		String emp_id = ud.getUsername();
		String dept_id = service.getDeptId(emp_id);
		
		HistoryVO vo = new HistoryVO();
		vo.setEmp_id(emp_id);
		vo.setDept_id(dept_id);
		vo.setPost_id(post_id); 
		
		return service.insertHistory(vo)==1
		?new ResponseEntity<String>("success", HttpStatus.OK)
		: new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	//history불러오기
		@GetMapping(value = "/history")
		public ResponseEntity<List<HistoryVO>> getHistory(Authentication auth){
			
			log.info("gethistory.......");
			
			//로그인한 사람의 emp_id 구하기
			UserDetails ud = (UserDetails)auth.getPrincipal();
			log.info(ud.getUsername());
			String emp_id = ud.getUsername();
			
	
			return new ResponseEntity<List<HistoryVO>>(service.selectHistory(emp_id),HttpStatus.OK);
		}
	
	

}
