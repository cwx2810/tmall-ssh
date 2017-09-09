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
    	//根据产品获取分类，和分类下的所有集合
        List<Property> propertys= propertyService.listByParent(product.getCategory());
        for (Property property: propertys) {
        	//利用属性和产品查询(获取，然后判断)
            PropertyValue propertyValue = get(property,product);
            if(null==propertyValue){
            	//如果不存在属性值就创建一个
                propertyValue = new PropertyValue();
                //设置此属性值对应产品
                propertyValue.setProduct(product);
                //设置此属性值对应属性
                propertyValue.setProperty(property);
                //插入数据库
                save(propertyValue);
            }
        }
    }
    //单独把根据属性和产品获取产品值得方法抽象出来
    private PropertyValue get(Property property, Product product) {
        List<PropertyValue> result= this.list("property",property, "product",product);
        if(result.isEmpty())
            return null;
        return result.get(0);
         
    }
}
