package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic.BaseStreamConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic.IterableConverter
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.generic.IteratorConverter

/**
 * @author seckinger
 * @since 10/17/16
 */
class ResultConverter extends DefaultResultConverter implements
        BaseStreamConverter,
        IterableConverter,
        IteratorConverter,
        RunnableInvokeActionResultConverter,
        SpliteratorResultConverter {
}
