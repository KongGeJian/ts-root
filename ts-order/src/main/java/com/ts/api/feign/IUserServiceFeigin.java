package com.ts.api.feign;

import com.ts.api.fallback.UserServiceFallback;
import com.ts.api.service.IUserService;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(value = "ts-user", fallback = UserServiceFallback.class)
public interface IUserServiceFeigin extends IUserService {
    // 服务降级 熔断
	// 实体类是存放接口项目还是 存放在实现项目 实体类存放在接口项目里面
	// 实体类和定义接口信息存放在接口项目
	// 代码实现存放在接口实现类里面

}
