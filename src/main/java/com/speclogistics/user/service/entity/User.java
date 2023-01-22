package com.speclogistics.user.service.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.speclogistics.user.service.enums.UserGrade;
import com.speclogistics.user.service.enums.UserType;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.relational.core.mapping.Table;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Objects;

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
    private LocalDate dateOfRegistration;

    private String userType;

    private String userGrade;

    @LastModifiedDate
    private LocalDate updatedAt;
    private String createdBy;

    private String updatedBy;

    private LocalDate dateOfAssociation;

    private char status;

    public void setUserGrade(UserGrade userGrade){
        this.userGrade = Objects.isNull(userGrade) ? null : userGrade.toString();
    }

    public void setUserType(UserType userType){
        this.userType = Objects.isNull(userType) ? null : userType.toString();
    }

    public  void setStatus(boolean status){
        this.status = status == true ? 'Y' : 'N';
    }

    public UserType getUserType(){
        return Objects.isNull(this.userType) ? null : UserType.valueOf(this.userType);
    }

    public UserGrade getUserGrade(){
        return Objects.isNull(this.userGrade) ? null : UserGrade.valueOf(this.userGrade);
    }

    public boolean getStatus(){
        return (this.status == 'Y' ? true : false);
    }

}
