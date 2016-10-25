package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import java.util.function.Function

import static com.github.jeysal.java.util.function.SupplierUtils.trying

/**
 * @author seckinger
 * @since 10/18/16
 */
trait InputStreamConverter<R> implements Function<Object, R> {
    R apply(Object obj) {
        obj instanceof InputStream ?
                apply(trying(obj.&getText).get().orElse(null)) :
                super.apply(obj)
    }
}
