package com.hbkj.test;

import java.util.List;

import com.hbkj.entity.Advice;
import com.hbkj.entity.User;
import com.hbkj.service.AdviceService;
import com.hbkj.service.UserService;

public class ServiceTest {
	public static void main(String[] args) {
		
		//UserService service = new UserService();
		AdviceService service = new AdviceService();
		
		/*List<User> users = service.getUserByPage(3,1);
		for (User user : users) {
			System.out.println(user.getUserName());
		}*/
		
//		System.out.println(service.deleteById(1));
		
		List<Advice> advices = service.getAdvice("1", 1, 1);
		for (Advice advice : advices) {
			System.out.println(advice.getAdviceId());
		}
	}
}
