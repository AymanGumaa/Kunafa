package net.avatarapps.kunafa.core.dimensions.dependent

import net.avatarapps.kunafa.core.components.View
import net.avatarapps.kunafa.core.dimensions.DependentDimension
import net.avatarapps.kunafa.core.dimensions.DimensionNotCalculatedException
import net.avatarapps.kunafa.core.dimensions.DynamicDimension
import org.w3c.dom.HTMLElement

/**
 * AVATAR APPS CONFIDENTIAL
 * ______________________________
 * [2013] - [2017] Avatar Apps
 * All Rights Reserved.
 * Created by islam
 * On: 10/6/17.
 */

class MatchParent internal constructor(val view: View) : DynamicDimension() {
    override fun configure(element: HTMLElement, type: Type) {
        when (type) {
            Type.height -> element.style.height = "100%"
            Type.width -> element.style.width = "100%"
        }
    }

}

val View.matchParent: MatchParent
    get() {
        return MatchParent(this)
    }


