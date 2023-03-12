package com.example.market.config;
import com.example.market.inteceptor.FruitCheckInterceptor;
import com.example.market.inteceptor.VegetableCheckInterceptor;
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
                .excludePathPatterns("/v1/product/token/**")
                .excludePathPatterns("/v1/product/vegetable/**");

        registry.addInterceptor(vegetableCheckInterceptor)
                .addPathPatterns("/v1/product/vegetable/**")
                .excludePathPatterns("/v1/product/token/**")
                .excludePathPatterns("/v1/product/fruit/**");
    }
}
