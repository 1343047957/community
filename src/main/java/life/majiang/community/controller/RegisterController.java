package life.majiang.community.controller;

import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.User;
import life.majiang.community.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;

/**
 *Created by codedrinker on 2019/11/21.
 **/
@Controller
public class RegisterController {
    @Autowired
    private UserService userService;

    @GetMapping("/register")
    public String re(){
        return "register";
    }
    @PostMapping("/register")
    public String resgist(@RequestParam(value="username") String username,
                        @RequestParam(value="password") String password,
                        Model model
                         ) {
        if (username != null || password != null) {
            User user = new User();
            user.setAccountId(username);
            user.setName(password);
            int num=(int)(Math.random()*4);
            switch (num){
                case 0:
                    user.setAvatarUrl("https://b-ssl.duitang.com/uploads/item/201608/21/20160821194924_UCvFZ.jpeg");
                    break;
                case 1:
                    user.setAvatarUrl("https://b-ssl.duitang.com/uploads/item/201511/13/20151113110434_kyReJ.jpeg");
                    break;
                case 2:
                    user.setAvatarUrl("https://b-ssl.duitang.com/uploads/item/201505/19/20150519231117_wmYEU.thumb.700_0.jpeg");
                    break;
                case 3:
                    user.setAvatarUrl("https://b-ssl.duitang.com/uploads/item/201505/19/20150519231117_wmYEU.thumb.700_0.jpeg");
                    break;
                case 4:
                    user.setAvatarUrl("https://a-ssl.duitang.com/uploads/item/201505/19/20150519233153_CAvFt.jpeg");
                    break;
                default:
                    user.setAvatarUrl("https://a-ssl.duitang.com/uploads/item/201505/19/20150519233153_CAvFt.jpeg");
            }

            String token = UUID.randomUUID().toString();
            user.setToken(token);
            int add = userService.add(user);
            if (add == 0) {
                model.addAttribute("sss", "用户已经存在");
                return "register";
            }else{
                return "redirect:login";
            }
        }else{
            String msg = "用户的密码或账号不能空";
            model.addAttribute("sss", msg);
            return "register";
        }
    }

    @PostMapping("/register1")
    @ResponseBody
    public String aa(@RequestParam("username") String username){
        if(username!=null){
            List<User> users = userService.select1(username);
            if(users.isEmpty()){
                return "shibai";
            }else{
                return "chenggong";
            }
        }else{
            return "shibai";
        }

    }
}
