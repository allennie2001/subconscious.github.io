package com.subconscious.anotherme.controller

import android.view.accessibility.AccessibilityEvent

/**
 * @author zunyu
 * @version 2021/7/15
 */
open class BaseController {
   open fun onAccessibilityEvent(event: AccessibilityEvent?) {}
}