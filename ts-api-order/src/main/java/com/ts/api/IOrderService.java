package com.ts.api;

import org.springframework.web.bind.annotation.RequestMapping;


public interface IOrderService {

	// 订单服务调用会员服务接口信息 feign
	@RequestMapping("/orderToUser")
	String orderToUser(String name);

	// 订单服务接口调用会员服务接口
	@RequestMapping("/orderToUserInfo")
	String orderToUserInfo();

	// 订单服务接口
	@RequestMapping("/orderInfo")
	String orderInfo();
}
