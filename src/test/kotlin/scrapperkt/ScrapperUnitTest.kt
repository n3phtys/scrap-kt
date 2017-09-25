package scrapperkt

import junit.framework.TestCase.assertTrue
import org.junit.Test


class ScrapperUnitTest {

    @Test
    fun scrapRedditTest() {
        val (request, response, result) = scrapManual()
        val containsFlag = result.get().contains("</html>")
        assertTrue(containsFlag)
    }

}