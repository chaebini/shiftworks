package org.shiftworks.service;

import java.util.List;

import org.shiftworks.domain.BoardCriteria;
import org.shiftworks.domain.HistoryVO;
import org.shiftworks.domain.BoardPageDTO;
import org.shiftworks.domain.BoardVO;
import org.shiftworks.domain.FileVO;
import org.shiftworks.domain.PostVO;
import org.shiftworks.domain.ScrapVO;
import org.shiftworks.domain.Temp_BoardVO;
import org.shiftworks.mapper.FileMapper;
import org.shiftworks.mapper.PostMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Service
@AllArgsConstructor
@Log4j
public class PostServiceImpl implements PostService {
	
	private PostMapper mapper;
	
	private FileMapper filemapper;
	
	@Transactional
	@Override
	public int insertPost(PostVO postvo) {
		
		int result = mapper.insertPost(postvo);
		//post 테이블에 글 삽입 시 filelist 도 함께 삽입
		if(postvo.getFileList()==null) {
			return result;
		}
		postvo.getFileList().forEach(file ->{
			
			filemapper.insertBoardFile(file);
		});
		
		return result;
		
		
	}


	/*
	 * @Override public List<PostVO> getList(Criteria cri) { return
	 * mapper.getListWithPaging(cri); }
	 */
	
	@Override
	public BoardPageDTO getListSearch(BoardCriteria cri) {
		List<PostVO> list =  mapper.getListWithPagingSearch(cri);
		BoardPageDTO dto = new BoardPageDTO(cri, mapper.getTotal(cri.getB_id()), list);
	
		return dto;
	}

	@Override
	public int updatePost(PostVO postvo) {
		return mapper.updatePost(postvo);
	}

	@Override
	public int deletePost(int post_id) {
		return mapper.deletePost(post_id);
	}

	@Override
	public PostVO getPost(int post_id) {
		
		PostVO vo = mapper.getPost(post_id);
		vo.setFileList(filemapper.selectBoardFile(post_id));
		return vo;
	}

	@Override
	public int getTotal(int b_id) {
		return mapper.getTotal(b_id);
	}


	@Override
	public int scrapPost(ScrapVO vo) {
		return mapper.scrapPost(vo);
	}


	@Override
	public int temporalPost(Temp_BoardVO vo) {
		return mapper.temporalPost(vo);
	}


	@Override
	public Temp_BoardVO temporalSelect(String emp_id) {
		return mapper.temporalSelect(emp_id);
	}


	@Override
	public int insertHistory(HistoryVO vo) {
		return mapper.insertHistory(vo);
	}


	@Override
	public List<HistoryVO> selectHistory(String emp_id) {
		return mapper.selectHistory(emp_id);
	}


	@Override
	public String getDeptId(String emp_id) {
		return mapper.getDeptId(emp_id);
	}


	@Override
	public PostVO selectPrev(int post_id) {
		return mapper.selectPrev(post_id);
	}


	@Override
	public PostVO selectNext(int post_id) {
		return mapper.selectNext(post_id);
	}


	@Override
	public int insertNewBoard(BoardVO vo) {
		return mapper.insertNewBoard(vo);
	}


	@Override
	public List<BoardVO> selectBoardList() {
		return mapper.selectBoardList();
	}


	@Override
	public List<BoardVO> allBoardList() {
		return mapper.allBoardList();
	}


	@Override
	public int deleteBoard(int b_id) {
		return mapper.deleteBoard(b_id);
	}




	

}
