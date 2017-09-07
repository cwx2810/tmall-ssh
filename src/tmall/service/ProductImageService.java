package tmall.service;

import java.util.List;

import tmall.pojo.Product;
import tmall.pojo.ProductImage;

public interface ProductImageService extends BaseService {
	
	//���峣����ʾͼƬ����
    public static final String type_single = "type_single";
    public static final String type_detail = "type_detail";
    
    //��ѯĳ����Ʒ�µģ�ĳ�����͵�ͼƬ
    public List<ProductImage> list(String key_product, Product product, String key_type, String type) ;   
    //������ҳͼƬ
    public void setFirstProdutImage(Product product);
}
