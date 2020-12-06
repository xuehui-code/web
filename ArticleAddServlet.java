package org.example.servlet;

import org.example.dao.ArticleDAO;
import org.example.model.Article;
import org.example.model.User;
import org.example.util.JSONUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;

@WebServlet("/articleAdd")
public class ArticleAddServlet extends AbstractBase{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse reps) throws IOException {
        HttpSession session = req.getSession(false);
        User user = (User)session.getAttribute("user");
        InputStream is = req.getInputStream();
        Article a  = JSONUtil.deserialize(is, Article.class);
        a.setUser_id(user.getId());
        int num = ArticleDAO.insert(a);
        return null;
    }
}
