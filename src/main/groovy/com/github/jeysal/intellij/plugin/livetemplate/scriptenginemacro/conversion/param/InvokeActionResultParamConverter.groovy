package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.Result

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/24/16
 */
trait InvokeActionResultParamConverter implements Function<Result, Object> {
    Object apply(Result res) {
        res instanceof InvokeActionResult ?
                res.action :
                super.apply(res)
    }
}
