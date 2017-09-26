package scrapperkt

import java.util.regex.Pattern

//{8,24,8} from 8 to 24 in steps of 8


fun parseWithIntervals(str: String) : List<String> {
    throw NotImplementedError()
}

data class ExtractedRange(val modifiedString: String, val beginInclude: Int, val endInclude: Int, val steps: Int)

val regexForIntervals = "\\{-?[0-9]+,-?[0-9]+(,-?[0-9]+)?\\}"

//get first range and exchange
fun String.extractRangeAndModify() : ExtractedRange? {
        val pattern = Pattern.compile(regexForIntervals)
        val matcher = pattern.matcher(this)

        if (matcher.find()) {
            val segment = matcher.group()
            val withoutBrackets = segment.substring(1, segment.length - 1)
            val splits = withoutBrackets.split(",")
            if (splits.size != 3 && splits.size != 2) {
                return null
            }
            val firstElement = Integer.parseInt(splits[0].trim())
            val secondElemenet = Integer.parseInt(splits[1].trim())

            val defaultstep = if (firstElement <= secondElemenet) 1 else -1

            val steps = if (splits.size == 3) {
                Integer.parseInt(splits[2].trim())
            } else defaultstep
            return ExtractedRange(matcher.replaceFirst("{}"), firstElement, secondElemenet, steps)

        } else {
            return null
        }
}