package com.chariotsolutions.jshepard.annotation.processing;

import com.sun.source.tree.Tree;
import com.sun.tools.javac.processing.JavacProcessingEnvironment;
import com.sun.tools.javac.tree.JCTree;
import com.sun.tools.javac.tree.TreeMaker;
import com.sun.tools.javac.tree.TreeTranslator;

public class ChangeTranslator extends TreeTranslator {
  private final CreateStatement createStatement;

  public ChangeTranslator(JavacProcessingEnvironment javacProcessingEnvironment, TreeMaker treeMaker) {
    createStatement = new CreateStatement(new GetElement(javacProcessingEnvironment), treeMaker);
  }

  @Override
  public void visitVarDef(JCTree.JCVariableDecl variableDeclaration) {
    super.visitVarDef(variableDeclaration);
    if (isChangeAnnotation(variableDeclaration.getModifiers()) && needsTranslation(variableDeclaration)) {
      // result is placed into the AST, replacing the current variable declaration.
      result = createStatement.apply(variableDeclaration);
    }
  }

  /**
   * Only change declarations that are method calls.
   */
  private boolean needsTranslation(JCTree.JCVariableDecl variableDeclaration) {
    return variableDeclaration.getInitializer().getKind() == Tree.Kind.METHOD_INVOCATION;
  }

  private boolean isChangeAnnotation(JCTree.JCModifiers modifiers) {
    return modifiers.toString().contains("@Change()");
  }
}
