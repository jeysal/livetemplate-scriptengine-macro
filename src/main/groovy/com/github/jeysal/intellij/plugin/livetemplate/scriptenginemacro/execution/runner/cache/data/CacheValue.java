package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.runner.cache.data;

import lombok.Value;
import lombok.experimental.Wither;

/**
 * @author seckinger
 * @since 1/9/17
 */
@Value
@Wither
public class CacheValue {
    Object val;
}
