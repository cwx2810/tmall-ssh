package tmall.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tmall.pojo.Category;
import tmall.pojo.Product;
import tmall.service.ProductImageService;
import tmall.service.ProductService;

@Service
public class ProductServiceImpl extends BaseServiceImpl implements ProductService {

	
	@Autowired
    ProductImageService productImageService;
	
	//Ϊ��ҳ�����������Ʒ����
	@Override
	public void fill(List<Category> categorys) {
		for (Category category : categorys) {
            fill(category);
        }
	}

	//Ϊ��ҳ��������Ʒ����
	@Override
	public void fill(Category category) {
		List<Product> products= listByParent(category);
        
        for (Product product : products) 
            productImageService.setFirstProdutImage(product);
         
        category.setProducts(products);
	}

	//Ϊ�����������Ƽ���Ʒ���ϣ�ÿ8��һ��
	@Override
	public void fillByRow(List<Category> categorys) {
		int productNumberEachRow = 8;
        for (Category category : categorys) {
            List<Product> products =  category.getProducts();
            List<List<Product>> productsByRow =  new ArrayList<>();
            for (int i = 0; i < products.size(); i+=productNumberEachRow) {
                int size = i+productNumberEachRow;
                size= size>products.size()?products.size():size;
                List<Product> productsOfEachRow =products.subList(i, size);
                productsByRow.add(productsOfEachRow);
            }
             
            category.setProductsByRow(productsByRow);
        }
	}

}
