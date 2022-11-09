package org.shiftworks.controller;

import org.shiftworks.domain.AccountCriteria;
import org.shiftworks.domain.EmployeeVO;
import org.shiftworks.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@AllArgsConstructor
@RequestMapping("/myaccount/*")
public class MyAccountController {
	
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	

	@GetMapping("/get")
	@PreAuthorize("isAuthenticated()")
	public void get(@RequestParam("emp_id") String emp_id, Model model) {
		log.info("get...my account...........");
		model.addAttribute("employee", service.get(emp_id));		
	}
	
	
	@PostMapping("/modify")
	@PreAuthorize("isAuthenticated()")
	public String modify(EmployeeVO empVO, RedirectAttributes rttr) {
		String inputPass=empVO.getPassword();
		String pwd=pwdEncoder.encode(inputPass);
		empVO.setPassword(pwd);
		log.info("modify............my account.................");
		if(service.modifyMyAccount(empVO)) {
			rttr.addFlashAttribute("modifyResult", "success");
		}

		return "redirect:/myaccount/get";
	}
	

}
