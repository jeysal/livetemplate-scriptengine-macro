package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IteratorConverter<R> implements Function<Object, R> {
    R apply(final obj) {
        obj instanceof Iterator ?
                apply(Spliterators.spliteratorUnknownSize(obj, 0)) :
                super.apply(obj)
    }
}
