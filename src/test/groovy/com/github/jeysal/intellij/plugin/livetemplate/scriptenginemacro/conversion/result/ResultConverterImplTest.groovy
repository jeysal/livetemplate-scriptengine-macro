package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.TextResult
import spock.lang.Specification

import java.util.function.Supplier
import java.util.stream.Stream

/**
 * @author seckinger
 * @since 10/17/16
 */
class ResultConverterImplTest extends Specification {
    final conv = new ResultConverterImpl()

    def 'toStrings the first element of a Collection-ish or null if empty into a TextResult'() {
        expect:
        (conv.convert(collection) as TextResult).text == res

        where:
        collection << [
                [42, 1337] as int[], [] as int[],
                [42, 1337] as Integer[], [] as Integer[],
                [42, 1337], [],
                [42, 1337].iterator(), [].iterator(),
                Spliterators.spliterator([42, 1337], 0), Spliterators.emptySpliterator(),
                Stream.of(42, 1337), Stream.empty(),
                [abc: 42, xyz: 1337], [:]
        ]
        res << ['42', 'null'] * 7
    }

    def 'unwraps an Optional'() {
        expect:
        (conv.convert(Optional.of('asdf')) as TextResult).text == 'asdf'
    }

    def 'fully reads a Reader into a TextResult'() {
        expect:
        (conv.convert(new StringReader('asdf')) as TextResult).text == 'asdf'
    }

    def 'fully reads an InputStream into a TextResult'() {
        expect:
        (conv.convert(new ByteArrayInputStream('asdf'.bytes)) as TextResult).text == 'asdf'
    }

    def 'gets an element from a Supplier'() {
        expect:
        (conv.convert({ 'asdf' } as Supplier) as TextResult).text == 'asdf'
    }

    def 'does not convert a Result'() {
        given:
        final res = new TextResult('asdf')

        expect:
        conv.convert(res) == res
    }

    def 'toStrings any object into a TextResult'() {
        given:
        final obj = new Object()
        final res = conv.convert(obj) as TextResult

        expect:
        res.text == obj.toString()
    }

    def 'toStrings null into a TextResult'() {
        expect:
        (conv.convert(null) as TextResult).text == 'null'
    }
}
