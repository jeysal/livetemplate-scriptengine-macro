package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache

import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data.Execution
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data.CacheKey
import com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data.CacheValue
import com.google.common.cache.Cache
import groovy.transform.TupleConstructor

/**
 * @author seckinger
 * @since 1/9/17
 */
@TupleConstructor
class CacheAccessorImpl implements CacheAccessor {
    Cache<CacheKey, CacheValue> cache
    Execution execution

    @Override
    <T> T store(T val, Object... meta) {
        cache.put createKey(meta), new CacheValue(val)
        val
    }

    @Override
    Optional<Object> load(Object... meta) {
        Optional.ofNullable(cache.getIfPresent(createKey(meta))).map { it.val }
    }

    private createKey(Object... meta) {
        new CacheKey(execution.script, meta as List ?: [execution.context])
    }
}
