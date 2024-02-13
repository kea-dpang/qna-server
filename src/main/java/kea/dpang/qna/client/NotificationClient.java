package kea.dpang.qna.client;

import kea.dpang.qna.base.BaseResponse;
import kea.dpang.qna.base.SuccessResponse;
import kea.dpang.qna.client.dto.RequestNotificationDto;
import kea.dpang.qna.config.feign.NotificationFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "notification-server", configuration = NotificationFeignConfig.class)
public interface NotificationClient {
    @PostMapping("/api/notifications/email")
    ResponseEntity<BaseResponse> postNotificationEmail (
            @RequestBody RequestNotificationDto requestDto);
}
