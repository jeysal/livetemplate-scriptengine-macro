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
        conv.apply(collection).collect { it.object } == res

        where:
        collection << [
                [42, 1337], [],
                [42, 1337].iterator(), [].iterator(),
                Spliterators.spliterator([42, 1337], 0), Spliterators.emptySpliterator(),
                Stream.of(42, 1337), Stream.empty(),
                [abc: 42, xyz: 1337], [:]
        ]
        res << [[42, 1337], []] * 4 +
                [[abc: 42, xyz: 1337].entrySet() as List, []]
    }

    def 'flattens a Collection before wrapping the elements'() {
        expect:
        conv.apply([[1, 2], 3]).collect { it.object } == [1, 2, 3]
    }

    def 'fully reads a Reader into a LookupElement'() {
        expect:
        conv.apply(new StringReader('asdf')).collect { it.object } == ['asdf']
    }

    def 'fully reads an InputStream into a LookupElement'() {
        expect:
        conv.apply(new ByteArrayInputStream('asdf'.bytes)).collect { it.object } == ['asdf']
    }

    def 'wraps any object into a LookupElement'() {
        given:
        final obj = new Object()
        final res = conv.apply(obj)

        expect:
        res.collect { it.object } == [obj]
    }

    def 'converts null to an empty LookupElement array'() {
        expect:
        conv.apply(null) as List == []
    }
}
