package kea.dpang.qna;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class QnaServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(QnaServerApplication.class, args);
    }

}
