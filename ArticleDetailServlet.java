package org.example.servlet;


import org.example.dao.ArticleDAO;
import org.example.model.Article;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/articleDetail")
public class ArticleDetailServlet extends AbstractBase{
    @Override
    protected Object process(HttpServletRequest req, HttpServletResponse reps) throws Exception {
        String id = req.getParameter("id"); //request.getParameter()方法  获取http协议提交过来的数据
        Article a = ArticleDAO.query(Integer.parseInt(id));
        return a;
    }
}
