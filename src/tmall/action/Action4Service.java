package tmall.action;

import java.lang.reflect.Method;

import org.apache.commons.lang3.text.WordUtils;
import org.springframework.beans.factory.annotation.Autowired;

import tmall.service.CategoryService;
import tmall.service.ProductImageService;
import tmall.service.ProductService;
import tmall.service.PropertyService;

//ר���ṩ��actionע��service
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
     * ˲ʱ����ת��Ϊ�־ö���
     * @param o
     */
    public void t2p(Object o){
            try {
            	//��ȡ˲ʱ����������
                Class clazz = o.getClass();
                //ͨ��������ö����get������ȡid
                int id = (Integer) clazz.getMethod("getId").invoke(o);
                //����id��ȡ�־û�����
                Object persistentBean = categoryService.get(clazz, id);
                //��ȡ����
                String beanName = clazz.getSimpleName();
                //ͨ�������ճ�set��������
                Method setMethod = getClass().getMethod("set" + WordUtils.capitalize(beanName), clazz);
                //ͨ������ѳ־ö��������ڷ���������
                setMethod.invoke(this, persistentBean);
            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
    }
}
