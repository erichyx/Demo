package cn.eric.myapplication.config;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by eric on 2019/3/24
 */
public class ConfigEntity {
    /**
     * company : Xiangyou
     * components : [{"hash":"28a785e1f8e918e444a6f05f2b9e12a0","name":"Servers.mejf"},{"hash":"b9d045eac735dbee734a78dd913a75d7","name":"Games.mejf"}]
     * file_version : 2019.0322.1400.39329
     * filter_list_new : ["com.tencent.android.qqdownloader","com.baidu.appsearch","com.xiaomi.shop"]
     * market_list : ["com.tencent.android.qqdownloader","com.dragon.android.pandaspace","com.lenovo.leos.appstore","com.wandoujia.phoenix2","com.xiaomi.market","com.meizu.mstore","com.baidu.appsearch","com.xiaomi.shop","com.qihoo.appstore","com.bbk.appstore","com.oppo.market","com.coolapk.market","cn.goapk.market","com.huawei.appmarket"]
     * open_route_info : true
     * options : {"end_replace":"!##","first_comments_count":30,"first_speed_effect_count":1,"gdd_markets":["GooglePlay","anzhi"],"gdd_version":"1.5.315","head_replace":"##!","number_opt":{"auto_report_traffic":10,"reconnect_http_timeout":500},"second_speed_effect_time":25,"share_description":"海豚手游加速器为国内外手游免费加速，智能云加速技术能够有效的解决游戏丢包、掉线、延时高和登录不上等问题。","share_thumb_url":"http://www.htjsq.mobi/assets/app/images/share_ios.png","share_title":"海豚手游加速器-加速全球手游","share_url":"http://www.htjsq.mobi/"}
     * product : DolphinQ_Mobile
     * product_name : 海豚手游加速器
     * qos_option : false
     * qos_test_open : false
     * role_id : DolphinQ_Mobile_Android
     * top_search : 天命之子,Steam,PUBG,传说对决
     * version : 1.0.1227
     * web : {"download_source":{"game_download_proxy":{"CMCC":{"proxy_download":"117.28.244.26","proxy_download_port":443},"CTCC":{"proxy_download":"117.28.244.12","proxy_download_port":443},"CUCC":{"proxy_download":"117.28.244.130","proxy_download_port":443}},"proxy_download":"117.28.244.12","proxy_download_port":443},"interface":{"interface_acc_report":"https://www.htjsq.mobi/app/report/acc_report","interface_encrypt_key_request":"http://api.htjsq.com/cgi/android/request","interface_encrypt_upload_file_request":"http://static.htjsq.com/cgi/resource/request","interface_error_log_upload":"https://www.htjsq.mobi/app/errorlog/handin","interface_game_wish_report":"https://www.htjsq.mobi/app/feedback/game","interface_ip_info":"https://www.htjsq.mobi/app/index/ipinfo.json","interface_log_upload":"https://www.htjsq.mobi/app/feedback/suggestion","interface_route_upload":"https://www.htjsq.mobi/app/report/route_info","interface_start_info":"https://www.htjsq.mobi/app/api/start","interface_suggest_report":"https://www.htjsq.mobi/app/feedback/feedback_log","web_notice":"https://www.htjsq.mobi/app/article.html?os=android","web_speed_effect":"https://www.htjsq.mobi/app/suggest/speed_feedback?platform=android","web_suggest":"https://www.htjsq.mobi/app/suggest/suggest_list/?os=android"},"proxy_server":"61.153.107.74","support":{"notice_url":"https://www.htjsq.mobi/app/article.html?os=android"},"update":{"configs_url":"https://www.htjsq.mobi/download/android/configs/","game_resource_url":"https://www.htjsq.mobi/download/gameres/"},"url_head":{"simple_domain_name":"www.htjsq.mobi","simple_pre_domain_name":"apptest.kuaile-u.com","simple_test_domain_name":"121.41.39.33"}}
     */

    @SerializedName("company")
    private String company;
    @SerializedName("file_version")
    private String fileVersion;
    @SerializedName("open_route_info")
    private boolean openRouteInfo;
    @SerializedName("options")
    private OptionsBean options;
    @SerializedName("product")
    private String product;
    @SerializedName("product_name")
    private String productName;
    @SerializedName("qos_option")
    private boolean qosOption;
    @SerializedName("qos_test_open")
    private boolean qosTestOpen;
    @SerializedName("role_id")
    private String roleId;
    @SerializedName("top_search")
    private String topSearch;
    @SerializedName("version")
    private String version;
    @SerializedName("web")
    private WebBean web;
    @SerializedName("components")
    private List<ComponentsBean> components;
    @SerializedName("filter_list_new")
    private List<String> filterListNew;
    @SerializedName("market_list")
    private List<String> marketList;

    public List<ComponentsBean> getComponents() {
        return components;
    }

    public static class OptionsBean {
        /**
         * end_replace : !##
         * first_comments_count : 30
         * first_speed_effect_count : 1
         * gdd_markets : ["GooglePlay","anzhi"]
         * gdd_version : 1.5.315
         * head_replace : ##!
         * number_opt : {"auto_report_traffic":10,"reconnect_http_timeout":500}
         * second_speed_effect_time : 25
         * share_description : 海豚手游加速器为国内外手游免费加速，智能云加速技术能够有效的解决游戏丢包、掉线、延时高和登录不上等问题。
         * share_thumb_url : http://www.htjsq.mobi/assets/app/images/share_ios.png
         * share_title : 海豚手游加速器-加速全球手游
         * share_url : http://www.htjsq.mobi/
         */

        @SerializedName("end_replace")
        private String endReplace;
        @SerializedName("first_comments_count")
        private int firstCommentsCount;
        @SerializedName("first_speed_effect_count")
        private int firstSpeedEffectCount;
        @SerializedName("gdd_version")
        private String gddVersion;
        @SerializedName("head_replace")
        private String headReplace;
        @SerializedName("number_opt")
        private NumberOptBean numberOpt;
        @SerializedName("second_speed_effect_time")
        private int secondSpeedEffectTime;
        @SerializedName("share_description")
        private String shareDescription;
        @SerializedName("share_thumb_url")
        private String shareThumbUrl;
        @SerializedName("share_title")
        private String shareTitle;
        @SerializedName("share_url")
        private String shareUrl;
        @SerializedName("gdd_markets")
        private List<String> gddMarkets;

        public static class NumberOptBean {
            /**
             * auto_report_traffic : 10
             * reconnect_http_timeout : 500
             */
            @SerializedName("auto_report_traffic")
            private int autoReportTraffic;
            @SerializedName("reconnect_http_timeout")
            private int reconnectHttpTimeout;
        }
    }

    public static class WebBean {
        /**
         * download_source : {"game_download_proxy":{"CMCC":{"proxy_download":"117.28.244.26","proxy_download_port":443},"CTCC":{"proxy_download":"117.28.244.12","proxy_download_port":443},"CUCC":{"proxy_download":"117.28.244.130","proxy_download_port":443}},"proxy_download":"117.28.244.12","proxy_download_port":443}
         * interface : {"interface_acc_report":"https://www.htjsq.mobi/app/report/acc_report","interface_encrypt_key_request":"http://api.htjsq.com/cgi/android/request","interface_encrypt_upload_file_request":"http://static.htjsq.com/cgi/resource/request","interface_error_log_upload":"https://www.htjsq.mobi/app/errorlog/handin","interface_game_wish_report":"https://www.htjsq.mobi/app/feedback/game","interface_ip_info":"https://www.htjsq.mobi/app/index/ipinfo.json","interface_log_upload":"https://www.htjsq.mobi/app/feedback/suggestion","interface_route_upload":"https://www.htjsq.mobi/app/report/route_info","interface_start_info":"https://www.htjsq.mobi/app/api/start","interface_suggest_report":"https://www.htjsq.mobi/app/feedback/feedback_log","web_notice":"https://www.htjsq.mobi/app/article.html?os=android","web_speed_effect":"https://www.htjsq.mobi/app/suggest/speed_feedback?platform=android","web_suggest":"https://www.htjsq.mobi/app/suggest/suggest_list/?os=android"}
         * proxy_server : 61.153.107.74
         * support : {"notice_url":"https://www.htjsq.mobi/app/article.html?os=android"}
         * update : {"configs_url":"https://www.htjsq.mobi/download/android/configs/","game_resource_url":"https://www.htjsq.mobi/download/gameres/"}
         * url_head : {"simple_domain_name":"www.htjsq.mobi","simple_pre_domain_name":"apptest.kuaile-u.com","simple_test_domain_name":"121.41.39.33"}
         */

        @SerializedName("download_source")
        private DownloadSourceBean download_source;
        @SerializedName("interface")
        private InterfaceBean interfaceX;
        @SerializedName("proxyServer")
        private String proxy_server;
        @SerializedName("support")
        private SupportBean support;
        @SerializedName("update")
        private UpdateBean update;
        @SerializedName("url_head")
        private UrlHeadBean urlHead;

        public static class DownloadSourceBean {
            /**
             * game_download_proxy : {"CMCC":{"proxy_download":"117.28.244.26","proxy_download_port":443},"CTCC":{"proxy_download":"117.28.244.12","proxy_download_port":443},"CUCC":{"proxy_download":"117.28.244.130","proxy_download_port":443}}
             * proxy_download : 117.28.244.12
             * proxy_download_port : 443
             */
            @SerializedName("game_download_proxy")
            private GameDownloadProxyBean gameDownloadProxy;
            @SerializedName("proxy_download")
            private String proxyDownload;
            @SerializedName("proxy_download_port")
            private int proxyDownloadPort;

            public static class GameDownloadProxyBean {
                /**
                 * CMCC : {"proxy_download":"117.28.244.26","proxy_download_port":443}
                 * CTCC : {"proxy_download":"117.28.244.12","proxy_download_port":443}
                 * CUCC : {"proxy_download":"117.28.244.130","proxy_download_port":443}
                 */
                @SerializedName("CMCC")
                private CMCCBean CMCC;
                @SerializedName("CTCC")
                private CTCCBean CTCC;
                @SerializedName("CUCC")
                private CUCCBean CUCC;

                public static class CMCCBean {
                    /**
                     * proxy_download : 117.28.244.26
                     * proxy_download_port : 443
                     */
                    @SerializedName("proxy_download")
                    private String proxyDownload;
                    @SerializedName("proxy_download_port")
                    private int proxyDownloadPort;
                }

                public static class CTCCBean {
                    /**
                     * proxy_download : 117.28.244.12
                     * proxy_download_port : 443
                     */
                    @SerializedName("proxy_download")
                    private String proxyDownload;
                    @SerializedName("proxy_download_port")
                    private int proxyDownloadPort;
                }

                public static class CUCCBean {
                    /**
                     * proxy_download : 117.28.244.130
                     * proxy_download_port : 443
                     */
                    @SerializedName("proxy_download")
                    private String proxyDownload;
                    @SerializedName("proxy_download_port")
                    private int proxyDownloadPort;
                }
            }
        }

        public static class InterfaceBean {
            /**
             * interface_acc_report : https://www.htjsq.mobi/app/report/acc_report
             * interface_encrypt_key_request : http://api.htjsq.com/cgi/android/request
             * interface_encrypt_upload_file_request : http://static.htjsq.com/cgi/resource/request
             * interface_error_log_upload : https://www.htjsq.mobi/app/errorlog/handin
             * interface_game_wish_report : https://www.htjsq.mobi/app/feedback/game
             * interface_ip_info : https://www.htjsq.mobi/app/index/ipinfo.json
             * interface_log_upload : https://www.htjsq.mobi/app/feedback/suggestion
             * interface_route_upload : https://www.htjsq.mobi/app/report/route_info
             * interface_start_info : https://www.htjsq.mobi/app/api/start
             * interface_suggest_report : https://www.htjsq.mobi/app/feedback/feedback_log
             * web_notice : https://www.htjsq.mobi/app/article.html?os=android
             * web_speed_effect : https://www.htjsq.mobi/app/suggest/speed_feedback?platform=android
             * web_suggest : https://www.htjsq.mobi/app/suggest/suggest_list/?os=android
             */

            @SerializedName("interface_acc_report")
            private String interfaceAccReport;
            @SerializedName("interface_encrypt_key_request")
            private String interfaceEncryptKeyRequest;
            @SerializedName("interface_encrypt_upload_file_request")
            private String interfaceEncryptUploadFileRequest;
            @SerializedName("interface_error_log_upload")
            private String interfaceErrorLogUpload;
            @SerializedName("interface_game_wish_report")
            private String interfaceGameWishReport;
            @SerializedName("interface_ip_info")
            private String interfaceIpInfo;
            @SerializedName("interface_log_upload")
            private String interfaceLogUpload;
            @SerializedName("interface_route_upload")
            private String interfaceRouteUpload;
            @SerializedName("interface_start_info")
            private String interfaceStartInfo;
            @SerializedName("interface_suggest_report")
            private String interfaceSuggestReport;
            @SerializedName("web_notice")
            private String webNotice;
            @SerializedName("web_speed_effect")
            private String webSpeedEffect;
            @SerializedName("web_suggest")
            private String webSuggest;
        }

        public static class SupportBean {
            /**
             * notice_url : https://www.htjsq.mobi/app/article.html?os=android
             */
            @SerializedName("notice_url")
            private String noticeUrl;
        }

        public static class UpdateBean {
            /**
             * configs_url : https://www.htjsq.mobi/download/android/configs/
             * game_resource_url : https://www.htjsq.mobi/download/gameres/
             */
            @SerializedName("configs_url")
            private String configsUrl;
            @SerializedName("game_resource_url")
            private String gameResourceUrl;
        }

        public static class UrlHeadBean {
            /**
             * simple_domain_name : www.htjsq.mobi
             * simple_pre_domain_name : apptest.kuaile-u.com
             * simple_test_domain_name : 121.41.39.33
             */
            @SerializedName("simple_domain_name")
            private String simpleDomainName;
            @SerializedName("simple_pre_domain_name")
            private String simplePreDomainName;
            @SerializedName("simple_test_domain_name")
            private String simpleTestDomainName;
        }
    }

    public static class ComponentsBean {
        /**
         * hash : 28a785e1f8e918e444a6f05f2b9e12a0
         * name : Servers.mejf
         */
        @SerializedName("hash")
        private String hash;
        @SerializedName("name")
        private String name;

        public String getHash() {
            return hash;
        }

        public String getName() {
            return name;
        }
    }
}
