package info.aliahmed.voxmartcountrycode.util

import java.util.regex.Matcher
import java.util.regex.Pattern

fun String.checkSqlInjection(): Boolean {
    val pattern: Pattern
    val regex = "/(?:INSERT INTO|UPDATE|SELECT|DELETE)(?:[^;'\"]|(?:'[^']*?')|(?:\"[^\"]*?\"))+;/i"
    pattern = Pattern.compile(regex)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}


fun String.checkValidNumber(): Boolean {
    val pattern: Pattern
    val regex = "^[+]*[(]{0,1}[0-9]{1,4}[)]{0,1}[-\\s\\./0-9]*\$"
    pattern = Pattern.compile(regex)
    val matcher: Matcher = pattern.matcher(this)
    return matcher.matches()
}
