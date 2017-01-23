package com.dmtar.randomid.actions;

import com.dmtar.randomid.handlers.ElementIdGeneratorHandler;
import com.intellij.openapi.editor.actionSystem.EditorAction;
import com.intellij.openapi.editor.actionSystem.EditorActionHandler;

/**
 *
 */
public class GenerateElementIdentifiers extends EditorAction {

    public GenerateElementIdentifiers(EditorActionHandler defaultHandler) {
        super(defaultHandler);
    }

    public GenerateElementIdentifiers() {
        super(new ElementIdGeneratorHandler());
    }
}
