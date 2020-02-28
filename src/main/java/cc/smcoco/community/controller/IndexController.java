package cc.smcoco.community.controller;

import cc.smcoco.community.mapper.UserMapper;
import cc.smcoco.community.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Controller
public class IndexController {
    //创建一个UserMapper对象，使用其中的findByToken方法。
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public String index(HttpServletRequest request) {
        //传入token 根据token寻找到user。token通过request取得。
        Cookie[] cookies = request.getCookies();
        //此处因为得到的是很多cookie所以要循环遍历，直到有一个的cookie满足。
        if (cookies != null && cookies.length != 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    //名字等于token的话他的值就是token的值。
                    String token = cookie.getValue();
                    User user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }

        }

        return "index";
    }
}
