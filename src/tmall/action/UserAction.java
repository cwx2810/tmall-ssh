package tmall.action;

import org.apache.struts2.convention.annotation.Action;

import tmall.util.Page;

public class UserAction extends Action4Result {
	//只有查询方法，添加、修改、删除交给用户完成
    @Action("admin_user_list")
    public String list() {
        if(page==null)
            page = new Page();
        int total = userService.total();
        page.setTotal(total);
        users = userService.listByPage(page);
        return "listUser";
    }
}
