package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data;

import com.intellij.openapi.editor.Editor;
import lombok.Value;
import lombok.experimental.Wither;

import java.util.List;

/**
 * Holds the dynamic information for the current script execution context,
 * i.e. information likely to change every time the macro is evaluated.
 *
 * @author seckinger
 * @since 10/24/16
 */
@Value
@Wither
public class Context {
    List args;
    Goal goal;
    Editor editor;
}
