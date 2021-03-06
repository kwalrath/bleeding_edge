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
package com.google.dart.tools.debug.ui.launch;

import com.google.dart.tools.debug.core.DartDebugCorePlugin;
import com.google.dart.tools.debug.core.DartLaunchConfigWrapper;
import com.google.dart.tools.debug.ui.internal.DartDebugUIPlugin;
import com.google.dart.tools.debug.ui.internal.util.LaunchUtils;

import org.eclipse.core.resources.IResource;
import org.eclipse.debug.core.ILaunchConfiguration;
import org.eclipse.debug.core.ILaunchConfigurationWorkingCopy;
import org.eclipse.debug.core.ILaunchManager;
import org.eclipse.debug.ui.DebugUITools;
import org.eclipse.debug.ui.ILaunchShortcut;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.ui.IWorkbenchWindow;

import java.util.List;

/**
 * Launch in Dartium
 */
public class RunInDartiumAction extends DartAbstractAction {

  public RunInDartiumAction() {
    this(null);
  }

  public RunInDartiumAction(IWorkbenchWindow window) {
    this(window, false);
  }

  public RunInDartiumAction(IWorkbenchWindow window, boolean noMenu) {
    super(window, "Run in Dartium", noMenu ? IAction.AS_PUSH_BUTTON : IAction.AS_DROP_DOWN_MENU);

    setActionDefinitionId("com.google.dart.tools.debug.ui.run.dartium");
    setImageDescriptor(DartDebugUIPlugin.getImageDescriptor("obj16/run_exc.gif"));
  }

  @Override
  public void run() {
    IResource resource = LaunchUtils.getSelectedResource(window);
    try {
      if (resource != null) {

        List<ILaunchConfiguration> launchConfigs = LaunchUtils.getExistingLaunchesFor(resource);
        for (ILaunchConfiguration config : launchConfigs) {
          if (config.getType().getIdentifier().equals(DartDebugCorePlugin.DARTIUM_LAUNCH_CONFIG_ID)) {
            ILaunchConfigurationWorkingCopy configCopy = config.copy(LaunchUtils.DARTIUM_LAUNCH_NAME);
            DartLaunchConfigWrapper launchConfig = new DartLaunchConfigWrapper(config);
            launchConfig.markAsLaunched();
            LaunchUtils.clearDartiumConsoles();
            DebugUITools.launch(configCopy, ILaunchManager.DEBUG_MODE);
            return;
          }
        }
        // new launch config
        ILaunchShortcut shortcut = LaunchUtils.getDartiumLaunchShortcut();
        ISelection selection = new StructuredSelection(resource);
        launch(shortcut, selection);

      }
    } catch (Exception e) {
      // TODO: handle exception
    }
  }
}
