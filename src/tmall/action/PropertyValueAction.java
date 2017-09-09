package tmall.action;

import org.apache.struts2.convention.annotation.Action;

public class PropertyValueAction extends Action4Result {
    @Action("admin_propertyValue_edit")
    public String edit() {
        //持久化，导航里要用
        t2p(product);
        //初始化
        propertyValueService.init(product);
        //根据产品获取对应属性值集合
        propertyValues = propertyValueService.listByParent(product); 
        //服务端跳转
        return "editPropertyValue";
    }
    @Action("admin_propertyValue_update")
    public String update() {
    	//前端editProperty.jsp把监听到的输入传过来，获取其传递过来的值
        String value = propertyValue.getValue();
        //持久化
        t2p(propertyValue);
        //修改此值
        propertyValue.setValue(value);
        //提交到数据库
        propertyValueService.update(propertyValue);
        //服务端跳转的成功页面，浏览器判断返回值是success修改成功，把边框设置成绿色，否则是红色修改失败
        return "success.jsp";
    }
}
