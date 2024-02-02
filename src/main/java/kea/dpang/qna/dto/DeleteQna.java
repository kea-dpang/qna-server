package kea.dpang.qna.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class DeleteQna {
    private List<Long> deleteIds;
}
