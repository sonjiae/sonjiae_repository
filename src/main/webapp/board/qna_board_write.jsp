<!-- 문의 게시판 글쓰기. 회원들만 쓸 수 있도록 해야함. 비회원은 X -->
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
String id = (String)session.getAttribute("id"); 
// qna_board_list.jsp에서 넘어온 값.
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<script>
	function addboard() {
		boardform.submit();
	}
</script>
</head>
<body>
<!-- 게시글 등록 -->
	<form action="./BoardAddAction.do" method="post" name="boardform">
		<input type="hidden" name="M_ID" value="<%=id %>">
		<!-- M_ID???? -->
		
		<table>
			<tr align="center" valign="middle">
				<td colspan="5">문의 게시판</td>
			</tr>
			
			<tr>
				<td style="font-family: 돋움; font-size: 12" height="16">
					<div align="center">글쓴이</div>
				</td>
				<td>
					<%=id %>
				</td>
			</tr>
			
			<tr>
				<td style="font-family: 돋움; font-size: 12" height="16">
					<div align="center">제 목</div>
				</td>
				<td>
					<input name="B_SUB" type="text" size="50" maxlength="100" value="">
				</td>
			</tr>
			
			<tr>
				<td style="font-family: 돋움; font-size: 12" height="16">
					<div align="center">내 용</div>
				</td>
				<td>
					<textarea name="B_CONTENT" cols="67" rows="15"></textarea>
				</td>
			</tr>
			
			<tr bgcolor="cccccc">
				<td colspan="2" style="height: 1px"></td>
			</tr>
			
			<tr>
				<td colspan="2">&nbsp;</td>
			</tr>
			
			<tr align="center" valign="middle">
				<td colspan="5">
					<a href="javascript:addboard()">[등록]</a>&nbsp;&nbsp; <!-- BoardAddAction.java로 이동 -->
					<a href="javascript:history.go(-1)">[뒤로]</a>
				</td>
			</tr>
		
		</table>
	
	</form>

</body>
</html>