package com.gd.travel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : GD
 * @date :2020/3/18 : 16:00
 */
public class ReadHtmlTest2 {
    public static void main(String[] args) {
        String html = "<a target=\"_blank\" href=\"//weibo.com/6580708111\"><img width=\"30\" height=\"30\" alt=\"jhgkjdgs\" src=\"https://tvax4.sinaimg.cn/crop.0.0.996.996.50/007blYNply8g7anpupkzij30ro0rogne.jpg?KID=imgbed,tva&amp;Expires=1584521934&amp;ssig=hN2mrKpOmi\" usercard=\"id=6580708111\" ucardconf=\"type=1\"></a>\n";
        String uidReg = "usercard\\=\"id=(.*?)\"";
        Pattern uidP = Pattern.compile(uidReg);
        Matcher uidM = uidP.matcher(html);
        if(uidM.find()){
            System.out.println(uidM.group(1));
        }
        String headUrlReg = "src\\=\"(.*?)\"";
        Pattern headUrlP = Pattern.compile(headUrlReg);
        Matcher headUrlM = headUrlP.matcher(html);
        if(headUrlM.find()){
            System.out.println(headUrlM.group(1));
        }
        String nikeReg = "alt\\=\"(.*?)\"";
        Pattern nikeRegP = Pattern.compile(nikeReg);
        Matcher nikeRegM = nikeRegP.matcher(html);
        if(nikeRegM.find()){
            System.out.println(nikeRegM.group(1));
        }

    }
}
