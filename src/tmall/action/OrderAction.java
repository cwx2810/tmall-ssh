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
        //��ҳ��ѯ
        orders = orderService.listByPage(page);
        //��������䶩������Ϣ
        orderItemService.fill(orders);
        //�������ת
        return "listOrder";
    }
 
    @Action("admin_order_delivery")
    public String delivery() {
        t2p(order);
        //�޸ķ���ʱ��
        order.setDeliveryDate(new Date());
        //���÷���״̬
        order.setStatus(OrderService.waitConfirm);
        //���µ����ݿ�
        orderService.update(order);
        //�ͻ�����ת
        return "listOrderPage";
    }
}
