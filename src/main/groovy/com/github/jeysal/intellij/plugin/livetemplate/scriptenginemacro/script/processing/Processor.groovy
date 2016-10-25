package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.script.processing

/**
 * @author seckinger
 * @since 10/25/16
 */
interface Processor<T, U, R> {
    R apply(T t, U u)
}
