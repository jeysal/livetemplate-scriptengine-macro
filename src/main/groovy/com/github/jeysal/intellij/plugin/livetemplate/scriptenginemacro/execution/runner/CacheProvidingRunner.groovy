package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.CacheAccessorImpl
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data.CacheKey
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data.CacheValue
import com.google.common.cache.Cache
import com.google.common.cache.CacheBuilder

import javax.script.ScriptEngine
import java.util.concurrent.TimeUnit

/**
 * @author seckinger
 * @since 1/7/17
 */
trait CacheProvidingRunner implements Runner {
    private Cache<CacheKey, CacheValue> cache = CacheBuilder.newBuilder().expireAfterWrite(10, TimeUnit.MINUTES).build()

    @Override
    Object run(Execution execution, ScriptEngine scriptEngine) {
        final accessor = new CacheAccessorImpl(cache, execution)
        scriptEngine.put '_cache', accessor

        accessor.load().orElseGet { super.run execution, scriptEngine }
    }
}
