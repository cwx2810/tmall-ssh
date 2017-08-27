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

@Namespace("/")
@ParentPackage("basicstruts")  
@Results(
		{
			//���巵�ص��ĸ�ҳ��
			@Result(name="listCategory", location="/admin/listCategory.jsp"),
		})
public class CategoryAction {
	//�Զ�ע��service����ʵ����
	@Autowired 
	CategoryService categoryService;
	//��ͨ��service��ȡ����ʵ���ౣ����categorys���ȳ�ʼ��֮
	List<Category> categorys;
	//�Ѷ�·���ķ���ӳ�䵽list������
	@Action("admin_category_list")
	public String list() {
		categorys = categoryService.list();
		return "listCategory";
	}
	//��ȡ�ŵ�categorys�����ݣ��򷵻�ҳ�洫������
	public List<Category> getCategorys() {
		return categorys;
	}
	//����İ�ʵ����ŵ�categorys�ķ���
	public void setCategorys(List<Category> categorys) {
		this.categorys = categorys;
	}

	
}
