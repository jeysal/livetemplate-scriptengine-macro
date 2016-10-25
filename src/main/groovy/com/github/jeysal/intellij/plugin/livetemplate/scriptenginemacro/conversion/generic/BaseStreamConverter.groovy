package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import java.util.function.Function
import java.util.stream.BaseStream

/**
 * @author seckinger
 * @since 10/17/16
 */
trait BaseStreamConverter<R> implements Function<Object, R> {
    R apply(final obj) {
        obj instanceof BaseStream ?
                apply(obj.spliterator()) :
                super.apply(obj)
    }
}
