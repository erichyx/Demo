package cn.eric.myapplication.network;

/*
created by CZ at 2018/11/24 16:00
与web通讯：
1）获取信息
2）上传信息，包括上传文件
 */

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import android.util.Base64;
import android.util.Log;

import com.loopj.android.http.RequestParams;

import org.apache.http.entity.StringEntity;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import mobi.htjsq.accelerator.AccelerateApplication;
import mobi.htjsq.accelerator.http.CustomOkHttpClient;
import mobi.htjsq.accelerator.http.HttpClient;
import mobi.htjsq.accelerator.http.WebInterfaceEnum;
import mobi.htjsq.accelerator.http.response.BaseResponseHandler;
import mobi.htjsq.accelerator.http.response.OkHttpResponse;
import mobi.htjsq.accelerator.http.response.UniversalResponse;
import mobi.htjsq.accelerator.model.UMengHelper;
import mobi.htjsq.accelerator.model.core.AccelerateManager;
import mobi.htjsq.accelerator.model.core.ConfigsManager;
import mobi.htjsq.accelerator.model.core.GamesManager;
import mobi.htjsq.accelerator.util.ApkTool;
import mobi.htjsq.accelerator.util.ConfigUtil;
import mobi.htjsq.accelerator.util.DeviceHelper;
import mobi.htjsq.accelerator.util.HttpDownloader;
import mobi.htjsq.accelerator.util.JsonParser;
import mobi.htjsq.accelerator.util.LogUtil;
import mobi.htjsq.accelerator.util.MyDate;
import mobi.htjsq.accelerator.util.PathHelper;
import mobi.htjsq.accelerator.util.PreferencesUtil;
import mobi.htjsq.accelerator.util.StringHelper;
import okhttp3.Call;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class WebCommunicator
{
    private static WebCommunicator web_communicator_;

    private static int kWebVersion = 2;
    private static String kPlatform = "android";
    private static String kDefaultKey = "elFYTUVfcAEBAh8AEw==";
    private static String encrypt_key_ = "";
    private static String request_url_ = "";
    private static String upload_file_url_ = "";

    public static String kProgramStart = "program_start";
    public static String kProgramInit = "program_init";
    public static String kIpInfo = "ipinfo";
    public static String kFileUpload = "file_upload";
    public static String kProgramExceptionReport = "program_exception_report";
    public static String kReportAccTraffic = "report_acc_traffic";
    public static String kUserFeedback = "user_feedback";
    public static String kScreenAdvert = "screen_advert";
    public static String kEvenReport = "event_report";
    public static String kReport_acc_data = "report_acc_data";

    public static WebCommunicator getInstance()
    {
        if (web_communicator_ == null) {
            synchronized (WebCommunicator.class) {
                if (web_communicator_ == null) {
                    web_communicator_ = new WebCommunicator();
                }
            }
        }
        return web_communicator_;
    }

    public static void setRequestURL(String request_url)
    {
        String dir = ConfigUtil.getDir("");
        File testFile = new File(dir,"httest");
        File pre_file = new File(dir,"htpre");

        if(pre_file.exists() && testFile.exists()){
            if (!TextUtils.isEmpty(ConfigsManager.getInstance().getPreDomainName())) {
                request_url = request_url.replace("api.htjsq.com","zqreport.kuaile-u.com");
            }
        }

        request_url_ = request_url;
    }

    public static String getRequest_url_()
    {
        return request_url_;
    }

    public static void setUploadFileURL(String upload_file_url)
    {
        String dir = ConfigUtil.getDir("");
        File testFile = new File(dir,"httest");
        File pre_file = new File(dir,"htpre");

        if(pre_file.exists() && testFile.exists()){
            if (!TextUtils.isEmpty(ConfigsManager.getInstance().getPreDomainName())) {
                upload_file_url = upload_file_url.replace("api.htjsq.com","zqresource.kuaile-u.com");
            }
        }

        upload_file_url_ = upload_file_url;
    }

    public static void PostProgramInit()
    {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {
                        //String request_method = "program_init";

                        String request_tring = MakeRequestString2(kProgramInit, null, false);
                        if (request_tring == null || request_tring.length() == 0) {
                            return;
                        }
                        String request_tring2 = "BFEACC1C" + request_tring;

                        HttpDownloader http_downloader = new HttpDownloader();
                        http_downloader.setTimeout(3000);

                        String response = http_downloader.postRequest(request_url_, request_tring2);
                        if (response != null && response.length() > 0) {
                            String response_decrypt = decrypt(response, decryptDefaultKey());
                            if (response_decrypt != null && response_decrypt.length() > 0) {
                                JSONObject response_object = JsonParser.parseString(response_decrypt);
                                String method = JsonParser.getString(response_object, "method", "");
                                if (method != null && method.length() > 0 && method.compareToIgnoreCase(kProgramInit) == 0) {
                                    JSONObject response_object2 = JsonParser.getObject(response_object, "response");
                                    String kc = JsonParser.getString(response_object2, "kc", "");
                                    if (kc != null && kc.length() > 0) {
                                        synchronized (WebCommunicator.getInstance()) {
                                            encrypt_key_ = kc;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
        ).start();
    }

    public class IPArea
    {
        public int ip_provider;
        public int ip_area;
    }

//    public static void getADmessage(BaseResponseHandler handler)
//    {
//        String app_version = AccelerateApplication.versionName;
//        String imei = AccelerateApplication.IMEI;
//
//        JSONObject request_params = new JSONObject();
//        JsonParser.setValueforKey(request_params, "version", app_version);
//        JsonParser.setValueforKey(request_params, "imei", imei);
//
//        String request_params_string = MakeRequestString(request_params);
//        StringEntity entity = MakeRequestStringEntity(request_params_string);
//
//        if (entity != null) {
//            try {
//                HttpClient http_client = new HttpClient(AccelerateApplication.mContext);
//                http_client.post(WebInterfaceEnum.URL_AD.getUrl(), entity, handler);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    }

    public static void getADmessageWithOkHttp(OkHttpResponse callback)
    {
        String app_version = AccelerateApplication.versionName;
        String imei = AccelerateApplication.IMEI;

        JSONObject request_params = new JSONObject();
        JsonParser.setValueforKey(request_params, "version", app_version);
        JsonParser.setValueforKey(request_params, "imei", imei);

        //String method = "program_start";

        String request_params_string = MakeRequestString2(kProgramStart,request_params,true);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_params_string);

        sendMessageReturnWithOkHttp(request_url_, requestBody, callback);
    }

    //获取广告图
    public static void getAdvertOkHttp(OkHttpResponse callback, String advertMethodName)
    {
        String app_version = AccelerateApplication.versionName;
        String imei = AccelerateApplication.IMEI;

        JSONObject request_params = new JSONObject();
        JsonParser.setValueforKey(request_params, "version", app_version);
        JsonParser.setValueforKey(request_params, "imei", imei);

        String request_params_string = MakeRequestString2(advertMethodName,request_params,true);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_params_string);

        sendMessageReturnWithOkHttp(request_url_, requestBody, callback);
    }

    /*
     *该接口必须用后台线程
     */
    public IPArea getIPInfo()
    {
        IPArea ip_area = null;

        JSONObject request_params = new JSONObject();
        //String request_method = "ipinfo";
        String request_string = MakeRequestString2(kIpInfo,request_params,true);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_string);

        String ip_info_string = sendMessageReturnWithOkHttp(request_url_,requestBody);
        JSONObject response_object = isResponseDecryptOk(ip_info_string,kIpInfo);
        if (response_object != null) {
            JSONObject response_object2 = JsonParser.getObject(response_object, "response");

            ip_area = new IPArea();

            ip_area.ip_area = JsonParser.getInt(response_object2,"area",0);

            int line_type = JsonParser.getInt(response_object2,"SP",0);
            if(line_type == 3){
                ip_area.ip_provider = 2;//电信
            }else if(line_type == 1){
                ip_area.ip_provider = 1;//移动
            }else if(line_type == 2){
                ip_area.ip_provider = 3;//联通
            }else if(line_type == 0){
                ip_area.ip_provider = 0;//通用
            }else if(line_type == 4){
                ip_area.ip_provider = 4;
            } else {
                ip_area.ip_provider = 0;
            }
        }

        return ip_area;
    }

    public static void reportEvent (String event_name, String event_sub_name, String user_id, HashMap<String,Object> map)
    {
        String app_version = AccelerateApplication.versionName;
        String imei = AccelerateApplication.IMEI;

        JSONObject request_params = new JSONObject();

        makeDeviceInfo(request_params);

        JsonParser.setValueforKey(request_params, "version", app_version);
        JsonParser.setValueforKey(request_params, "imei", imei);
        JsonParser.setValueforKey(request_params, "event_name", event_name);
        JsonParser.setValueforKey(request_params, "event_sub_name", event_sub_name);
        JsonParser.setValueforKey(request_params, "user_id", user_id);

        JSONArray event_parameter = new JSONArray();
        if (map != null) {
            for (Map.Entry<String, Object> entry : map.entrySet()) {
                JsonParser.setValueforKey(event_parameter,entry.getKey(),entry.getValue(),new JSONObject());
            }
        }
        JsonParser.setValueforKey(request_params, "event_parameter", event_parameter);

        String request_params_string = MakeRequestString2(kEvenReport,request_params,true);
        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_params_string);

        sendMessageIgnoreReturnWithOkHttp(request_url_,requestBody,null);
    }

    public static void webFeedback(String feedback_id, HashMap label_list, HashMap<Integer,File> pic_list,
                                   String message, String linker, OkHttpResponse callback)
    {
        try{
            JSONObject params = new JSONObject();
            LogUtil.getInstance(AccelerateApplication.mContext).e("123456", "jwh feedback feedback == ");

            makeDeviceInfo(params);

            String finger = Build.FINGERPRINT;
            String encodFingerString = com.loopj.android.http.Base64.encodeToString(finger.getBytes(), com.loopj.android.http.Base64.DEFAULT);
            JsonParser.setValueforKey(params, "finger",encodFingerString);
            JsonParser.setValueforKey(params,"version",AccelerateApplication.versionName);
            String UniMessage = ConfigUtil.cnToUnicode(message);
            JsonParser.setValueforKey(params,"content",UniMessage);
            long timestamp = System.currentTimeMillis()/1000;
            JsonParser.setValueforKey(params,"add_time",timestamp);
            JsonParser.setValueforKey(params,"qq",linker);
            JsonParser.setValueforKey(params,"imei",AccelerateApplication.IMEI);
            String UniModel = ConfigUtil.cnToUnicode(Build.MODEL);
            JsonParser.setValueforKey(params,"equip_type",UniModel);
            String UniOs = ConfigUtil.cnToUnicode(Build.VERSION.RELEASE);
            JsonParser.setValueforKey(params,"os","Android "+UniOs);

            String provinces = ConfigUtil.cnToUnicode("");
            JsonParser.setValueforKey(params,"loc_provinces",provinces);

            String city = ConfigUtil.cnToUnicode("");
            JsonParser.setValueforKey(params,"loc_city",city);

            String district = ConfigUtil.cnToUnicode("");
            JsonParser.setValueforKey(params,"loc_district",district);

            String descrip = ConfigUtil.cnToUnicode("");
            JsonParser.setValueforKey(params,"loc_descrip",descrip);

            JsonParser.setValueforKey(params,"fb_id",feedback_id);

            JSONObject file_version = new JSONObject();
            JsonParser.setValueforKey(file_version,"games",GamesManager.getInstance().getFileVersion());
            JsonParser.setValueforKey(file_version,"servers", ConfigsManager.getInstance().getServerFileVersion());
            JsonParser.setValueforKey(file_version,"configs",ConfigsManager.getInstance().getFileVersion());

            JsonParser.setValueforKey(params,"config_version",file_version);

            String label_list_str = "";
            if(label_list != null && label_list.size() > 0){
                Set keys = label_list.keySet(); // 获取主键集合
                Iterator it=keys.iterator();
                while(it.hasNext()){
                    label_list_str=label_list_str+label_list.get(it.next())+"；";
                }

                LogUtil.getInstance(AccelerateApplication.mContext).e("123456", "jwh feedback label_list_str == "+label_list_str);
                String UniLable = ConfigUtil.cnToUnicode(label_list_str);
                JsonParser.setValueforKey(params,"label_list",UniLable);
            }

            JSONObject jsonParams = MakeRequestParam(params);

            MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
            RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), jsonParams.toString());
            MediaType type=MediaType.parse("text/xml;charset=utf-8");//"text/xml;charset=utf-8"

            if (pic_list != null && pic_list.size()  > 0) {
                for (int i = 1 ; i <= pic_list.size() ; i++) {
                    if (pic_list.get(i) != null) {
                        RequestBody fileBody = RequestBody.create(type,pic_list.get(i));
                        bodyBuilder.addPart(Headers.of(
                                "Content-Disposition",
                                "form-data; name=\"pic"+i+"\"; filename=\"pic"+i+"\"")
                                , fileBody);
                    }
                }
            }

            File documents = new File(PathHelper.documentsDirectory());
            String upload_directory = PathHelper.tmpUploadDirectory();
            if (documents != null) {
                File[] files = documents.listFiles();
                if (files != null && files.length > 0) {
                    for (int i = 0 ; i < files.length ; i++) {
                        File file = files[i];
                        if (file == null) {
                            continue;
                        }
                        String file_name = file.getName();
                        if (file_name.contains(".log") && file_name.contains("dq_")) {
                            ConfigUtil.copyFile(file.getPath(), upload_directory + "/" + file_name);

                            RequestBody fileBody = RequestBody.create(type,file);
                            bodyBuilder.addPart(Headers.of(
                                    "Content-Disposition",
                                    "form-data; name=\"profile"+i+"\"; filename=\""+files[i].getName()+"\"")
                                    , fileBody);
                        }
                    }
                }
            }

            RequestBody multipartBody = bodyBuilder
                    .setType(MultipartBody.FORM)
                    .addPart(Headers.of(
                            "Content-Disposition",
                            "form-data; name=\"json\"")
                            ,requestBody)
                    .addPart(Headers.of(
                            "Content-Disposition",
                            "form-data; name=\"version\"")
                            ,RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), AccelerateApplication.versionName))
                    .build();

            try {
                Request request = new Request.Builder().url(WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl())
                        .addHeader("User-Agent","android")
                        .header("multipart/form-data","text/html; charset=utf-8;")
                        .post(multipartBody)//传参数、文件或者混合，改一下就行请求体就行
                        .build();

                CustomOkHttpClient okHttpClient = new CustomOkHttpClient();
                Call call = okHttpClient.newCall(request);
                call.enqueue(callback);
            } catch (Exception e) {
                e.printStackTrace();
                UMengHelper.event(AccelerateApplication.mContext,"http_post_error","url",
                        WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() == null ?
                                "null" : WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() +
                                ":" + WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl().length() + ":"+e);
            }

            LogUtil.getInstance(AccelerateApplication.mContext).e("ht_acc_log", "jwh simpleRequest feedback %%% ");
            LogUtil.getInstance(AccelerateApplication.mContext).e("123456",
                    "jwh_xapk feedback WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() == "
                    +WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl());
        }catch(Exception e){
            LogUtil.getInstance(AccelerateApplication.mContext).e("123456", "jwh feedback try catch e == "+e);
            e.printStackTrace();
        }
    }


    //TODO 待修改
    public static void gameFeedback(String pkgname, String gamename, String linker, OkHttpResponse callback){
        try{
            JSONObject params = new JSONObject();
            LogUtil.getInstance(AccelerateApplication.mContext).e("123456", "jwh feedback gameFeedback == ");

            String UniGamename = ConfigUtil.cnToUnicode(gamename);
            JsonParser.setValueforKey(params, "game_name",UniGamename);
            long timestamp = System.currentTimeMillis()/1000;
            JsonParser.setValueforKey(params, "add_time",timestamp);
            JsonParser.setValueforKey(params, "qq",linker);
            JsonParser.setValueforKey(params, "package_name",pkgname);

            String request_params_string = MakeRequestString(params);
            RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_params_string);

            sendMessageReturnWithOkHttp(WebInterfaceEnum.URL_GAME_FEEDBACK.getUrl(), requestBody, callback);
            LogUtil.getInstance(AccelerateApplication.mContext).e("ht_acc_log", "jwh simpleRequest gameFeedback %%% ");
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public static void reportErrorInfo(String user_id, int error_code, int game_id, String game_name, String phone_num, boolean upload_log)
    {
        String files_path = "";
        JSONObject request_params = new JSONObject();
        makeDeviceInfo(request_params);

        if (upload_log) {
            String upload_directory = PathHelper.tmpUploadDirectory();

            File documents = new File(PathHelper.documentsDirectory());
            ArrayList<File> upload_files = new ArrayList<>();
            try {
                if (documents != null) {
                    File[] files = documents.listFiles();
                    if (files != null && files.length > 0) {
                        for (int i = 0; i < files.length; i++) {
                            File file = files[i];
                            if (file == null) {
                                continue;
                            }
                            String file_name = file.getName();
                            if (file_name.contains(".log") && file_name.contains("dq_")) {
                                ConfigUtil.copyFile(file.getPath(), upload_directory + "/" + file_name);
                                File file_new = new File(upload_directory + "/" + file_name);

                                String fileName = file_new.getName().replace(".log","");
                                fileName = fileName.replace("dq_","");

                                if (MyDate.getFileName().equals(fileName)) {
                                    upload_files.add(file_new);
                                }
                            }
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            //String request_method = "file_upload";
            String request_tring = MakeRequestString2(kFileUpload, request_params, true);

            if (upload_files.size() > 0) {
                files_path = UploadFileReturnStringWithOkHttp(upload_file_url_,kFileUpload,request_tring,upload_files);
            }
        }

        JsonParser.setValueforKey(request_params, "user_id", user_id);
        JsonParser.setValueforKey(request_params, "game_id", game_id);
        JsonParser.setValueforKey(request_params, "game_name", game_name);
        JsonParser.setValueforKey(request_params, "phone", phone_num);
        JsonParser.setValueforKey(request_params, "error_code", error_code);
        JSONObject log_json = new JSONObject();
        JsonParser.setValueforKey(log_json, "log", files_path);
        JsonParser.setValueforKey(request_params, "file_path", log_json);

        //String request_method = "program_exception_report";
        String request_tring = MakeRequestString2(kProgramExceptionReport, request_params, true);
        if (request_tring == null || request_tring.length() == 0) {
            return;
        }

        HttpDownloader http_downloader = new HttpDownloader();
        http_downloader.setTimeout(3000);

        String response = http_downloader.postRequest(request_url_, request_tring);
//        JSONObject response_object = isResponseDecryptOk(response,kProgramExceptionReport);
//        if (response_object != null) {
//
//        }
    }

    public static void reportAccInfo(AccelerateManager.AccelerateInfo accelerate_info, boolean is_restart)
    {
        final int pkgid = accelerate_info.game_id;
        final String acc_line_name = accelerate_info.acc_node_name;
        final String acc_line_ip = accelerate_info.acc_node_ip;
        final int port = accelerate_info.acc_node_port;
        final int ping = accelerate_info.acc_node_ping;
        final int loss = accelerate_info.acc_node_loss;
        final int acc_node_id = accelerate_info.acc_node_id;
        GamesManager.GameInfo2 game_info = GamesManager.getInstance().getGameInfoWithGameId(pkgid);
        if (game_info == null) {
            return;
        }
        final String game_name = game_info.name;
        String android_id = "";
        android_id = Build.FINGERPRINT;

        if(PreferencesUtil.getSettingBoolean("is_open_emulator",false)){
            android_id = "$"+android_id;
        }
        if (is_restart) {
            android_id = "@"+android_id;
        }

        final String lan_ip = ConfigUtil.getHostIp();

        final String versionName = AccelerateApplication.versionName;
        final String version_release = Build.VERSION.RELEASE;

        final String finalAndroid_id = android_id;

        final int qos_opt = 0;
        final String instance_id = "";

        Thread sendThread = new Thread(
                new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try {
                            JSONObject params = new JSONObject();
                            LogUtil.getInstance(AccelerateApplication.mContext).e("123456", "jwh feedback sendPara == ");
                            makeDeviceInfo(params);

                            if(lan_ip.length()>7){
                                JsonParser.setValueforKey(params,"lan_ip",lan_ip);
                            }
                            if(qos_opt==2){
                                JsonParser.setValueforKey(params,"instanceid",instance_id);
                            }

                            JsonParser.setValueforKey(params,"qosopt",qos_opt);
                            JsonParser.setValueforKey(params,"finger",finalAndroid_id);
                            JsonParser.setValueforKey(params,"versionName",versionName);
                            JsonParser.setValueforKey(params,"version",versionName);
                            JsonParser.setValueforKey(params,"imei",AccelerateApplication.IMEI);
                            JsonParser.setValueforKey(params,"gameid",pkgid);
                            JsonParser.setValueforKey(params,"gameName",game_name);
                            JsonParser.setValueforKey(params,"netType",DeviceHelper.getInstance().getNetwork().getNetworkStateString());
                            JsonParser.setValueforKey(params,"networkType",acc_node_id);
//                            String acclinename = ConfigUtil.cnToUnicode(acc_line_name);
                            JsonParser.setValueforKey(params,"acc_line_name",acc_line_name);
                            long timestamp = System.currentTimeMillis()/1000;
                            JsonParser.setValueforKey(params,"add_time",timestamp);
                            JsonParser.setValueforKey(params,"ip",acc_line_ip);
                            JsonParser.setValueforKey(params,"port",port);
                            JsonParser.setValueforKey(params,"ping",ping);
                            JsonParser.setValueforKey(params,"loss",loss);
                            String UniModel = ConfigUtil.cnToUnicode(Build.MODEL);
                            JsonParser.setValueforKey(params,"equip_type",UniModel);
                            String UniOs = ConfigUtil.cnToUnicode(version_release);
                            JsonParser.setValueforKey(params,"os","Android "+UniOs);
                            JsonParser.setValueforKey(params,"platform","android");

                            JsonParser.setValueforKey(params,"channel", ApkTool.getCurrentChannelName());

                            String provinces = ConfigUtil.cnToUnicode("");
                            JsonParser.setValueforKey(params,"loc_provinces",provinces);

                            String city = ConfigUtil.cnToUnicode("");
                            JsonParser.setValueforKey(params,"loc_city",city);

                            String district = ConfigUtil.cnToUnicode("");
                            JsonParser.setValueforKey(params,"loc_district",district);

                            String descrip = ConfigUtil.cnToUnicode("");
                            JsonParser.setValueforKey(params,"loc_descrip",descrip);

//                            String request_params_string = MakeRequestString(params);
                            String request_params_string = MakeRequestString2(kReport_acc_data,params,true);
                            if (request_params_string == null || request_params_string.length() == 0) {
                                return;
                            }

                            RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_params_string);
                            sendMessageIgnoreReturnWithOkHttp(getRequest_url_(), requestBody, null);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
        );
        sendThread.start();
    }

    //TODO 待修改
    public static void UploadErrorLog(String message, String Url)
    {
        JSONObject params = new JSONObject();
        LogUtil.getInstance(AccelerateApplication.mContext).e("ht_acc_log 123456", "jwh feedback UploadErrorLog == ");

        makeDeviceInfo(params);

        String finger = Build.FINGERPRINT;
        String encodFingerString = com.loopj.android.http.Base64.encodeToString(finger.getBytes(), com.loopj.android.http.Base64.DEFAULT);
        JsonParser.setValueforKey(params, "finger",encodFingerString);
        JsonParser.setValueforKey(params,"version",AccelerateApplication.versionName);
        String UniMessage = ConfigUtil.cnToUnicode(message);
        JsonParser.setValueforKey(params,"content",UniMessage);
        long timestamp = System.currentTimeMillis()/1000;
        JsonParser.setValueforKey(params,"add_time",timestamp);
        JsonParser.setValueforKey(params,"imei",AccelerateApplication.IMEI);
        String UniModel = ConfigUtil.cnToUnicode(Build.MODEL);
        JsonParser.setValueforKey(params,"equip_type",UniModel);
        String UniOs = ConfigUtil.cnToUnicode(Build.VERSION.RELEASE);
        String os = "Android " + UniOs;
        JsonParser.setValueforKey(params,"os",os);

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        MediaType type = MediaType.parse("text/xml;charset=utf-8");//"text/xml;charset=utf-8"

        String request_param_string_md5 = StringHelper.stringToMD5(JsonParser.objectToString(params));
        String key = StringHelper.stringToMD5(request_param_string_md5 + "htjsq%@)");
        String auth_code = StringHelper.stringToSHA1(key + "-" + os + "|[" + timestamp + "]" );

        RequestBody multipartBody = bodyBuilder
                .setType(MultipartBody.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"os\"")
                        ,RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), os))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"timestamp\"")
                        ,RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), timestamp+""))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"key\"")
                        ,RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), key))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"auth_code\"")
                        ,RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), auth_code))
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"request\"")
                        ,RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), JsonParser.objectToString(params)))
                .build();

        File documents = new File(PathHelper.documentsDirectory());
        String upload_directory = ConfigUtil.getDir("upload/")+timestamp;
        File upload_directory_file = new File(upload_directory);
        if (documents != null) {
            File[] files = documents.listFiles();
            if (files != null && files.length > 0) {
                for (int i = 0 ; i < files.length ; i++) {
                    File file = files[i];
                    if (file == null) {
                        continue;
                    }
                    String file_name = file.getName();
                    if (file_name.contains(".log") && file_name.contains("dq_")) {
                        ConfigUtil.copyFile(file.getPath(), upload_directory + "/" + file_name);

                        File upload_file = new File(upload_directory+"/"+file_name);
                        RequestBody fileBody = RequestBody.create(type,upload_file);
                        bodyBuilder.addPart(Headers.of(
                                "Content-Disposition",
                                "form-data; name=\"profile"+i+"\"; filename=\""+file_name+"\"")
                                , fileBody);
                    }
                }
            }
        }

        sendMessageIgnoreReturnWithOkHttp(Url, multipartBody, upload_directory_file);

        LogUtil.getInstance(AccelerateApplication.mContext).e("ht_acc_log 123456", "jwh feedback UploadErrorLog url == "+Url);
    }

    public static void reportSpeedEffect(String user_id, String game_name, String acc_node_name, int user_grade, String feedback_content)
    {
        JSONObject request_params = new JSONObject();
        JsonParser.setValueforKey(request_params, "feedback_type", "speed_feedback");

        makeDeviceInfo(request_params);

        JsonParser.setValueforKey(request_params, "user_id", user_id);
        JsonParser.setValueforKey(request_params, "user_qq", "");
        JsonParser.setValueforKey(request_params, "acc_game", StringHelper.stringToUrlEncode(game_name));
        JsonParser.setValueforKey(request_params, "acc_node", StringHelper.stringToUrlEncode(acc_node_name));
        JsonParser.setValueforKey(request_params, "user_grade", user_grade);
        JsonParser.setValueforKey(request_params, "feedback_content", StringHelper.stringToUrlEncode(feedback_content));

        String request_tring = MakeRequestString2(kUserFeedback, request_params, true);
        if (request_tring == null || request_tring.length() == 0) {
            return;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_tring);
        sendMessageIgnoreReturnWithOkHttp(request_url_, requestBody, null);
    }

    public static void reportAccTraffic(Context context, String user_id, AccelerateManager.AccelerateInfo accelerateInfo1)
    {
        JSONObject request_params = new JSONObject();

        makeDeviceInfo(request_params);

        AccelerateManager.AccelerateInfo accelerateInfo = null;
        if (accelerateInfo1 == null) {
            accelerateInfo = PreferencesUtil.getObject(context, AccelerateManager.AccelerateInfo.class);
        } else {
            accelerateInfo = accelerateInfo1;
        }
        if (accelerateInfo == null) {
            return;
        }

        JsonParser.setValueforKey(request_params, "user_id", user_id);
        JsonParser.setValueforKey(request_params, "channel", ApkTool.getCurrentChannelName());

        JsonParser.setValueforKey(request_params, "game_id",
                accelerateInfo.game_id);
        JsonParser.setValueforKey(request_params, "game_name",
                accelerateInfo.game_name);
        JsonParser.setValueforKey(request_params, "start_network_type",
                accelerateInfo.start_network_type);
        JsonParser.setValueforKey(request_params, "switch_network_type",
                accelerateInfo.switch_network_type);

        int timeStep = accelerateInfo.speed_duration;
//        int hour =  timeStep/3600;
//        int minute = (timeStep - hour*3600) / 60;
//        int second = (timeStep - hour*60) % 60;
//        String speed_duration = MyDate.getFormattedStr(hour) +
//                ":" + MyDate.getFormattedStr(minute) +
//                ":" + MyDate.getFormattedStr(second);
//        JsonParser.setValueforKey(request_params, "acc_duration",
//                speed_duration);
        JsonParser.setValueforKey(request_params, "acc_duration",
                timeStep);
        JsonParser.setValueforKey(request_params, "network_switch_times",
                accelerateInfo.switch_net_times);

        JsonParser.setValueforKey(request_params, "node_ip", accelerateInfo.acc_node_ip);
        JsonParser.setValueforKey(request_params, "node_type", accelerateInfo.acc_node_name);
        JsonParser.setValueforKey(request_params, "node_delay", accelerateInfo.acc_node_ping);
        JsonParser.setValueforKey(request_params, "tcp_bytes_send_acc", accelerateInfo.tcp_send_acc);
        JsonParser.setValueforKey(request_params, "tcp_bytes_send_no_acc", accelerateInfo.tcp_send_no_acc);
        JsonParser.setValueforKey(request_params, "tcp_bytes_recv_acc", accelerateInfo.tcp_recv_acc);
        JsonParser.setValueforKey(request_params, "tcp_bytes_recv_no_acc", accelerateInfo.tcp_recv_no_acc);
        JsonParser.setValueforKey(request_params, "udp_bytes_send_acc", accelerateInfo.udp_send_acc);
        JsonParser.setValueforKey(request_params, "udp_bytes_send_no_acc", accelerateInfo.udp_send_no_acc);
        JsonParser.setValueforKey(request_params, "udp_bytes_recv_acc", accelerateInfo.udp_recv_acc);
        JsonParser.setValueforKey(request_params, "udp_bytes_recv_no_acc", accelerateInfo.udp_recv_no_acc);

        String request_tring = MakeRequestString2(kReportAccTraffic, request_params, true);
        if (request_tring == null || request_tring.length() == 0) {
            return;
        }

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/x-markdown; charset=utf-8"), request_tring);
        sendMessageIgnoreReturnWithOkHttp(request_url_, requestBody, null);
    }

    private static void addFilesToUpload(ArrayList<File> upload_files, RequestParams request_params)
    {
        if (upload_files == null || upload_files.size() == 0) {
            return;
        }

        try {
            for (int i = 0 ; i < upload_files.size() ; i++) {
                request_params.put("file" + i, upload_files.get(i), "text/xml");  // Upload a File
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void addFilesToUpload2(ArrayList<File> upload_files, MultipartBody.Builder bodyBuilder)
    {
        if (upload_files == null || upload_files.size() == 0) {
            return;
        }

        MediaType type=MediaType.parse("text/xml;charset=utf-8");//"text/xml;charset=utf-8"

        try {
            for (int i = 0 ; i < upload_files.size() ; i++) {
                RequestBody fileBody = RequestBody.create(type,upload_files.get(i));
                bodyBuilder.addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"file"+i+"\"; filename=\""+upload_files.get(i).getName()+"\"")
                        , fileBody);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String MakeRequestString(JSONObject request_param_object)
    {
        JSONObject send_request_object = MakeRequestParam(request_param_object);

        String request_string = JsonParser.objectToString(send_request_object);

        return request_string;
    }

    public static JSONObject MakeRequestParam(JSONObject request_param_object)
    {
        String os_version_unicode = StringHelper.stringToUnicode(Build.VERSION.RELEASE);
        String os = "Android " + os_version_unicode;

        long timestamp = System.currentTimeMillis()/1000;

        String request_param_string_md5 = StringHelper.stringToMD5(JsonParser.objectToString(request_param_object));
        String key = StringHelper.stringToMD5(request_param_string_md5 + "htjsq%@)");
        String auth_code = StringHelper.stringToSHA1(key + "-" + os + "|[" + timestamp + "]" );

        JSONObject send_request_object = new JSONObject();
        JsonParser.setValueforKey(send_request_object, "version", AccelerateApplication.versionName);
        JsonParser.setValueforKey(send_request_object, "os", os);
        JsonParser.setValueforKey(send_request_object, "timestamp", timestamp);
        JsonParser.setValueforKey(send_request_object, "key", key);
        JsonParser.setValueforKey(send_request_object, "auth_code", auth_code);
        JsonParser.setValueforKey(send_request_object, "request", request_param_object);

        return send_request_object;
    }

    private static void sendMessageIgnoreReturn(String request_url, String request_data)
    {
        StringEntity entity = MakeRequestStringEntity(request_data);

        if (entity != null) {
            try {
                final BaseResponseHandler http_response_handler = new UniversalResponse(null,
                        null,null, true);

                HttpClient http_client = new HttpClient(AccelerateApplication.mContext);
                http_client.post(request_url, entity, http_response_handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void sendMessageIgnoreReturnWithOkHttp(final String request_url, final RequestBody requestBody, final File file)
    {
        new Thread(
                new Runnable() {
                    @Override
                    public void run() {

                        try {
                            Request request = new Request.Builder()
                                    .url(request_url)
                                    .post(requestBody)
                                    .build();
                            CustomOkHttpClient okHttpClient = new CustomOkHttpClient();
                            Call call = okHttpClient.newCall(request);

                            Response response = call.execute();
                            //String s = response.body().string();
                            //JSONObject response_obj = isResponseDecryptOk(s,kEvenReport);
                        } catch (Exception e) {
                            e.printStackTrace();
                            UMengHelper.event(AccelerateApplication.mContext,"http_post_error","url",
                                    WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() == null ?
                                    "null" : WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() +
                                            ":" + WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl().length() + ":"+e);
                        }

                        if (file != null) {
                            ConfigUtil.deleteFile(file);
                        }
                    }
                }
        ).start();
    }

    private static void sendMessageReturnWithOkHttp(String request_url, RequestBody requestBody, OkHttpResponse callback)
    {
        try {
            Request request = new Request.Builder()
                    .url(request_url)
                    .post(requestBody)
                    .build();
            CustomOkHttpClient okHttpClient = new CustomOkHttpClient();
            Call call = okHttpClient.newCall(request);
            call.enqueue(callback);
        } catch (Exception e) {
            e.printStackTrace();
            UMengHelper.event(AccelerateApplication.mContext,"http_post_error","url",
                    WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() == null ?
                            "null" : WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() +
                            ":" + WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl().length() + ":"+e);
        }
    }

    private static String sendMessageReturnWithOkHttp(String request_url, RequestBody requestBody)
    {
        String response_str = null;

        try {
            Request request = new Request.Builder()
                    .url(request_url)
                    .post(requestBody)
                    .build();
            CustomOkHttpClient okHttpClient = new CustomOkHttpClient();
            Call call = okHttpClient.newCall(request);

            Response response = call.execute();
            response_str = response.body().string();
        } catch (Exception e) {
            e.printStackTrace();
            UMengHelper.event(AccelerateApplication.mContext,"http_post_error","url",
                    WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() == null ?
                            "null" : WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() +
                            ":" + WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl().length() + ":"+e);
        }

        return response_str;
    }

    private static void sendMessageIgnoreReturn(String request_url, String request_data, ArrayList upload_files)
    {
        RequestParams request_params = new RequestParams();
        request_params.put("request", request_data);
        request_params.put("platform", "android");
        addFilesToUpload(upload_files, request_params);

        if (request_params != null) {
            try {
                final BaseResponseHandler http_response_handler = new UniversalResponse(null,
                        null,null, true);

                HttpClient http_client = new HttpClient(AccelerateApplication.mContext);
                http_client.post(request_url, request_params, http_response_handler);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static String UploadFileReturnStringWithOkHttp(String request_url, String request_method, String request_data, ArrayList upload_files)
    {
        String files = "";

        MultipartBody.Builder bodyBuilder = new MultipartBody.Builder();
        addFilesToUpload2(upload_files, bodyBuilder);

        RequestBody requestBody = RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), request_data);
        RequestBody requestBody2 = RequestBody.create(MediaType.parse("text/plain; charset=UTF-8"), "android");

        RequestBody multipartBody = bodyBuilder
                .setType(MultipartBody.FORM)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"request\"")
                        ,requestBody)
                .addPart(Headers.of(
                        "Content-Disposition",
                        "form-data; name=\"platform\"")
                        ,requestBody2)
                .build();


        try {
            Request request=new Request.Builder().url(request_url)
                    .addHeader("User-Agent","android")
                    .header("multipart/form-data","text/html; charset=UTF-8;")
                    .post(multipartBody)//传参数、文件或者混合，改一下就行请求体就行
                    .build();

            CustomOkHttpClient okHttpClient = new CustomOkHttpClient();
            Call call = okHttpClient.newCall(request);

            Response response = call.execute();
            String response_body = new String(response.body().string());

            JSONObject response_object = isResponseDecryptOk(response_body,request_method);
            if (response_object != null) {
                int error_id = JsonParser.getInt(response_object, "error_id",10);
                if (error_id == 0) {
                    JSONObject response_json = JsonParser.getObject(response_object, "response");

                    for (int i = 0 ; i < upload_files.size() ; i++) {
                        String key = "file" + i;
                        JSONObject value = JsonParser.getObject(response_json,key);
                        String path = JsonParser.getString(value,"path","");
                        if (i == 0) {
                            files = path;
                        } else {
                            files = files + ";" + path;
                        }
                    }
                    //Log.e("jwh_report_error","files  == "+files);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            UMengHelper.event(AccelerateApplication.mContext,"http_post_error","url",
                    WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() == null ?
                            "null" : WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl() +
                            ":" + WebInterfaceEnum.URL_WEB_FEEDBACK.getUrl().length() + ":"+e);
        }

        return files;
    }

    private static void makeDeviceInfo(JSONObject request_params)
    {
        String phone_brand = StringHelper.stringToUnicode(Build.BRAND);
        String phone_brand_unicode = StringHelper.stringToUnicode(phone_brand);
        String phone_model = StringHelper.stringToUnicode(Build.MODEL);
        String phone_model_unicode = StringHelper.stringToUnicode(phone_model);
        String network_type = DeviceHelper.getInstance().getNetwork().getNetworkStateString();

        JsonParser.setValueforKey(request_params, "device_brand", phone_brand_unicode);
        JsonParser.setValueforKey(request_params, "phone_brand", phone_brand_unicode);
        JsonParser.setValueforKey(request_params, "device_type", phone_model_unicode);
        JsonParser.setValueforKey(request_params, "equip_type", phone_model_unicode);
        JsonParser.setValueforKey(request_params, "network_type", network_type);
    }

    private static StringEntity MakeRequestStringEntity(String request_strig)
    {
        StringEntity entity = null;
        try {
            entity = new StringEntity(request_strig,"utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entity;
    }

    private static String MakeRequestString2(String method, JSONObject request_param_object, boolean auth_independent) {
        String os_version_unicode = StringHelper.stringToUnicode(Build.VERSION.RELEASE);
        String os = "Android " + os_version_unicode;

        long timestamp = System.currentTimeMillis() / 1000;

        JSONObject send_request_object = new JSONObject();
        JsonParser.setValueforKey(send_request_object, "method", method);
        JsonParser.setValueforKey(send_request_object, "version", kWebVersion);
        JsonParser.setValueforKey(send_request_object, "platform", kPlatform);
        JsonParser.setValueforKey(send_request_object, "os", os);
        JsonParser.setValueforKey(send_request_object, "timestamp", timestamp);
        JsonParser.setValueforKey(send_request_object, "identifier", AccelerateApplication.IMEI);

        String key1 = StringHelper.stringToMD5(
                AccelerateApplication.ROLD_ID + "-" + AccelerateApplication.versionName + "|[" + timestamp + "]").toLowerCase();
        String auth_key = StringHelper.stringToMD5(key1 + "Htjsq%@)").toUpperCase();
        if (auth_independent) {
            JSONObject auth_object = new JSONObject();
            JsonParser.setValueforKey(auth_object, "auth_key", auth_key);
            JsonParser.setValueforKey(auth_object, "role_id", AccelerateApplication.ROLD_ID);
            JsonParser.setValueforKey(auth_object, "role_version", AccelerateApplication.versionName);
            JsonParser.setValueforKey(send_request_object, "auth", auth_object);
        } else {
            JsonParser.setValueforKey(send_request_object, "auth_key", auth_key);
            JsonParser.setValueforKey(send_request_object, "role_id", AccelerateApplication.ROLD_ID);
            JsonParser.setValueforKey(send_request_object, "role_version", AccelerateApplication.versionName);
        }

        if (request_param_object != null) {
            JsonParser.setValueforKey(send_request_object, "request", request_param_object);
        }

        String request_string = JsonParser.objectToString(send_request_object);
        //if (method.equals(kEvenReport)) {
            //Log.e("jwh_event_report","request_string == "+request_string);
        //}

        String request_string_encrypted = encryptRequestData(request_string);

        return request_string_encrypted;
    }

    private static String encryptRequestData(String request_data)
    {
        String key = "";
        synchronized (WebCommunicator.getInstance()) {
            key = encrypt_key_;
        }
        if (key == null || key.length() == 0) {
            key = decryptDefaultKey();
        } else {
            key = decrypt(key, decryptDefaultKey());
        }

        return encrypt(request_data, key);
    }

    private static String encrypt_before(String str, String key){
        int keylen = key.length();
        int strlen = str.length();
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < strlen; i += keylen){
            String segment;
            if(i + keylen <= strlen){
                segment = str.substring(i, i + keylen);
            } else {
                segment = str.substring(i);
            }
            char[] segs = segment.toCharArray();
            for(int j = 0; j < Math.min(keylen, segs.length); j ++){
                segs[j] ^= key.charAt(j);
            }
            sb.append(String.valueOf(segs));
        }

        String base64_encode_string = Base64.encodeToString(sb.toString().getBytes(), Base64.NO_WRAP);
        /*if (base64_encode_string != null && base64_encode_string.length() > 0) {
            base64_encode_string = base64_encode_string.replace("=","");
        }*/

        return base64_encode_string;
    }

    public static String encrypt(String str, String key){
        int keylen = key.length();
        byte[] bytes = str.getBytes();
        for(int i = 0; i < bytes.length; i += keylen){
            for(int j = i; j < Math.min(i + keylen, bytes.length); j ++){
                bytes[j] ^= key.charAt(j - i);
            }
        }
        return Base64.encodeToString(bytes, Base64.NO_WRAP);
    }

    public static String decrypt(String str, String key){
        if (key.length() <= 0) {
            return "";
        }
        int keylen = key.length();

        byte[] bytes = null;
        try {
            bytes = Base64.decode(str.getBytes(), Base64.NO_WRAP);
        } catch (Exception e) {
            UMengHelper.event(AccelerateApplication.mContext,"decrypt_error","decry_str",str);
            e.printStackTrace();
        }
        if (bytes != null) {
            for(int i = 0; i < bytes.length; i += keylen){
                for(int j = i; j < Math.min(i + keylen, bytes.length); j ++){
                    bytes[j] ^= key.charAt(j - i);
                }
            }

            return new String(bytes);
        } else {
            return "";
        }
    }

    private static String decrypt_before(String str, String key){
        if (key.length() <= 0) {
            return "";
        }
        int keylen = key.length();
        try {
            str = new String(Base64.decode(str.getBytes(), Base64.NO_WRAP));
        } catch (Exception e) {
            UMengHelper.event(AccelerateApplication.mContext,"decrypt_error","decry_str",str);
            e.printStackTrace();
        }
        int strlen = str.length();
        StringBuilder sb = new StringBuilder();
        try {
            for(int i = 0; i < strlen; i += keylen){
                String segment;
                if(i + keylen <= strlen){
                    segment = str.substring(i, i + keylen);
                } else {
                    segment = str.substring(i);
                }
                char[] segs = segment.toCharArray();
                for(int j = 0; j < Math.min(keylen, segs.length); j ++){
                    segs[j] ^= key.charAt(j);
                }
                sb.append(String.valueOf(segs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private static String decryptDefaultKey()
    {
        String key = "201901031022";

        return decrypt(kDefaultKey, key);
    }

    public static JSONObject isResponseDecryptOk (String response_str, String request_method)
    {
        JSONObject decrypt_object = null;
        if (response_str != null && response_str.length() > 0) {
            String json_decrypt = decrypt(response_str, decrypt(encrypt_key_,decryptDefaultKey()));
            if (json_decrypt != null && json_decrypt.length() > 0) {
                JSONObject response_object = JsonParser.parseString(json_decrypt);
                String method = JsonParser.getString(response_object, "method", "");
                if (method != null && method.length() > 0 && method.compareToIgnoreCase(request_method) == 0) {
                    decrypt_object = response_object;
                }
            }
        }

        return decrypt_object;
    }

    public static String decryptJson (String response_str)
    {
        String json_decrypt = null;
        if (response_str != null && response_str.length() > 0) {
            json_decrypt = decrypt(response_str, decrypt(encrypt_key_,decryptDefaultKey()));
        }else{
            LogUtil.getInstance(AccelerateApplication.mContext).e("WebCommunicator/decryptJson", "数据不存在");

        }

        return json_decrypt;
    }

}
