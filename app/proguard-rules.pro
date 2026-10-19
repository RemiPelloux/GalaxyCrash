# Keep native method names — exp.c binds to them via JNI.
-keepclasseswithmembernames class * {
    native <methods>;
}

# Keep the app package: exp.c does FindClass("df/root/IReporter") and
# view binding generates com.galaxycrash.app.databinding.* referenced reflectively.
-keep class com.galaxycrash.app.** { *; }
-keep class com.galaxycrash.app.databinding.** { *; }

# Keep click-handler wiring used by Material components.
-keepclassmembers class * {
    void *Click(...);
}

-dontwarn com.galaxycrash.app.**