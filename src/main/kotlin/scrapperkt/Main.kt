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
import java.io.File


fun main(args: Array<String>) {

    println("<CommandlineArgs>" )
    args.forEach {
        println(it)
    }
    println("</CommandlineArgs>")




    println("Hello, world!")

    val (request, response, result) = scrapManual()
    println("Call to reddit ended, result:")
    println(result.get())


    val dir = File("./target")


    val res = scrapUrl("https://github.com/n3phtys").unpack().appendToDiskFile(dir)

    println("Also wrote down scrapped html from: " + res.url)



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


class MyArgsParser() {
    val url: String = "" //required, includes potential intervals
    val filedir: String = "" //if set and non-empty, use as parent dir to store

}