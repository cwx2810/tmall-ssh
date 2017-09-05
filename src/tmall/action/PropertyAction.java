package tmall.action;

import org.apache.struts2.convention.annotation.Action;

import tmall.util.Page;

public class PropertyAction extends Action4Result {
    @Action("admin_property_list")
    public String list() {
    	//�ж��Ƿ��з�ҳ����û�о��½�һ��
        if(page==null)
            page = new Page();
        //�жϵ�ǰ������һ���ж�������
        int total = propertyService.total(category);
        //Ϊ��ҳ������������
        page.setTotal(total);
        //Ϊ��ҳ�������ò���
        page.setParam("&category.id="+category.getId());
        //���ݷ�ҳ�ͷ�������ȡ���Լ���
        propertys = propertyService.list(page,category);
        //˲ʱ�����־ö�����Ϊ�������м����Ҫ�õ�category
        t2p(category);
        //���ظ�ǰ̨ҳ��
        return "listProperty";
    }
     
    @Action("admin_property_add")
    public String add() {
    	//ͨ��service��property�������ݿ⣬��jsp�������ĳ������ƻ���id�������������ת�ͻ����id
        propertyService.save(property);
        return "listPropertyPage";
    }
     
    @Action("admin_property_delete")
    public String delete() {
    	//.....��֮�����ݵ�ʹ�ö���ֹ��һ������Ҫ�־û�
        t2p(property);
        propertyService.delete(property);
        return "listPropertyPage";
    }
     
    @Action("admin_property_edit")
    public String edit() {
    	//��propertyת�ɳ־û�����sturctע���˲ʱ����ֻ��id�����־û���������ݿ�ͬ��������id����name
        t2p(property);
        //�������ת
        return "editProperty";
    }
    @Action("admin_property_update")
    public String update() {
    	//ͨ��service����property����
        propertyService.update(property);
        return "listPropertyPage";
    }
}
