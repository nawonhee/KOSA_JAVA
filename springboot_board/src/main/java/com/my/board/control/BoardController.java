package com.my.board.control;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.my.board.dto.Board;
import com.my.board.dto.Reply;
import com.my.board.service.BoardService;
import com.my.exception.AddException;
import com.my.exception.FindException;
import com.my.exception.ModifyException;
import com.my.exception.RemoveException;

@RestController
@RequestMapping("/board")
public class BoardController {
	
	private Logger log = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private BoardService service;
	
	@GetMapping("/list")
	public List<Board> list() throws FindException{
		return service.findAll();
	}

	@GetMapping("/{boardNo}")
	public Board info(@PathVariable int boardNo) throws FindException{
		return service.findByBoardNo(boardNo);
	}
	
	//POST /board
	@PostMapping(value="",produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> write(@RequestBody Board board) throws AddException {
		service.write(board);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.12:5500"); 
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<> ("게시글이 작성되었습니다",headers,HttpStatus.OK);
		//return "글쓰기" + board;
	}
	
	//PUT /board/1
	@PutMapping(value="/{boardNo}", produces="application/json;charset=UTF-8")
	public ResponseEntity<?> modify(@PathVariable int boardNo, @RequestBody Board board) throws ModifyException{
		board.setBoardNo(boardNo);
		service.modify(board);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.12:5500"); 
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<> ("게시글이 수정되었습니다",headers,HttpStatus.OK);
		//return "글수정"+board;
	}
	
	//DELETE /board/1
	@DeleteMapping(value="/{boardNo}", produces = "application/json;charset=UTF-8")
	public ResponseEntity<?> remove(@PathVariable int boardNo) throws RemoveException {
		service.remove(boardNo);
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.12:5500"); 
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<> ("게시글이 삭제되었습니다",headers,HttpStatus.OK);
		//return "글삭제"+boardNo;
	}
	
	//POST  /board/reply/1,  
	//POST  /board/reply/1/9
	@PostMapping(value= {"reply/{boardNo}/{parentNo}", "reply/{boardNo}"})
	public ResponseEntity<?> writeReply(@PathVariable int boardNo, 
										@PathVariable(name="parentNo") Optional<Integer> optParentNo,
										@RequestBody Reply reply) throws AddException{
		reply.setReplyBoardNo(boardNo);
		if(!optParentNo.isPresent()) { //parentNo가 없는 경우 --일반 답글쓰기
			service.writeReply(reply);
		}else { //parentNo가 있는 경우 -- 답글의 답글
			Integer parentNo = optParentNo.get();
			reply.setReplyParentNo(parentNo);
			service.writeReply(reply);
		}
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.12:5500"); 
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<>("댓글이 작성되었습니다", headers, HttpStatus.OK);
	}
	
	//PUT     /board/reply/15
	@PutMapping(value="reply/{replyNo}")
	public ResponseEntity<?> modifyReply(@PathVariable int replyNo,
										@RequestBody Reply reply) throws ModifyException{
		System.out.println(replyNo);
		log.debug("DEBUG 메시지");
		log.info("INFO 메시지");
		log.warn("WARN 메시지");
		log.error("ERROR 메시지");
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.12:5500"); 
		headers.add("Access-Control-Allow-Credentials", "true");
		
		reply.setReplyNo(replyNo);
		service.updateReply(reply);
		return new ResponseEntity<> ("댓글이 수정되었습니다", headers, HttpStatus.OK);
	}
	
	//DELETE  /board/reply/9
	@DeleteMapping(value="reply/{replyNo}")
	public ResponseEntity<?> removeReply(@PathVariable int replyNo) throws RemoveException{
		service.deleteReply(replyNo);
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Type", "text/html; charset=UTF-8");
		headers.add("Access-Control-Allow-Origin", "http://192.168.1.12:5500"); 
		headers.add("Access-Control-Allow-Credentials", "true");
		return new ResponseEntity<>("댓글이 삭제되었습니다", headers, HttpStatus.OK);
	}
}
