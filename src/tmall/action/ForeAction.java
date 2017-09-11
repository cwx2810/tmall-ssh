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
		//转义账号，防恶意注册
        user.setName(HtmlUtils.htmlEscape(user.getName()));
        //根据账号密码获取user对象，在userService中新写一个获取对象的方法
        User user_session = userService.get(user.getName(),user.getPassword());
        //如果对象为空，跳回登陆页面
        if(null==user_session){
            msg= "账号密码错误";
            return "login.jsp"; 
        }
        //如果对象存在，保存到session中
        ActionContext.getContext().getSession().put("user", user_session);
        //客户端跳转到首页
        return "homePage";      
    }
	
	@Action("forelogout")
    public String logout() {
		//在session中去掉user
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
