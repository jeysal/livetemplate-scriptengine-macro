package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import spock.lang.Specification

import java.util.function.Supplier
import java.util.stream.Stream

/**
 * @author seckinger
 * @since 10/18/16
 */
class LookupElementsConverterImplTest extends Specification {
    final conv = new LookupElementsConverterImpl()

    def 'wraps each element of a Collection-ish in a LookupElement'() {
        expect:
        conv.convert(collection).collect { it.object } == res

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
        res << [[42, 1337], []] * 7
    }

    def 'unwraps an Optional'() {
        expect:
        conv.convert(Optional.of('asdf')).collect { it.object } == ['asdf']
    }

    def 'flattens a Collection before wrapping the elements'() {
        expect:
        conv.convert([[1, 2], 3]).collect { it.object } == [1, 2, 3]
    }

    def 'fully reads a Reader into a LookupElement'() {
        expect:
        conv.convert(new StringReader('asdf')).collect { it.object } == ['asdf']
    }

    def 'fully reads an InputStream into a LookupElement'() {
        expect:
        conv.convert(new ByteArrayInputStream('asdf'.bytes)).collect { it.object } == ['asdf']
    }

    def 'gets an element from a Supplier'() {
        expect:
        conv.convert({ 'asdf' } as Supplier).collect { it.object } == ['asdf']
    }

    def 'wraps a LookupElement in an array'() {
        given:
        final elem = LookupElementBuilder.create('asdf')

        expect:
        conv.convert(elem) == [elem] as LookupElement[]
    }

    def 'does not convert a LookupElement array'() {
        given:
        final elems = [LookupElementBuilder.create('asdf')] as LookupElement[]

        expect:
        conv.convert(elems) == elems
    }

    def 'wraps any object into a LookupElement'() {
        given:
        final obj = new Object()
        final res = conv.convert(obj)

        expect:
        res.collect { it.object } == [obj]
    }

    def 'converts null to an empty LookupElement array'() {
        expect:
        conv.convert(null) as List == []
    }
}
