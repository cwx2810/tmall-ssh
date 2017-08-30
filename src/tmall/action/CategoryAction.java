package tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.springframework.beans.factory.annotation.Autowired;

import tmall.pojo.Category;
import tmall.service.CategoryService;
import tmall.util.ImageUtil;
import tmall.util.Page;

@Namespace("/")
@ParentPackage("basicstruts")  
@Results(
		{
			//定义返回到哪个页面
			@Result(name="listCategory", location="/admin/listCategory.jsp"),
			@Result(name="listCategoryPage", type = "redirect", location="/admin_category_list"),
		})
public class CategoryAction {
	//自动注入service访问实体类
	@Autowired 
	CategoryService categoryService;
	//将通过service获取到的实体类保存在categorys，先初始化之
	List<Category> categorys;
	//初始化分类，以接受浏览器传过来的分类
	Category category;
	//初始化图片，以接受浏览器传过来的图片
	File img;
	//分页
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
	//把对路径的访问映射到add方法上
    @Action("admin_category_add")
    public String add() {
    	//将category对象保存到数据库
        categoryService.save(category);
        //保存到数据库后，获取路径，根据路径获取到id，计算图片名称
        File imageFolder= new File(ServletActionContext.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,category.getId()+".jpg");
        try {
        	//把浏览器上传的临时文件复制一份
            FileUtils.copyFile(img, file);
            //修改这个图片的格式，让其是jpg
            BufferedImage img = ImageUtil.change2jpg(file);
            //写入图片
            ImageIO.write(img, "jpg", file);            
        } catch (IOException e) {
            e.printStackTrace();
        }
        //返回页面
        return "listCategoryPage";
    }  
	//把对路径的访问映射到delete方法上
    public String delete(){
    	//通过service删除对象
    	categoryService.delete(category);
    	//客户端跳转
    	return "listCategoryPage";
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
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public File getImg() {
		return img;
	}
	public void setImg(File img) {
		this.img = img;
	}
	
}
