package org.gordianknot.conf.web.controller;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

import org.gordianknot.conf.web.domain.User;
import org.gordianknot.conf.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
/**
* 用戶控制器
* @author aaron
* @date 2019-06-28
*/
@Controller
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/user/login")
    public Object login() {
        return "user/login";
    }
    
    @PostMapping("/user/login")
    public Object login(String username, String pass, Map<String, String> model, HttpServletRequest request) {
        User user = userService.getUser(username, pass);
        if (user == null) {
            model.put("msg", "賬號或者密碼錯誤");
            return "user/login";
        }
        request.getSession().setAttribute("login_user_name", user.getUsername());
        request.getSession().setAttribute("login_user_envs", user.getEnvs());
        return "redirect:/";
    }
    
    @GetMapping("/user/logout")
    public Object logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/user/login";
    }
}
