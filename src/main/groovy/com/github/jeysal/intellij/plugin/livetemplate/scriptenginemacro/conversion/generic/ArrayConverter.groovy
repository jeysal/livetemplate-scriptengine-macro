package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter

import static java.util.Arrays.asList

/**
 * @author seckinger
 * @since 10/17/16
 */
trait ArrayConverter<R> implements Converter<Object, R> {
    @Override
    R convert(final obj) {
        obj?.class?.array ?
                convert(asList(obj as Object[])) :
                super.convert(obj)
    }
}
