package tmall.action;

import java.util.List;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import tmall.pojo.Category;
import tmall.service.CategoryService;
import tmall.util.Page;

@Namespace("/")
@ParentPackage("basicstruts")  
@Results(
		{
			//定义返回到哪个页面
			@Result(name="listCategory", location="/admin/listCategory.jsp"),
		})
public class CategoryAction {
	//自动注入service访问实体类
	@Autowired 
	CategoryService categoryService;
	//将通过service获取到的实体类保存在categorys，先初始化之
	List<Category> categorys;
	
	Page page;
	
	//把对路径的访问映射到list方法上
	@Action("admin_category_list")
	public String list() {
		//如果没有分页参数传进来，就new一个，相当于查询第一页
		if(page==null)
			page = new Page();
		//获取总数
		int total = categoryService.total();
		//给new的对象设置总数
		page.setTotal(total);
		
		categorys = categoryService.list();
		return "listCategory";
	}
	//获取放到categorys的数据，向返回页面传递数据
	public List<Category> getCategorys() {
		return categorys;
	}
	//具体的把实体类放到categorys的方法
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
