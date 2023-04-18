package org.example.Controller;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.example.entity.User;
import org.example.feign.UserFeignService;
import org.example.entity.CommonResult;
import org.example.loadbalance.CustomerLoadBalanceConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/cart")
@LoadBalancerClient(name="provider-server",configuration=CustomerLoadBalanceConfiguration.class)
public class CartController {
    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/hello")
    public String hello(){
        return userFeignService.hello();
    }
    
    @GetMapping("/addCart/{userId}")
    //@CircuitBreaker(name = "backendA",fallbackMethod = "fallback")//fallback提供服务降级的返回
    public CommonResult<User> addCart(@PathVariable Integer userId) throws InterruptedException {
        //4月20号
//        System.out.println("进入方法");
        //Thread.sleep(10000L);//阻塞10秒
//        CommonResult<User> list=userFeignService.getUserById(userId);
//        System.out.println("离开方法");
//        return list;
        return userFeignService.getUserById(userId);
    }

    public CommonResult<User> fallback(Integer userId, Throwable e){

        e.printStackTrace();
        System.out.println("fallback已经调用");
        CommonResult<User> result=new CommonResult<>(400,"当前用户服务不正常，请稍后再试",new User());
        return result;
    }
}

