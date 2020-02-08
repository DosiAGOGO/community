package cc.smcoco.community.controller;

import cc.smcoco.community.dto.AccessTokenDTO;
import cc.smcoco.community.dto.GithubUser;
import cc.smcoco.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthorizeController {
    //授权控制器
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String Callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state){
        //创建一个AccessTokenDTO对象，并设置其参数，为我们要去Github中找到指定accessToken的标识。
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        //通过设定好的参数标识，获得accessToken和user信息
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);//GithubProvider类中的方法之一
        GithubUser user = githubProvider.getUser(accessToken);//GithubProvider类中的方法之一
        System.out.println(user.getName());
        return "index";
    }
}
