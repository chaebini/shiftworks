package org.shiftworks.controller;

import java.util.List;

import org.shiftworks.domain.ChatDTO;
import org.shiftworks.domain.ChatRoomDTO;
import org.shiftworks.domain.ChatRoomVO;
import org.shiftworks.domain.ChatVO;
import org.shiftworks.service.ChatRoomService;
import org.shiftworks.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.log4j.Log4j;


@Controller
@Log4j
public class ChatRoomController {

	@Autowired
	private ChatRoomService chatRoomService;
	
	@Autowired
	private ChatService chatService;

	// 채팅방 목록 조회
	@PreAuthorize("isAuthenticated()")
	@GetMapping("/messenger/chat")
	public String chat(Model model, Authentication auth) {

		UserDetails userDetails = (UserDetails) auth.getPrincipal();
		log.info("@ChatRoomController, GET Chat / Username : " + userDetails.getUsername());

		List<ChatRoomVO> chatRoomList = chatRoomService.getList(userDetails.getUsername());
		// List<ChatDTO> chatList = chatService.getChatList(1);

		model.addAttribute("chatRoomList", chatRoomList);
		// model.addAttribute("chatList", chatList);

//			CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//			
//			log.info("==================================");
//			log.info("@ChatController, GET Chat / Username : " + user.getUsername());
//			
//			model.addAttribute("userid", user.getUsername());

		return "messenger/MSG_main";
	}

	// 채팅방 개설
	@PostMapping("/messenger/chat/room")
	@PreAuthorize("isAuthenticated()")
	public String createChatRoom(ChatRoomDTO chatRoom, RedirectAttributes rttr) {

		log.info("@ChatRoomController, POST createChatRoom");
		chatRoomService.insertChatRoom(chatRoom);

		rttr.addFlashAttribute("room_name", chatRoom.getRoom_name());
		return "redirect:/messenger/chat";
	}

	// 선택된 채팅방의 정보 요청
	@GetMapping("/messenger/chat/room/{room_id}")
	@PreAuthorize("isAuthenticated()")
	@ResponseBody
	public ResponseEntity<List<ChatDTO>> getChat(@PathVariable("room_id") String room_id) {
		log.info("@ChatRoomController, GET getChat...............");
		return new ResponseEntity<>(chatService.getChatList(room_id), HttpStatus.OK);
	}
	
	// 메시지 전송 요청
	@PostMapping(value="/messenger/chat/send/{room_id}")
	@ResponseBody
	public ResponseEntity<String> sendChat(@PathVariable("room_id") String room_id, @RequestBody ChatVO chat){
		log.info("@ChatRoomController, POST sendMessage...............");
		log.info("@ChatRoomController, content : "  + chat.getContent());
		
		Integer sendResult = chatService.sendChat(chat);
		Integer updateResult = chatService.updateLastChat(chat);
		
		Integer result = (sendResult == 1 && updateResult == 1) ? 1 : -1;
		
		// insert 유무에 따라 헤더값을 다르게 전달
		return result == 1 ? new ResponseEntity<>("success", HttpStatus.OK) // 200
						: new ResponseEntity<>("fail", HttpStatus.INTERNAL_SERVER_ERROR); // 500
	}
}
