package tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tmall.pojo.Product;
import tmall.pojo.Property;
import tmall.pojo.PropertyValue;
import tmall.service.PropertyService;
import tmall.service.PropertyValueService;

@Service
public class PropertyValueServiceImpl extends BaseServiceImpl implements PropertyValueService {
	
    @Autowired
    PropertyService propertyService; 
     
    @Override
    public void init(Product product) {
    	//���ݲ�Ʒ��ȡ���࣬�ͷ����µ����м���
        List<Property> propertys= propertyService.listByParent(product.getCategory());
        for (Property property: propertys) {
        	//�������ԺͲ�Ʒ��ѯ(��ȡ��Ȼ���ж�)
            PropertyValue propertyValue = get(property,product);
            if(null==propertyValue){
            	//�������������ֵ�ʹ���һ��
                propertyValue = new PropertyValue();
                //���ô�����ֵ��Ӧ��Ʒ
                propertyValue.setProduct(product);
                //���ô�����ֵ��Ӧ����
                propertyValue.setProperty(property);
                //�������ݿ�
                save(propertyValue);
            }
        }
    }
    //�����Ѹ������ԺͲ�Ʒ��ȡ��Ʒֵ�÷����������
    private PropertyValue get(Property property, Product product) {
        List<PropertyValue> result= this.list("property",property, "product",product);
        if(result.isEmpty())
            return null;
        return result.get(0);
         
    }
}
