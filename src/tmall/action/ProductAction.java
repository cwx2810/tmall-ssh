package tmall.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;

import tmall.pojo.Product;
import tmall.util.Page;

public class ProductAction extends Action4Result {
	//查询产品
    @Action("admin_product_list")
    public String list() {
    	//判断是否有分页对象，没有就创建一个
        if(page==null)
            page = new Page();
        //判断当前分类的下一页有多少条数据
        int total = productService.total(category);
        //为分页对象设置总数
        page.setTotal(total);
        //为分页对象设置参数
        page.setParam("&category.id="+category.getId());
        //根据分页和分类对象获取产品集合
        products = productService.list(page,category);
        //引用持久化
        t2p(category);
        return "listProduct";
    }
     
    @Action("admin_product_add")
    public String add() {
    	//为产品设置创建时间
        product.setCreateDate(new Date());
        //用service把产品保存到数据库
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
    	//根据id从数据库查询产品对象
        Product productFromDB= (Product)productService.get(product.getId());
        //通过产品对象获取到创建时间，把他设置在实体类上
        product.setCreateDate(productFromDB.getCreateDate());
        //更新实体类
        productService.update(product);
        return "listProductPage";
    }
}
