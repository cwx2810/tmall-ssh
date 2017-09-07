package tmall.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;

import tmall.pojo.Product;
import tmall.pojo.ProductImage;
import tmall.service.ProductImageService;

@Service
public class ProductImageServiceImpl extends BaseServiceImpl implements ProductImageService {

	@Override
	public List<ProductImage> list(String key_product, Product product, String key_type, String type) {
        DetachedCriteria dc = DetachedCriteria.forClass(clazz);
        //��ѯĳ�ֲ�Ʒ��ͼƬ
        dc.add(Restrictions.eq(key_product, product));
        //��ѯ��Ʒĳ�����͵�ͼƬ
        dc.add(Restrictions.eq(key_type, type));
        dc.addOrder(Order.desc("id"));
        return this.findByCriteria(dc);
	}

	@Override
	public void setFirstProdutImage(Product product) {
        if(null!=product.getFirstProductImage())
            return;
        //��ѯĳ����Ʒ��������type_single��ͼƬ����
        List<ProductImage> pis= list("product", product, "type", ProductImageService.type_single);
        //���������ϲ�Ϊ�գ�ȡ�����ĵ�һ����Ϊ��ƷչʾͼƬ
        if(!pis.isEmpty())
            product.setFirstProductImage(pis.get(0));
	}

}
