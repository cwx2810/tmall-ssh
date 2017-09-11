package tmall.service;

import tmall.pojo.User;

public interface UserService extends BaseService {

	//µÇÂ½Ê±ÓÃµ½
	User get(String name, String password);

}
