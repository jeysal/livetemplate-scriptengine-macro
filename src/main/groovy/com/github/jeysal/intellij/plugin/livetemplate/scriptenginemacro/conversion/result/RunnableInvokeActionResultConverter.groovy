package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.Converter
import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/17/16
 */
trait RunnableInvokeActionResultConverter implements Converter<Object, Result> {
    @Override
    Result apply(Object obj) {
        obj instanceof Runnable ?
                new InvokeActionResult(obj) :
                super.apply(obj)
    }
}
