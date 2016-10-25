package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import java.util.function.Function

import static com.github.jeysal.java.util.function.SupplierUtils.trying

/**
 * @author seckinger
 * @since 10/18/16
 */
trait ReaderConverter<R> implements Function<Object, R> {
    R apply(final obj) {
        obj instanceof Reader ?
                apply(trying(obj.&getText).get().orElse(null)) :
                super.apply(obj)
    }
}
