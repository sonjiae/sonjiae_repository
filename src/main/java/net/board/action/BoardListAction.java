package net.board.action;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.board.db.BoardDAO;
import net.board.action.Action;
import net.board.action.ActionForward;

public class BoardListAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		ActionForward forward = new ActionForward();
		HttpSession session = request.getSession();
		
		String id = "1"; // 임의. view 테스트 용 
		// String id = (String)session.getAttribute("id"); // MemberLoginAction.java 로그인할 때 넘어온 값
		
		
		if(id == null) { // 넘어온 세션 값이 없다면
			forward.setRedirect(true);
			forward.setPath("./MemberLogin.me"); // 다시 로그인으로
			return forward;
		}
		
		BoardDAO boarddao = new BoardDAO();
		List boardlist = new ArrayList();
		
		int page = 1;
		int limit = 10;
		
		if(request.getParameter("page") != null) {  // qna_board_list.jsp(97줄 등)에서 [이전]이나 [다음]등을 누르고 넘어왔다면 
			page = Integer.parseInt(request.getParameter("page"));
		}
		
		int listcount = boarddao.getListCount(); // 총 문의게시글 개수 받아옴
		System.out.println(listcount);
		boardlist = boarddao.getBoardList(page, limit); // 게시글을 최대 10개씩 가져옴
		
		// 총 페이지
		int maxpage = (int)((double)listcount / limit + 0.95); // 올림 처리
		
		// 현재 페이지에 보여줄 시작 페이지 수 1, 11, 21 등
		int startpage = (((int)((double)page / 10 + 0.9)) - 1) * 10 + 1;
		
		// 현재 페이지에 보여줄 마지막 페이지수 10, 20, 30 등
		int endpage = maxpage;
		
		if(endpage > startpage + 10 -1) endpage = startpage + 10 - 1;
		
		request.setAttribute("page", page); // 현재 페이지수
		request.setAttribute("maxpage", maxpage); // 최대 페이지수
		request.setAttribute("startpage", startpage); // 현재 페이지에 표시할 첫 페이지수
		request.setAttribute("endpage", endpage); // 현재 페이지에 표시할 끝 페이지수
		request.setAttribute("listcount", listcount); // 글 수
		request.setAttribute("boardlist", boardlist); 
		
		forward.setRedirect(false); // request.setAttribute은 session.setAttribute와 달리 RequestDispatcher 방식으로 이동해야함
		forward.setPath("./board/qna_board_list.jsp"); // qna_board_list.jsp로 이동
		return forward;
		
	}

}
