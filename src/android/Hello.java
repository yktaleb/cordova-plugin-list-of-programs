package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;
import java.util.List;

public class Hello extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {
            StringBuffer applications = new StringBuffer();

            PackageManager packageManager = this.cordova.getActivity().getApplicationContext().getPackageManager();
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

            ArrayList <ApplicationInfo> applicationInfos = new ArrayList<ApplicationInfo>();

            for (ApplicationInfo installedApplication : installedApplications) {
                if (packageManager.getLaunchIntentForPackage(installedApplication.packageName) != null){
                    applicationInfos.add(installedApplication);
                }
            }

            for (ApplicationInfo applicationInfo : applicationInfos) {
                applications.append(applicationInfo.loadLabel(packageManager).toString() + "\n");
            }

            callbackContext.success(applications.toString());

            return true;

        } else {
            
            return false;

        }
    }
}
