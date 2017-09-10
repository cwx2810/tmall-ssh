package tmall.service;

import java.util.List;

import tmall.pojo.Order;

public interface OrderItemService extends BaseService {
	public void fill(List<Order> orders);
    public void fill(Order order);
}
