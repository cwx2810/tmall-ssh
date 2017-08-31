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
			//���巵�ص��ĸ�ҳ��
			@Result(name="listCategory", location="/admin/listCategory.jsp"),
			@Result(name="listCategoryPage", type = "redirect", location="/admin_category_list"),
			@Result(name="editCategory", location="/admin/editCategory.jsp"),
		})
public class CategoryAction {
	//�Զ�ע��service����ʵ����
	@Autowired 
	CategoryService categoryService;
	//��ͨ��service��ȡ����ʵ���ౣ����categorys���ȳ�ʼ��֮
	List<Category> categorys;
	//��ʼ�����࣬�Խ���������������ķ���
	Category category;
	//��ʼ��ͼƬ���Խ����������������ͼƬ
	File img;
	//��ҳ
	Page page;
	
	
	
	//�Ѷ�·���ķ���ӳ�䵽list������
	@Action("admin_category_list")
	public String list() {
		//���û�з�ҳ��������������newһ�����൱�ڲ�ѯ��һҳ
		if(page==null)
			page = new Page();
		//��ȡ����
		int total = categoryService.total();
		//��new�Ķ�����������
		page.setTotal(total);
		
		categorys = categoryService.list();
		return "listCategory";
	}
	//�Ѷ�·���ķ���ӳ�䵽add������
    @Action("admin_category_add")
    public String add() {
    	//��category���󱣴浽���ݿ�
        categoryService.save(category);
        //���浽���ݿ�󣬻�ȡ·��������·����ȡ��id������ͼƬ����
        File imageFolder= new File(ServletActionContext.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,category.getId()+".jpg");
        try {
        	//��������ϴ�����ʱ�ļ�����һ��
            FileUtils.copyFile(img, file);
            //�޸����ͼƬ�ĸ�ʽ��������jpg
            BufferedImage img = ImageUtil.change2jpg(file);
            //д��ͼƬ
            ImageIO.write(img, "jpg", file);            
        } catch (IOException e) {
            e.printStackTrace();
        }
        //����ҳ��
        return "listCategoryPage";
    }  
	//�Ѷ�·���ķ���ӳ�䵽delete������
    @Action("admin_category_delete")
    public String delete(){
    	//ͨ��serviceɾ������
    	categoryService.delete(category);
    	//�ͻ�����ת
    	return "listCategoryPage";
    }
    //�Ѷ�·���ķ���ӳ�䵽edit������
    @Action("admin_category_edit")
    public String edit() {
    	//��ȡid
        int id = category.getId();
        //����id��ȡ���󣬼�����������ύ��id��Ϣ�����ݿ��в��Ҷ�Ӧ��������
        category = categoryService.get(Category.class,id);
        //�������ת
        return "editCategory";
    }
    @Action("admin_category_update")
    public String update() {
    	//����category����
        categoryService.update(category);
        //�������ϴ���ͼƬ��Ҫ���е�����ͼƬ���£�Ϊʲô����ͼƬ��������һ������Ϊ�����п��ܲ��ϴ�ͼƬ��ֻ������
        if(null!=img){
        	//��ȡ·��
            File  imageFolder= new File(ServletActionContext.getServletContext().getRealPath("img/category"));
            //����id����ͼƬ����
            File file = new File(imageFolder,category.getId()+".jpg");
            try {
            	//�����ϴ���ͼƬ��id.jpg
                FileUtils.copyFile(img, file);
                //ȷ��ͼƬ��jpg��ʽ
                BufferedImage img = ImageUtil.change2jpg(file);
                ImageIO.write(img, "jpg", file);
            } catch (IOException e) {
                e.printStackTrace();
            }           
        }
        //�ͻ�����ת
        return "listCategoryPage";
    } 
    
    
	
	
	//��ȡ�ŵ�categorys�����ݣ��򷵻�ҳ�洫������
	public List<Category> getCategorys() {
		return categorys;
	}
	//����İ�ʵ����ŵ�categorys�ķ���
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
