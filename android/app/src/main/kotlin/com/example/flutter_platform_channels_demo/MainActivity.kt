package com.example.flutter_platform_channels_demo

import io.flutter.embedding.android.FlutterActivity
import androidx.annotation.NonNull
import io.flutter.embedding.engine.FlutterEngine
import io.flutter.plugin.common.MethodChannel



class MainActivity: FlutterActivity() {
    override fun configureFlutterEngine(@NonNull flutterEngine: FlutterEngine) {
        super.configureFlutterEngine(flutterEngine)
        MethodChannel(flutterEngine.dartExecutor.binaryMessenger, "example.com/channel").setMethodCallHandler {
            call, result ->
            if (call.method == "getRandomString") {
                val limit = call.argument("len") ?: 4
                val prefix = call.argument("prefix") ?: ""
                val rand = ('a'..'z').shuffled().take(4).joinToString(prefix = prefix, separator = "")
                result.success(rand)
            } else {
                result.notImplemented()
            }
        }
    }
}
