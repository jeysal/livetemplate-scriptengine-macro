package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic.*

/**
 * @author seckinger
 * @since 10/18/16
 */
class LookupElementsConverter extends DefaultLookupElementsConverter implements
        ArrayConverter,
        BaseStreamConverter,
        InputStreamConverter,
        IterableConverter,
        IteratorConverter,
        MapConverter,
        OptionalConverter,
        ReaderConverter,
        SingleLookupElementConverter,
        SpliteratorLookupElementsConverter {
}
