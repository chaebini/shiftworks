package org.shiftworks.messenger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiftworks.domain.ChatDTO;
import org.shiftworks.domain.ChatVO;
import org.shiftworks.mapper.ChatMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;


@RunWith(SpringJUnit4ClassRunner.class)
//context 경로를 알아야 테스트 가능
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChatTests {

	@Autowired
	ChatMapper mapper;
	
	// 지난 채팅 5개 가져오긴
//	@Test
//	public void testGetList() {
//				
//		mapper.getList("1").forEach(chat->log.info(chat));
//	}
	
	// 채팅 저장하기
	@Test
	public void testInsert() {
		
		ChatVO chat = new ChatVO();
		chat.setContent("안녕하세요");
		chat.setSender("user2");
		chat.setSendtime("2022-11-04 11:58:33");
		chat.setRoom_id("2");
		
		int re = mapper.insertChat(chat);
		log.info(chat);
		log.info("re = " + re);
	}
}
