package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import java.util.function.Function
import java.util.function.Supplier

/**
 * @author seckinger
 * @since 10/17/16
 */
trait SupplierConverter<R> implements Function<Object, R> {
    R apply(final obj) {
        obj instanceof Supplier ?
                apply(obj.get()) :
                super.apply(obj)
    }
}
