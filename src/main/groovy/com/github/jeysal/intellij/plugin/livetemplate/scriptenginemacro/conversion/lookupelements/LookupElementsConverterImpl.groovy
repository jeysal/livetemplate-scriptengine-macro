package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic.*
import com.intellij.codeInsight.lookup.LookupElement

/**
 * @author seckinger
 * @since 10/18/16
 */
class LookupElementsConverterImpl extends DefaultLookupElementsConverter implements
        ArrayConverter<LookupElement[]>,
        BaseStreamConverter<LookupElement[]>,
        InputStreamConverter<LookupElement[]>,
        IterableConverter<LookupElement[]>,
        IteratorConverter<LookupElement[]>,
        MapConverter<LookupElement[]>,
        OptionalConverter<LookupElement[]>,
        ReaderConverter<LookupElement[]>,
        SupplierConverter<LookupElement[]>,
        SingleLookupElementConverter,
        SpliteratorLookupElementsConverter {
}
