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
		
		categorys = categoryService.listByPage(page);
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
    //�Ѷ�·���ķ���ӳ�䵽edit�����ϣ�editֻ�ܱ�֤��༭�з�Ӧ�������޸�
    @Action("admin_category_edit")
    public String edit() {
        t2p(category);
        return "editCategory";
    }
    //�Ѷ�·���ķ���ӳ�䵽update�����ϣ����editʵ���޸�
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
}
