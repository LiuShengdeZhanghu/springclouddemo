package com.liusheng.client;

import com.liusheng.entity.Customer;
import com.liusheng.factory.SearchClientFallBackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 只能使用RequestMapping，不能使用GetMapping
 * 在注解中指定fallback的实现类
 */
@FeignClient(value = "SEARCH",
//        fallback = SearchClientFallback.class
        fallbackFactory = SearchClientFallBackFactory.class
        )
public interface SearchClient {

    // 这里的方法要和调用的目标服务的方法相同
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    String search();

    // 参数传递
    @RequestMapping(value = "/search/{id}", method = RequestMethod.GET)
    Customer findById(@PathVariable(value = "id") Integer id);

    @RequestMapping(value = "/getCustomer", method = RequestMethod.GET)
    Customer getCustomer(@RequestParam(value = "id") Integer id, @RequestParam(value = "name") String name,
                         @RequestParam(value = "age") Integer age);

    @RequestMapping(value = "/customer", method = RequestMethod.POST)
    Customer saveCustomer(@RequestBody Customer customer);
}
