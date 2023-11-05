package com.example.def0zero.app.models

data class Meta(val code: Int)

data class Duration(val value: Double, val text: String)

data class Distance(val value: Double, val text: String)

data class StartLocation(val latitude: Double, val longitude: Double)

data class EndLocation(val latitude: Double, val longitude: Double)

data class Leg(
    val startLocation: StartLocation,
    val endLocation: EndLocation,
    val startIndex: Int,
    val endIndex: Int,
    val duration: Duration,
    val distance: Distance,
    val geometry: Geometry
)

data class Geometry(val polyline: String)

data class RouteResponse(
    val meta: Meta,
    val route: Route
)

data class Route(
    val duration: Duration,
    val distance: Distance,
    val legs: List<Leg>
)
