package com.put.mrugas.pepperdemo

import android.os.Bundle
import android.util.Log
import com.aldebaran.qi.sdk.QiContext
import com.aldebaran.qi.sdk.QiSDK
import com.aldebaran.qi.sdk.RobotLifecycleCallbacks
import com.aldebaran.qi.sdk.builder.ChatBuilder
import com.aldebaran.qi.sdk.builder.QiChatbotBuilder
import com.aldebaran.qi.sdk.builder.SayBuilder
import com.aldebaran.qi.sdk.builder.TopicBuilder
import com.aldebaran.qi.sdk.design.activity.RobotActivity

class MainActivity : RobotActivity(), RobotLifecycleCallbacks {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        QiSDK.register(this, this)
    }

    override fun onRobotFocusGained(qiContext: QiContext?) {
        val say = SayBuilder.with(qiContext)
            .withText("Hello ISWD!")
            .build()
        say.run()
        val topic = TopicBuilder.with(qiContext)
            .withResource(R.raw.greetings)
            .build()
        val chatBot = QiChatbotBuilder.with(qiContext)
            .withTopic(topic)
            .build()
        val chat = ChatBuilder.with(qiContext)
            .withChatbot(chatBot)
            .build()
        chat.async().run()

    }

    override fun onRobotFocusLost() {
        Log.d("Pepper", "onRobotFocusLost")
    }

    override fun onRobotFocusRefused(reason: String?) {
        Log.d("Pepper", "onRobotFocusRefused")
    }

    override fun onDestroy() {
        QiSDK.unregister(this, this)
        super.onDestroy()
    }
}
