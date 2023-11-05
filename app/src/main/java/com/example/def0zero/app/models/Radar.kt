package com.example.def0zero.app.models
import android.util.Log
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
class Radar {

    fun getRadarRoute() {
        val apiKey = "prj_live_pk_814053e8245ee752c0600205795a92fa1c6cd400"
        val locations = "40.7343164,-73.995688|40.6396701,-73.9257626|40.6390023,-73.9975035|40.7227423,-73.9983234|40.6410279,-73.7746107|40.708955145948664,-73.84434376512914"
        val mode = "car"

        val client = OkHttpClient()
        val url = "https://api.radar.io/v1/route/optimize?locations=$locations&mode=$mode"

        val request = Request.Builder()
            .url(url)
            .addHeader("Authorization", apiKey)
            .build()

        try {
            val response: Response = client.newCall(request).execute()
            val responseBody = response.body?.string()

            if (response.isSuccessful && responseBody != null) {
                // Parse the response JSON here and handle it as needed.
                // You can use a JSON parsing library like Gson.
                // Example: val routeResponse = Gson().fromJson(responseBody, RouteResponse::class.java)
            } else {
                // Handle the error response
                Log.e("API Request", "Failed with code: ${response.code}")
            }
        } catch (e: Exception) {
            // Handle exceptions
            e.printStackTrace()
        }
    }

}