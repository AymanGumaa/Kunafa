package net.avatarapps.kunafa.core.components

import net.avatarapps.kunafa.core.components.layout.Container
import net.avatarapps.kunafa.core.dimensions.IndependentDimension
import net.avatarapps.kunafa.core.dimensions.independent.px
import org.w3c.dom.HTMLSpanElement
import kotlin.browser.document
import kotlin.browser.window

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/9/17.
 */
open class TextView(parent: Container? = null) : View(parent) {
    protected val span by lazy {
        val s = document.createElement("span") as HTMLSpanElement
        element.append(s)
        return@lazy s
    }

    var text = ""
        set(value) {
            field = value
            span.innerHTML = value
        }

    override fun render() {
        super.render()
    }

    override val wrappedContentHeight: IndependentDimension
        get(){
            println("Computed: ${window.getComputedStyle(span).fontSize}")
            return span.clientHeight.px
        }

    override val wrappedContentWidth: IndependentDimension
        get() = span.clientWidth.px
}