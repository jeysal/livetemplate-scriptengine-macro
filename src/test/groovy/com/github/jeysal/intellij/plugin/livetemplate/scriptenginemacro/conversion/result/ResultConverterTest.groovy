package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.TextResult
import spock.lang.Specification

import java.util.stream.Stream

/**
 * @author seckinger
 * @since 10/17/16
 */
class ResultConverterTest extends Specification {
    final conv = new ResultConverter()

    def 'toStrings the first element of a Spliterator into a TextResult'() {
        given:
        final obj = new Object()
        final res = conv(Spliterators.spliterator([obj], 0)) as TextResult

        expect:
        res.text == obj.toString()
    }

    def 'converts an empty Spliterator to null toStringed into a TextResult'() {
        given:
        final res = conv(Spliterators.emptySpliterator()) as TextResult

        expect:
        res.text == 'null'
    }

    def 'wraps a Runnable in an InvokeActionResult'() {
        given:
        final run = { -> } as Runnable
        final res = conv(run) as InvokeActionResult

        expect:
        res.action == run
    }

    def 'toStrings the first element of a Stream into a TextResult'() {
        given:
        final obj = new Object()
        final res = conv([obj].stream()) as TextResult

        expect:
        res.text == obj.toString()
    }

    def 'converts an empty Stream to null toStringed into a TextResult'() {
        given:
        final res = conv(Stream.empty()) as TextResult

        expect:
        res.text == 'null'
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
