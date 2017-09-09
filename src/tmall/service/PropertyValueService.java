package tmall.service;

import tmall.pojo.Product;

public interface PropertyValueService extends BaseService {
	//产品属性值没有增加，只有初始化，然后修改
	public void init(Product product);
}
