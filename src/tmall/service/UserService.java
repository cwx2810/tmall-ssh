package tmall.service;

import tmall.pojo.User;

public interface UserService extends BaseService {

	//��½ʱ�õ�
	User get(String name, String password);

}
