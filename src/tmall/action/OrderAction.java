package tmall.action;

import java.util.Date;

import org.apache.struts2.convention.annotation.Action;

import tmall.service.OrderService;
import tmall.util.Page;

public class OrderAction extends Action4Result {
    @Action("admin_order_list")
    public String list() {
        if (page == null)
            page = new Page();
        int total = orderService.total();
        page.setTotal(total);
        //分页查询
        orders = orderService.listByPage(page);
        //给订单填充订单项信息
        orderItemService.fill(orders);
        //服务端跳转
        return "listOrder";
    }
 
    @Action("admin_order_delivery")
    public String delivery() {
        t2p(order);
        //修改发货时间
        order.setDeliveryDate(new Date());
        //设置发货状态
        order.setStatus(OrderService.waitConfirm);
        //更新到数据库
        orderService.update(order);
        //客户端跳转
        return "listOrderPage";
    }
}
