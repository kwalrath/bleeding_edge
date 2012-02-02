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
package com.google.dart.tools.debug.core.dartium;

import com.google.dart.tools.debug.core.DartDebugCorePlugin;

import org.eclipse.debug.core.model.DebugElement;
import org.eclipse.debug.core.model.IDebugTarget;

/**
 * The abstract super class of the Dartiun debug elements. This provides common functionality like
 * access to the debug target and ILaunch object, as well as making sure that all Dartium debug
 * elements return the return DartDebugCorePlugin.DEBUG_MODEL_ID debug model identifier.
 */
public abstract class DartiumDebugElement extends DebugElement {

  /**
   * Create a new dartium debug element
   * 
   * @param target
   */
  public DartiumDebugElement(IDebugTarget target) {
    super(target);

  }

  @Override
  public String getModelIdentifier() {
    return DartDebugCorePlugin.DEBUG_MODEL_ID;
  }

  /**
   * @return the DartiumDebugTarget for this element
   */
  protected DartiumDebugTarget getTarget() {
    return (DartiumDebugTarget) getDebugTarget();
  }
}
