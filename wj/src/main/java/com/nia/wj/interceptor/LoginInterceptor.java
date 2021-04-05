package com.nia.wj.interceptor;

import com.nia.wj.pojo.User;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
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

        boolean flag = this.verifyStateOfLoginBySessionId(request, response, handler);
        if (flag){
            return true;
        }

        return true;
    }

    /**
     * 是否以某个字符串开头
     * @param page
     * @param requiredAuthPages
     * @return
     */
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

    /**
     * 验证是否处于登陆状态
     * @param request
     * @param response
     * @param handler
     * @return
     */
    private boolean verifyStateOfLoginBySessionId(HttpServletRequest request, HttpServletResponse response, Object handler){
        // 放行 options 请求，否则无法让前端带上自定义的 header 信息，导致 sessionID 改变，shiro 验证失败
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        Subject subject = SecurityUtils.getSubject();
        // 使用 shiro 验证
        if (!subject.isAuthenticated()) {
            return false;
        }
        return true;
    }
}
