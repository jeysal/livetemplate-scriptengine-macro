package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.TextResult
import spock.lang.Specification

/**
 * @author seckinger
 * @since 10/17/16
 */
class ResultConverterTest extends Specification {
    final conv = new ResultConverter()

    def 'toStrings the first element of a Collection-ish or null if empty into a TextResult'() {
        expect:
        (conv.call(collectionIsh([42, 1337])) as TextResult).text == '42'
        (conv.call(collectionIsh([])) as TextResult).text == 'null'

        where:
        collectionIsh << [
                Closure.IDENTITY,
                { it.iterator() },
                Spliterators.&spliterator.rcurry(0),
                { it.stream() }
        ]
    }

    def 'wraps a Runnable in an InvokeActionResult'() {
        given:
        final run = { -> } as Runnable
        final res = conv(run) as InvokeActionResult

        expect:
        res.action == run
    }

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
