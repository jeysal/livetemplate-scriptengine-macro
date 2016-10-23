package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import spock.lang.Specification

import java.util.stream.Stream

/**
 * @author seckinger
 * @since 10/18/16
 */
class LookupElementsConverterTest extends Specification {
    final conv = new LookupElementsConverter()

    def 'wraps each element of a Collection-ish in a LookupElement'() {
        expect:
        conv.call(collection).collect { it.object } == res

        where:
        collection << [
                [42, 1337], [],
                Spliterators.spliterator([42, 1337], 0), Spliterators.emptySpliterator(),
                Stream.of(42, 1337), Stream.empty()
        ]
        res << [[42, 1337], []] * 3
    }

    def 'flattens a Collection before wrapping the elements'() {
        expect:
        conv.call([[1, 2], 3]).collect { it.object } == [1, 2, 3]
    }

    def 'fully reads an InputStream into a LookupElement'() {
        expect:
        conv.call(new ByteArrayInputStream('asdf'.bytes)).collect { it.object } == ['asdf']
    }

    def 'wraps any object into a LookupElement'() {
        given:
        final obj = new Object()
        final res = conv(obj)

        expect:
        res.collect { it.object } == [obj]
    }

    def 'converts null to an empty LookupElement array'() {
        expect:
        conv.call(null) as List == []
    }
}
