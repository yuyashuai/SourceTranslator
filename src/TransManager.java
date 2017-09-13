import com.google.gson.Gson;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.BalloonBuilder;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.JBColor;
import com.intellij.ui.awt.RelativePoint;
import entity.BaiduResponseEntity;
import entity.BingResponseEntity;
import entity.YouDaoResponseEntity;
import okhttp3.*;
import org.apache.http.util.TextUtils;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TransManager {

    private static TransManager instance;

    private TransManager() {

    }

    public synchronized static TransManager getInstance() {
        if (instance == null) {
            instance = new TransManager();
        }
        return instance;
    }

    private Balloon waitBalloon;

    /**
     * 翻译,根据类型判断
     *
     * @param text   src
     * @param editor
     */
    public void translate(String text, Editor editor) {
        if (TextUtils.isEmpty(text)) return;
        text = text.replaceAll("[^a-z^A-Z^0-9^ ^_^(^)]", "");
        if (TextUtils.isBlank(text) || text.length() < 2) return;
        if (text.contains(" ")) {
            translateParagraph(text, editor);
            return;
        }
        if (text.contains("_")) {
            translateParagraph(text.replaceAll("_", " "), editor);
            return;
        }
        if (!containsUpperCase(text)) {
            queryWord(text, editor);
            return;
        }
        //驼峰
        text = parseWord(text);
        System.out.println("parse后： " + text);
        if (text.contains(" ")) {
            translateParagraph(text, editor);
            return;
        }
        queryWord(text, editor);

    }
    /**
     * 在大写字母前自动加入空格
     *
     * @param text
     * @return
     */
    private String parseWord(String text) {
        StringBuilder builder = new StringBuilder(text);
        for (int i = text.length() - 1; i > 0; i--) {
            if (Character.isUpperCase(text.charAt(i))) {
                builder.insert(i, " ");
            }
        }
        return builder.toString();
    }

    /**
     * 是否包含大写字母
     *
     * @param text
     * @return
     */
    private boolean containsUpperCase(String text) {
        char charSet[] = text.toCharArray();
        charSet[0] = Character.toLowerCase(charSet[0]);
        for (char c : charSet) {
            if (Character.isUpperCase(c))
                return true;
        }
        return false;
    }

    /**
     * 查询单词
     *
     * @param word
     * @param editor
     */
    private void queryWord(String word, Editor editor) {
        transByYouDao(word, editor);
    }

    /**
     * 有道翻译接口
     * @param words
     * @param editor
     */
    private void transByYouDao(String words, Editor editor) {
        final long requestTime = System.currentTimeMillis();
        System.out.println("queryWord：" + words);
        String radom = "196890";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        FormBody body = new FormBody.Builder()
                .add("q", words)
                .add("from", "en")
                .add("to", "zh")
                .add("appKey", Config.YD_APP_ID)
                .add("salt", radom)
                .add("sign", CommonUtils.getYouDaoMD5(words, radom))
                .build();
        Request request = new Request.Builder().url(Config.YD_URL)
                .post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("输出错误： " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String sp = response.body().string();
                System.out.println("有道请求所需时间: " + (System.currentTimeMillis() - requestTime) + " \n" + sp);
                Gson gson = new Gson();
                YouDaoResponseEntity entity = gson.fromJson(sp, YouDaoResponseEntity.class);
                YouDaoResponseEntity.Basic basic = entity.basic;
                String queryWord = "";
                if (entity.web.size() > 0) {
                    queryWord = entity.web.get(0).key;
                }
                StringBuilder expBuilder = new StringBuilder();
                for (int i = 0; i < entity.basic.explains.size(); i++) {
                    String exp = entity.basic.explains.get(i);
                    if (i == entity.basic.explains.size() - 1)
                        expBuilder.append("<p style=\"line-height: 7px;margin-top: 5px;margin-bottom:6px\">" + exp + "</p>");

                    else if(i==0)
                        expBuilder.append("<p style=\"line-height: 7px;margin-top: 8px\">" + exp + "</p>");
                    else
                        expBuilder.append("<p style=\"line-height: 7px;margin-top: 5px\">" + exp + "</p>");
                }
                String html =
                        "<html lang=\"en\">" +
                                "<body>" +
                                "<p style=\"color:rgb(193,73,255);font-size: large; margin:0px\">" + queryWord.toLowerCase() + "</p>" +
                                "<p style=\"color:gray;line-height: 7px;margin-top:3px\">" + "美[" + entity.basic.usPhonetic + "]</p>" +
                                expBuilder.toString() +
                                "</body>" +
                                "</html>";
                showMessage(html, editor);
            }
        });
    }

    /**
     * bing翻译，主要单词
     *
     * @param word
     * @param editor
     */
    private void transByBing(String word, Editor editor) {
        final long requestTime = System.currentTimeMillis();
        OkHttpClient okHttpClient = new OkHttpClient.Builder().build();
        Request request = new Request.Builder().url(CommonUtils.getBingUrl(word))
                .build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("BING FAILED: " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("请求所需时间: " + (System.currentTimeMillis() - requestTime));
                String resp = response.body().string();
                System.out.println("resp: " + resp);
                Gson gson = new Gson();
                BingResponseEntity entity = gson.fromJson(resp, BingResponseEntity.class);
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("<font size=\"34\">" + entity.word + "</font> " + "\n  [" + entity.pronunciation.AmE + "]\n\n");
                for (BingResponseEntity.DefsEntity defsEntity : entity.defs) {
                    stringBuilder.append(defsEntity.pos + " " + defsEntity.def + "\n");
                }
                showMessage(stringBuilder.toString(), editor);
            }
        });
    }

    /**
     * 句子的翻译
     *
     * @param words
     * @param editor
     */
    private void translateParagraph(String words, Editor editor) {
        transByBaidu(words, editor);

    }

    /**
     * 百度翻译，主要翻译句子
     *
     * @param words
     * @param editor
     */
    private void transByBaidu(String words, Editor editor) {
        final long requestTime = System.currentTimeMillis();
        System.out.println("translateParagraph：" + words);
        System.out.println("queryWord：" + words);
        String random = "156890";
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .build();
        FormBody body = new FormBody.Builder()
                .add("q", words)
                .add("from", "en")
                .add("to", "zh")
                .add("appid", Config.BD_APP_ID)
                .add("salt", random)
                .add("sign", CommonUtils.getBaiduMD5(words, random))
                .build();
        Request request = new Request.Builder().url(Config.BD_URL)
                .post(body).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("输出错误： " + e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("百度请求所需时间: " + (System.currentTimeMillis() - requestTime));
                String sp = response.body().string();
                Gson gson = new Gson();
                BaiduResponseEntity baiduResponse = gson.fromJson(sp, BaiduResponseEntity.class);
                System.out.println("返回：" + baiduResponse.trans_result.get(0).dst);
                showMessage(baiduResponse.trans_result.get(0).dst,editor);
            }
        });
    }

    private Balloon balloon;
    private Balloon lastBalloon;

    private void showMessage(String message, Editor editor) {
        ApplicationManager.getApplication().invokeLater(() -> {
            if(waitBalloon!=null)
            {
                waitBalloon.hide();
            }
            JBPopupFactory jbPopupFactory = JBPopupFactory.getInstance();
            BalloonBuilder balloonBuilder = jbPopupFactory.createHtmlTextBalloonBuilder(message, null, new JBColor(
                    new Color(222, 222, 222)
                    , new Color(77, 77, 77)), null);
            balloonBuilder.setFadeoutTime(20000);
            balloon = balloonBuilder.createBalloon();

            if (lastBalloon != null) {
                lastBalloon.hide();
            }
            lastBalloon = balloon;
            balloon.setAnimationEnabled(false);
            balloon.show(jbPopupFactory.guessBestPopupLocation(editor), Balloon.Position.below);
        });


    }
}
