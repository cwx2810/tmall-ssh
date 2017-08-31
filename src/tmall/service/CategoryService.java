package tmall.service;

import java.util.List;

import tmall.pojo.Category;
import tmall.util.Page;

public interface CategoryService {
	public List list();
	public int total();
	public List<Category> listByPage(Page page);
	public void save(Category category);
	public void delete(Category category);
	public Category get(Class clazz,int id);
	public void update(Category category);
}
