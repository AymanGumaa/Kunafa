package net.avatarapps.kunafa.core.components.layout

import net.avatarapps.kunafa.core.components.View


/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 9/30/17.
 */
open class Container(parent: Container?) : View(parent) {
    val children: ArrayList<View> = arrayListOf()

    override fun configureElement() {
        super.configureElement()

        isScrollableVertically = true
        isScrollableHorizontally = true
    }

    open fun addChild(child: View) {
        addToElement(child)
        child.parent = this
        children.add(child)
    }

    private fun addToElement(child: View) {
        element.append(child.element)
    }

    open fun removeChild(child: View) {
        children.remove(child)
        element.removeChild(child.element)
        child.parent = null

    }

}