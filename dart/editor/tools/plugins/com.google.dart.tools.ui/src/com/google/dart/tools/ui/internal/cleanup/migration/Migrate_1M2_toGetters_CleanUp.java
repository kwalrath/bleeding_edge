/*
 * Copyright (c) 2012, the Dart project authors.
 * 
 * Licensed under the Eclipse Public License v1.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 * 
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */
package com.google.dart.tools.ui.internal.cleanup.migration;

import com.google.dart.compiler.ast.ASTVisitor;
import com.google.dart.compiler.ast.DartClass;
import com.google.dart.compiler.ast.DartExpression;
import com.google.dart.compiler.ast.DartIdentifier;
import com.google.dart.compiler.ast.DartMethodDefinition;
import com.google.dart.compiler.ast.DartMethodInvocation;
import com.google.dart.compiler.resolver.ClassElement;
import com.google.dart.compiler.resolver.Elements;
import com.google.dart.compiler.type.InterfaceType;
import com.google.dart.compiler.type.Type;
import com.google.dart.tools.core.utilities.general.SourceRangeFactory;

/**
 * In 1.0 M2 library many methods become getters.
 * 
 * @coverage dart.editor.ui.cleanup
 */
public class Migrate_1M2_toGetters_CleanUp extends AbstractMigrateCleanUp {
  private static class MethodSpec {
    private final String libUri;
    private final String className;
    private final String methodName;

    public MethodSpec(String libUri, String className, String methodName) {
      this.libUri = libUri;
      this.className = className;
      this.methodName = methodName;
    }
  }

  private static final MethodSpec[] METHODS = new MethodSpec[] {
      new MethodSpec("dart://core/core.dart", "Object", "hashCode"),
      new MethodSpec(null, "_JustForInternalTest", "foo")};

  /**
   * @return <code>true</code> if given {@link InterfaceType} is sub type of required type from
   *         library with required name.
   */
  private static boolean isSubType(InterfaceType type, String requiredName, String requiredLib) {
    if (type == null) {
      return false;
    }
    ClassElement element = type.getElement();
    if (element.getName().equals(requiredName)
        && (requiredLib == null || element.getLibrary().getName().equals(requiredLib))) {
      return true;
    }
    if (isSubType(element.getSupertype(), requiredName, requiredLib)) {
      return true;
    }
    for (InterfaceType intf : element.getInterfaces()) {
      if (isSubType(intf, requiredName, requiredLib)) {
        return true;
      }
    }
    return false;
  }

  @Override
  protected void createFix() {
    unitNode.accept(new ASTVisitor<Void>() {
      @Override
      public Void visitMethodDefinition(DartMethodDefinition node) {
        if (!node.getModifiers().isGetter() && node.getFunction().getParameters().isEmpty()
            && node.getParent() instanceof DartClass) {
          DartClass parentClass = (DartClass) node.getParent();
          for (MethodSpec spec : METHODS) {
            DartExpression nameNode = node.getName();
            if (Elements.isIdentifierName(nameNode, spec.methodName)) {
              InterfaceType parentType = parentClass.getElement().getType();
              if (isSubType(parentType, spec.className, spec.libUri)) {
                addReplaceEdit(SourceRangeFactory.forStartLength(nameNode, 0), "get ");
                addReplaceEdit(SourceRangeFactory.forEndEnd(
                    nameNode,
                    node.getFunction().getParametersCloseParen() + 1), "");
                break;
              }
            }
          }
        }
        return super.visitMethodDefinition(node);
      }

      @Override
      public Void visitMethodInvocation(DartMethodInvocation node) {
        for (MethodSpec spec : METHODS) {
          DartIdentifier nameNode = node.getFunctionName();
          if (node.getArguments().isEmpty() && Elements.isIdentifierName(nameNode, spec.methodName)) {
            if (node.getTarget() != null) {
              Type targetType = node.getTarget().getType();
              if (targetType instanceof InterfaceType) {
                if (isSubType((InterfaceType) targetType, spec.className, spec.libUri)) {
                  addReplaceEdit(SourceRangeFactory.forEndEnd(nameNode, node), "");
                }
              }
            }
          }
        }
        return super.visitMethodInvocation(node);
      }
    });
  }
}