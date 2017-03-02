package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult

/**
 * @author seckinger
 * @since 10/17/16
 */
class DefaultResultConverter implements ResultConverter<Object> {
    @Override
    Result convert(final obj) {
        new TextResult(Objects.toString(obj))
    }
}
