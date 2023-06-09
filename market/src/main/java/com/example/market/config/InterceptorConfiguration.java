package com.example.market.config;
import com.example.market.interceptor.FruitCheckInterceptor;
import com.example.market.interceptor.VegetableCheckInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfiguration implements WebMvcConfigurer {
    private final FruitCheckInterceptor fruitCheckInterceptor;
    private final VegetableCheckInterceptor vegetableCheckInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(fruitCheckInterceptor)
                .addPathPatterns("/v1/product/fruit/**")
                .excludePathPatterns("/v1/product/token/**");

        registry.addInterceptor(vegetableCheckInterceptor)
                .addPathPatterns("/v1/product/vegetable/**")
                .excludePathPatterns("/v1/product/token/**");
    }
}
