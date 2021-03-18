package com.nifcloud.mbaas.ncmbpushquickstart

import android.util.Log
import com.nifcloud.mbaas.core.NCMBInstallation
import com.nifcloud.mbaas.core.NCMBPush
import com.nifcloud.mbaas.core.NCMBQuery

private const val TAG = "FcmService"
const val NOTIFICATION_TITLE = "UITest push notification"
const val NOTIFICATION_TEXT = "Thank you! We appreciate your business, and we’ll do our best to continue to give you the kind of service you deserve."
const val NOTIFICATION_RICH_URL = "https://www.nifcloud.com/"

object Utils {
    fun sendPushWithSearchCondition() {
        val installation = NCMBInstallation.getCurrentInstallation()
        installation.getDeviceTokenInBackground { token, e ->
            val query = NCMBQuery<NCMBInstallation>("installation")
            query.whereEqualTo("deviceToken", token)
            val push = NCMBPush()
            push.setSearchCondition(query)
            push.title = NOTIFICATION_TITLE
            push.message = NOTIFICATION_TEXT
            push.richUrl = NOTIFICATION_RICH_URL
            push.dialog = true
            push.sendInBackground { e ->
                if (e != null) {
                    Log.d(TAG, "Send push fail")
                } else {
                    Log.d(TAG, "Send push success!")
                }
            }
        }
    }
}