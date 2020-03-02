@file:Suppress("MemberVisibilityCanBePrivate", "unused")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.Component
import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.components.view
import com.narbase.kunafa.core.lifecycle.Observable

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2019/05/30.
 */
class ComponentRoute(
        meta: RouteMeta,
        segments: List<RouteSegment>,
        val component: Component,
        parentRoute: Route?,
        val parentView: View?,
        private val referenceView: View,
        isExact: Boolean
) : Route(meta, segments, parentRoute, isExact) {
    override fun onMatch(windowSegments: List<RouteSegment>) {
        val shouldUpdateChildren = component.isInitialized
        try {
            parentView?.mountAfter(component, referenceView)
        } catch (e: RedirectException) {
            children.clear()
            throw e
        }
        if (shouldUpdateChildren) {
            children.forEach {
                it.update()
            }
        }
    }

    override fun onUnMatch() {
        parentView?.unMount(component)
    }

    companion object {
        fun createComponentRoute(
                parentView: View,
                path: String,
                isExact: Boolean = false,
                isAbsolute: Boolean = false,
                block: (meta: RouteMeta) -> Component
        ): ComponentRoute {
            val routePath = getPath(Router.currentPath, path, isAbsolute)

            val routeSegments = getSegments(routePath)

            val reference = parentView.view { isVisible = false }
            val meta = RouteMeta(routePath, Observable())
            val component = block(meta)
            val route = ComponentRoute(meta, routeSegments, component, Router.parentRoute, parentView, reference, isExact)
            addToParent(route)
            route.update()
            return route
        }

    }

}