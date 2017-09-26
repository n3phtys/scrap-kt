package scrapperkt

import junit.framework.TestCase.assertTrue
import org.junit.Test


class ScrapperUnitTest {

    /*@Test
    fun scrapRedditTest() {
        val (request, response, result) = scrapManual()
        val containsFlag = result.get().contains("</html>")
        assertTrue(containsFlag)
    }*/



    @Test
    fun extractUrlRange() {
        val input = "this_is_my_secret_url_with_range_a_{0,4,2}_and"

        val extract = input.extractRangeAndModify()

        if (extract != null) {
            assertTrue(extract.modifiedString == "this_is_my_secret_url_with_range_a_{}_and")
            assertTrue(extract.beginInclude == 0)
            assertTrue(extract.endInclude == 4)
            assertTrue(extract.steps == 2)
        } else {
            assertTrue(false)
        }
    }

    @Test
    fun extractUrlRangeDefaultRange() {
        val input = "this_is_my_secret_url_with_range_a_{0,4}_and"

        val extract = input.extractRangeAndModify()

        if (extract != null) {
            assertTrue(extract.modifiedString == "this_is_my_secret_url_with_range_a_{}_and")
            assertTrue(extract.beginInclude == 0)
            assertTrue(extract.endInclude == 4)
            assertTrue(extract.steps == 1)
        } else {
            assertTrue(false)
        }
    }

    @Test
    fun extractUrlNegativeStep() {
        val input = "this_is_my_secret_url_with_range_a_{4,0,-1}_and"

        val extract = input.extractRangeAndModify()

        if (extract != null) {
            assertTrue(extract.modifiedString == "this_is_my_secret_url_with_range_a_{}_and")
            assertTrue(extract.beginInclude == 4)
            assertTrue(extract.endInclude == 0)
            assertTrue(extract.steps == -1)
        } else {
            assertTrue(false)
        }
    }


    @Test
    fun extractNoUrlRange() {
        val input = "this_is_my_secret_url_with_range_a_0,4,2}_and"

        val extract = input.extractRangeAndModify()

        assertTrue(extract == null)
    }


    @Test
    fun extractComplexUrlRange() {
        val input = "this_is_my_secret_url_with_range_a_{0,4,2}_and_b_{3,2}"

        val extract1 = input.extractRangeAndModify()
        val extract2 = extract1!!.modifiedString.extractRangeAndModify()

        assertTrue(extract2!!.modifiedString == "this_is_my_secret_url_with_range_a_{}_and_b_{}")
        assertTrue(extract1.steps == 2)
        assertTrue(extract2.steps == -1)
        assertTrue(extract2.beginInclude == 3)
        assertTrue(extract2.endInclude == 2)

    }

/*
    @Test
    fun parseUrlRange() {
        val input = "this_is_my_secret_url_with_range_a_{0,4,2}_and_b_{3,2}"

        val shouldCount = 6



    }*/

}