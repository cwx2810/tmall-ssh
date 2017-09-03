package tmall.action;

import java.util.List;

import tmall.pojo.Category;

//提供实体对象的getter、setter
public class Action4Pojo extends Action4Pagination{
    protected Category category;
    
    protected List<Category> categorys;
    
    //对外提供get方法，让jsp获取
    public Category getCategory() {
        return category;
    }
    //对外提供set方法，用于接收注入
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
