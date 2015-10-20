package com.chariotsolutions.jshepard.annotation.processing;

import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.util.Name;

/**
 * Create a new JCVariableDecl instance.
 */
public class CreateStatement {
  private final GetElement getElement;
  private final TreeMaker treeMaker;

  public CreateStatement(GetElement getElement, TreeMaker treeMaker) {
    this.getElement = getElement;
    this.treeMaker = treeMaker;
  }

  public JCTree.JCVariableDecl apply(JCTree.JCVariableDecl variableDeclaration) {
    // For this simple example, change the type to a String.
    JCTree.JCExpression stringType = treeMaker.Ident(getElement.apply(String.class));
    // Use the same variable name.
    Name variableName = variableDeclaration.getName();
    // Use a String literal.
    JCTree.JCLiteral changedValue = treeMaker.Literal("Changed");
    // Remove the modifiers.
    JCTree.JCModifiers modifiers = treeMaker.Modifiers(0);
    // Create the new variable declaration.
    JCTree.JCVariableDecl newVariableDeclaration = treeMaker.VarDef(modifiers, variableName, stringType, changedValue);
    return newVariableDeclaration;
  }
}
