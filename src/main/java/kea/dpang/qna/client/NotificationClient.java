package kea.dpang.qna.client;

import kea.dpang.qna.base.BaseResponse;
import kea.dpang.qna.base.SuccessResponse;
import kea.dpang.qna.client.dto.RequestNotificationDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient("notification-server")
public interface NotificationClient {
    @PostMapping("/api/notifications/email")
    ResponseEntity<BaseResponse> postNotificationEmail (
            @RequestHeader("X-DPANG-CLIENT-ROLE")
            @RequestBody RequestNotificationDto requestDto);
}
