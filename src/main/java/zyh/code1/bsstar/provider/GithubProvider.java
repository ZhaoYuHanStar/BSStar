package zyh.code1.bsstar.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;
import zyh.code1.bsstar.dto.AccessTokenDTO;
import zyh.code1.bsstar.dto.GithubUser;

import java.io.IOException;

/**
 * @ClassName GithubProvider
 * @Description
 * @Author 赵语涵
 * @Date 2020-11-03 22:48
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO accessTokenDTO) {
        MediaType mediaType = MediaType.get("application/json; charset=utf-8");
        OkHttpClient client = new OkHttpClient();

        RequestBody body = RequestBody.create (mediaType, JSON.toJSONString(accessTokenDTO));
        Request request = new Request.Builder()
                .url("https://github.com/login/oauth/access_token")
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            String tokenString = string.split("&")[0].split("=")[1];

            return tokenString;
        } catch (Exception e) {

            e.printStackTrace();
        }
        return null;
    }
    public GithubUser getUser(String accessToken) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://api.github.com/user")
                .header("Authorization", "token " + accessToken)
                .build();
        try {
            Response response = client.newCall(request).execute();
            String string = response.body().string();
            GithubUser githubUser = JSON.parseObject(string, GithubUser.class);

            return githubUser;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
