package tmall.action;

import org.apache.struts2.convention.annotation.Action;

public class ForeAction extends Action4Result {
	@Action("forehome")
    public String home() {
        categorys = categoryService.list();
        productService.fill(categorys);
        productService.fillByRow(categorys);
        return "home.jsp";
    }
}
