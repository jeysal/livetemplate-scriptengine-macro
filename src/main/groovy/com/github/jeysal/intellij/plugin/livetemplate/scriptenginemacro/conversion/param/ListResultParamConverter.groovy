package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.template.ListResult
import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/24/16
 */
trait ListResultParamConverter implements Converter<Result, Object> {
    Object apply(Result res) {
        res instanceof ListResult ?
                res.components.collect(this.&apply) :
                super.apply(res)
    }
}
