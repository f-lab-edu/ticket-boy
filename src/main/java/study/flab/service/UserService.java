package study.flab.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import study.flab.dto.UserDTO;
import study.flab.entity.UserEntity;
import study.flab.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  public void save(UserDTO userDTO) {
    //dto-> entity 변환
    //repository save메서드 호출
    //repository의 save메서드 호출 (entity객체를 넘겨줘야함)
    UserEntity userEntity = UserEntity.toUserEntity(userDTO);
    userRepository.save(userEntity);
  }

  public UserDTO login(UserDTO userDTO) {
    //이메일 체크, 비밀번호 일치 여부 판단
    Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(userDTO.getUserEmail());
    //해당 이메일을 가진 회원정보
    if(byUserEmail.isPresent()){
      UserEntity userEntity = byUserEmail.get();
      if(userEntity.getUserPassword().equals(userDTO.getUserPassword())){
        //비밀번호 일치
        //entity -> dto 변환
        UserDTO dto = UserDTO.toUserDTO(userEntity);
        return dto;
      }else {
        return null;
      }
    }else{ //조회결과 x
      return null;
    }
  }

  public List<UserDTO> findAll() {
    List<UserEntity> userEntityList = userRepository.findAll();
    List<UserDTO> userDTOList = new ArrayList<>();
    for(UserEntity userEntity : userEntityList){
      userDTOList.add(UserDTO.toUserDTO(userEntity));
    }
    return userDTOList;
  }

  public UserDTO findById(Long id){
    Optional<UserEntity> optionalUserEntity = userRepository.findById(id);
    if(optionalUserEntity.isPresent()){
      return UserDTO.toUserDTO(optionalUserEntity.get());
    } else{
      return null;
    }
  }

  public UserDTO updateForm(String myEmail) {
    Optional<UserEntity> optionalUserEntity = userRepository.findByUserEmail(myEmail);
    if(optionalUserEntity.isPresent()){
      return UserDTO.toUserDTO(optionalUserEntity.get());
    } else{
      return null;
    }
  }

  public void update(UserDTO userDTO) {
    userRepository.save(UserEntity.toUpdateUserEntity(userDTO));
  }

  public void deleteById(Long id) {
    userRepository.deleteById(id);
  }

  public String emailCheck(String userEmail) {
    Optional<UserEntity> byUserEmail = userRepository.findByUserEmail(userEmail);
    if(byUserEmail.isPresent()){
      return null;
    } else{
      return "ok";
    }
  }

}
