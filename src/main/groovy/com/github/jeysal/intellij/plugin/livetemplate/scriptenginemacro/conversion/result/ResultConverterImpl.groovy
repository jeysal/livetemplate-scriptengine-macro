package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic.*
import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/17/16
 */
class ResultConverterImpl extends DefaultResultConverter implements
        ArrayConverter<Result>,
        BaseStreamConverter<Result>,
        InputStreamConverter<Result>,
        IterableConverter<Result>,
        IteratorConverter<Result>,
        MapConverter<Result>,
        OptionalConverter<Result>,
        ReaderConverter<Result>,
        SupplierConverter<Result>,
        IdentityResultConverter,
        SpliteratorResultConverter {
}
