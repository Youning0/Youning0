package com.the.community.controller;

import com.the.community.dto.AccessTokenDTO;
import com.the.community.dto.GithubUser;
import com.the.community.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
public class AuthorizeController {
    @Autowired
    private GithubProvider githubProvider;

    @GetMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state")String state)  {
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setCode(code);
        accessTokenDTO.setClient_id("6f1539df751dbbed9b2b");
        accessTokenDTO.setClient_secret("47c7ddfa7748caf81fde21a22e1e9bfc477208b5");
        accessTokenDTO.setRedirect_uri("http://loacalhost:8887/callback");
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        try {
           GithubUser user = githubProvider.getUser(accessToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "index";
    }
}
