package kea.dpang.qna.client.dto;

import lombok.Getter;

@Getter
public class RequestItemServiceDto {
    private Long id;
    private String thumbnailImage;
    private String name;
    private int price;
    private int quantity;
    private int discountRate;
    private int discountPrice;
}
