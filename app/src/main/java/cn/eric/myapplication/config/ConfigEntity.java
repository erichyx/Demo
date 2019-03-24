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

    private String company;
    private String file_version;
    private boolean open_route_info;
    private OptionsBean options;
    private String product;
    private String product_name;
    private boolean qos_option;
    private boolean qos_test_open;
    private String role_id;
    private String top_search;
    private String version;
    private WebBean web;
    private List<ComponentsBean> components;
    private List<String> filter_list_new;
    private List<String> market_list;

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFile_version() {
        return file_version;
    }

    public void setFile_version(String file_version) {
        this.file_version = file_version;
    }

    public boolean isOpen_route_info() {
        return open_route_info;
    }

    public void setOpen_route_info(boolean open_route_info) {
        this.open_route_info = open_route_info;
    }

    public OptionsBean getOptions() {
        return options;
    }

    public void setOptions(OptionsBean options) {
        this.options = options;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public boolean isQos_option() {
        return qos_option;
    }

    public void setQos_option(boolean qos_option) {
        this.qos_option = qos_option;
    }

    public boolean isQos_test_open() {
        return qos_test_open;
    }

    public void setQos_test_open(boolean qos_test_open) {
        this.qos_test_open = qos_test_open;
    }

    public String getRole_id() {
        return role_id;
    }

    public void setRole_id(String role_id) {
        this.role_id = role_id;
    }

    public String getTop_search() {
        return top_search;
    }

    public void setTop_search(String top_search) {
        this.top_search = top_search;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public WebBean getWeb() {
        return web;
    }

    public void setWeb(WebBean web) {
        this.web = web;
    }

    public List<ComponentsBean> getComponents() {
        return components;
    }

    public void setComponents(List<ComponentsBean> components) {
        this.components = components;
    }

    public List<String> getFilter_list_new() {
        return filter_list_new;
    }

    public void setFilter_list_new(List<String> filter_list_new) {
        this.filter_list_new = filter_list_new;
    }

    public List<String> getMarket_list() {
        return market_list;
    }

    public void setMarket_list(List<String> market_list) {
        this.market_list = market_list;
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

        private String end_replace;
        private int first_comments_count;
        private int first_speed_effect_count;
        private String gdd_version;
        private String head_replace;
        private NumberOptBean number_opt;
        private int second_speed_effect_time;
        private String share_description;
        private String share_thumb_url;
        private String share_title;
        private String share_url;
        private List<String> gdd_markets;

        public String getEnd_replace() {
            return end_replace;
        }

        public void setEnd_replace(String end_replace) {
            this.end_replace = end_replace;
        }

        public int getFirst_comments_count() {
            return first_comments_count;
        }

        public void setFirst_comments_count(int first_comments_count) {
            this.first_comments_count = first_comments_count;
        }

        public int getFirst_speed_effect_count() {
            return first_speed_effect_count;
        }

        public void setFirst_speed_effect_count(int first_speed_effect_count) {
            this.first_speed_effect_count = first_speed_effect_count;
        }

        public String getGdd_version() {
            return gdd_version;
        }

        public void setGdd_version(String gdd_version) {
            this.gdd_version = gdd_version;
        }

        public String getHead_replace() {
            return head_replace;
        }

        public void setHead_replace(String head_replace) {
            this.head_replace = head_replace;
        }

        public NumberOptBean getNumber_opt() {
            return number_opt;
        }

        public void setNumber_opt(NumberOptBean number_opt) {
            this.number_opt = number_opt;
        }

        public int getSecond_speed_effect_time() {
            return second_speed_effect_time;
        }

        public void setSecond_speed_effect_time(int second_speed_effect_time) {
            this.second_speed_effect_time = second_speed_effect_time;
        }

        public String getShare_description() {
            return share_description;
        }

        public void setShare_description(String share_description) {
            this.share_description = share_description;
        }

        public String getShare_thumb_url() {
            return share_thumb_url;
        }

        public void setShare_thumb_url(String share_thumb_url) {
            this.share_thumb_url = share_thumb_url;
        }

        public String getShare_title() {
            return share_title;
        }

        public void setShare_title(String share_title) {
            this.share_title = share_title;
        }

        public String getShare_url() {
            return share_url;
        }

        public void setShare_url(String share_url) {
            this.share_url = share_url;
        }

        public List<String> getGdd_markets() {
            return gdd_markets;
        }

        public void setGdd_markets(List<String> gdd_markets) {
            this.gdd_markets = gdd_markets;
        }

        public static class NumberOptBean {
            /**
             * auto_report_traffic : 10
             * reconnect_http_timeout : 500
             */

            private int auto_report_traffic;
            private int reconnect_http_timeout;

            public int getAuto_report_traffic() {
                return auto_report_traffic;
            }

            public void setAuto_report_traffic(int auto_report_traffic) {
                this.auto_report_traffic = auto_report_traffic;
            }

            public int getReconnect_http_timeout() {
                return reconnect_http_timeout;
            }

            public void setReconnect_http_timeout(int reconnect_http_timeout) {
                this.reconnect_http_timeout = reconnect_http_timeout;
            }
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

        private DownloadSourceBean download_source;
        @SerializedName("interface")
        private InterfaceBean interfaceX;
        private String proxy_server;
        private SupportBean support;
        private UpdateBean update;
        private UrlHeadBean url_head;

        public DownloadSourceBean getDownload_source() {
            return download_source;
        }

        public void setDownload_source(DownloadSourceBean download_source) {
            this.download_source = download_source;
        }

        public InterfaceBean getInterfaceX() {
            return interfaceX;
        }

        public void setInterfaceX(InterfaceBean interfaceX) {
            this.interfaceX = interfaceX;
        }

        public String getProxy_server() {
            return proxy_server;
        }

        public void setProxy_server(String proxy_server) {
            this.proxy_server = proxy_server;
        }

        public SupportBean getSupport() {
            return support;
        }

        public void setSupport(SupportBean support) {
            this.support = support;
        }

        public UpdateBean getUpdate() {
            return update;
        }

        public void setUpdate(UpdateBean update) {
            this.update = update;
        }

        public UrlHeadBean getUrl_head() {
            return url_head;
        }

        public void setUrl_head(UrlHeadBean url_head) {
            this.url_head = url_head;
        }

        public static class DownloadSourceBean {
            /**
             * game_download_proxy : {"CMCC":{"proxy_download":"117.28.244.26","proxy_download_port":443},"CTCC":{"proxy_download":"117.28.244.12","proxy_download_port":443},"CUCC":{"proxy_download":"117.28.244.130","proxy_download_port":443}}
             * proxy_download : 117.28.244.12
             * proxy_download_port : 443
             */

            private GameDownloadProxyBean game_download_proxy;
            private String proxy_download;
            private int proxy_download_port;

            public GameDownloadProxyBean getGame_download_proxy() {
                return game_download_proxy;
            }

            public void setGame_download_proxy(GameDownloadProxyBean game_download_proxy) {
                this.game_download_proxy = game_download_proxy;
            }

            public String getProxy_download() {
                return proxy_download;
            }

            public void setProxy_download(String proxy_download) {
                this.proxy_download = proxy_download;
            }

            public int getProxy_download_port() {
                return proxy_download_port;
            }

            public void setProxy_download_port(int proxy_download_port) {
                this.proxy_download_port = proxy_download_port;
            }

            public static class GameDownloadProxyBean {
                /**
                 * CMCC : {"proxy_download":"117.28.244.26","proxy_download_port":443}
                 * CTCC : {"proxy_download":"117.28.244.12","proxy_download_port":443}
                 * CUCC : {"proxy_download":"117.28.244.130","proxy_download_port":443}
                 */

                private CMCCBean CMCC;
                private CTCCBean CTCC;
                private CUCCBean CUCC;

                public CMCCBean getCMCC() {
                    return CMCC;
                }

                public void setCMCC(CMCCBean CMCC) {
                    this.CMCC = CMCC;
                }

                public CTCCBean getCTCC() {
                    return CTCC;
                }

                public void setCTCC(CTCCBean CTCC) {
                    this.CTCC = CTCC;
                }

                public CUCCBean getCUCC() {
                    return CUCC;
                }

                public void setCUCC(CUCCBean CUCC) {
                    this.CUCC = CUCC;
                }

                public static class CMCCBean {
                    /**
                     * proxy_download : 117.28.244.26
                     * proxy_download_port : 443
                     */

                    private String proxy_download;
                    private int proxy_download_port;

                    public String getProxy_download() {
                        return proxy_download;
                    }

                    public void setProxy_download(String proxy_download) {
                        this.proxy_download = proxy_download;
                    }

                    public int getProxy_download_port() {
                        return proxy_download_port;
                    }

                    public void setProxy_download_port(int proxy_download_port) {
                        this.proxy_download_port = proxy_download_port;
                    }
                }

                public static class CTCCBean {
                    /**
                     * proxy_download : 117.28.244.12
                     * proxy_download_port : 443
                     */

                    private String proxy_download;
                    private int proxy_download_port;

                    public String getProxy_download() {
                        return proxy_download;
                    }

                    public void setProxy_download(String proxy_download) {
                        this.proxy_download = proxy_download;
                    }

                    public int getProxy_download_port() {
                        return proxy_download_port;
                    }

                    public void setProxy_download_port(int proxy_download_port) {
                        this.proxy_download_port = proxy_download_port;
                    }
                }

                public static class CUCCBean {
                    /**
                     * proxy_download : 117.28.244.130
                     * proxy_download_port : 443
                     */

                    private String proxy_download;
                    private int proxy_download_port;

                    public String getProxy_download() {
                        return proxy_download;
                    }

                    public void setProxy_download(String proxy_download) {
                        this.proxy_download = proxy_download;
                    }

                    public int getProxy_download_port() {
                        return proxy_download_port;
                    }

                    public void setProxy_download_port(int proxy_download_port) {
                        this.proxy_download_port = proxy_download_port;
                    }
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

            private String interface_acc_report;
            private String interface_encrypt_key_request;
            private String interface_encrypt_upload_file_request;
            private String interface_error_log_upload;
            private String interface_game_wish_report;
            private String interface_ip_info;
            private String interface_log_upload;
            private String interface_route_upload;
            private String interface_start_info;
            private String interface_suggest_report;
            private String web_notice;
            private String web_speed_effect;
            private String web_suggest;

            public String getInterface_acc_report() {
                return interface_acc_report;
            }

            public void setInterface_acc_report(String interface_acc_report) {
                this.interface_acc_report = interface_acc_report;
            }

            public String getInterface_encrypt_key_request() {
                return interface_encrypt_key_request;
            }

            public void setInterface_encrypt_key_request(String interface_encrypt_key_request) {
                this.interface_encrypt_key_request = interface_encrypt_key_request;
            }

            public String getInterface_encrypt_upload_file_request() {
                return interface_encrypt_upload_file_request;
            }

            public void setInterface_encrypt_upload_file_request(String interface_encrypt_upload_file_request) {
                this.interface_encrypt_upload_file_request = interface_encrypt_upload_file_request;
            }

            public String getInterface_error_log_upload() {
                return interface_error_log_upload;
            }

            public void setInterface_error_log_upload(String interface_error_log_upload) {
                this.interface_error_log_upload = interface_error_log_upload;
            }

            public String getInterface_game_wish_report() {
                return interface_game_wish_report;
            }

            public void setInterface_game_wish_report(String interface_game_wish_report) {
                this.interface_game_wish_report = interface_game_wish_report;
            }

            public String getInterface_ip_info() {
                return interface_ip_info;
            }

            public void setInterface_ip_info(String interface_ip_info) {
                this.interface_ip_info = interface_ip_info;
            }

            public String getInterface_log_upload() {
                return interface_log_upload;
            }

            public void setInterface_log_upload(String interface_log_upload) {
                this.interface_log_upload = interface_log_upload;
            }

            public String getInterface_route_upload() {
                return interface_route_upload;
            }

            public void setInterface_route_upload(String interface_route_upload) {
                this.interface_route_upload = interface_route_upload;
            }

            public String getInterface_start_info() {
                return interface_start_info;
            }

            public void setInterface_start_info(String interface_start_info) {
                this.interface_start_info = interface_start_info;
            }

            public String getInterface_suggest_report() {
                return interface_suggest_report;
            }

            public void setInterface_suggest_report(String interface_suggest_report) {
                this.interface_suggest_report = interface_suggest_report;
            }

            public String getWeb_notice() {
                return web_notice;
            }

            public void setWeb_notice(String web_notice) {
                this.web_notice = web_notice;
            }

            public String getWeb_speed_effect() {
                return web_speed_effect;
            }

            public void setWeb_speed_effect(String web_speed_effect) {
                this.web_speed_effect = web_speed_effect;
            }

            public String getWeb_suggest() {
                return web_suggest;
            }

            public void setWeb_suggest(String web_suggest) {
                this.web_suggest = web_suggest;
            }
        }

        public static class SupportBean {
            /**
             * notice_url : https://www.htjsq.mobi/app/article.html?os=android
             */

            private String notice_url;

            public String getNotice_url() {
                return notice_url;
            }

            public void setNotice_url(String notice_url) {
                this.notice_url = notice_url;
            }
        }

        public static class UpdateBean {
            /**
             * configs_url : https://www.htjsq.mobi/download/android/configs/
             * game_resource_url : https://www.htjsq.mobi/download/gameres/
             */

            private String configs_url;
            private String game_resource_url;

            public String getConfigs_url() {
                return configs_url;
            }

            public void setConfigs_url(String configs_url) {
                this.configs_url = configs_url;
            }

            public String getGame_resource_url() {
                return game_resource_url;
            }

            public void setGame_resource_url(String game_resource_url) {
                this.game_resource_url = game_resource_url;
            }
        }

        public static class UrlHeadBean {
            /**
             * simple_domain_name : www.htjsq.mobi
             * simple_pre_domain_name : apptest.kuaile-u.com
             * simple_test_domain_name : 121.41.39.33
             */

            private String simple_domain_name;
            private String simple_pre_domain_name;
            private String simple_test_domain_name;

            public String getSimple_domain_name() {
                return simple_domain_name;
            }

            public void setSimple_domain_name(String simple_domain_name) {
                this.simple_domain_name = simple_domain_name;
            }

            public String getSimple_pre_domain_name() {
                return simple_pre_domain_name;
            }

            public void setSimple_pre_domain_name(String simple_pre_domain_name) {
                this.simple_pre_domain_name = simple_pre_domain_name;
            }

            public String getSimple_test_domain_name() {
                return simple_test_domain_name;
            }

            public void setSimple_test_domain_name(String simple_test_domain_name) {
                this.simple_test_domain_name = simple_test_domain_name;
            }
        }
    }

    public static class ComponentsBean {
        /**
         * hash : 28a785e1f8e918e444a6f05f2b9e12a0
         * name : Servers.mejf
         */

        private String hash;
        private String name;

        public String getHash() {
            return hash;
        }

        public void setHash(String hash) {
            this.hash = hash;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
