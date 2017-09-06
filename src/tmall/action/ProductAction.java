package tmall.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;

import tmall.pojo.Product;
import tmall.util.Page;

public class ProductAction extends Action4Result {
	//��ѯ��Ʒ
    @Action("admin_product_list")
    public String list() {
    	//�ж��Ƿ��з�ҳ����û�оʹ���һ��
        if(page==null)
            page = new Page();
        //�жϵ�ǰ�������һҳ�ж���������
        int total = productService.total(category);
        //Ϊ��ҳ������������
        page.setTotal(total);
        //Ϊ��ҳ�������ò���
        page.setParam("&category.id="+category.getId());
        //���ݷ�ҳ�ͷ�������ȡ��Ʒ����
        products = productService.list(page,category);
        //���ó־û�
        t2p(category);
        return "listProduct";
    }
     
    @Action("admin_product_add")
    public String add() {
    	//Ϊ��Ʒ���ô���ʱ��
        product.setCreateDate(new Date());
        //��service�Ѳ�Ʒ���浽���ݿ�
        productService.save(product);
        return "listProductPage";
    }
     
    @Action("admin_product_delete")
    public String delete() {
        t2p(product);
        productService.delete(product);
        return "listProductPage";
    }
     
    @Action("admin_product_edit")
    public String edit() {
        t2p(product);
        return "editProduct";
    }
    @Action("admin_product_update")
    public String update() {
    	//����id�����ݿ��ѯ��Ʒ����
        Product productFromDB= (Product)productService.get(product.getId());
        //ͨ����Ʒ�����ȡ������ʱ�䣬����������ʵ������
        product.setCreateDate(productFromDB.getCreateDate());
        //����ʵ����
        productService.update(product);
        return "listProductPage";
    }
}
