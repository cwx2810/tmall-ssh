package tmall.action;

import java.util.List;

import tmall.pojo.Category;

//�ṩʵ������getter��setter
public class Action4Pojo extends Action4Pagination{
    protected Category category;
    
    protected List<Category> categorys;
    
    //�����ṩget��������jsp��ȡ
    public Category getCategory() {
        return category;
    }
    //�����ṩset���������ڽ���ע��
    public void setCategory(Category category) {
        this.category = category;
    }
 
    public List<Category> getCategorys() {
        return categorys;
    }
 
    public void setCategorys(List<Category> categorys) {
        this.categorys = categorys;
    }
}
