package tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import tmall.util.ImageUtil;
import tmall.util.Page;

public class CategoryAction extends Action4Result{

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
		
		categorys = categoryService.listByPage(page);
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
    @Action("admin_category_delete")
    public String delete(){
    	//通过service删除对象
    	categoryService.delete(category);
    	//客户端跳转
    	return "listCategoryPage";
    }
    //把对路径的访问映射到edit方法上，edit只能保证点编辑有反应，不能修改
    @Action("admin_category_edit")
    public String edit() {
        t2p(category);
        return "editCategory";
    }
    //把对路径的访问映射到update方法上，配合edit实现修改
    @Action("admin_category_update")
    public String update() {
    	//更新category对象
        categoryService.update(category);
        //当发现上传了图片，要进行单独的图片更新，为什么这里图片单独处理不一起处理，因为更新有可能不上传图片，只改名字
        if(null!=img){
        	//获取路径
            File  imageFolder= new File(ServletActionContext.getServletContext().getRealPath("img/category"));
            //根据id计算图片名称
            File file = new File(imageFolder,category.getId()+".jpg");
            try {
            	//复制上传的图片到id.jpg
                FileUtils.copyFile(img, file);
                //确保图片是jpg格式
                BufferedImage img = ImageUtil.change2jpg(file);
                ImageIO.write(img, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }           
        }
        //客户端跳转
        return "listCategoryPage";
    } 
}
