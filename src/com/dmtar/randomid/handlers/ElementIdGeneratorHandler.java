package com.dmtar.randomid.handlers;

import com.intellij.openapi.actionSystem.DataContext;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.editor.actionSystem.EditorWriteActionHandler;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Handler for the key combination shortcut.
 * Extracts the marked text in the Editor and builds DOM document out of the partial.
 */
public class ElementIdGeneratorHandler extends EditorWriteActionHandler {

    private static final Logger LOGGER = Logger.getLogger(ElementIdGeneratorHandler.class.getName());

    @Override
    public void executeWriteAction(Editor editor, Caret caret, DataContext dataContext) {
        LOGGER.log(Level.INFO, "HTML elements identifiers generation started");
        Document document = editor.getDocument();
        if (!document.isWritable()) {
            LOGGER.log(Level.SEVERE, "File NOT writable");
            return;
        }
        SelectionModel selectionModel = editor.getSelectionModel();
        if (!selectionModel.hasSelection()) {
            LOGGER.log(Level.INFO, "Editor contents selection empty");
            return;
        }
        //Get the marked text in the Editor
        String selectedText = selectionModel.getSelectedText();
        LOGGER.log(Level.INFO, "Current editor selection: " + selectedText);
    }
}
