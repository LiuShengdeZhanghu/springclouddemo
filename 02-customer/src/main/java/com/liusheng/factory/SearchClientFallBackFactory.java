package com.liusheng.factory;

import com.liusheng.client.SearchClient;
import com.liusheng.fallback.SearchClientFallback;
import feign.hystrix.FallbackFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 结合使用了SearchClientFallback
 */
@Component
public class SearchClientFallBackFactory implements FallbackFactory<SearchClient> {

    @Autowired
    private SearchClientFallback searchClientFallback;

    @Override
    public SearchClient create(Throwable throwable) {
        // 打印被调用方的出错日志
        throwable.printStackTrace();
        return searchClientFallback;
    }
}
