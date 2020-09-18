package com.itzixue.weixin;

import com.itzixue.utils.SignUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author Mr.Dong
 * @create 2019-05-29 16:39
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {

    @RequestMapping("/core")
    public void core(String signature, String timestamp, String nonce, String echostr,
                     HttpServletRequest request,
                     HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        // 通过检验 signature 对请求进行校验，若校验成功则原样返回 echostr，表示接入成功，否则接入失败
        if (SignUtil.checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        }
        out.close();
        out = null;
    }

    @RequestMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth......");
        log.info("code={}",code);
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=wx2205cb8b3b816819&secret=1e73be1be27b95e96f73cc94f1d86616&code=CODE&grant_type=authorization_code";
        url = url.replaceAll("CODE",code);
        RestTemplate restTemplate = new RestTemplate();
        String jsonReuslt = restTemplate.getForObject(url, String.class);
        System.out.println(jsonReuslt);
    }

}
