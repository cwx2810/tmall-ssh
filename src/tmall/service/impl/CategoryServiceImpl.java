package tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tmall.dao.impl.DAOImpl;
import tmall.pojo.Category;
import tmall.service.CategoryService;

@Service
public class CategoryServiceImpl  implements CategoryService {

	@Autowired DAOImpl dao;
	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(Category.class);
		dc.addOrder(Order.desc("id"));
		return dao.findByCriteria(dc);
	}

	
}
