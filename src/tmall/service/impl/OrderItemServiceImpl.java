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
		//计算多个订单，基于下面的单个
		for (Order order : orders) 
            fill(order);
	}

	@Override
	public void fill(Order order) {
		//为订单填充订单项
		List<OrderItem> orderItems= this.listByParent(order);
        order.setOrderItems(orderItems);
         
        float total = 0;
        int totalNumber = 0;            
        for (OrderItem oi :orderItems) {
        	//计算订单总额
            total+=oi.getNumber()*oi.getProduct().getPromotePrice();
            //计算订单总购买数量
            totalNumber+=oi.getNumber();
            //订单页面显示第一个产品图片 
            productImageService.setFirstProdutImage(oi.getProduct());
        }
        //为订单填充总额、订单项、总数量
        order.setTotal(total);
        order.setOrderItems(orderItems);
        order.setTotalNumber(totalNumber);
	}

}
