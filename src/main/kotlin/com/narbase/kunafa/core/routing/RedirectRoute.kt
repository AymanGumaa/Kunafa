@file:Suppress("MemberVisibilityCanBePrivate")

package com.narbase.kunafa.core.routing

import com.narbase.kunafa.core.components.View
import com.narbase.kunafa.core.lifecycle.Observable

/**
 * NARBASE TECHNOLOGIES CONFIDENTIAL
 * ______________________________
 * [2017] -[2019] Narbase Technologies
 * All Rights Reserved.
 * Created by islam
 * On: 2020/02/11.
 */
class RedirectRoute(
        val redirectPath: String,
        meta: RouteMeta,
        segments: List<RouteSegment>,
        parentRoute: Route?,
        isExact: Boolean
) : Route(meta, segments, parentRoute, isExact) {
    override fun onMatch(windowSegments: List<RouteSegment>) {
        Router.navigateTo(redirectPath)
    }

    override fun onUnMatch() {

    }

    companion object {
        fun createRoute(
                redirectPath: String,
                path: String,
                isExact: Boolean,
                isAbsolute: Boolean
        ): Route {
            val routePath = getPath(Router.currentPath, path, isAbsolute)

            val routeSegments = getSegments(routePath)
            val meta = RouteMeta(routePath, Observable())
            val route = RedirectRoute(redirectPath, meta, routeSegments, Router.parentRoute, isExact)
            addToParent(route)
            route.update()
            return route
        }
    }


}

fun View.redirect(
        to: String,
        from: String = "/",
        isExact: Boolean = true,
        isAbsolute: Boolean = false
) {
    RedirectRoute.createRoute(to, from, isExact, isAbsolute)
}
