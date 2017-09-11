package tmall.interceptor;

import java.util.Arrays;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.StrutsStatics;
import org.springframework.beans.factory.annotation.Autowired;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

import tmall.pojo.User;
import tmall.service.OrderItemService;

public class AuthInterceptor extends AbstractInterceptor {
	
	@Autowired
    OrderItemService orderItemService;

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		//准备字符串数组，存放不需要登陆也能访问的页面
		String[] noNeedAuthPage = new String[]{
                "login"};
        //调用获取context 
        ActionContext ctx = invocation.getInvocationContext();
        //获取request和response
        HttpServletRequest request= (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response= (HttpServletResponse) ctx.get(StrutsStatics.HTTP_RESPONSE);
        ServletContext servletContext= (ServletContext) ctx.get(StrutsStatics.SERVLET_CONTEXT);
        //获取contextPath：tmall-ssh
        String contextPath = servletContext.getContextPath();
        //获取uri
        String uri = request.getRequestURI();
        //去掉前缀/tmall-ssh
        uri =StringUtils.remove(uri, contextPath);
        //如果字符串以fore开头，则继续执行
        if(uri.startsWith("/fore")){
        	//取出fore后面的字符串
            String method = StringUtils.substringAfterLast(uri,"/fore" );
            //如果不再不需要登陆的列表
            if(!Arrays.asList(noNeedAuthPage).contains(method)){
            	//从session中取出user对象
                User user = (User) ctx.getSession().get("user");
                //如果user不存在，就跳转到登陆
                if(null==user){
                    response.sendRedirect("login.jsp");
                    return null;
                }
            }
        }
        //不以fore开头，不影响
        return  invocation.invoke();
	}

}
