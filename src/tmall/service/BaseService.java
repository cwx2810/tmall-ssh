package tmall.service;

import java.util.List;

import tmall.util.Page;

//对Service的无数CRUD进行抽象
public interface BaseService {
	//委派模式下dao的save是自增长的，so为了和其兼容，这里声明的save必须是自增长的子类，故为integer
    public Integer save(Object object);
    public void update(Object object);
    public void delete(Object object);
    public Object get(Class clazz,int id);
    public Object get(int id);
     
    public List list();
    public List listByPage(Page page);
    public int total();
}
