package tmall.service;

import tmall.pojo.Product;

public interface ProductImageService extends BaseService {
	
	//���峣����ʾͼƬ����
    public static final String type_single = "type_single";
    public static final String type_detail = "type_detail";
    
    //��ѯĳ����Ʒ�µģ�ĳ�����͵�ͼƬ
    //��base�Ķ�������ѯ���棬����������
//    public List<ProductImage> list(String key_product, Product product, String key_type, String type) ;   
    //������ҳͼƬ
    public void setFirstProdutImage(Product product);
}
