package controller;

import DBUtil.Db;
import entity.Menu;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("gbk");
        List<Menu> lists = new ArrayList<>();
        String price = req.getParameter("price");
        ResultSet resultSet;
        PreparedStatement preparedStatement;
        try {
            if (price == null) {
                String sql = "select * from menu";
                 preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Menu menu = new Menu();
                        menu.setId(resultSet.getInt("id"));
                        menu.setName(resultSet.getString("name"));
                        menu.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(menu);
                    }
                }
            } else {
                String sql = "select * from menu where price > ?";
                preparedStatement = Db.createPreparedStatement(sql);
                if (preparedStatement != null) {
                    preparedStatement.setObject(1,price);
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Menu menu = new Menu();
                        menu.setId(resultSet.getInt("id"));
                        menu.setName(resultSet.getString("name"));
                        menu.setPrice(Integer.valueOf(resultSet.getString("price")));
                        lists.add(menu);
                    }
                }
            }
            Db.closeAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        HttpSession session = req.getSession();
        session.setAttribute("menus", lists);
        resp.sendRedirect("/menu.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doGet(req, resp);
    }
}
