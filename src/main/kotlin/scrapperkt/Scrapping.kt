package scrapperkt

import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.FuelError
import com.github.kittinunf.fuel.core.FuelManager
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.httpDelete
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.fuel.httpPost
import com.github.kittinunf.fuel.httpPut
import com.github.kittinunf.result.Result
import com.github.kittinunf.result.getAs

fun scrapManual() {
    //an extension over string (support GET, PUT, POST, DELETE with httpGet(), httpPut(), httpPost(), httpDelete())
    "http://httpbin.org/get".httpGet().responseString { request, response, result ->
        //do something with response
        when (result) {
            is Result.Failure -> {
                val error = result.getException()
                println(error.message)
            }
            is Result.Success -> {
                val data = result.get()
                println(data)
            }
        }
    }
}