package tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import tmall.service.BaseService;
import tmall.util.Page;

@Service
public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {
	
	//声明clazz，表明用来处理某个实体类的CRUD，具体是哪个类，用反射实现哪个业务继承了就声明哪个实体类
	protected Class clazz;
	//在构造方法中用异常和反射得到声明的是category.class还是product.class
	public BaseServiceImpl(){
		try{
			//先通过抛出异常捕获是哪个子类在运作
			throw new Exception();	
		}
		catch(Exception e){
			//在栈中捕获的第二个元素就是子类
			StackTraceElement stes[] = e.getStackTrace();
		    String serviceImpleClassName = stes[1].getClassName();
		    try {
		    	//反射，从捕获到的子类中反射出名称
				Class  serviceImplClazz= Class.forName(serviceImpleClassName);
				//获取捕获到的服务中实现的类名并替换成实体类中的类名，其实是之间剪掉了，不需要
				String serviceImpleClassSimpleName = serviceImplClazz.getSimpleName();
				String pojoSimpleName = serviceImpleClassSimpleName.replaceAll("ServiceImpl", "");
				//获取捕获到的服务中实现的包名并替换成实体类的包名，注意这里项目中的包必须在xxx.service和xxx.pojo下，其他项目复用这段代码要替换这里
				String pojoPackageName = serviceImplClazz.getPackage().getName().replaceAll(".service.impl", ".pojo");
				//字符串拼接成包名+实体类名
				String pojoFullName = pojoPackageName +"."+ pojoSimpleName;
				//反射，从拼接成的字符串中得到clazz名称
				clazz=Class.forName(pojoFullName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}		
	}
	
	//实现baseService
	@Override
	public Integer save(Object object) {
		return (Integer)super.save(object);
	}
//在委派中已经有了更新和删除，不再提供
//	@Override
//	public void update(Object object) {
//		dao.update(object);
//	}
//
//	@Override
//	public void delete(Object object) {
//		dao.delete(object);
//	}

	@Override
	public Object get(Class clazz, int id) {
		return super.get(clazz, id);
	}

	@Override
	public Object get(int id) {
		return get(clazz, id);
	}

	@Override
	public List list() {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc);
	}

	@Override
	public List listByPage(Page page) {
		DetachedCriteria dc = DetachedCriteria.forClass(clazz);
		dc.addOrder(Order.desc("id"));
		return findByCriteria(dc, page.getStart(), page.getCount());
	}

	@Override
	public int total() {
		String hql = "select count(*) from " + clazz.getName();
		List<Long> l = find(hql);
		if(l.isEmpty())
			return 0;
		Long result = l.get(0);
		return result.intValue();
	}

}
