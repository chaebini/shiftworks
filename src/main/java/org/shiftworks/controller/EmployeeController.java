package org.shiftworks.controller;

import org.shiftworks.domain.AccountCriteria;
import org.shiftworks.domain.EmployeeVO;
import org.shiftworks.domain.AccountPageDTO;
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
@RequestMapping("/manager/*")
@AllArgsConstructor
public class EmployeeController {
	@Autowired
	private EmployeeService service;
	
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	@GetMapping("/list")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void list(AccountCriteria cri, Model model) {
		model.addAttribute("list", service.getList(cri));
		
		int total = service.getTotal(cri);
		log.info("total: " + total);
		model.addAttribute("pageMaker", new AccountPageDTO(cri, total));
		
		log.info("list11.............."+model);
		
	}
	
	@GetMapping("/retireelist")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void retireeList(AccountCriteria cri, Model model) {
		model.addAttribute("list", service.getRetireeList(cri));
		
		int total = service.getTotalRetiree(cri);
		log.info("total: " + total);
		model.addAttribute("pageMaker", new AccountPageDTO(cri, total));
		
		log.info("list11.............."+model);
		
	}

	@GetMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void register() {
		
	}
	
	@PostMapping("/register")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String register(EmployeeVO empVO, RedirectAttributes rttr){
		log.info("==========================");
		
		String inputPass=empVO.getPassword();
		String pwd=pwdEncoder.encode(inputPass);
		empVO.setPassword(pwd);
		
		log.info("ready register......................");
		service.register(empVO);
		log.info("register..............1" + empVO);
		rttr.addFlashAttribute("result", empVO.getEmp_id());
		
		return "redirect:/manager/list";
		
	}
	
	
	@GetMapping({"/get", "/modify"})
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void get(@RequestParam("emp_id") String emp_id, @ModelAttribute("cri") AccountCriteria cri, Model model) {
		log.info("get..............");
		model.addAttribute("employee", service.get(emp_id));		
	}
	
	
	@PostMapping("/modify")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String modify(EmployeeVO empVO, @ModelAttribute("cri") AccountCriteria cri,
				RedirectAttributes rttr) {
		String inputPass=empVO.getPassword();
		String pwd=pwdEncoder.encode(inputPass);
		empVO.setPassword(pwd);
		log.info("modify.............................");
		if(service.modify(empVO)) {
			rttr.addFlashAttribute("result", "success");
		}
		
		rttr.addAttribute("pageNum",cri.getPageNum());
		rttr.addAttribute("amount",cri.getAmount());
		
		return "redirect:/manager/list";
	}
	
	@PostMapping("/remove")
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String remove(@RequestParam("emp_id") String emp_id, 
			@ModelAttribute AccountCriteria cri, RedirectAttributes rttr) {
		log.info("remove............."+emp_id);
		if(service.remove(emp_id)) {
			rttr.addAttribute("result", "success");
		}		
		return "redirect:/manager/list";
	}
	
	
}
