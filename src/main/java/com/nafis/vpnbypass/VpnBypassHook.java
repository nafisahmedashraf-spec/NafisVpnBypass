package com.nafis.vpnbypass;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import java.util.Arrays;
import java.util.List;

public class VpnBypassHook implements IXposedHookLoadPackage {

    // Target packages list
    private static final List<String> TARGET_PACKAGES = Arrays.asList(
            "com.nafis.vpntest"
    );

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!TARGET_PACKAGES.contains(lpparam.packageName)) {
            return;
        }

        XposedBridge.log("NafisVpnBypass: Hooking package -> " + lpparam.packageName);

        try {
            Class<?> connectivityManagerClass = XposedHelpers.findClass(
                "android.net.ConnectivityManager",
                lpparam.classLoader
            );

            XposedHelpers.findAndHookMethod(
                connectivityManagerClass,
                "getNetworkCapabilities",
                android.net.Network.class,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        XposedBridge.log("NafisVpnBypass: Intercepted getNetworkCapabilities for " + lpparam.packageName);
                    }
                }
            );
        } catch (Exception e) {
            XposedBridge.log("NafisVpnBypass Error: " + e.getMessage());
        }
    }
}
