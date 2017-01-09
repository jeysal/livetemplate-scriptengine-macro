package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache;

import java.util.Optional;

/**
 * @author seckinger
 * @since 1/9/17
 */
public interface CacheAccessor {
    <T> T store(T val, Object... meta);

    Optional<Object> load(Object... meta);
}
