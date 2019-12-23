package com.gd;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.poi.excel.ExcelReader;
import cn.hutool.poi.excel.ExcelUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @Description
 * @Author GD
 * @Date 2019/11/14 22:51
 */
@Slf4j
public class Test1 {

    private ConcurrentMap<String, String> cookies = new ConcurrentHashMap<>();

    @Test
    public void test() {
        ExcelReader reader = ExcelUtil.getReader(FileUtil.file("C:/Users/Doctor/Desktop/流量平台64.xls"), 0);
        List<List<Object>> rows = reader.read();
        String token2 = "1250606707";
        String cookie2 = "pgv_pvi=2594577408; RK=6KgAhGJdRE; ptcz=1d854362a9bbc6007bcd83b73ef88a91048e45a26cccd148456cc87310a7bf26; pgv_pvid=3997208831; eas_sid=z1Y566L327m770C3a865F5u4r0; LW_sid=r1t5n6H3Y7H7x0Z5w8n2q3M9w5; LW_uid=s1l5w6f3j7H7V0T5u8b204E0c2; ua_id=RNTMI5o1Na8VxPamAAAAACo2wBnitGZO1nSnGIouIiQ=; mm_lang=zh_CN; o_cookie=455607047; tvfe_boss_uuid=94c85faf088831a1; ts_uid=6146665378; noticeLoginFlag=1; data_bizuin=3091150353; bizuin=3229029696; data_ticket=i7rp3D1tMlvueE03AUN+BEgDM+eDPqc9Kv5Lp7l6VlyOhpjahV4zdRrq62Egc19Q; slave_sid=NzNGdzZTTjROaW1JRnk4OEhsNlNVYmpUVndZcW94QXhhWWdtdTIwXzVmZWNocVo4VDJzcjRVQkl6ek1tSzlaZWJuRzY5WVNaTlJIRkVoQWtHTlM3RGUxVVhfVmQ0d3lnMmVqb2U2TjVCdVZDZERJMTRoNzRSVGVUMlFMVmVaYm1oam1ja0c1MXFaM0NTTFVm; slave_user=gh_831e2d6f3f35; xid=75f5d4a0a5f7b5da38e953b5ff2e523a; openid2ticket_oETY_uAZWsM0AZqrHCzYt0wrNMXQ=A2vuMTvAAiN5TXjyb9+L6m7+/WxM/0Rp+nISIxlpPHM=";
        cookies.put(token2, cookie2);

        for (int i = 200; i < 301; i++) {
            Object nickname = rows.get(i).get(0);
            if (nickname == null || nickname.toString().isEmpty()) {
                System.out.println("第" + (i + 1) + "行昵称为空");
                continue;
            }
            String nick = nickname.toString();
            try {
                String nickEncode = URLEncoder.encode(nick,"UTF-8");
                while (true){
                    String fakeid = null;
                    for (Map.Entry<String, String > entry : cookies.entrySet()) {
                        fakeid = getByNickname(nickEncode, entry.getKey(), entry.getValue());
                        if (fakeid != null) {
                            break;
                        }
                    }
                    Thread.sleep(RandomUtil.randomInt(20 * 1000,  30 * 1000));
                    if (fakeid == null){
                        // 三个号都不可以睡眠一会 1-3分钟
                        // 随机睡眠
                        Thread.sleep(RandomUtil.randomInt(60 * 1000, 3 * 60 * 1000));
                    } else {
                        System.out.println(nick + " : " + fakeid);
                        break;
                    }
                }
            } catch (Exception e) {
                log.error("url编码异常nick:{}", nick, e);
            }
        }
    }

    public String getByNickname(String nickEncode, String token, String cookie){
        Map<String, String> headers = new HashMap<>(32);
        headers.put("authority", "mp.weixin.qq.com");
        headers.put("method", "GET");
        headers.put("scheme", "https");
        headers.put("accept", "application/json, text/javascript, */*; q=0.01");
        headers.put("accept-encoding", "gzip, deflate, br");
        headers.put("accept-language", "zh-CN,zh;q=0.9,en;q=0.8");
        headers.put("cache-control", "no-cache");
        headers.put("cookie", cookie);
        headers.put("pragma", "no-cache");
        headers.put("referer", "https://mp.weixin.qq.com/cgi-bin/appmsg?t=media/appmsg_edit_v2&action=edit&isNew=1&type=10&token="+ token +"&lang=zh_CN");
        headers.put("sec-fetch-mode", "cors");
        headers.put("sec-fetch-site", "same-origin");
        headers.put("user-agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/78.0.3904.97 Safari/537.36");
        headers.put("x-requested-with", "XMLHttpRequest");
//        String randomNumbers = "0." + RandomUtil.randomNumbers(17);
        String url = "https://mp.weixin.qq.com/cgi-bin/searchbiz?action=search_biz&token=" + token +"&lang=zh_CN&f=json&ajax=1&random=0.60265928104563988&query=" + nickEncode + "&begin=0&count=5";
        HttpRequest request = HttpUtil.createGet(url).addHeaders(headers);
        String body = request.execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        JSONArray list = jsonObject.getJSONArray("list");
        if (list != null) {
            JSONObject jsonObject1 = list.getJSONObject(0);
            if (jsonObject1 != null) {
                return jsonObject1.getString("fakeid");
            }
        }
        return null;
    }

}
