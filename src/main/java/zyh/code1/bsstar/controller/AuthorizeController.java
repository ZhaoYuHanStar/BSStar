package zyh.code1.bsstar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zyh.code1.bsstar.dto.AccessTokenDTO;
import zyh.code1.bsstar.dto.GithubUser;
import zyh.code1.bsstar.provider.GithubProvider;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName AuthorizeController
 * @Description
 * @Author 赵语涵
 * @Date 2020-11-03 22:42
 */
@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private String clientId;

    @Value("${github.client.secret}")
    private String clientSecret;

    @Value("${github.redirect.uri}")
    private String redirectUri;

    @GetMapping("/callback")
    public String callback(@RequestParam(name = "code") String code,
                           @RequestParam(name = "state") String state,
                           HttpServletRequest request) {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);

        String token = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser githubUser = githubProvider.getUser(token);
        if(githubUser != null) {
            // 登录成功 写入 cookie 和 session
            request.getSession().setAttribute("user", githubUser);
            return "redirect:/";
        } else {
            //登录失败
            return "redirect:/";
        }
    }
}
