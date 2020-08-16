package com.liusheng.fallback;

import com.liusheng.entity.Customer;
import com.liusheng.client.SearchClient;
import org.springframework.stereotype.Component;

/**
 * 设置被调用服务的api出错不能返回数据时，返回什么内容
 */
@Component
public class SearchClientFallback implements SearchClient {


    @Override
    public String search() {
        return "出现问题了";
    }

    @Override
    public Customer findById(Integer id) {
        return null;
    }

    @Override
    public Customer getCustomer(Integer id, String name, Integer age) {
        return null;
    }

    @Override
    public Customer saveCustomer(Customer customer) {
        return null;
    }
}
