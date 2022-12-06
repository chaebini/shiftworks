package org.shiftworks.messenger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.shiftworks.domain.ChatRoomDTO;
import org.shiftworks.domain.ChatRoomVO;
import org.shiftworks.domain.ChatVO;
import org.shiftworks.mapper.ChatRoomMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
//context 경로를 알아야 테스트 가능
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class ChatRoomTests {

	@Autowired
	ChatRoomMapper mapper;
	
//	@Test
//	public void testUpdateLastchat() {
//		ChatVO chat = new ChatVO();
//		chat.setRoom_id("1");
//		chat.setContent("test3");
//		chat.setSendtime("2022-10-25 11:58:33");
//		log.info(mapper.updateLastchat(chat));
//	}
	
	@Test
	public void testInsert() {
		ChatRoomDTO chatRoom = new ChatRoomDTO();
		chatRoom.setRoom_name("채팅방3");
		chatRoom.setEmp_id("user1");
		chatRoom.setDept_id("dept1");
		
		log.info(mapper.insertChatRoom(chatRoom));
	}
	
//	@Test
//	public void testGetList() {
//		mapper.getList("U3948709").forEach(chatRoom->log.info(chatRoom));
//	}
	
//	@Test
//	public void testDelete() {
//		mapper.deleteChatRoom(2);
//	}
}
