package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/24/16
 */
trait InvokeActionResultParamConverter implements Converter<Result, Object> {
    Object call(Result res) {
        res instanceof InvokeActionResult ?
                res.action :
                super.call(res)
    }
}
