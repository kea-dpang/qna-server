package kea.dpang.qna.entity;

/**
 * 문의 카테고리를 나타내는 enum 클래스
 */
public enum Category {
    ITEM_INQUIRY, // 상품 문의
    MEMBER_INFORMATION, // 회원 정보 문의
    ITEM_CONFIRMATION, // 상품 확인 문의
    DELIVERY, // 배송 문의
    EXCHANGE_CANCELLATION, // 교환/취소 문의
    ETC; // 기타 문의

    /**
     * 주어진 문자열을 대문자로 변환하여 카테고리 enum으로 변환합니다.
     *
     * @param value 카테고리 문자열
     * @return 변환된 카테고리 enum
     */
    public static Category fromString(String value) {
        return Category.valueOf(value.toUpperCase());
    }
}