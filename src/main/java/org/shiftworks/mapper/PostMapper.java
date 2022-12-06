package org.shiftworks.mapper;

import java.util.List;

import org.shiftworks.domain.BoardCriteria;
import org.shiftworks.domain.BoardVO;
import org.shiftworks.domain.HistoryVO;
import org.shiftworks.domain.PostVO;
import org.shiftworks.domain.ScrapVO;
import org.shiftworks.domain.Temp_BoardVO;

public interface PostMapper {
	
	public int insertPost(PostVO postvo);
	
	//public List<PostVO> getListWithPaging(Criteria cri);
	
	public List<PostVO> getListWithPagingSearch(BoardCriteria cri);
	
	public int updatePost(PostVO postvo);
	
	public int deletePost(int post_id);
	
	public PostVO getPost(int post_id);
	
	public int getTotal(int b_id);
	
	public int scrapPost(ScrapVO vo);
	
	public int temporalPost(Temp_BoardVO vo);
	
	public Temp_BoardVO temporalSelect(String emp_id);
	
	public int insertHistory(HistoryVO vo);
	
	public List<HistoryVO> selectHistory(String emp_id);
	
	public String getDeptId(String emp_id);
	
	public PostVO selectPrev(int post_id);
	
	public PostVO selectNext(int post_id);
	
	public int insertNewBoard(BoardVO vo);
	
	public List<BoardVO> selectBoardList();
	
	public List<BoardVO> allBoardList();
	
	public int deleteBoard(int b_id);
	

}
