package com.peazh.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.peazh.entity.Board;
import com.peazh.service.BoardService;

@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@GetMapping("/board")
	public String board(Model model) {
		
		int count = boardService.count();
		model.addAttribute("count",count);
		
		List<Board> list = boardService.list();
		model.addAttribute("list",list);
		return "board";
	}
	
	@GetMapping("/write")
	public String write() {
		return "write";
	}
	
   @PostMapping("/write")
   public String write(@RequestParam("img") MultipartFile img, HttpServletRequest request) throws IOException {// img는 img로 받고 나머지는 request로 받음
      // System.out.println(board);
      //System.out.println(img.getSize());
      //System.out.println(img.getOriginalFilename());
      //System.out.println(Arrays.toString(img2Byte));
      Board board = new Board();
      board.setTitle(request.getParameter("title"));
      board.setContent(request.getParameter("content"));
      board.setName("홍길동");
      board.setDate(LocalDateTime.now());//현재시간
      if(img.getSize() > 0) {// 파일 입력한게 0보다 크다면 = 파일이 있다면
         String fileName = img.getOriginalFilename();// 파일을 fileName으로 저장할 것
         //String extension = fileName.substring(fileName.lastIndexOf(".") + 1);
         //System.out.println("확장자" + extension);
         //String regExp = "/([^\\s]+(?=\\.(jpg|gif|png))\\.\\2)/";
         //boolean b = Pattern.matches("([^\\s]+(\\.(?i)(jpg|gif|png))?)", fileName);
         //System.out.println(b);
         
         if(Pattern.matches("([^\\s]+(\\.(?i)(jpg|gif|png))?)", fileName)) {// 파일명이 .뒤에 있는 녀석이 확장자명이 jpg, gif, png로 끝나?    
        	 // 데이터베이스에 저장하려고 이렇게 한거임
        	 // byte를 인코딩해서 저렇게 넣어줘~ 그림을 byte단위로 깨서 저장한 
            byte[] img2Byte = Base64.encodeBase64(img.getBytes());
            board.setImg("data:image/png;base64," + new String(img2Byte));         
         }
      }
      boardService.save(board);
      return "redirect:/board";
   }
   
   @GetMapping("/detail")
   public String board(@RequestParam("no") String id, Model model) {
	   Board detail = boardService.findById(id).get();
	   //Optional
	   model.addAttribute("detail", detail);
	   return "detail";
   }
   
   @GetMapping("/sns")
   public String sns(Model model) {
	   // 정렬 옵션
      List<Sort.Order> order = new ArrayList<Sort.Order>();
      // 날짜 순으로 정렬할거임
      order.add(Sort.Order.desc("date"));
      
      //										최신글 10개만 뜨기
      Page<Board> list = boardService.findAll(PageRequest.of(0, 10, Sort.by(order)));
      model.addAttribute("list", list);
      return "sns";
   }
   
   @GetMapping("/delete")
   public String delete(@RequestParam("no") String id) {
	   // id를 기준으로 찾아서 삭제하기
	   boardService.deleteById(id);
      return "redirect:/board";
   }
   
   @GetMapping("/update")
   public String update(@RequestParam("no") String id, Model model) {
      Board detail = boardService.findById(id).get();
      model.addAttribute("detail", detail);
      return "update";
   }
   
   @PostMapping("/update")
   public String update(Board board) {
      System.out.println(board.getTitle());
      System.out.println(board.getContent());
      // 아이디가 있으면 수정
      board.setName("수정함.");
      board.setDate(LocalDateTime.now());
      boardService.save(board);
      return "redirect:/board";
   }
	
}
