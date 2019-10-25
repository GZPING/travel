package com.gd.travel.service.impl;

import cn.hutool.json.JSONObject;
import com.gd.travel.service.IWeiboDataService;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.omg.CORBA.NameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Author : GD
 * @Create :2019/10/25 : 14:46
 */
public class WeiboDataServiceImpl implements IWeiboDataService {


    /**
     *
     * @param username
     * @param password
     * @return
     */
    public boolean login(String username,String password) {
        if (preLogin(username,password)) {
            String url = "http://login.sina.com.cn/sso/login.php?client=ssologin.js(v1.4.19)";
            List<NameValuePair> parms = new ArrayList<NameValuePair>();
            Map<String,String> paramMap = new HashMap<String,String>();

            paramMap.put("entry", "weibo");
            paramMap.put("geteway", "1");
            paramMap.put("from", "");
            paramMap.put("savestate", "7");
            paramMap.put("useticket", "1");
            paramMap.put(
                    "pagerefer",
                    "http://login.sina.com.cn/sso/logout.php?entry=miniblog&r=http%3A%2F%2Fweibo.com%2Flogout.php%3Fbackurl%3D%2F");
            paramMap.put("vsnf", "1");
            paramMap.put("su", su);
            paramMap.put("service", "miniblog");
            paramMap.put("servertime", servertime + "");
            paramMap.put("nonce", nonce);
            paramMap.put("pwencode", "rsa2");
            paramMap.put("rsakv", rsakv);
            paramMap.put("sp", sp);
            paramMap.put("encoding", "UTF-8");
            paramMap.put("prelt", "182");
            paramMap.put(
                    "url",
                    "http://weibo.com/ajaxlogin.php?framelogin=1&callback=parent.sinaSSOController.feedBackUrlCallBack");
            paramMap.put("domain", "sina.com.cn");
            paramMap.put("returntype", "META");
            try {
                String content = HttpsClientUtil.doPost(url, paramMap, "utf-8");
                logger.info("content----------" + content);
                String regex = "location.replace\\('([\\s\\S]*?)'\\);";// \\(' '\\)特殊符转译
                // 匹配（''）里面的内容//location.replace([\\s\\S]*?)
                Pattern p = Pattern.compile(regex);
                Matcher m = p.matcher(content);
                if (m.find()) {
                    logger.info("ss = " + m.group());
                    location = m.group(1);
                    if (location.contains("reason=")) {// 进入此处逻辑说明报错
                        errInfo = location.substring(location
                                .indexOf("reason=") + 7);
                        errInfo = URLDecoder.decode(errInfo, "GBK");
                    } else {
                        logger.info("location = " + location);
                        String result = HttpsClientUtil.doGet(location);

                        int beginIndex = result.indexOf("(");
                        int endIndex = result.lastIndexOf(")");
                        result = result.substring(beginIndex + 1, endIndex);// 截取括号里面的json字符串
                        // content = URLDecoder.decode(content, "UTF-8");
                        JSONObject jsonObject = JSONObject.parseObject(result);// 转换为json
                        uniqueid = jsonObject.getJSONObject("userinfo")
                                .getString("uniqueid");
                        userdomain = jsonObject.getJSONObject("userinfo")
                                .getString("userdomain");
                        logger.info("result--------------" + result);
                        return true;
                    }
                }
            } catch (IOException e) {
                logger.error("抛出IO异常:"+e);
            }
        }
        return false;
    }

    public boolean preLogin(String username,String password) {

        boolean flag = false;
        try {
            su = new String(Base64.encode(URLEncoder.encode(username,
                    "UTF-8").getBytes()));
            String url = "https://login.sina.com.cn/sso/prelogin.php?entry=weibo&callback=sinaSSOController.preloginCallBack&rsakt=mod&checkpin=1&"
                    + "client=ssologin.js(v1.4.19)&_=" + System.currentTimeMillis();
            url += "&su=" + su;
            String content;
            content =HttpsClientUtil.doGet(url);
            logger.info("content------------" + content);
            JSONObject json = JSONObject.parseObject(content.replace("sinaSSOController.preloginCallBack(", "").replace("})", "}"));
            logger.info(json.toJSONString());
            servertime = json.getLong("servertime");
            nonce = json.getString("nonce");
            rsakv = json.getString("rsakv");
            pubkey = json.getString("pubkey");
            flag = encodePwd(password);
        } catch (UnsupportedEncodingException e) {
            logger.info("preLogin:抛出UnsupportedEncoding异常:"+e);
        } catch (IOException e) {
            logger.info("preLogin抛出IO异常:"+e);
        }
        return flag;
    }
}

