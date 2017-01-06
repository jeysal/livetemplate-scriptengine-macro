package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements.LookupElementsConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.lookupelements.LookupElementsConverterImpl
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param.ParamConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.param.ParamConverterImpl
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result.ResultConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result.ResultConverterImpl
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.Executor
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.ExecutorImpl
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Context
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Goal
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing.ScriptProcessor
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing.ScriptProcessorImpl
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
    private static final PRESENTABLE_NAME = "$NAME($SCRIPT_PARAM_FORMAT, args...)"

    private final ScriptProcessor processor = new ScriptProcessorImpl()
    private final ParamConverter paramConv = new ParamConverterImpl()

    private final Executor executor = new ExecutorImpl()

    private final ResultConverter resConv = new ResultConverterImpl()
    private final LookupElementsConverter lookupElemConv = new LookupElementsConverterImpl()

    @Override
    String getName() {
        NAME
    }

    @Override
    String getPresentableName() {
        PRESENTABLE_NAME
    }

    @Override
    Result calculateResult(@NotNull final Expression[] params, final ExpressionContext context) {
        resConv.convert(calculate(params as List, context, Goal.RESULT))
    }

    @Override
    LookupElement[] calculateLookupItems(@NotNull final Expression[] params, final ExpressionContext context) {
        lookupElemConv.convert(calculate(params as List, context, Goal.LOOKUP_ELEMENTS))
    }

    private Object calculate(@NotNull final List<Expression> params, final ExpressionContext context, final Goal goal) {
        // process script param
        def script

        if (!params)
            return 'too few arguments'

        try {
            script = processor.process(paramConv.convert(params[0].calculateResult(context)))
        } catch (RuntimeException e) {
            return e.message
        }

        // exec
        final args = params.tail().collect { it.calculateResult(context) }.collect paramConv.&convert
        Execution exec = new Execution(script, new Context(args, goal, context.editor))

        executor.execute exec
    }
}
