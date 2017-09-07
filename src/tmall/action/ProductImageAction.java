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
    	//���ݲ�Ʒ�Ͳ�Ʒ���ͻ�ȡͼƬ�ĵ�����ϸ�ڼ���
        productSingleImages = productImageService.list("product",product,"type", ProductImageService.type_single);
        productDetailImages = productImageService.list("product",product,"type", ProductImageService.type_detail);
        //�־û������м����Ҫ��
        t2p(product);
        //�������ת
        return "listProductImage";
    }
 
    @Action("admin_productImage_add")
    public String add() {
    	//��������������Ķ���������ݿ�
        productImageService.save(productImage);
        //���ݴ��������ֶ�ֵȷ�����λ�� 
        String folder = "img/";
        if(ProductImageService.type_single.equals(productImage.getType())){
            folder +="productSingle";
        }
        else{
            folder +="productDetail";
        }
        //ȷ�������ϵ�����λ��     
        File  imageFolder= new File(ServletActionContext.getServletContext().getRealPath(folder));
        //����idȷ���������ݿ�Ĳ�Ʒ���ơ�����·��
        File file = new File(imageFolder,productImage.getId()+".jpg");
        String fileName = file.getName();
        try {
        	//img���������ϴ��ļ�����������ʱ�ģ��������ʱ�ĸ��Ƶľ���·����
            FileUtils.copyFile(img, file);
            //ȷ��ͼƬ����jpg��ʽ
            BufferedImage img = ImageUtil.change2jpg(file);
            ImageIO.write(img, "jpg", file);            
        } catch (IOException e) {
             
            e.printStackTrace();
        }
        //��������ͼ�����ͼƬ��single���͵ģ��ı�ͼƬ��С�ŵ���Ӧ��Ŀ¼�������detail�ģ�����Ҫ���ı� 
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
    	//�־û�����Ϊɾ����Ҫת����ѯҳ��鿴ɾ����Ľ��
        t2p(productImage);
        //����service��ɾ������
        productService.delete(productImage);
        return "listProductImagePage";
    }
}
