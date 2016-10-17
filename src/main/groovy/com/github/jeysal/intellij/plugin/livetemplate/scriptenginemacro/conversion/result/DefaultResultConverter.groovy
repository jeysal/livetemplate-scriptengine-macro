package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult

/**
 * @author seckinger
 * @since 10/17/16
 */
class DefaultResultConverter implements Converter<Object, Result> {
    @Override
    Result call(Object obj) {
        new TextResult(Objects.toString(obj))
    }
}
