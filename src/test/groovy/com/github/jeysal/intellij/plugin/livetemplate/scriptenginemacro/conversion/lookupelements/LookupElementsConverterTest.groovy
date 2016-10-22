package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import spock.lang.Specification

/**
 * @author seckinger
 * @since 10/18/16
 */
class LookupElementsConverterTest extends Specification {
    final conv = new LookupElementsConverter()

    def 'wraps each element of a Collection-ish in a LookupElement'() {
        expect:
        conv.call(collection).collect { it.lookupString } == res

        where:
        collection << [
                Spliterators.spliterator([42, 1337], 0), Spliterators.emptySpliterator()
        ]
        res << [
                ['42', '1337'], []
        ]
    }

    def 'wraps any object into a LookupElement'() {
        given:
        final obj = new Object()
        final res = conv(obj)

        expect:
        res.length == 1
        res[0].object == obj
    }

    def 'converts null to an empty LookupElement array'() {
        expect:
        conv.call(null) as List == []
    }
}
