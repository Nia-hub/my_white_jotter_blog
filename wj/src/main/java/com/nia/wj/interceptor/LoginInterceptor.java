package com.nia.wj.interceptor;

import com.nia.wj.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @program: wj
 * @description: 登录拦截器
 * @author: ganwei
 * @create: 2021-04-04 10:30
 **/
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        HttpSession session = request.getSession();
        String contextPath=session.getServletContext().getContextPath();//项目名
        String[] requireAuthPages = new String[]{
                "index",
        };

        String requestURI = request.getRequestURI();//例：/store/UserServlet，url除去IP：端口，以及？后的参数剩下的

        requestURI = StringUtils.remove(requestURI, contextPath+"/"); //删除 项目名/ 剩下：UserServlet
        String page = requestURI;

        if(begingWith(page, requireAuthPages)){//若uri以index开头，或者MyWebConfigurer中配置的拦截请求，就判断是否登录，没登录调到登录页面
            User user = (User) session.getAttribute("user");
            if(user==null) {
                response.sendRedirect("login");
                return false;
            }
        }
        return true;
    }

    private boolean begingWith(String page, String[] requiredAuthPages) {
        boolean result = false;
        for (String requiredAuthPage : requiredAuthPages) {
            if(StringUtils.startsWith(page, requiredAuthPage)) {
                result = true;
                break;
            }
        }
        return result;
    }
}
