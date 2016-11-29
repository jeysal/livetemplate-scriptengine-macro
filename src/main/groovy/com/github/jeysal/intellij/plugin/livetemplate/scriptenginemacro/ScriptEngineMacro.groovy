package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro

import com.intellij.codeInsight.template.*
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

    @Override
    String getName() {
        NAME
    }

    @Override
    String getPresentableName() {
        "$NAME($SCRIPT_PARAM_FORMAT, args...)"
    }

    @Override
    Result calculateResult(@NotNull Expression[] params, ExpressionContext context) {
        new TextResult('')
    }
}
