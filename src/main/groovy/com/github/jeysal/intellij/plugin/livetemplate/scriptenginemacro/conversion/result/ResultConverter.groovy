package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic.*

/**
 * @author seckinger
 * @since 10/17/16
 */
class ResultConverter extends DefaultResultConverter implements
        ArrayConverter,
        BaseStreamConverter,
        InputStreamConverter,
        IterableConverter,
        IteratorConverter,
        MapConverter,
        OptionalConverter,
        ReaderConverter,
        SupplierConverter,
        IdentityResultConverter,
        SpliteratorResultConverter {
}
