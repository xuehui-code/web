package org.example.filter;

import org.example.model.JSONResponse;
import org.example.util.JSONUtil;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 配置用户统一会话管理的过滤器 匹配所有请求路径
 * 服务端资源：/login 不用校验sesssion 其他都要校验 若不通过
 * 前端资源：/jsp校验session
 */



@WebServlet("/*")
public class LoginFilter implements Filter{
    @Override
    public void  init(FilterConfig filterConfig)throws ServletException{

    }

    @Override
    public  void doFilter(ServletRequest request,ServletResponse response,FilterChain chain) throws ServletException, IOException {
        HttpServletRequest req =(HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse)response;
        String servletPath = req.getServletPath();
        //
        if(servletPath.startsWith("/js/")||servletPath.startsWith("/static/")||
                servletPath.startsWith("/view/")||servletPath.equals("/login")){
            chain.doFilter(request, response);
        }else{
            //获取session对象 没有返回null
            HttpSession session = req.getSession(false);
            //验证用户是否登录 若没有登录还需要根据前端或后端做不同处理
            if(session == null||session.getAttribute("user")==null){
                //前端重定向到登录页面
                if(servletPath.startsWith("/jsp/")){
                    resp.sendRedirect(basePath(req)+"/view/login.html");
                }else{  //后端返回401
                    resp.setStatus(401);
                    resp.setCharacterEncoding("UTF-8");
                    resp.setContentType("application/json");
                    JSONResponse json = new JSONResponse();
                    json.setCode("LOG000");
                    json.setMessage("用户未登录不允许访问");
                    PrintWriter pw = resp.getWriter();
                    pw.println(JSONUtil.serialize(json));
                    pw.flush();
                    pw.close();
                }
            }else{
                chain.doFilter(request, response);  //敏感资源已登录  继续执行
            }
        }
    }

    //根据http请求 动态的获取访问路径（服务路径之前的部分）
    public static String basePath(HttpServletRequest req){
        String schema = req.getScheme();
        String host = req.getServerName();
        int port = req.getServerPort();
        String contextPath = req.getContextPath();//获取应用上下文路径
        return schema+"://"+host+":"+port+contextPath;    //绝对路径
    }

    @Override
    public void destroy() {

    }
}