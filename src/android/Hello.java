package com.example.plugin;

import org.apache.cordova.*;
import org.json.JSONArray;
import org.json.JSONException;

public class Hello extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray data, CallbackContext callbackContext) throws JSONException {

        if (action.equals("greet")) {
            List<String> programs = new ArrayList<String>();

            PackageManager packageManager = this.getApplicationContext().getPackageManager();
            List<ApplicationInfo> installedApplications = packageManager.getInstalledApplications(PackageManager.GET_META_DATA);

            ArrayList <ApplicationInfo> applicationInfos = new ArrayList<>();

            for (ApplicationInfo installedApplication : installedApplications) {
                if (packageManager.getLaunchIntentForPackage(installedApplication.packageName) != null){
                    applicationInfos.add(installedApplication);
                }
            }

            for (ApplicationInfo applicationInfo : applicationInfos) {
                programs.add(applicationInfo.loadLabel(packageManager).toString());
                programs.add("\n");
            }

//            System.out.println(programs);

            callbackContext.success(programs.toString);

            return true;

        } else {
            
            return false;

        }
    }
}
