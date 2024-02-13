package kea.dpang.qna.config.feign;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationFeignConfig {

    @Bean
    public RequestInterceptor requestInterceptor() {
        return requesttemplate -> {
            template.header("X-DPANG-SERVICE-NAME", "QNA-SERVICE");
        };
    }
}
