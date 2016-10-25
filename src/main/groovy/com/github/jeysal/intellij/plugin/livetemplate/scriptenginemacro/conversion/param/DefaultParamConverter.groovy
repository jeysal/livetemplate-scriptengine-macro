package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.Result

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/24/16
 */
class DefaultParamConverter implements Function<Result, Object> {
    @Override
    Object apply(final Result res) {
        Objects.toString(res)
    }
}
