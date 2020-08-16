package top.xiaobolin.shequ.provider;

import com.alibaba.fastjson.JSON;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.stereotype.Component;
import top.xiaobolin.shequ.dto.AccessTokenDTO;
import top.xiaobolin.shequ.dto.GithubUser;

import java.io.IOException;

/**
 * @author：xiaobolin
 * @date 2020/7/9/0009 - 22:20
 */
@Component
public class GithubProvider {
    public String getAccessToken(AccessTokenDTO AccessTokenDTO) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String a = "https://graph.qq.com/oauth2.0/token?grant_type="+AccessTokenDTO.getGrant_type()+"&client_id="+AccessTokenDTO.getClient_id()+"&client_secret="
                +AccessTokenDTO.getClient_secret()+"&code="+AccessTokenDTO.getCode()+"&redirect_uri="+AccessTokenDTO.getRedirect_uri();
        Request request = new Request.Builder()
                .url(a)
                .build();
        System.out.println("获取accessToken路径"+request);

        try (Response response = client.newCall(request).execute()) {
            String string = response.body().string();
            String token = string.split("&")[0].split("=")[1];
            return token;
        } catch (Exception e){
        e.printStackTrace();
        }
        return null;
    }
    public String getOpenid(String accessToken){
    OkHttpClient OkHttpClient = new OkHttpClient();
    Request request = new Request.Builder()
            .url("https://graph.qq.com/oauth2.0/me?access_token="+accessToken)
            .build();
        System.out.println("获取openid路径"+request);
    try {
        Response response = OkHttpClient.newCall(request).execute();
        String string = response.body().string();
        String openid = string.substring(45, 77);
        return openid;
    }catch (IOException e){

    }
    return null;
    }

    public GithubUser getUser(String accessToken, String openid, String Client_id){
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("https://graph.qq.com/user/get_user_info?access_token="+accessToken+"&oauth_consumer_key="+Client_id+"&openid="+openid)
                .build();
        System.out.println("获取信息路径"+request);
         try {
            Response response = client.newCall(request).execute();
            String user = response.body().string();
            System.out.println(user);
            GithubUser GithubUser = JSON.parseObject(user, top.xiaobolin.shequ.dto.GithubUser.class);
            return GithubUser;
         } catch (IOException e) {
             e.printStackTrace();
         }
         return null;
    }

    public String githubUnionid(String accessToken) {
        OkHttpClient client = new OkHttpClient();

        Request request = new Request.Builder()
                .url("https://graph.qq.com/oauth2.0/me?access_token=" + accessToken + "&unionid=1")
                .build();
        System.out.println("获取唯一id路径"+request);
        try{
            Response response = client.newCall(request).execute();
            String unionId = response.body().string();
            String unionId2 = unionId.substring(94, 126);
            return unionId2;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
