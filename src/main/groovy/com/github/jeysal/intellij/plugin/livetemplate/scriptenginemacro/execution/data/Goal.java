package com.github.jeysal.intellij.plugin.livetemplate.scriptenginemacro.execution.data;

import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.template.Result;

/**
 * Describes the goal of a script execution.
 * Currently two goals exist:
 * <ul>
 * <li>{@link #RESULT} indicates a {@link Result}
 * to insert into the editor is being calculated</li>
 * <li>{@link #LOOKUP_ELEMENTS} indicates a {@link LookupElement}[]
 * to show in a completion popup is being calculated</li>
 * </ul>
 *
 * @author seckinger
 * @since 10/24/16
 */
public enum Goal {
    RESULT, LOOKUP_ELEMENTS
}
