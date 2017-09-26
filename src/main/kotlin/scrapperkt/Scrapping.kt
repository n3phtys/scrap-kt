package scrapperkt

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.*
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

fun scrapManual(): Triple<Request, Response, Result<String, FuelError>> {
    //an extension over string (support GET, PUT, POST, DELETE with httpGet(), httpPut(), httpPost(), httpDelete())
    return "https://reddit.com".httpGet().responseString()
}

fun scrapLocal(): Triple<Request, Response, Result<String, FuelError>> {
    //an extension over string (support GET, PUT, POST, DELETE with httpGet(), httpPut(), httpPost(), httpDelete())
    return "http://localhost:8080".httpGet().responseString()
}

fun scrapUrl(url: String): Triple<Request, Response, Result<String, FuelError>> {
    return url.httpGet().responseString()
}

fun Triple<Request, Response, Result<String, FuelError>>.unpack(): scrapperkt.Result {
    val millis = System.currentTimeMillis()
    val url = this.first.url.toString()
    return scrapperkt.Result(url = url, html = this.third.get(), accessedAtEpochMillis = millis)
}