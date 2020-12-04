package org.example.Servlet;

import org.example.exception.AppException;
import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public abstract class AbstractBaseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //设置请求体的编码格式
        req.setCharacterEncoding("UTF-8");
        //设置响应体编码
        resp.setCharacterEncoding("UTF-8");
        //设置响应体数据类型（浏览器采取什么方式执行）
        resp.setContentType("application/json");

        //Session会话管理，除注册登录接口，其他都需要登录后访问
        //req.getServletPath() 获取请求服务路径
        //TODO

        JSONResponse json = new JSONResponse();
        try{
            //调用子类重写方法
            Object data = process(req, resp);
            //子类process 方法
            json.setSuccess(true);
            json.setData(data);
        }catch (Exception e){
            //异常如何处理？JDBC异常SQLException，JSON异常，自定义异常返回错误消息
            e.printStackTrace();
            //json。setSuccess（false）不用设置了 因为new的时候就是
            String code = "UNKNOWN";
            String s = "未知的错误";
            if(e instanceof AppException){
                code = ((AppException) e).getCode();
                s = e.getMessage();
            }
            json.setCode(code);
            json.setMessage(s);
        }
        PrintWriter pw = resp.getWriter();
        pw.println(JSONUtil.serialize(json));//序列化
        pw.flush();
        pw.close();
    }

    protected abstract Object process(HttpServletRequest req,
                                      HttpServletResponse resp) throws Exception;
}
