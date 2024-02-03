package kea.dpang.qna.client;

import io.swagger.v3.oas.annotations.Parameter;
import kea.dpang.qna.base.SuccessResponse;
import kea.dpang.qna.dto.response.UserDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-server")
public interface UserServiceClient {
    @GetMapping("/api/users/{userId}")
    ResponseEntity<SuccessResponse<UserDto>> getUser(
            @PathVariable @Parameter(description = "정보를 요청할 유저 ID") Long userId
    );
}