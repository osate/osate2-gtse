/**
 * Copyright (c) 2004-2020 Carnegie Mellon University and others. (see Contributors file).
 * All Rights Reserved.
 * 
 * NO WARRANTY. ALL MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO WARRANTIES OF ANY
 * KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING, BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE
 * OR MERCHANTABILITY, EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON UNIVERSITY DOES NOT
 * MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 * 
 * This program and the accompanying materials are made available under the terms of the Eclipse Public License 2.0
 * which is available at https://www.eclipse.org/legal/epl-2.0/
 * SPDX-License-Identifier: EPL-2.0
 * 
 * Created, in part, with funding and support from the United States Government. (see Acknowledgments file).
 * 
 * This program includes and/or can make use of certain third party source code, object code, documentation and other
 * files ("Third Party Software"). The Third Party Software that is used by this program is dependent upon your system
 * configuration. By using this program, You agree to comply with any and all relevant Third Party Software terms and
 * conditions contained in any such Third Party Software or separate license file distributed with such Third Party
 * Software. The parties who own the Third Party Software ("Third Party Licensors") are intended third party benefici-
 * aries to this license with respect to the terms applicable to their Third Party Software. Third Party Software li-
 * censes only apply to the Third Party Software and not any other portion of this program or this program as a whole.
 */
package org.osate.atsv.integration.tests;

import com.google.common.base.Objects;
import com.google.inject.Inject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Comparator;
import java.util.List;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.serializer.tokens.SerializerScopeProviderBinding;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.junit.After;
import org.junit.Before;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.core.AadlNature;
import org.osate.pluginsupport.PluginSupportUtil;

/**
 * Add a couple of utility methods for managing files in the test workspace
 * 
 * @deprecated Will be removed in 2.7.
 */
@Deprecated
@SuppressWarnings("all")
public abstract class OsateTest /* implements XtextTest  */{
  @Inject
  private IScopeProvider scopeProvider;
  
  @Inject
  @SerializerScopeProviderBinding
  private IScopeProvider serializerScopeProvider;
  
  private static final Logger LOGGER = Logger.getLogger(OsateTest.class);
  
  protected final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
  
  @Before
  public Object setUp() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method setResourceRoot(String) is undefined");
  }
  
  @After
  public void cleanUp() {
    this.deleteProject(this.getProjectName());
  }
  
  public String getProjectName() {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field class is undefined"
      + "\nsimpleName cannot be resolved");
  }
  
  /**
   * Create a project with subdirectories in the current workspace.
   */
  public IProject createProject(final String projectName, final String... srcDirs) {
    try {
      IProject _xblockexpression = null;
      {
        final IProject project = this.workspaceRoot.getProject(projectName);
        final WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
          @Override
          public void execute(final IProgressMonitor monitor) {
            try {
              boolean _exists = project.exists();
              boolean _not = (!_exists);
              if (_not) {
                project.create(monitor);
                project.open(monitor);
                final IProjectDescription description = project.getDescription();
                description.setNatureIds(new String[] { "org.eclipse.xtext.ui.shared.xtextNature", AadlNature.ID });
                project.setDescription(description, monitor);
                for (final String srcDir : srcDirs) {
                  {
                    final IFolder src = project.getFolder(srcDir);
                    src.create(true, true, monitor);
                  }
                }
              }
            } catch (Throwable _e) {
              throw Exceptions.sneakyThrow(_e);
            }
          }
        };
        operation.run(null);
        _xblockexpression = project;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void deleteProject(final String projectName) {
    try {
      final WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
        @Override
        public void execute(final IProgressMonitor monitor) {
          try {
            OsateTest.this.workspaceRoot.getProject(projectName).delete(true, true, null);
          } catch (Throwable _e) {
            throw Exceptions.sneakyThrow(_e);
          }
        }
      };
      operation.run(null);
      this.waitForBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Build the named project. Optionally wait until the build is done.
   */
  public void buildProject(final String name, final boolean wait) {
    final IProject project = this.workspaceRoot.getProject(name);
    Assert.isTrue(project.exists(), (("Project " + name) + " does not exist in the workspace"));
    this.buildProject(project, wait);
  }
  
  /**
   * Build a given project. Optionally wait until the build is done.
   */
  public void buildProject(final IProject project, final boolean wait) {
    try {
      project.build(IncrementalProjectBuilder.CLEAN_BUILD, null);
    } catch (final Throwable _t) {
      if (_t instanceof CoreException) {
        final CoreException e = (CoreException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    if (wait) {
      this.waitForBuild();
    }
  }
  
  protected String readFile(final String path) {
    String _xblockexpression = null;
    {
      String result = "";
      try {
        final URL url = new URL(("platform:/plugin/" + path));
        final InputStream inputStream = url.openConnection().getInputStream();
        InputStreamReader _inputStreamReader = new InputStreamReader(inputStream);
        final BufferedReader in = new BufferedReader(_inputStreamReader);
        String inputLine = null;
        while (((inputLine = in.readLine()) != null)) {
          String _result = result;
          result = (_result + (inputLine + "\n"));
        }
        in.close();
      } catch (final Throwable _t) {
        if (_t instanceof IOException) {
          result = "";
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      _xblockexpression = result;
    }
    return _xblockexpression;
  }
  
  /**
   * Create a set of files in the workspace given as filename -> text pairs
   */
  protected void createFiles(final Pair<String, String>... models) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field resourceRoot is undefined"
      + "\n+ cannot be resolved"
      + "\n+ cannot be resolved");
  }
  
  public IFile createFile(final URI uri, final String content) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method or field class is undefined for the type OsateTest"
      + "\nsimpleName cannot be resolved");
  }
  
  public void waitForBuild() {
    final IJobManager jobManager = Job.getJobManager();
    try {
      jobManager.join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
      jobManager.join(ResourcesPlugin.FAMILY_MANUAL_BUILD, null);
    } catch (final Throwable _t) {
      if (_t instanceof InterruptedException) {
        this.waitForBuild();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  /**
   * @deprecated Use {@link AssertHelper#assertError()}
   */
  @Deprecated
  protected static void assertError(final EObject eObject, final List<Issue> allIssues, final /* FluentIssueCollection */Object issueCollection, final String... expectedMessages) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method assertIssue(EObject, List<Issue>, FluentIssueCollection, Severity, String...) from the type OsateTest refers to the missing type FluentIssueCollection");
  }
  
  /**
   * @deprecated Use {@link AssertHelper#assertWarning()}
   */
  @Deprecated
  protected static void assertWarning(final EObject eObject, final List<Issue> allIssues, final /* FluentIssueCollection */Object issueCollection, final String... expectedMessages) {
    throw new Error("Unresolved compilation problems:"
      + "\nThe method assertIssue(EObject, List<Issue>, FluentIssueCollection, Severity, String...) from the type OsateTest refers to the missing type FluentIssueCollection");
  }
  
  /**
   * @deprecated Use {@link AssertHelper#assertIssue()}
   */
  @Deprecated
  protected static void assertIssue(final EObject eObject, final List<Issue> allIssues, final /* FluentIssueCollection */Object issueCollection, final Severity severity, final String... expectedMessages) {
    throw new Error("Unresolved compilation problems:"
      + "\naddIssue cannot be resolved");
  }
  
  /**
   * @deprecated Use {@link AssertHelper#assertScope()}
   */
  @Deprecated
  protected void assertScope(final EObject context, final EReference reference, final Iterable<String> expected) {
    this.assertScope(this.scopeProvider, context, reference, expected);
  }
  
  /**
   * @deprecated Use {@link AssertHelper#assertSerializerScope()}
   */
  @Deprecated
  protected void assertSerializerScope(final EObject context, final EReference reference, final Iterable<String> expected) {
    this.assertScope(this.serializerScopeProvider, context, reference, expected);
  }
  
  @Deprecated
  private void assertScope(final IScopeProvider scopeProvider, final EObject context, final EReference reference, final Iterable<String> expected) {
    final String expectedNames = IterableExtensions.join(IterableExtensions.<String>sortWith(expected, OsateTest.CUSTOM_NAME_COMPARATOR), ", ");
    final Function1<IEObjectDescription, Boolean> _function = (IEObjectDescription eObjectDescription) -> {
      boolean _xblockexpression = false;
      {
        final URI resourceURI = eObjectDescription.getEObjectURI().trimFragment();
        final String fileName = IterableExtensions.<String>last(((Iterable<String>)Conversions.doWrapArray(resourceURI.trimFileExtension().segments())));
        _xblockexpression = ((!PluginSupportUtil.getContributedAadl().contains(resourceURI)) || AadlUtil.isPredeclaredPropertySet(fileName));
      }
      return Boolean.valueOf(_xblockexpression);
    };
    final Iterable<IEObjectDescription> actual = IterableExtensions.<IEObjectDescription>filter(scopeProvider.getScope(context, reference).getAllElements(), _function);
    final Function1<IEObjectDescription, String> _function_1 = (IEObjectDescription it) -> {
      return it.getName().toString("::");
    };
    final String actualNames = IterableExtensions.join(IterableExtensions.<String>sortWith(IterableExtensions.<IEObjectDescription, String>map(actual, _function_1), OsateTest.CUSTOM_NAME_COMPARATOR), ", ");
    org.junit.Assert.assertEquals(expectedNames, actualNames);
  }
  
  /**
   * @deprecated Use {@link AssertHelper#CUSTOM_NAME_COMPARATOR}
   */
  @Deprecated
  private static final Comparator<String> CUSTOM_NAME_COMPARATOR = new Comparator<String>() {
    @Override
    public int compare(final String o1, final String o2) {
      int _xblockexpression = (int) 0;
      {
        final int o1SeparatorIndex = o1.indexOf("::");
        final int o2SeparatorIndex = o2.indexOf("::");
        int _xifexpression = (int) 0;
        if (((o1SeparatorIndex == (-1)) && (o2SeparatorIndex == (-1)))) {
          _xifexpression = o1.compareTo(o2);
        } else {
          int _xifexpression_1 = (int) 0;
          if ((o1SeparatorIndex == (-1))) {
            _xifexpression_1 = (-1);
          } else {
            int _xifexpression_2 = (int) 0;
            if ((o2SeparatorIndex == (-1))) {
              _xifexpression_2 = 1;
            } else {
              int _xblockexpression_1 = (int) 0;
              {
                final boolean o1PsIsPredeclared = AadlUtil.isPredeclaredPropertySet(o1.substring(0, o1SeparatorIndex));
                final boolean o2PsIsPredeclared = AadlUtil.isPredeclaredPropertySet(o2.substring(0, o2SeparatorIndex));
                int _xifexpression_3 = (int) 0;
                if ((o1PsIsPredeclared == o2PsIsPredeclared)) {
                  _xifexpression_3 = o1.compareTo(o2);
                } else {
                  int _xifexpression_4 = (int) 0;
                  if (o2PsIsPredeclared) {
                    _xifexpression_4 = (-1);
                  } else {
                    _xifexpression_4 = 1;
                  }
                  _xifexpression_3 = _xifexpression_4;
                }
                _xblockexpression_1 = _xifexpression_3;
              }
              _xifexpression_2 = _xblockexpression_1;
            }
            _xifexpression_1 = _xifexpression_2;
          }
          _xifexpression = _xifexpression_1;
        }
        _xblockexpression = _xifexpression;
      }
      return _xblockexpression;
    }
    
    @Override
    public boolean equals(final Object obj) {
      Class<? extends Comparator<String>> _class = this.getClass();
      Class<?> _class_1 = obj.getClass();
      return Objects.equal(_class, _class_1);
    }
  };
}
