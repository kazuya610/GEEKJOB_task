<%@page import="jums.JumsHelper" 
        import="jums.UserDataDTO" 
        import="java.time.Year" 
        import="java.util.Calendar" %>
<%
    JumsHelper jh = JumsHelper.getInstance();
    HttpSession hs=request.getSession();
    UserDataDTO udd = (UserDataDTO)hs.getAttribute("resultData");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JUMS変更画面</title>
    </head>
    <body>
    <!-- 【修正・加筆】各項目のvalueに初期値を追加 -->
    <form action="UpdateResult" method="POST">
        名前:
        <input type="text" name="name" value="<%= udd.getName() %>">
        <br><br>

        生年月日:　
        <% 
            Calendar calBirth = Calendar.getInstance();
            calBirth.setTime(udd.getBirthday());
        %>
        <select name="year">
            <option value="">----</option>
            <% for(int i=1950; i<=2010; i++){ %>
            <option value="<%=i%>" <% if(calBirth.get(Calendar.YEAR)==i){out.print("selected = \"selected\"");}%> ><%=i%></option>
            <% } %>
        </select>年
        <select name="month">
            <option value="">--</option>
            <% for(int i = 1; i<=12; i++){ %>
            <option value="<%=i%>" <% if(calBirth.get(Calendar.MONTH)+1==i){out.print("selected = \"selected\"");}%> ><%=i%></option>
            <% } %>
        </select>月
        <select name="day">
            <option value="">--</option>
            <% for(int i = 1; i<=31; i++){ %>
            <option value="<%=i%>"<% if(calBirth.get(Calendar.DATE)==i){out.print("selected = \"selected\"");}%> ><%=i%></option>
            <% } %>
        </select>日
        <br><br>

        種別:
        <br>
            <% for(int i = 1; i<=3; i++){ %>
            <input type="radio" name="type" value="<%=i%>" <%if(udd.getType() == i){out.print("checked = \"checked\"");}%>><%=jh.exTypenum(i)%><br>
            <% } %>
        <br>

        電話番号:
        <input type="text" name="tell" value="<%= udd.getTell() %>">
        <br><br>

        自己紹介文
        <br>
        <textarea name="comment" rows=10 cols=50 style="resize:none" wrap="hard">
            <%= udd.getComment() %>
        </textarea><br><br>
        
        <input type="submit" name="btnSubmit" value="確認画面へ">
        <!-- 【修正・加筆】UpdateResultにuserIDを渡すために追加 -->
        <input type="hidden" name="id"  value="<%= udd.getUserID() %>">
        <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
    </form>
    <!-- 【修正・加筆】詳細画面へ戻るボタンの追加 -->
    <form action="ResultDetail" method="POST">
        <input type="submit" name="back" value="詳細画面へ戻る"style="width:100px">
        <!-- 【修正・加筆】ResultDetailにuserIDを渡すために追加 -->
        <input type="hidden" name="id"  value="<%= udd.getUserID() %>">
        <input type="hidden" name="ac"  value="<%=hs.getAttribute("ac")%>">
    </form>
        <br>
        <%=jh.home()%>
    </body>
</html>
