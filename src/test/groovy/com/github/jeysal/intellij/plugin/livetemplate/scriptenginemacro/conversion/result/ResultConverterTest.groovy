package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.TextResult
import spock.lang.Specification

/**
 * @author seckinger
 * @since 10/17/16
 */
class ResultConverterTest extends Specification {
    final conv = new ResultConverter()

    def 'toStrings any object into a TextResult'() {
        given:
        final obj = new Object()
        final res = conv(obj) as TextResult

        expect:
        res.text == obj.toString()
    }

    def 'toStrings null into a TextResult'() {
        given:
        final res = conv(null) as TextResult

        expect:
        res.text == 'null'
    }
}
