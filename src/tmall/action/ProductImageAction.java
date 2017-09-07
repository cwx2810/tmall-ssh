package tmall.action;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;

import tmall.service.ProductImageService;
import tmall.util.ImageUtil;

public class ProductImageAction extends Action4Result {
    @Action("admin_productImage_list")
    public String list() {
    	//根据产品和产品类型获取图片的单个和细节集合
        productSingleImages = productImageService.list("product",product,"type", ProductImageService.type_single);
        productDetailImages = productImageService.list("product",product,"type", ProductImageService.type_detail);
        //持久化，面包屑导航要用
        t2p(product);
        //服务端跳转
        return "listProductImage";
    }
 
    @Action("admin_productImage_add")
    public String add() {
    	//把浏览器传过来的对象插入数据库
        productImageService.save(productImage);
        //根据传进来的字段值确定存放位子 
        String folder = "img/";
        if(ProductImageService.type_single.equals(productImage.getType())){
            folder +="productSingle";
        }
        else{
            folder +="productDetail";
        }
        //确定磁盘上的真正位置     
        File  imageFolder= new File(ServletActionContext.getServletContext().getRealPath(folder));
        //根据id确定插入数据库的产品名称、绝对路径
        File file = new File(imageFolder,productImage.getId()+".jpg");
        String fileName = file.getName();
        try {
        	//img用来接受上传文件，但是是临时的，把这个临时的复制的绝对路径上
            FileUtils.copyFile(img, file);
            //确保图片是真jpg格式
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);            
        } catch (IOException e) {
             
            e.printStackTrace();
        }
        //制造缩略图，如果图片是single类型的，改变图片大小放到相应的目录，如果是detail的，不需要做改变 
        if(ProductImageService.type_single.equals(productImage.getType())){
            String imageFolder_small= ServletActionContext.getServletContext().getRealPath("img/productSingle_small");
            String imageFolder_middle= ServletActionContext.getServletContext().getRealPath("img/productSingle_middle");        
             
            File f_small = new File(imageFolder_small, fileName);
            File f_middle = new File(imageFolder_middle, fileName);
 
            f_small.getParentFile().mkdirs();
            f_middle.getParentFile().mkdirs();
             
            ImageUtil.resizeImage(file, 56, 56, f_small);
            ImageUtil.resizeImage(file, 217, 190, f_middle);
        }       
                 
        return "listProductImagePage";
    }
     
    @Action("admin_productImage_delete")
    public String delete() {
    	//持久化，因为删除后要转到查询页面查看删除后的结果
        t2p(productImage);
        //调用service的删除方法
        productService.delete(productImage);
        return "listProductImagePage";
    }
}
