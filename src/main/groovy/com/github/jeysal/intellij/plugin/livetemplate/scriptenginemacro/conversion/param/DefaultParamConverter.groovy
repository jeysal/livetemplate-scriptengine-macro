package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/24/16
 */
class DefaultParamConverter implements Converter<Result, Object> {
    @Override
    Object call(Result res) {
        Objects.toString(res)
    }
}
