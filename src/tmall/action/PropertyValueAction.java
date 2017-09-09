package tmall.action;

import org.apache.struts2.convention.annotation.Action;

public class PropertyValueAction extends Action4Result {
    @Action("admin_propertyValue_edit")
    public String edit() {
        //�־û���������Ҫ��
        t2p(product);
        //��ʼ��
        propertyValueService.init(product);
        //���ݲ�Ʒ��ȡ��Ӧ����ֵ����
        propertyValues = propertyValueService.listByParent(product); 
        //�������ת
        return "editPropertyValue";
    }
    @Action("admin_propertyValue_update")
    public String update() {
    	//ǰ��editProperty.jsp�Ѽ����������봫��������ȡ�䴫�ݹ�����ֵ
        String value = propertyValue.getValue();
        //�־û�
        t2p(propertyValue);
        //�޸Ĵ�ֵ
        propertyValue.setValue(value);
        //�ύ�����ݿ�
        propertyValueService.update(propertyValue);
        //�������ת�ĳɹ�ҳ�棬������жϷ���ֵ��success�޸ĳɹ����ѱ߿����ó���ɫ�������Ǻ�ɫ�޸�ʧ��
        return "success.jsp";
    }
}
