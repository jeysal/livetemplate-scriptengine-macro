package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

import static com.github.jeysal.java.util.function.SupplierUtils.trying

/**
 * @author seckinger
 * @since 10/18/16
 */
trait ReaderConverter<R> implements Converter<Object, R> {
    R call(Object obj) {
        obj instanceof Reader ?
                call(trying(obj.&getText).get().orElse(null)) :
                super.call(obj)
    }
}
