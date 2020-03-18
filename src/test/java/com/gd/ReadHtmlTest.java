package com.gd;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : GD
 * @date :2020/3/18 : 14:30
 */
public class ReadHtmlTest {

    public static void main(String[] args) {
        String html = "<div class=\"list_box\">\n" +
                "    <div class=\"list_ul\" node-type=\"comment_list\">\n" +
                "        <!-- root_comment_id为了判断一级评论还是二级评论，增加dom表示用于前端递归查找节点 -->\n" +
                "        <div comment_id=\"4483827017863390\" class=\"list_li S_line1 clearfix\">\n" +
                "            <div class=\"WB_face W_fl\"><a target=\"_blank\" href=\"//weibo.com/6580708111\"><img width=\"30\" height=\"30\"\n" +
                "                                                                                            alt=\"jhgkjdgs\"\n" +
                "                                                                                            src=\"https://tvax4.sinaimg.cn/crop.0.0.996.996.50/007blYNply8g7anpupkzij30ro0rogne.jpg?KID=imgbed,tva&Expires=1584521934&ssig=hN2mrKpOmi\"\n" +
                "                                                                                            usercard=\"id=6580708111\"\n" +
                "                                                                                            ucardconf=\"type=1\"></a>\n" +
                "            </div>\n" +
                "            <div class=\"list_con\" node-type=\"replywrap\">\n" +
                "                <div class=\"WB_text\"><a target=\"_blank\" href=\"//weibo.com/6580708111\" usercard=\"id=6580708111\"\n" +
                "                                        ucardconf=\"type=1\">jhgkjdgs</a>：？\n" +
                "                </div>\n" +
                "                <div class=\"WB_expand_media_box\" style=\"display: none;\" node-type=\"comment_media_disp\"></div>\n" +
                "                <div class=\"WB_func clearfix\">\n" +
                "                    <div class=\"WB_handle W_fr\">\n" +
                "                        <ul class=\"clearfix\">\n" +
                "                            <li class=\"hover\"><span class=\"line S_line1\"><a class=\"S_txt1\" href=\"javascript:void(0);\"\n" +
                "                                                                            onclick=\"javascript:window.open('https://service.account.weibo.com/reportspam?rid=IzaIRwZCS&type=2&url=&bottomnav=1&wvr=6', 'newwindow', 'height=700, width=550, toolbar =yes, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no');\">投诉</a></span>\n" +
                "                            </li>\n" +
                "                            <li><span class=\"line S_line1\"><a href=\"javascript:void(0);\" class=\"S_txt1 \"\n" +
                "                                                              onclick=\"return false\" action-type=\"reply\"\n" +
                "                                                              action-data=\"ouid=6580708111&cid=4483827017863390&amp;nick=jhgkjdgs&ispower=1&status_owner_user=7331391545&area=2&canUploadImage=1&canUploadFunnypic=1\"\n" +
                "                                                              title=''>回复</a></span> <span class=\"arrow\"><span\n" +
                "                                    class=\"W_arrow_bor W_arrow_bor_t\"><i class=\"S_bg2_br\"></i></span></span></li>\n" +
                "                            <li><span class=\"line S_line1\">                                                                                                                                                                                            <a\n" +
                "                                    href=\"javascript:void(0)\" class=\"S_txt1\" action-type=\"fl_like\"\n" +
                "                                    action-data=\"object_id=4483827017863390&object_type=comment&o_uid=6580708111&commentmid=4483766917444777\"\n" +
                "                                    title=\"赞\"><span node-type=\"like_status\" class=\"\"><em\n" +
                "                                    class=\"W_ficon ficon_praised S_txt2\">ñ</em><em>赞</em></span></a>                    </span>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                    <div class=\"WB_from S_txt2\">10秒前</div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>    <!-- root_comment_id为了判断一级评论还是二级评论，增加dom表示用于前端递归查找节点 -->\n" +
                "        <div comment_id=\"4483826917534919\" class=\"list_li S_line1 clearfix\">\n" +
                "            <div class=\"WB_face W_fl\"><a target=\"_blank\" href=\"//weibo.com/2036146923\"><img width=\"30\" height=\"30\"\n" +
                "                                                                                            alt=\"孫辰sunshine\"\n" +
                "                                                                                            src=\"https://tvax1.sinaimg.cn/crop.0.0.996.996.50/795d22ebly8gcs1xa2ey5j20ro0rotbm.jpg?KID=imgbed,tva&Expires=1584521934&ssig=Z9a1O1Fb94\"\n" +
                "                                                                                            usercard=\"id=2036146923\"\n" +
                "                                                                                            ucardconf=\"type=1\"></a>\n" +
                "            </div>\n" +
                "            <div class=\"list_con\" node-type=\"replywrap\">\n" +
                "                <div class=\"WB_text\"><a target=\"_blank\" href=\"//weibo.com/2036146923\" usercard=\"id=2036146923\"\n" +
                "                                        ucardconf=\"type=1\">孫辰sunshine</a><a target=\"_blank\"\n" +
                "                                                                            suda-data=\"key=pc_apply_entry&value=feed_icon\"\n" +
                "                                                                            href=\"//verified.weibo.com/verify\"><i\n" +
                "                        title=\"微博个人认证 \" class=\"W_icon icon_approve\"></i></a>：支持老铁\n" +
                "                </div>\n" +
                "                <div class=\"WB_expand_media_box\" style=\"display: none;\" node-type=\"comment_media_disp\"></div>\n" +
                "                <div class=\"WB_func clearfix\">\n" +
                "                    <div class=\"WB_handle W_fr\">\n" +
                "                        <ul class=\"clearfix\">\n" +
                "                            <li class=\"hover\"><span class=\"line S_line1\"><a class=\"S_txt1\" href=\"javascript:void(0);\"\n" +
                "                                                                            onclick=\"javascript:window.open('https://service.account.weibo.com/reportspam?rid=IzaIHvCaX&type=2&url=&bottomnav=1&wvr=6', 'newwindow', 'height=700, width=550, toolbar =yes, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no');\">投诉</a></span>\n" +
                "                            </li>\n" +
                "                            <li><span class=\"line S_line1\"><a href=\"javascript:void(0);\" class=\"S_txt1 \"\n" +
                "                                                              onclick=\"return false\" action-type=\"reply\"\n" +
                "                                                              action-data=\"ouid=2036146923&cid=4483826917534919&amp;nick=孫辰sunshine&ispower=1&status_owner_user=7331391545&area=2&canUploadImage=1&canUploadFunnypic=1\"\n" +
                "                                                              title=''>回复</a></span> <span class=\"arrow\"><span\n" +
                "                                    class=\"W_arrow_bor W_arrow_bor_t\"><i class=\"S_bg2_br\"></i></span></span></li>\n" +
                "                            <li><span class=\"line S_line1\">                                                                                                                                                                                            <a\n" +
                "                                    href=\"javascript:void(0)\" class=\"S_txt1\" action-type=\"fl_like\"\n" +
                "                                    action-data=\"object_id=4483826917534919&object_type=comment&o_uid=2036146923&commentmid=4483766917444777\"\n" +
                "                                    title=\"赞\"><span node-type=\"like_status\" class=\"\"><em\n" +
                "                                    class=\"W_ficon ficon_praised S_txt2\">ñ</em><em>赞</em></span></a>                    </span>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                    <div class=\"WB_from S_txt2\">30秒前</div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>    <!-- root_comment_id为了判断一级评论还是二级评论，增加dom表示用于前端递归查找节点 -->\n" +
                "        <div comment_id=\"4483823863376945\" class=\"list_li S_line1 clearfix\">\n" +
                "            <div class=\"WB_face W_fl\"><a target=\"_blank\" href=\"//weibo.com/1899156270\"><img width=\"30\" height=\"30\"\n" +
                "                                                                                            alt=\"娱毒\"\n" +
                "                                                                                            src=\"https://tvax3.sinaimg.cn/crop.0.0.664.664.50/7132d32ely8g4pv14cvlnj20ig0ig3yw.jpg?KID=imgbed,tva&Expires=1584521934&ssig=j1oJ4zguns\"\n" +
                "                                                                                            usercard=\"id=1899156270\"\n" +
                "                                                                                            ucardconf=\"type=1\"></a>\n" +
                "            </div>\n" +
                "            <div class=\"list_con\" node-type=\"replywrap\">\n" +
                "                <div class=\"WB_text\"><a target=\"_blank\" href=\"//weibo.com/1899156270\" usercard=\"id=1899156270\"\n" +
                "                                        ucardconf=\"type=1\">娱毒</a><a target=\"_blank\"\n" +
                "                                                                    suda-data=\"key=pc_apply_entry&value=feed_icon\"\n" +
                "                                                                    href=\"//verified.weibo.com/verify\"><i\n" +
                "                        title=\"微博个人认证 \" class=\"W_icon icon_approve_gold\"></i></a><a action-type=\"ignore_list\"\n" +
                "                                                                                    title=\"微博会员\" target=\"_blank\"\n" +
                "                                                                                    href=\"https://vip.weibo.com/personal?from=main\"><em\n" +
                "                        class=\"W_icon icon_member5\"></em></a>：你能收下吗？写着“易碎品小心轻放”的我的心。\n" +
                "                </div>\n" +
                "                <div class=\"WB_expand_media_box\" style=\"display: none;\" node-type=\"comment_media_disp\"></div>\n" +
                "                <div class=\"WB_func clearfix\">\n" +
                "                    <div class=\"WB_handle W_fr\">\n" +
                "                        <ul class=\"clearfix\">\n" +
                "                            <li class=\"hover\"><span class=\"line S_line1\"><a class=\"S_txt1\" href=\"javascript:void(0);\"\n" +
                "                                                                            onclick=\"javascript:window.open('https://service.account.weibo.com/reportspam?rid=IzaDMeauR&type=2&url=&bottomnav=1&wvr=6', 'newwindow', 'height=700, width=550, toolbar =yes, menubar=no, scrollbars=yes, resizable=yes, location=no, status=no');\">投诉</a></span>\n" +
                "                            </li>\n" +
                "                            <li><span class=\"line S_line1\"><a href=\"javascript:void(0);\" class=\"S_txt1 \"\n" +
                "                                                              onclick=\"return false\" action-type=\"reply\"\n" +
                "                                                              action-data=\"ouid=1899156270&cid=4483823863376945&amp;nick=娱毒&ispower=1&status_owner_user=7331391545&area=2&canUploadImage=1&canUploadFunnypic=1\"\n" +
                "                                                              title=''>回复</a></span> <span class=\"arrow\"><span\n" +
                "                                    class=\"W_arrow_bor W_arrow_bor_t\"><i class=\"S_bg2_br\"></i></span></span></li>\n" +
                "                            <li><span class=\"line S_line1\">                                                                                                                                                                                            <a\n" +
                "                                    href=\"javascript:void(0)\" class=\"S_txt1\" action-type=\"fl_like\"\n" +
                "                                    action-data=\"object_id=4483823863376945&object_type=comment&o_uid=1899156270&commentmid=4483766917444777\"\n" +
                "                                    title=\"赞\"><span node-type=\"like_status\" class=\"\"><em\n" +
                "                                    class=\"W_ficon ficon_praised S_txt2\">ñ</em><em>赞</em></span></a>                    </span>\n" +
                "                            </li>\n" +
                "                        </ul>\n" +
                "                    </div>\n" +
                "                    <div class=\"WB_from S_txt2\">13分钟前</div>\n" +
                "                </div>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "        <div class=\"W_tips tips_rederror clearfix\"><p class=\"icon\"><span class=\"W_icon icon_rederrorS\"></span></p>\n" +
                "            <p class=\"txt\">为了避免骚扰，微博智能反垃圾系统会过滤掉部分广告用户。</p></div>    <!--分页-->\n" +
                "        <div class=\"WB_cardpage S_line1\" action-type=\"commentFilter\">\n" +
                "            <div class=\"W_pages\"><a action-data=\"id=4483766917444777&page=1\" class=\"page S_txt1 S_bg2\"\n" +
                "                                    href=\"javascript:void(0);\" action-type=\"feed_list_page\">1</a> <a\n" +
                "                    action-data=\"id=4483766917444777&page=2\" class=\"page S_txt1\" href=\"javascript:void(0);\"\n" +
                "                    action-type=\"feed_list_page\">2</a> <a action-data=\"id=4483766917444777&page=3\" class=\"page S_txt1\"\n" +
                "                                                          href=\"javascript:void(0);\" action-type=\"feed_list_page\">3</a>\n" +
                "                <a action-data=\"id=4483766917444777&page=4\" class=\"page S_txt1\" href=\"javascript:void(0);\"\n" +
                "                   action-type=\"feed_list_page\">4</a> <a class=\"page next S_txt1 S_line1\" href=\"javascript:void(0);\">\n" +
                "                    <span action-type=\"feed_list_page\" action-data=\"id=4483766917444777&page=2\">下一页</span> </a></div>\n" +
                "        </div><!--/分页--></div>\n" +
                "</div>\n";
        //6.Jsoup解析html
        html = readCityFile();
        Document document = Jsoup.parse(html);
        // 获取评论根节点数据
        Element listUl = document.getElementsByClass("list_ul").first();
        // 获取每一条评论
        Elements listLi = listUl.getElementsByClass("list_li");
        // 记录父亲节点的评论id
        String pCid = "";
        handlerComment(listLi);
    }

    public static void handlerComment(Elements listLi){
        if(listLi != null){
            listLi.forEach(replyItemElement -> {
                String commentId = null;
                Element rootChild = null;

                try {
                    //  System.out.println(replyItemElement);
                    System.out.println("评论mid:XXXXXX" );
                    // 获取评论ID
                    commentId = replyItemElement.attr("comment_id");
                    System.out.println("comment_id:" + commentId);
                    // 用户信息处理
                    Element wbFace = replyItemElement.getElementsByClass("WB_face").first();
                    String firstWbFace = wbFace.getElementsByTag("a").first().toString();
                    String headUrl,uid,nickName;
                    String uidReg = "usercard\\=\"id=(.*?)\"";
                    String headUrlReg = "src\\=\"(.*?)\"";
                    String nikeReg = "alt\\=\"(.*?)\"";
                    headUrl = pattern(headUrlReg,firstWbFace);
                    uid = pattern(uidReg,firstWbFace);
                    nickName = pattern(nikeReg,firstWbFace);
                    System.out.println("评论人头像:" + headUrl);
                    System.out.println("评论人UID:" + uid);
                    System.out.println("评论人昵称:" + nickName);
                    // 用户信息处理 结束
                    // 获取评论内容
                    String wbText = replyItemElement.getElementsByClass("WB_text").text();
                    String textReg = nickName + "：(.*)";
                    String text = pattern(textReg,wbText);
                    System.out.println("评论内容:" + text);
                    Elements wbFrom = replyItemElement.getElementsByClass("WB_func").first().getElementsByClass("WB_from");
                    System.out.println("评论时间:" + wbFrom.text());
                    System.out.println("根节点评论:" + commentId);
                    System.out.println("==============================================" );
                    // 处理子评论
                    rootChild = replyItemElement.getElementsByClass("list_box_in").first().getElementsByClass("list_ul").first();
                    // 如果是子节点
                    if("child_comment".equals(rootChild.attr("node-type"))){
                        handlerChildComment(rootChild.getElementsByClass("list_li"),commentId);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static void handlerChildComment(Elements listLi,String rootCid){
        if(listLi != null){
            listLi.forEach(replyItemElement -> {
                //  System.out.println(replyItemElement);
                System.out.println("评论mid:XXXXXX" );
                // 获取评论ID
                System.out.println("comment_id:" + replyItemElement.attr("comment_id"));
                // 用户信息处理
                Element wbFace = replyItemElement.getElementsByClass("list_con").first()
                        .getElementsByClass("WB_text").first();
                Element firstWbFace = wbFace.getElementsByTag("a").first();
                String firstWbFaceStr = firstWbFace.toString();
                String uid,nickName;
                String uidReg = "usercard\\=\"id=(.*?)\"";
                uid = pattern(uidReg,firstWbFaceStr);
                nickName = firstWbFace.text();
                System.out.println("评论人头像:" + "");
                System.out.println("评论人UID:" + uid);
                System.out.println("评论人昵称:" + nickName);
                // 用户信息处理 结束
                // 获取评论内容
                String wbText = wbFace.text();;
                String textReg = nickName + "：(.*)";
                String text = pattern(textReg,wbText);
                System.out.println("评论内容:" + text);
                Elements wbFrom = replyItemElement.getElementsByClass("WB_func").first().getElementsByClass("WB_from");
                System.out.println("评论时间:" + wbFrom.text());
                System.out.println("根节点评论:" + rootCid);
                System.out.println("==============================================" );
            });
        }
    }


    public static String pattern(String reg,String html){
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(html);
        if(m.find()){
            return m.group(1);
        }
        return "";
    }

    /**
     * 读出城市列表文件
     */
    public static String readCityFile() {
        File file02 = new File("E:\\src\\my\\travel\\src\\main\\resources\\templates\\pages\\test.html");
        FileInputStream is = null;
        StringBuilder stringBuilder = null;
        try {
            if (file02.length() != 0) {
                is = new FileInputStream(file02);
                InputStreamReader streamReader = new InputStreamReader(is);
                BufferedReader reader = new BufferedReader(streamReader);
                String line;
                stringBuilder = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                reader.close();
                is.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return String.valueOf(stringBuilder);

    }
}
