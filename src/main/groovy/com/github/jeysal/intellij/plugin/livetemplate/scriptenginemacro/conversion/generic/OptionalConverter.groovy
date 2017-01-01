package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/17/16
 */
trait OptionalConverter<R> implements Function<Object, R> {
    R apply(final obj) {
        obj instanceof Optional ?
                apply(obj.orElse('empty')) :
                super.apply(obj)
    }
}
