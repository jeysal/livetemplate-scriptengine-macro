package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import java.util.function.Function

import static java.util.Arrays.asList

/**
 * @author seckinger
 * @since 10/17/16
 */
trait ArrayConverter<R> implements Function<Object, R> {
    R apply(final obj) {
        obj?.class?.array ?
                apply(asList(obj as Object[])) :
                super.apply(obj)
    }
}
