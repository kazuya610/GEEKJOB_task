<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
	Object login = session.getAttribute("login");

	if (login == null || !login.equals("OK")){
        session.setAttribute("login", null);
        response.sendRedirect("Data13_login.jsp");
	}

%>


<div>商品登録</div>

<p>商品情報を入力してください。</p>

	<form action ="Data13_auth2" method="post">
		<p>商品ID：<input type="text" name="productID" value="商品ID"></p>
		<p>商品カテゴリー：<input type="text" name="category" value="商品カテゴリー"></p>
		<p>商品名：<input type="text" name="name" value="商品名"></p>
		<p>価格：<input type="text" name="price" value="価格"></p>
		<p>配送料：<input type="text" name="delivery" value="配送料"></p>
		<p>メーカー：<input type="text" name="maker" value="メーカー"></p>
		<p>サイズ：<input type="text" name="size" value="サイズ"></p>
		<p>商品説明文：<textarea name="introduction" rows="4" cols="40"></textarea></p>
		<p>ポイント数：<input type="text" name="point" value="ポイント"></p>
		<p><input type="submit" name="btnSubmit" value="登録"></p><br>
	</form>

	// 各ページへのリンク
	<a href="data13_auth1.jsp">商品登録</a>
	<a href="Data13_auth3">商品一覧</a>
	<a href="Data13_logout">ログアウト</a>

</body>
</html>