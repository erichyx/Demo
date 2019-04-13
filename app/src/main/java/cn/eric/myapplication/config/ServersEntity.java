package cn.eric.myapplication.config;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eric on 2019/4/13
 */
public class ServersEntity {

    /**
     * file_version : 2019.0410.0937.37759
     * servers : [{"acc_line_id":2,"acc_line_type":0,"child_nodes":[{"first_node_area":13,"ip":"150.242.98.68","name":"华东多线5-1","payload_high_percent":50,"port":443,"rtt2_time_delay":25}],"group_name":"华东多线5"}]
     * version : 1.2.628
     */
    @SerializedName("file_version")
    private String fileVersion;
    @SerializedName("version")
    private String version;
    @SerializedName("servers")
    private List<ServersBean> servers;

    public static class ServersBean {
        /**
         * acc_line_id : 2
         * acc_line_type : 0
         * child_nodes : [{"first_node_area":13,"ip":"150.242.98.68","name":"华东多线5-1","payload_high_percent":50,"port":443,"rtt2_time_delay":25}]
         * group_name : 华东多线5
         */
        @SerializedName("acc_line_id")
        private int accLineId;
        @SerializedName("acc_line_type")
        private int accLineType;
        @SerializedName("group_name")
        private String groupName;
        @SerializedName("child_nodes")
        private List<ChildNodesBean> childNodes;


        public static class ChildNodesBean {
            /**
             * first_node_area : 13
             * ip : 150.242.98.68
             * name : 华东多线5-1
             * payload_high_percent : 50
             * port : 443
             * rtt2_time_delay : 25
             */
            @SerializedName("first_node_area")
            private int firstNodeArea;
            @SerializedName("ip")
            private String ip;
            @SerializedName("name")
            private String name;
            @SerializedName("payload_high_percent")
            private int payloadHighPercent;
            @SerializedName("port")
            private int port;
            @SerializedName("rtt2_time_delay")
            private int rtt2TimeDelay;
        }
    }
}
