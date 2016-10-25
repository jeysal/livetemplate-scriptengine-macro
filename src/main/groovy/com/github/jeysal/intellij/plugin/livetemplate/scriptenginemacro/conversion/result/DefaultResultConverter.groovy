package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/17/16
 */
class DefaultResultConverter implements Function<Object, Result> {
    @Override
    Result apply(Object obj) {
        new TextResult(Objects.toString(obj))
    }
}
