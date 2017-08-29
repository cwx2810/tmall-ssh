package tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tmall.dao.impl.DAOImpl;
import tmall.pojo.Category;
import tmall.service.CategoryService;
import tmall.util.Page;

@Service
public class CategoryServiceImpl  implements CategoryService {

	@Autowired DAOImpl dao;
	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc);
	}
	@Override
	public int total() {
		String hql = "select count(*) from Category ";
		List<Long> l= dao.find(hql);
		if(l.isEmpty())
			return 0;
		Long result= l.get(0);
		return result.intValue();
	}
	@Override
	public List<Category> listByPage(Page page) {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc,page.getStart(),page.getCount());
	}
	@Override
	public void save(Category category) {
		dao.save(category);
	}
	
}
