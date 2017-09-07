package tmall.service;

import java.util.List;

import tmall.pojo.Product;
import tmall.pojo.ProductImage;

public interface ProductImageService extends BaseService {
	
	//定义常量表示图片类型
    public static final String type_single = "type_single";
    public static final String type_detail = "type_detail";
    
    //查询某个产品下的，某种类型的图片
    public List<ProductImage> list(String key_product, Product product, String key_type, String type) ;   
    //设置首页图片
    public void setFirstProdutImage(Product product);
}
