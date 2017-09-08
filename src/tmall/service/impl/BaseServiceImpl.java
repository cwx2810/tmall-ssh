package tmall.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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

	@Override
	public List listByParent(Object parent) {
		//���ݷ����ȡ������������
	    String parentName= parent.getClass().getSimpleName();
	    //�ѵ�һ����ĸ��Сд
	    String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
	    //ͨ��ί�ɰѴӸ����ȡ����������󸶸����ݿ�
	    DetachedCriteria dc = DetachedCriteria.forClass(clazz);
	    dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));
	    dc.addOrder(Order.desc("id"));
	    return findByCriteria(dc);
	}

	@Override
	public List list(Page page, Object parent) {
		//��ҳ�ڸ����в�ѯ���༯�ϣ�ǰ��Ͳ�ѯ���������༯��һ���������Ϸ�ҳ
	    String parentName= parent.getClass().getSimpleName();
	    String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
	    DetachedCriteria dc = DetachedCriteria.forClass(clazz);
	    dc.add(Restrictions.eq(parentNameWithFirstLetterLower, parent));
	    dc.addOrder(Order.desc("id"));
	    return findByCriteria(dc,page.getStart(),page.getCount());
	}

	@Override
	public int total(Object parentObject) {
	    String parentName= parentObject.getClass().getSimpleName();
	    String parentNameWithFirstLetterLower = StringUtils.uncapitalize(parentName);
	     
	    String sqlFormat = "select count(*) from %s bean where bean.%s = ?";
	    String hql = String.format(sqlFormat, clazz.getName(), parentNameWithFirstLetterLower);
	     
	    List<Long> l= this.find(hql,parentObject);
	    if(l.isEmpty())
	        return 0;
	    Long result= l.get(0);
	    return result.intValue();
	}

	//��������ѯ
	@Override
	public List list(Object... pairParms) {
		//��ʼ����ϣmap
        HashMap<String,Object> m = new HashMap<>();
        //�Ѳ���ȡ�����ŵ���ϣmap�У�ÿż������һ�Σ���Ϊ�Ǽ�ֵ��
        for (int i = 0; i < pairParms.length; i=i+2) 
            m.put(pairParms[i].toString(), pairParms[i+1]);
        //�½�ί�ɽ��в�ѯ
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        //���չ�ϣ���еļ�ֵ�����ò�ѯ����
        Set<String> ks = m.keySet();
        //������ֵ�Բ�ѯ
        for (String key : ks) {
        	//����鵽�ǿգ���Ӹ�ί�ɣ�����д��ѯ
            if(null==m.get(key))
                dc.add(Restrictions.isNull(key));
            else
            	//��������д��ѯ
                dc.add(Restrictions.eq(key, m.get(key)));
        }
        //����id����
        dc.addOrder(Order.desc("id"));
        //��ί���еķ���ִ�в�ѯ�����ؽ��
        return this.findByCriteria(dc);
	}

}
