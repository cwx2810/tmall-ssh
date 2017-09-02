package tmall.service;

import java.util.List;

import tmall.util.Page;

//��Service������CRUD���г���
public interface BaseService {
	//ί��ģʽ��dao��save���������ģ�soΪ�˺�����ݣ�����������save�����������������࣬��Ϊinteger
    public Integer save(Object object);
    public void update(Object object);
    public void delete(Object object);
    public Object get(Class clazz,int id);
    public Object get(int id);
     
    public List list();
    public List listByPage(Page page);
    public int total();
}
