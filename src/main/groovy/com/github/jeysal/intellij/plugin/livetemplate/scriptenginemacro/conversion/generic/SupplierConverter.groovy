package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

import java.util.function.Supplier

/**
 * @author seckinger
 * @since 10/17/16
 */
trait SupplierConverter<R> implements Converter<Object, R> {
    R convert(final obj) {
        obj instanceof Supplier ?
                convert(obj.get()) :
                super.convert(obj)
    }
}
