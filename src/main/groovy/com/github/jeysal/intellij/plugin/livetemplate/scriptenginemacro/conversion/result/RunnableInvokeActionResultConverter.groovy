package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.InvokeActionResult
import com.intellij.codeInsight.template.Result

import java.util.function.Function

/**
 * @author seckinger
 * @since 10/17/16
 */
trait RunnableInvokeActionResultConverter implements Function<Object, Result> {
    @Override
    Result apply(Object obj) {
        obj instanceof Runnable ?
                new InvokeActionResult(obj) :
                super.apply(obj)
    }
}
