package com.ts.api.service.impl;

import com.ts.api.IOrderService;
import com.ts.api.fallback.UserServiceFallback;
import com.ts.api.po.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
public class OrderService implements IOrderService {

	// 订单服务继承会员服务接口，用来实现feign客户端 减少重复接口代码
	@Autowired
	private UserServiceFallback userServiceFallback;

	@RequestMapping("/orderToUser")
	public String orderToUser(String name) {
		UserDTO user = userServiceFallback.getUser(name);
		return user == null ? "没有找到用户信息" : user.toString();
	}

	// 没有解决服务雪崩效应
	@RequestMapping("/orderToUserInfo")
	public String orderToUserInfo() {
		return userServiceFallback.getUserInfo();
	}

	// 订单服务接口
	@RequestMapping("/orderInfo")
	public String orderInfo() {
		System.out.println("orderInfo:" + "线程池名称:" + Thread.currentThread().getName());
		return "订单服务接口-成功";
	}


	// 解决服务雪崩效应
	// fallbackMethod 方法的作用：服务降级执行
	// @HystrixCommand 默认开启线程池隔离方式,服务降级,服务熔断
	// 设置Hystrix服务超时时间
	/**
	 * @HystrixCommand<br>
	 * 					默认开启服务隔离方式 以线程池方式<br>
	 *                     默认开启服务降级执行方法orderToMemberUserInfoHystrixFallback<br>
	 *                     默认开启服务熔断机制<br>
	 * 
	 * @return
	 */
	@HystrixCommand(fallbackMethod = "orderToUserInfoHystrixFallback")
	@RequestMapping("/orderToUserInfoHystrix")
	public String orderToUserInfoHystrix() {
		System.out.println("orderToMemberUserInfoHystrix:" + "线程池名称:" + Thread.currentThread().getName());
		return userServiceFallback.getUserInfo();
	}

	// @HystrixCommand(fallbackMethod = "orderToMemberUserInfoHystrixFallback")
	@RequestMapping("/orderToUserInfoHystrix02")
	public String orderToUserInfoHystrix02() {
		System.out.println("orderToMemberUserInfoHystrix:" + "线程池名称:" + Thread.currentThread().getName());
		return userServiceFallback.getUserInfo();
	}

	public String orderToUserInfoHystrixFallback() {
		return "返回一个友好的提示：服务降级,服务器忙，请稍后重试!";
	}

	// Hystrix 有两种方式配置保护服务 通过注解和接口形式
}
