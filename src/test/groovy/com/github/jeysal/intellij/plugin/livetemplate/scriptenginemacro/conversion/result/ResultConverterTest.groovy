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
        (conv.apply(collection) as TextResult).text == res

        where:
        collection << [
                [42, 1337], [],
                [42, 1337].iterator(), [].iterator(),
                Spliterators.spliterator([42, 1337], 0), Spliterators.emptySpliterator(),
                Stream.of(42, 1337), Stream.empty(),
                [abc: 42, xyz: 1337], [:]
        ]
        res << ['42', 'null'] * 5
    }

    def 'fully reads a Reader into a TextResult'() {
        expect:
        (conv.apply(new StringReader('asdf')) as TextResult).text == 'asdf'
    }

    def 'fully reads an InputStream into a TextResult'() {
        expect:
        (conv.apply(new ByteArrayInputStream('asdf'.bytes)) as TextResult).text == 'asdf'
    }

    def 'wraps a Runnable in an InvokeActionResult'() {
        given:
        final run = { -> } as Runnable
        final res = conv.apply(run) as InvokeActionResult

        expect:
        res.action == run
    }

    def 'toStrings any object into a TextResult'() {
        given:
        final obj = new Object()
        final res = conv.apply(obj) as TextResult

        expect:
        res.text == obj.toString()
    }

    def 'toStrings null into a TextResult'() {
        expect:
        (conv.apply(null) as TextResult).text == 'null'
    }
}
