package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements.LookupElementsConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param.ParamConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result.ResultConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.Executor
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Goal
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing.ScriptProcessor
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.template.Expression
import com.intellij.codeInsight.template.ExpressionContext
import com.intellij.codeInsight.template.Macro
import com.intellij.codeInsight.template.Result
import org.jetbrains.annotations.NotNull

/**
 * The central macro class registered with the IntelliJ IDEA platform.
 * Delegates more complex individual tasks to the processor, converters, executor, ...
 *
 * @author seckinger
 * @since 11/29/16
 */
class ScriptEngineMacro extends Macro {
    private static final NAME = 'script'
    private static final SCRIPT_PARAM_FORMAT = '"path or language:code"'

    private final ScriptProcessor processor = new ScriptProcessor()
    private final ParamConverter paramConv = new ParamConverter()

    private final Executor exec = new Executor()

    private final ResultConverter resConv = new ResultConverter()
    private final LookupElementsConverter lookupElemConv = new LookupElementsConverter()

    @Override
    String getName() {
        NAME
    }

    @Override
    String getPresentableName() {
        "$NAME($SCRIPT_PARAM_FORMAT, args...)"
    }

    @Override
    Result calculateResult(@NotNull final Expression[] params, final ExpressionContext context) {
        resConv.apply(calculate(params, context, Goal.RESULT))
    }

    @Override
    LookupElement[] calculateLookupItems(@NotNull final Expression[] params, final ExpressionContext context) {
        lookupElemConv.apply(calculate(params, context, Goal.LOOKUP_ELEMENTS))
    }

    private Object calculate(@NotNull final Expression[] params, final ExpressionContext context, final Goal goal) {
        null
    }
}
