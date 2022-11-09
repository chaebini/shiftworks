package org.shiftworks.mapper;

import java.util.List;

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
public class EmployeeMapperTests {

	@Autowired
	private EmployeeMapper mapper;
	
//	@Test
//	public void testRead() {
//		EmployeeVO vo = mapper.read("user1");
//		log.info(vo);
//	}
	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(employee->log.info(employee));
//	}
//	
//	@Test
//	public void testGetList() {
//		mapper.getList().forEach(employee ->log.info(employee));
//	}
	
//	@Test
//	public void testInsert() {
//		EmployeeVO empVO = new EmployeeVO();
//		empVO.setEmp_id("user11");
//		empVO.setDept_id("dept2");
//		empVO.setName("u11");
//		empVO.setPassword("pw11");
//		
//		mapper.insert(empVO);
//		
//		log.info(empVO);
//	}
	
//	@Test
//	public void testDetail() {
//		
//		EmployeeVO empVO = mapper.detail("user11");
//		
//		log.info(empVO);
//	}
	
//	@Test
//	public void testDelete() {
//		
//		log.info("Delete Count: " + mapper.delete("user11"));
//	}
//	
//	@Test
//	public void testUpdate() {
//		
//		EmployeeVO empVO = new EmployeeVO();
//		empVO.setEmp_id("user1");
//		empVO.setDept_id("dept2");
//		
//		int count = mapper.update(empVO);
//		log.info("Update Count: " + empVO);
//		
//	}
	@Test
	public void testGetListWithPaging() {
		//검색 테스트
		AccountCriteria cri = new AccountCriteria();
		cri.setKeyword("회계");
		cri.setType("D");
		cri.setPageNum(1);
		cri.setAmount(10);
		
		mapper.getListWithPaging(cri);
	}

}
