package scrapperkt

import io.kotlintest.matchers.shouldBe
import io.kotlintest.specs.WordSpec

class MyTests : WordSpec() {
    init {
        "String.length" should {
            "return the length of the string" {
                "sammy".length shouldBe 5
                "".length shouldBe 0
            }
        }

        "scrapping reddit" should {
            "return html" {

                val (request, response, result) = scrapManual()
                result.get().contains("</html>") shouldBe true
            }
        }
    }
}