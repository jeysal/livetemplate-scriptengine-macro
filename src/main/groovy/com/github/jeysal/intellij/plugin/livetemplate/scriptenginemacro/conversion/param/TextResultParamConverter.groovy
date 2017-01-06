package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.Result
import com.intellij.codeInsight.template.TextResult

/**
 * @author seckinger
 * @since 10/24/16
 */
trait TextResultParamConverter implements ParamConverter {
    Object convert(final Result res) {
        res instanceof TextResult ?
                res.text :
                super.convert(res)
    }
}
