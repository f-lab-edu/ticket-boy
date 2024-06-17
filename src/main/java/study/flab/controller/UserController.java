package study.flab.controller;

import jakarta.servlet.http.HttpSession;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import study.flab.dto.UserDTO;
import study.flab.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  //회원가입 페이지 출력
  @GetMapping("/user/save")
  public String saveForm(){
    return "save";
  }

  @GetMapping("/user/login")
  public String loginForm(){
    return "login";
  }

  @GetMapping("/user/")
  public String findAll(Model model){
    List<UserDTO> userDtoList = userService.findAll();
    // html로 가져갈 데이터
    model.addAttribute("userList", userDtoList);
    return "list";
  }

  @GetMapping("/user/{id}")
  public String findId(@PathVariable Long id, Model model){
    UserDTO userDTO = userService.findById(id);
    model.addAttribute("user", userDTO);
    return "detail";
  }

  @GetMapping("/user/update")
  public String updateForm(HttpSession session, Model model){
    String myEmail = (String) session.getAttribute("loginEmail");
    UserDTO userDTO = userService.updateForm(myEmail);
    model.addAttribute("updateUser", userDTO);
    return "update";
  }

  @GetMapping("/user/delete/{id}")
  public String deleteById(@PathVariable Long id){
    userService.deleteById(id);
    return "redirect:/user/";
  }

  @GetMapping("/user/logout")
  public String logout(HttpSession session){
    session.invalidate();
    return "index";
  }

  @PostMapping("/user/save")
  public String save(@ModelAttribute UserDTO userDTO){
    System.out.println("userDTO" + userDTO);

    userService.save(userDTO);

    return "login";
  }

  @PostMapping("/user/login")
  public String login(@ModelAttribute UserDTO userDTO, HttpSession session){
    UserDTO loginResult = userService.login(userDTO);
    if(loginResult != null) {
      //로그인 성공
      session.setAttribute("loginEmail",loginResult.getUserEmail());
      return "main";
    } else {
      //로그인 실패
      return "login";
    }
  }

  @PostMapping("/user/update")
  public String update(@ModelAttribute UserDTO userDTO){
    userService.update(userDTO);
    return "redirect:/user/" + userDTO.getId();
  }

  @PostMapping("/user/email-check")
  public @ResponseBody String emailCheck(@RequestParam("userEmail") String userEmail){
//    System.out.println("userEmail" + userEmail);

    String checkResult = userService.emailCheck(userEmail);

    return checkResult;
  }

}
