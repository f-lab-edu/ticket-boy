package study.flab.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import study.flab.dto.UserDTO;

@Entity
@Setter
@Getter
@Table(name= "user_table")
public class UserEntity {
  // 테이블역할 자바객체처럼 활용
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY) //auto_increment
  private Long id;

  @Column(unique = true)
  private String userEmail;

  @Column
  private String userPassword;

  @Column(unique = true)
  private String userNickname;

  public static UserEntity toUserEntity(UserDTO userDTO){
    UserEntity userEntity = new UserEntity();
    userEntity.setUserEmail(userDTO.getUserEmail());
    userEntity.setUserNickname(userDTO.getUserNickname());
    userEntity.setUserPassword(userDTO.getUserPassword());
    return userEntity;
  }

  public static UserEntity toUpdateUserEntity(UserDTO userDTO){
    UserEntity userEntity = new UserEntity();
    userEntity.setId(userDTO.getId());
    userEntity.setUserEmail(userDTO.getUserEmail());
    userEntity.setUserNickname(userDTO.getUserNickname());
    userEntity.setUserPassword(userDTO.getUserPassword());
    return userEntity;
  }

}
