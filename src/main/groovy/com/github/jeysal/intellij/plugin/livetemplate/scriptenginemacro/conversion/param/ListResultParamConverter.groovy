package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param

import com.intellij.codeInsight.template.ListResult
import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/24/16
 */
trait ListResultParamConverter implements ParamConverter {
    @Override
    Object convert(final Result res) {
        res instanceof ListResult ?
                res.components.collect(this.&convert) :
                super.convert(res)
    }
}
