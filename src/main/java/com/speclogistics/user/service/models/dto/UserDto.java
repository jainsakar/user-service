package com.speclogistics.user.service.models.dto;

import com.speclogistics.user.service.enums.UserGrade;
import com.speclogistics.user.service.enums.UserType;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@RequiredArgsConstructor
public class UserDto {
    private Integer parentId = -1;
    @NotNull(message = "User Name cannot be Null")
    @NotBlank(message = "User Name cannot be empty")
    private String name;
    private UserType userType;
    private UserGrade userGrade = UserGrade.NORMAL;
    private String createdBy;
    private String updatedBy;
}
