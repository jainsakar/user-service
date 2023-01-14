package com.speclogistics.user.service.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.speclogistics.user.service.enums.UserGrade;
import com.speclogistics.user.service.enums.UserType;
import lombok.*;
import org.springframework.data.annotation.*;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
@RequiredArgsConstructor
@Table("Users")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class User {
    @Id
    private Integer id;
    private Integer parentId;

    @NotNull(message = "User Name cannot be Null")
    @NotBlank(message = "User Name cannot be empty")
    private String name;

    @CreatedDate
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfRegistration;

    private UserType userType;

    private UserGrade userGrade;
    @LastModifiedDate
    private LocalDate updatedAt;
    private String createdBy;

    private String updatedBy;

    private LocalDate dateOfAssociation;

}
