package org.shiftworks.mapper;

import java.util.List;

import org.shiftworks.domain.AccountCriteria;
import org.shiftworks.domain.EmployeeVO;

public interface EmployeeMapper {
	public EmployeeVO read(String emp_id);
	
	//관리자 계정 메뉴
	public List<EmployeeVO> getList(AccountCriteria cri);//현 근무자 전체 리스트 
	public List<EmployeeVO> getRetireeList(AccountCriteria cri);//퇴사자 리스트
	public List<EmployeeVO> getListWithPaging	(AccountCriteria cri);//검색 및 페이징 처리
	public void insert(EmployeeVO empVO);//계정 생성
	public EmployeeVO detail(String emp_id);//계정 상세 정보
	public int delete(String emp_id);//계정 삭제
	public int update(EmployeeVO empVO);//계정정보 수정
	public int getTotalCount(AccountCriteria cri);//현 근무자 계정 총 개수
	public int getTotalRetireeCount(AccountCriteria cri);//퇴직자 계정 개수
	public List<EmployeeVO> getListWithRetireePaging(AccountCriteria cri);
	//일반 계정 메뉴
	public int updateMyAccount(EmployeeVO empVO);//나의 계정정보 수정
	
	
}