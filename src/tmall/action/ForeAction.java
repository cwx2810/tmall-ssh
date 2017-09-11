package tmall.action;

import org.apache.struts2.convention.annotation.Action;
import org.springframework.web.util.HtmlUtils;

import com.opensymphony.xwork2.ActionContext;

import tmall.pojo.User;
import tmall.service.ProductImageService;

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
	
	//��ҳ��ת��Ʒҳ
	@Action("foreproduct")
    public String product() {
		
		//�־û���Ʒ
        t2p(product);
        //��������ͼ  
        productImageService.setFirstProdutImage(product);
        //���õ���������ͼ
        productSingleImages = productImageService.list("product",product,"type", ProductImageService.type_single);
        productDetailImages = productImageService.list("product",product,"type", ProductImageService.type_detail);
        product.setProductSingleImages(productSingleImages);
        product.setProductDetailImages(productDetailImages);
        //��ȡ��Ʒ����ֵ����  
        propertyValues = propertyValueService.listByParent(product);       
      
        //�������ת����Ʒ�б�ҳ��
        return "product.jsp";           
    }
	
	//��ת����Ʒ�б�ҳ
	@Action("forecategory")
	public String category(){
		//�־û�����
	    t2p(category);
	    //����Ʒ������
	    productService.fill(category);      
	    //�������ת
	    return "category.jsp";          
	}
	
}
