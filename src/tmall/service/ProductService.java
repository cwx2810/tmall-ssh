package tmall.service;

import java.util.List;

import tmall.pojo.Category;

public interface ProductService extends BaseService {
	//����������Ϊ�˲�������ʵ������������ʾ��ҳ�����Ķ���
	public void fill(List<Category> categorys);
    public void fill(Category category);
    public void fillByRow(List<Category> categorys);
}
