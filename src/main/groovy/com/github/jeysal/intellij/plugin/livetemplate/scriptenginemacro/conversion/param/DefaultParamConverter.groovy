package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/24/16
 */
class DefaultParamConverter implements ParamConverter {
    @Override
    Object convert(final Result res) {
        Objects.toString(res)
    }
}
