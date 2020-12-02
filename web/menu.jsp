<%@ page import="entity.Menu" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: baobao
  Date: 2020/11/30
  Time: 10:48 下午
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>菜单</title>
</head>
<body>
<div style="height:500px; width:500px; margin:0 auto;text-align: center;border: 2px solid; border-radius: 10px;background-color: palegoldenrod;">
    <h1 align="center">菜单:</h1><br>
    <form action="/menu" method="get">
        搜索价格大于输入值的菜谱：
        <input type="number" name="price">&nbsp;<input type="submit" value="搜索">
        <%List<Menu> menus = (List<Menu>) session.getAttribute("menus");%>
        <table style="margin-top: 80px;margin-left: 160px;border: 2px solid;border-radius: 20px; width: 200px;height: 200px; text-align: center">
            <th>菜谱名：</th>
            <th>菜谱价格：</th>
            <%
                for (Menu menu : menus) {
            %>
            <tr>
                <td><%=menu.getName()%>
                </td>
                <td>￥<%=menu.getPrice()%>.00
                </td>
                <%}%>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
