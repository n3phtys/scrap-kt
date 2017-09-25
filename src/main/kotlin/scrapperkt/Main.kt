package scrapperkt

import kotlinx.coroutines.experimental.CommonPool
import kotlinx.coroutines.experimental.async
import kotlinx.coroutines.experimental.launch
import org.jetbrains.ktor.routing.*
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.features.DefaultHeaders
import org.jetbrains.ktor.application.*
import org.jetbrains.ktor.features.*
import org.jetbrains.ktor.host.*
import org.jetbrains.ktor.http.*
import org.jetbrains.ktor.netty.*
import org.jetbrains.ktor.response.*
import org.jetbrains.ktor.routing.*


fun main(args: Array<String>) {
    println("Hello, world!")

    val (request, response, result) = scrapManual()
    println("Call ended, result:")
    println(result.get())


    launch(CommonPool) {

        embeddedServer(Netty, 8080) {
            routing {
                get("/") {
                    call.respondText("Hello, world!", ContentType.Text.Html)
                }
            }
        }.start(wait = true)

    }

    Thread.sleep(2000)

    val (request2, response2, result2) = scrapLocal()
    println("Call local ended, result:")
    println(result2.get())

    System.exit(0)
}