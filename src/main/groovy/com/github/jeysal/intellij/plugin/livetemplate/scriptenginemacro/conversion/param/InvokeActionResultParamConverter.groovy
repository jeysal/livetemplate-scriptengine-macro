package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/24/16
 */
trait InvokeActionResultParamConverter implements ParamConverter {
    @Override
    Object convert(final Result res) {
        res instanceof InvokeActionResult ?
                res.action :
                super.convert(res)
    }
}
