package tmall.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tmall.pojo.Order;
import tmall.pojo.OrderItem;
import tmall.service.OrderItemService;
import tmall.service.ProductImageService;

@Service
public class OrderItemServiceImpl extends BaseServiceImpl implements OrderItemService {

	@Autowired
    ProductImageService productImageService;
	
	@Override
	public void fill(List<Order> orders) {
		//��������������������ĵ���
		for (Order order : orders) 
            fill(order);
	}

	@Override
	public void fill(Order order) {
		//Ϊ������䶩����
		List<OrderItem> orderItems= this.listByParent(order);
        order.setOrderItems(orderItems);
         
        float total = 0;
        int totalNumber = 0;            
        for (OrderItem oi :orderItems) {
        	//���㶩���ܶ�
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            //���㶩���ܹ�������
            totalNumber+=oi.getNumber();
            //����ҳ����ʾ��һ����ƷͼƬ 
            productImageService.setFirstProdutImage(oi.getProduct());
        }
        //Ϊ��������ܶ�����������
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
	}

}
