package tmall.service.impl;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import tmall.pojo.Category;
import tmall.service.PropertyService;
import tmall.util.Page;
 

 
@Service
public class PropertyServiceImpl extends BaseServiceImpl implements PropertyService{
	@Override
    public List listByCategory(Category category) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq("category", category));
        dc.addOrder(Order.desc("id"));
        return findByCriteria(dc);      
    }
 
    @Override
    public List list(Page page, Category category) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        dc.add(Restrictions.eq("category", category));
        dc.addOrder(Order.desc("id"));
        return findByCriteria(dc,page.getStart(),page.getCount());
    }
 
    @Override
    public int total(Category category) {
        String sqlFormat = "select count(*) from %s bean where bean.category = ?";
        String hql = String.format(sqlFormat, clazz.getName());
         
        List<Long> l= this.find(hql,category);
        if(l.isEmpty())
            return 0;
        Long result= l.get(0);
        return result.intValue();
    }
}
