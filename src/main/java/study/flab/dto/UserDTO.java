package study.flab.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import study.flab.entity.UserEntity;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
  private Long id;
  private String userEmail;
  private String userPassword;
  private String userNickname;

  public static UserDTO toUserDTO(UserEntity userEntity){
    UserDTO userDTO = new UserDTO();
    userDTO.setId(userEntity.getId());
    userDTO.setUserEmail(userEntity.getUserEmail());
    userDTO.setUserPassword(userEntity.getUserPassword());
    userDTO.setUserNickname(userEntity.getUserNickname());
    return userDTO;
  }
}
