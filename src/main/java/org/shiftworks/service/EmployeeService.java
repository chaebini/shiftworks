package org.shiftworks.service;

import java.util.List;

import org.shiftworks.domain.AccountCriteria;
import org.shiftworks.domain.EmployeeVO;

public interface EmployeeService {
	
	//관리자 계정 메뉴
	public void register(EmployeeVO empVO);//계정 생성
	public EmployeeVO get(String emp_id);//계정 상세 정보
	public boolean modify(EmployeeVO empVO);//계정정보 수정
	public boolean remove(String emp_id);//계정 삭제
	public List<EmployeeVO> getList(AccountCriteria cri);//현 근무자 전체 리스트
	public int getTotal(AccountCriteria cri);//현 근무자 계정 총 개수
	public List<EmployeeVO> getRetireeList(AccountCriteria cri);//퇴사자 전체 리스트
	public int getTotalRetiree(AccountCriteria cri);//퇴사자 계정 총 개수
	
		
	//일반 계정 메뉴
	public boolean modifyMyAccount(EmployeeVO empVO);//나의 계정 정보 수정
	
}
