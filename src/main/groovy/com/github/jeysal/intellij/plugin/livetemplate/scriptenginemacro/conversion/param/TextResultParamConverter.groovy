package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/24/16
 */
trait TextResultParamConverter implements Function<Result, Object> {
    Object apply(Result res) {
        res instanceof TextResult ?
                res.text :
                super.apply(res)
    }
}
