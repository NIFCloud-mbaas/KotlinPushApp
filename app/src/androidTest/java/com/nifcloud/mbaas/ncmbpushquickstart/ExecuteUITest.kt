package com.nifcloud.mbaas.ncmbpushquickstart

import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.UiObject2
import androidx.test.uiautomator.Until
import com.nifcloud.mbaas.ncmbpushquickstart.Utils.sendPushWithSearchCondition
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

private const val TIMEOUT = 150000L

@RunWith(AndroidJUnit4::class)
@LargeTest
class ExecuteUITest {
    private lateinit var device: UiDevice

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun init() {
        device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
    }

    @Test
    fun initialScreen() {
        Espresso.onView(ViewMatchers.withText("Hello world!"))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun clickOnSendNotification() {
        sendPushWithSearchCondition()
        device.openNotification()
        device.wait(Until.hasObject(By.text(NOTIFICATION_TITLE)), TIMEOUT.toLong())
        val title: UiObject2 = device.findObject(By.text(NOTIFICATION_TITLE))
        val text: UiObject2 = device.findObject(By.text(NOTIFICATION_TEXT))
        Assert.assertEquals(NOTIFICATION_TITLE, title.text)
        Assert.assertEquals(NOTIFICATION_TEXT, text.text)
        title.click()
    }
}