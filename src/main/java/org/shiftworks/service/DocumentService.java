package org.shiftworks.service;

import java.util.List;

import org.shiftworks.domain.DocumentCriteria;
import org.shiftworks.domain.DocumentPageDTO;
import org.shiftworks.domain.ApprovalCriteria;
import org.shiftworks.domain.ApprovalDTO;
import org.shiftworks.domain.ApprovalListDTO;
import org.shiftworks.domain.ApprovalPageDTO;
import org.shiftworks.domain.ApprovalVO;
import org.shiftworks.domain.BoardPageDTO;
import org.shiftworks.domain.PostVO;
import org.shiftworks.domain.ScrapDTO;
import org.shiftworks.domain.ScrapVO;

public interface DocumentService {
	
	public DocumentPageDTO getMyDocumentListWithPaging(DocumentCriteria cri);
	
	public PostVO getMyDocument(PostVO vo);
	
	public ScrapDTO getScrapListWithPaging(DocumentCriteria cri);
	
	public ScrapVO getScrap(ScrapVO vo);
	
	public DocumentPageDTO deptSelectList(DocumentCriteria cri);
	
	public PostVO deptSelect(PostVO vo);
	
	public ApprovalListDTO approvalSelectList(ApprovalCriteria cri);
	
	public  ApprovalVO approvalSelect(ApprovalVO vo);
	
	public String getDept(String emp_id);
	
	
}
