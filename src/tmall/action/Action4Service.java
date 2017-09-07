package tmall.action;

import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import tmall.service.CategoryService;
import tmall.service.ProductImageService;
import tmall.service.ProductService;
import tmall.service.PropertyService;

//专门提供给action注入service
public class Action4Service extends Action4Pojo{
    @Autowired
    CategoryService categoryService;
    @Autowired
    PropertyService propertyService;
    @Autowired
    ProductService productService;
    @Autowired
    ProductImageService productImageService;
    /**
     * transient to persistent
     * 瞬时对象转换为持久对象
     * @param o
     */
    public void t2p(Object o){
            try {
            	//获取瞬时对象的类对象
                Class clazz = o.getClass();
                //通过反射调用对象的get方法获取id
                int id = (Integer) clazz.getMethod("getId").invoke(o);
                //根据id获取持久化对象
                Object persistentBean = categoryService.get(clazz, id);
                //获取类名
                String beanName = clazz.getSimpleName();
                //通过类名凑成set方法引用
                Method setMethod = getClass().getMethod("set" + WordUtils.capitalize(beanName), clazz);
                //通过反射把持久对象设置在方法引用上
                setMethod.invoke(this, persistentBean);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
