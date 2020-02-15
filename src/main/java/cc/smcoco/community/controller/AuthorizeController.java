package cc.smcoco.community.controller;

import cc.smcoco.community.dto.AccessTokenDTO;
import cc.smcoco.community.dto.GithubUser;
import cc.smcoco.community.mapper.UserMapper;
import cc.smcoco.community.model.User;
import cc.smcoco.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Controller
public class AuthorizeController {
    //授权控制器
    @Autowired
    private GithubProvider githubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/callback")
        public String Callback(@RequestParam(name="code") String code,
                @RequestParam(name="state") String state,
                HttpServletRequest request){//Spring把上下文中的request写入使用。
            //创建一个AccessTokenDTO对象，并设置其参数，为我们要去Github中找到指定accessToken的标识。
            AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
            accessTokenDTO.setClient_id(clientId);
            accessTokenDTO.setClient_secret(clientSecret);
            accessTokenDTO.setCode(code);
            accessTokenDTO.setRedirect_uri(redirectUri);
            accessTokenDTO.setState(state);
            System.out.println(accessTokenDTO.getClient_id());
            System.out.println(accessTokenDTO.getClient_secret());
            //通过设定好的参数标识，获得accessToken和user信息
            String accessToken = githubProvider.getAccessToken(accessTokenDTO);//GithubProvider类中的方法之一
            GithubUser githubUser = githubProvider.getUser(accessToken);//GithubProvider类中的方法之一
            System.out.println(githubUser
                    .getId());
            if(githubUser!=null){
                //创建model里的User并赋值
                User user = new User();
                user.setToken(UUID.randomUUID().toString());
                user.setName(githubUser.getName());
                user.setAccountId(String.valueOf(githubUser.getId()));
                user.setGmtCreate(System.currentTimeMillis());
                user.setGmtModified(user.getGmtCreate());
                userMapper.insert(user);
                //登陆成功，写Cookies和session
                request.getSession().setAttribute("user",githubUser);
                return "redirect:/";
            }
            else{
                //登陆失败，重新登录
                return "redirect:/";
            }
    }
}
