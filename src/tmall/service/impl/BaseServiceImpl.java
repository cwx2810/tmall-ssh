package tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Service;

import tmall.service.BaseService;
import tmall.util.Page;

@Service
public class BaseServiceImpl extends ServiceDelegateDAO implements BaseService {
	
	//����clazz��������������ĳ��ʵ�����CRUD���������ĸ��࣬�÷���ʵ���ĸ�ҵ��̳��˾������ĸ�ʵ����
	protected Class clazz;
	//�ڹ��췽�������쳣�ͷ���õ���������category.class����product.class
	public BaseServiceImpl(){
		try{
			//��ͨ���׳��쳣�������ĸ�����������
			throw new Exception();	
		}
		catch(Exception e){
			//��ջ�в���ĵڶ���Ԫ�ؾ�������
			StackTraceElement stes[] = e.getStackTrace();
		    String serviceImpleClassName = stes[1].getClassName();
		    try {
		    	//���䣬�Ӳ��񵽵������з��������
				Class  serviceImplClazz= Class.forName(serviceImpleClassName);
				//��ȡ���񵽵ķ�����ʵ�ֵ��������滻��ʵ�����е���������ʵ��֮������ˣ�����Ҫ
				String serviceImpleClassSimpleName = serviceImplClazz.getSimpleName();
				String pojoSimpleName = serviceImpleClassSimpleName.replaceAll("ServiceImpl", "");
				//��ȡ���񵽵ķ�����ʵ�ֵİ������滻��ʵ����İ�����ע��������Ŀ�еİ�������xxx.service��xxx.pojo�£�������Ŀ������δ���Ҫ�滻����
				String pojoPackageName = serviceImplClazz.getPackage().getName().replaceAll(".service.impl", ".pojo");
				//�ַ���ƴ�ӳɰ���+ʵ������
				String pojoFullName = pojoPackageName +"."+ pojoSimpleName;
				//���䣬��ƴ�ӳɵ��ַ����еõ�clazz����
				clazz=Class.forName(pojoFullName);
			} catch (ClassNotFoundException e1) {
				e1.printStackTrace();
			}
		}		
	}
	
	//ʵ��baseService
	@Override
	public Integer save(Object object) {
		return (Integer)super.save(object);
	}
//��ί�����Ѿ����˸��º�ɾ���������ṩ
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
