package kea.dpang.qna.client.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Builder
@Data
@Getter
public class RequestNotificationDto {
    private String to;
    private String title;
    private String body;
}
