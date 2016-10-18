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

    def 'toStrings the first element of a Collection-ish or null if empty into a TextResult'() {
        expect:
        (conv.call(collection) as TextResult).text == res

        where:
        collection << [
                [42, 1337], [],
                [42, 1337].iterator(), [].iterator(),
                Spliterators.spliterator([42, 1337], 0), Spliterators.emptySpliterator(),
                Stream.of(42, 1337), Stream.empty(),
                [abc: 42, xyz: 1337], [:]
        ]
        res << ['42', 'null'] * 4 +
                ['abc=42', 'null']
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
