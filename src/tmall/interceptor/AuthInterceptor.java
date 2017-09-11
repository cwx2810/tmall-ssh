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
		//׼���ַ������飬��Ų���Ҫ��½Ҳ�ܷ��ʵ�ҳ��
		String[] noNeedAuthPage = new String[]{
                "login"};
        //���û�ȡcontext 
        ActionContext ctx = invocation.getInvocationContext();
        //��ȡrequest��response
        HttpServletRequest request= (HttpServletRequest) ctx.get(StrutsStatics.HTTP_REQUEST);
        HttpServletResponse response= (HttpServletResponse) ctx.get(StrutsStatics.HTTP_RESPONSE);
        ServletContext servletContext= (ServletContext) ctx.get(StrutsStatics.SERVLET_CONTEXT);
        //��ȡcontextPath��tmall-ssh
        String contextPath = servletContext.getContextPath();
        //��ȡuri
        String uri = request.getRequestURI();
        //ȥ��ǰ׺/tmall-ssh
        uri =StringUtils.remove(uri, contextPath);
        //����ַ�����fore��ͷ�������ִ��
        if(uri.startsWith("/fore")){
        	//ȡ��fore������ַ���
            String method = StringUtils.substringAfterLast(uri,"/fore" );
            //������ٲ���Ҫ��½���б�
            if(!Arrays.asList(noNeedAuthPage).contains(method)){
            	//��session��ȡ��user����
                User user = (User) ctx.getSession().get("user");
                //���user�����ڣ�����ת����½
                if(null==user){
                    response.sendRedirect("login.jsp");
                    return null;
                }
            }
        }
        //����fore��ͷ����Ӱ��
        return  invocation.invoke();
	}

}
