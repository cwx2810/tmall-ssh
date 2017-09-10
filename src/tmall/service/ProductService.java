package tmall.service;

import java.util.List;

import tmall.pojo.Category;

public interface ProductService extends BaseService {
	//这三个方法为了操作分类实体类新增的显示首页导航的东东
	public void fill(List<Category> categorys);
    public void fill(Category category);
    public void fillByRow(List<Category> categorys);
}
