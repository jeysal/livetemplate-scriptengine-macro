package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.ListResult
import com.intellij.codeInsight.template.Result

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/24/16
 */
trait ListResultParamConverter implements Function<Result, Object> {
    Object apply(Result res) {
        res instanceof ListResult ?
                res.components.collect(this.&apply) :
                super.apply(res)
    }
}
