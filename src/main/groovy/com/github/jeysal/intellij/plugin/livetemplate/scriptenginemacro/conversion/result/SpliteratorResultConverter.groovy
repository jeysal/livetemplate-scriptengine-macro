package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.Result

import java.util.stream.StreamSupport

/**
 * @author seckinger
 * @since 10/17/16
 */
trait SpliteratorResultConverter implements ResultConverter<Spliterator> {
    @Override
    Result convert(final Spliterator spliterator) {
        convert(StreamSupport.stream(spliterator, false).findFirst().orElse(null))
    }
}
