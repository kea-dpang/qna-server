package kea.dpang.qna.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
@AllArgsConstructor
public class UserDto {
    private Long userId;
    private Long employeeNumber;
    private String name;
    private String email;
    private Date joinDate;
}
