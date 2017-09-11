package tmall.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import com.opensymphony.xwork2.ActionContext;

import tmall.pojo.User;

public class ForeAction extends Action4Result {
	
	String msg;
	
	@Action("forehome")
    public String home() {
        categorys = categoryService.list();
        productService.fill(categorys);
        productService.fillByRow(categorys);
        return "home.jsp";
    }
	
	@Action("forelogin")
    public String login() {
		//ת���˺ţ�������ע��
        user.setName(HtmlUtils.htmlEscape(user.getName()));
        //�����˺������ȡuser������userService����дһ����ȡ����ķ���
        User user_session = userService.get(user.getName(),user.getPassword());
        //�������Ϊ�գ����ص�½ҳ��
        if(null==user_session){
            msg= "�˺��������";
            return "login.jsp"; 
        }
        //���������ڣ����浽session��
        ActionContext.getContext().getSession().put("user", user_session);
        //�ͻ�����ת����ҳ
        return "homePage";      
    }
	
	@Action("forelogout")
    public String logout() {
		//��session��ȥ��user
        ActionContext.getContext().getSession().remove("user");
        return "homePage";  
    }

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
	
}
