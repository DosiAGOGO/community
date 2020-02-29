package cc.smcoco.community.controller;

import cc.smcoco.community.mapper.QuestionMapper;
import cc.smcoco.community.mapper.UserMapper;
import cc.smcoco.community.model.Question;
import cc.smcoco.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


//使方法成为Controller方法控制publish页面，同时，用@GetMapping获得publish路径，并附到publish方法上。
@Controller
public class PublishController {
    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/publish")
    public String publish() {
        return "publish";
    }

    @PostMapping("/publish")
    public String doPublish(
            @RequestParam("title") String title,
            @RequestParam("description") String description,
            @RequestParam("tag") String tag,
            HttpServletRequest request,
            Model model
    ) {
        model.addAttribute("title", title);
        model.addAttribute("description", description);
        model.addAttribute("tag", tag);
        if (title == null || title.equals("")) {
            model.addAttribute("error", 2);
            return "publish";
        }
        if (description == null || description.equals("")) {
            model.addAttribute("error", 3);
            return "publish";
        }
        if (tag == null || tag.equals("")) {
            model.addAttribute("error", 4);
            return "publish";
        }

        User user = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("token")) {
                    //名字等于token的话他的值就是token的值。
                    String token = cookie.getValue();
                    user = userMapper.findByToken(token);
                    if (user != null) {
                        request.getSession().setAttribute("user", user);
                    }
                    break;
                }
            }
        }

        if (user == null) {
            model.addAttribute("error", 1);
            return "publish";
        }

        Question question = new Question();
        question.setTitle(title);
        question.setDescription(description);
        question.setTag(tag);
        question.setCreator(user.getId());
        question.setGmtCreate(System.currentTimeMillis());
        question.setGmtModified(question.getGmtCreate());
        questionMapper.create(question);
        return "redirect:/";

    }
}

