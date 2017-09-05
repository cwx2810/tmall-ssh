package tmall.action;

import org.apache.struts2.convention.annotation.Action;

import tmall.util.Page;

public class PropertyAction extends Action4Result {
    @Action("admin_property_list")
    public String list() {
    	//判断是否有分页对象，没有就新建一个
        if(page==null)
            page = new Page();
        //判断当前分类下一共有多少数据
        int total = propertyService.total(category);
        //为分页对象设置总数
        page.setTotal(total);
        //为分页对象设置参数
        page.setParam("&category.id="+category.getId());
        //根据分页和分类对象获取属性集合
        propertys = propertyService.list(page,category);
        //瞬时对象变持久对象，因为后面面包屑导航要用到category
        t2p(category);
        //返回给前台页面
        return "listProperty";
    }
     
    @Action("admin_property_add")
    public String add() {
    	//通过service将property插入数据库，从jsp传过来的除了名称还有id，这样下面的跳转就会带上id
        propertyService.save(property);
        return "listPropertyPage";
    }
     
    @Action("admin_property_delete")
    public String delete() {
    	//.....总之，数据的使用都不止在一处，总要持久化
        t2p(property);
        propertyService.delete(property);
        return "listPropertyPage";
    }
     
    @Action("admin_property_edit")
    public String edit() {
    	//把property转成持久化对象，sturct注入的瞬时对象只有id，而持久化对象和数据库同步，既有id又有name
        t2p(property);
        //服务端跳转
        return "editProperty";
    }
    @Action("admin_property_update")
    public String update() {
    	//通过service更新property对象
        propertyService.update(property);
        return "listPropertyPage";
    }
}
