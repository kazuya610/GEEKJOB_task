<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8" import="java.util.*,java.io.*,java.net.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<h1>在庫管理システム</h1>
<h2>ログイン画面</h2>

<p>ユーザーIDとパスワードを入力してください。</p>

<%
	Object status = session.getAttribute("status");

	if (status != null){
		out.println("IDかパスワードが間違っています<br>");
		out.println("再度入力してください<br><br>");
	}

	session.setAttribute("status", null);
%>

<form action ="Data13_logincheck" method="post">
<p>ユーザーID：　<input type="text" name="user" value=""></p>
<p>パスワード：　<input type="text" name="pass" value=""></p>
<p><input type="submit" name="btnSubmit" value="ログイン"></p>
</form>

</body>
</html>