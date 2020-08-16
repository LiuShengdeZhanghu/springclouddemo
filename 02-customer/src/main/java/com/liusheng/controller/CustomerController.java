package com.liusheng.controller;

import com.liusheng.entity.Customer;
import com.liusheng.client.SearchClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

/**
 *
 */
@RestController
public class CustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private SearchClient searchClient;

//    @Autowired
//    private EurekaClient eurekaClient;

    @GetMapping("/test")
    public String test(){
//        // 通过EurekaClient获取到SEARCH的服务信息，这里的false表示使用http而不是https
//        InstanceInfo info = eurekaClient.getNextServerFromEureka("SEARCH", false);
//        // 获取目标服务的访问地址
//        String homePageUrl = info.getHomePageUrl();
//        System.out.println(homePageUrl);

        // 使用RestTemplate+Ribbon去请求目标服务
//        String res = restTemplate.getForObject("http://SEARCH/search", String.class);

        // 使用feign去调用目标服务的接口，这种方式也有Ribbon设置的负载均衡的策略
        String res = searchClient.search();
        return res;
    }

    /**
     * 使用HystrixCommand指定线程隔离策略，以及服务调用出错时怎么处理的方法。
     * @param id
     * @return
     */
    @GetMapping(value = "/search/{id}", produces = { "application/json;charset=UTF-8" })
    @HystrixCommand(fallbackMethod = "findByIdFallback", commandProperties = {
            @HystrixProperty(name="execution.isolation.strategy",value = "THREAD"),
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "3000"),
    })
    public Customer findById(@PathVariable Integer id) throws InterruptedException {
        System.out.println(Thread.currentThread().getName());
//        int i = 10/0;
        Thread.sleep(3000);
        Customer customer = searchClient.findById(1);
        return customer;
    }

    // hystrix的服务降级机制，当方法描述（除了方法名之外都相同）相同的方法执行调用目标服务出错时，启动此方法
    // 返回一个托底数据
    public Customer findByIdFallback(@PathVariable Integer id){
        return new Customer(-1,"error",0);
    }

    @GetMapping(value = "/getCustomer", produces = { "application/json;charset=UTF-8" })
    public Customer getCustomer(@RequestParam Integer id, @RequestParam String name, @RequestParam Integer age){
        return searchClient.getCustomer(2,"wangling",22);
    }

    @GetMapping(value = "/customer", produces = { "application/json;charset=UTF-8" })
    public Customer saveCustomer(Customer customer){
        return searchClient.saveCustomer(customer);
    }
}
