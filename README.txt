Nafis VPN Bypass - LSPosed Module Project Template
==================================================

Project Files Structure:
1. build.gradle -> Gradle configuration including Xposed API dependency.
2. AndroidManifest.xml -> Xposed metadata declarations.
3. assets/xposed_init -> Defines main hook entry point class.
4. VpnBypassHook.java -> Main Xposed hook implementation.
5. module.prop -> Module metadata information.

How to use:
- Open or import this standard Android project structure in Android Studio or compile via gradle.
- Build APK and install on device running LSPosed framework.
- Enable module in LSPosed Manager app and select scope.

Test scope and behavior:
- Target package: com.nafis.vpntest only.
- The current hook observes and logs getNetworkCapabilities() calls.
- It does not alter the returned NetworkCapabilities or bypass VPN detection.
- Use only with the owned test application during controlled testing.
