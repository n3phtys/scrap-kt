package scrapperkt



fun main(args : Array<String>) {
    println("Hello, world!")

    val (request, response, result) = scrapManual()
    println("Call ended, result:")
    println(result.get())
}