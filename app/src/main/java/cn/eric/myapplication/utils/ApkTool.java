package cn.eric.myapplication.utils;

import android.content.Context;
import android.content.pm.PackageInfo;

import cn.eric.basiclib.global.Configurator;


/**
 * Created by o on 2017/9/5.
 */

public class ApkTool {
    private static String currentAppVer;

    public static String getCurrentAppVersion() {
        Context appContext = Configurator.get().getAppContext();
        if (currentAppVer == null || currentAppVer.length() == 0) {
            try {
                PackageInfo pi = appContext.getPackageManager().getPackageInfo(appContext.getPackageName(), 0);
                currentAppVer = pi.versionName;
            } catch (Exception e) {
                e.printStackTrace();
            }

            if (currentAppVer == null || currentAppVer.length() == 0) {
                currentAppVer = "0.0.0";
            }
        }

        return currentAppVer;
    }
}
