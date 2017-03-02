package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.conversion.result

import com.intellij.codeInsight.template.Result

/**
 * @author seckinger
 * @since 10/17/16
 */
trait IdentityResultConverter implements ResultConverter<Result> {
    @Override
    Result convert(final Result res) {
        res
    }
}
