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
        //查询某种产品的图片
        dc.add(Restrictions.eq(key_product, product));
        //查询产品某种类型的图片
        dc.add(Restrictions.eq(key_type, type));
        dc.addOrder(Order.desc("id"));
        return this.findByCriteria(dc);
	}

	@Override
	public void setFirstProdutImage(Product product) {
        if(null!=product.getFirstProductImage())
            return;
        //查询某个产品下类型是type_single的图片集合
        List<ProductImage> pis= list("product", product, "type", ProductImageService.type_single);
        //如果这个集合不为空，取出它的第一张作为产品展示图片
        if(!pis.isEmpty())
            product.setFirstProductImage(pis.get(0));
	}

}
