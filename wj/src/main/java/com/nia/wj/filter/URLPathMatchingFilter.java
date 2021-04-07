package com.nia.wj.filter;


import com.nia.wj.service.AdminPermissionService;
import com.nia.wj.utils.SpringContextUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.PathMatchingFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * @ClassName URLPathMatchingFilter
 * @Description TODO
 * @Author ganwei
 * @Date 2021/4/7 22:33
 * @VERSION 1.0
 **/
public class URLPathMatchingFilter extends PathMatchingFilter {

    private AdminPermissionService adminPermissionService;

    @Override
    protected boolean onPreHandle(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        // 放行 options 请求
        if (HttpMethod.OPTIONS.toString().equals((httpServletRequest).getMethod())) {
            httpServletResponse.setStatus(HttpStatus.NO_CONTENT.value());
            return true;
        }

        /**
         * 在 Shiro 的配置文件中，我们不能把 URLPathMatchingFilter 用 @Bean 被 Spring 管理起来。
         * 原因是 Shiro 存在 bug, 这个也是过滤器，ShiroFilterFactoryBean 也是过滤器，当他们都出现的时候，
         * 默认的什么 anno,authc 过滤器就失效了。所以不能把他声明为 @Bean。
         *
         * 因此，我们无法在 URLPathMatchingFilter 中使用 @Autowired 注入 AdminPermissionService 类，
         * 所以需要借助一个工具类利用 Spring 应用上下文获取 AdminPermissionService 的实例
         */
        if (null == adminPermissionService) {
            adminPermissionService = SpringContextUtils.getContext().getBean(AdminPermissionService.class);
        }

        String requestAPI = getPathWithinApplication(request);
        System.out.println("访问接口：" + requestAPI);

        Subject subject = SecurityUtils.getSubject();

        //判断是否登录（必须登录，remember me也不行）
        if (!subject.isAuthenticated()) {
            System.out.println("需要登录");
            return false;
        }

        // 判断访问接口是否需要过滤（数据库中是否有对应信息）
        boolean needFilter = adminPermissionService.needFilter(requestAPI);
        if (!needFilter) {
            System.out.println("接口：" + requestAPI + "无需权限");
            return true;
        } else {
            System.out.println("验证访问权限：" + requestAPI);
            // 判断当前用户是否有相应权限
            boolean hasPermission = false;
            //当前用户的登录名
            String loginName = subject.getPrincipal().toString();
            //根据用户名查出其对应的所有菜单的url（去重后的）,判断当前访问的api是否拥有权限
            Set<String> permissionAPIs = adminPermissionService.listPermissionURLsByUser(loginName);
            for (String api : permissionAPIs) {
                if (api.equals(requestAPI)) {
                    hasPermission = true;
                    break;
                }
            }

            if (hasPermission) {
                System.out.println("访问权限：" + requestAPI + "验证成功");
                return true;
            } else {
                System.out.println("当前用户没有访问接口" + requestAPI + "的权限");
                return false;
            }
        }
    }
}
