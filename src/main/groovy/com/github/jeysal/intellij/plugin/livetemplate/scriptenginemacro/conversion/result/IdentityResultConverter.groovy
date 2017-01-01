package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.Result

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IdentityResultConverter implements Function<Object, Result> {
    @Override
    Result apply(final obj) {
        obj instanceof Result ?
                obj :
                super.apply(obj)
    }
}
