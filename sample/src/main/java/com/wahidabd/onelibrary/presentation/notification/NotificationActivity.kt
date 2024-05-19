package com.wahidabd.onelibrary.presentation.notification

import android.content.Context
import android.content.Intent
import com.wahidabd.library.presentation.activity.BaseActivity
import com.wahidabd.library.utils.exts.onClick
import com.wahidabd.library.utils.notification.showNotification
import com.wahidabd.onelibrary.databinding.ActivityNotificationBinding
import com.wahidabd.onelibrary.presentation.MainActivity

class NotificationActivity : BaseActivity<ActivityNotificationBinding>() {

    override fun getViewBinding(): ActivityNotificationBinding =
        ActivityNotificationBinding.inflate(layoutInflater)

    override fun initIntent() {}

    override fun initUI() {}

    override fun initAction() {

        binding.btnNotification.onClick {
            showNotification(
                context = this,
                title = "This is title",
                body = "This is body",
                intent = MainActivity.pendingIntent(this)
            )
        }

        binding.btnNotificationWithImage.onClick {
            showNotification(
                context = this,
                title = "This is title",
                body = "This is body",
                intent = MainActivity.pendingIntent(this),
                imgUrl = "https://fastly.picsum.photos/id/938/200/300.jpg?hmac=MVXKrDXBUPK5fv_Ev3FTdCFeYf9rvJE2Tz9xynjeelM"
            )
        }

    }

    override fun initProcess() {}

    override fun initObservers() {}

    companion object {
        fun start(context: Context){
            context.startActivity(Intent(context, NotificationActivity::class.java))
        }
    }

}