package life.majiang.community.controller;

import com.sun.org.apache.bcel.internal.generic.RETURN;
import life.majiang.community.model.User;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author shaojie
 * @date 2019/11/21 - 13:56
 **/
@Controller
public class loginController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String loginn(@RequestParam("username") String username,
                         @RequestParam("password") String password,
                         Model model,
                         HttpServletResponse response) {
        if(username==""&&password==""){
            model.addAttribute("msg", "输入的密码和账户不能为");
            return "login";
        }
        List<User> yanzhen = userService.yanzhen(username, password);
        if (yanzhen != null) {
            //分布系统，单点登录
            for (User u : yanzhen) {
                String token = u.getToken();
                Cookie cookie = new Cookie("token", token);
                response.addCookie(cookie);
                return "redirect:/";
            }
        }
            model.addAttribute("msg", "输入的密码或账户错误");
            return "login";
    }
}