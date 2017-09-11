package tmall.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import tmall.pojo.User;
import tmall.service.UserService;

@Service
public class UserServiceImpl extends BaseServiceImpl implements UserService {
	@Override
	public User get(String name, String password) {
		List<User> l  = list("name",name, "password",password);
		if(l.isEmpty())
			return null;
		return l.get(0);

	}
}
