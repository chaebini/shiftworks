package org.shiftworks.service;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiftworks.domain.AccountCriteria;
import org.shiftworks.domain.EmployeeVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class EmployeeServiceTests {
	
	@Autowired
	private EmployeeService service;

//	@Test
//	public void testRegister() {
//		
//		EmployeeVO empVO = new EmployeeVO();
//		empVO.setEmp_id("user11");
//		empVO.setDept_id("dept2");
//		empVO.setName("u11");
//		empVO.setPassword("pw11");
//		
//		service.register(empVO);
//		log.info("���� ��ϵ� ���: " + empVO.getEmp_id());
//	
//	}
	
	@Test
	public void testGetList() {
		AccountCriteria cri = new AccountCriteria();
		service.getList(cri).forEach(employee->log.info(employee));
	}
	
//	@Test
//	public void testGet() {
//		log.info(service.get("user3"));
//	}
	
//	@Test
//	public void testUpdate() {
//		EmployeeVO empVO = new EmployeeVO();
//		empVO = service.get("user11");
//		
//		if(empVO == null) {
//			return;
//		}
//		empVO.setDept_id("dept_3");
//		log.info("modify result: " + service.modify(empVO));
//	}
	
//	@Test
//	public void testDelete() {
//		log.info("remove result: " + service.remove("user11"));
//	}
	

}
