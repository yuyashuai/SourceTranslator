package entity;

import java.util.List;

public class BaiduResponseEntity {
    /**
     * trans_result : [{"dst":"想象上的","src":"supposed"}]
     * from : en
     * to : zh
     */
    public List<resultEntity> trans_result;
    public String from;
    public String to;

    public class resultEntity {
        /**
         * dst : 想象上的
         * src : supposed
         */
        public String dst;
        public String src;
    }
}
