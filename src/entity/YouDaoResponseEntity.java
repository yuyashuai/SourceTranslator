package entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class YouDaoResponseEntity {

    /**
     * web : [{"value":["自然力量","要素","份子"],"key":"elements"},{"value":["超导元素"],"key":"superconducting elements"},{"value":["基本元素","基本要素","基本构成部分"],"key":"Basic Elements"}]
     * query : elements
     * translation : ["元素"]
     * errorCode : 0
     * basic : {"us-phonetic":"ˈɛləmənts","phonetic":"'elimənts","uk-phonetic":"'elimənts","explains":["n. 基础；原理"]}
     * l : en2zh-CHS
     */
    public List<WebEntity> web;
    public String query;
    public List<String> translation;
    public String errorCode;
    public Basic basic;

    public class Basic{
        @SerializedName("us-phonetic")
        public String usPhonetic;

        @SerializedName("uk-phonetic")
        public String ukPhonetic;

        public List<String> explains;
    }
    public class WebEntity {
        /**
         * value : ["自然力量","要素","份子"]
         * key : elements
         */
        public List<String> value;
        public String key;
    }
}
