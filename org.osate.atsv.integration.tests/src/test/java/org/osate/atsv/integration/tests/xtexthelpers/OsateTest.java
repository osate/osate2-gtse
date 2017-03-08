package org.osate.atsv.integration.tests.xtexthelpers;

import com.google.common.base.Objects;
import com.google.common.collect.Iterables;
import com.google.inject.Inject;
import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import org.apache.log4j.Logger;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.IncrementalProjectBuilder;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Assert;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.jobs.IJobManager;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.xtext.diagnostics.Severity;
import org.eclipse.xtext.resource.IEObjectDescription;
import org.eclipse.xtext.scoping.IScopeProvider;
import org.eclipse.xtext.serializer.tokens.SerializerScopeProviderBinding;
import org.eclipse.xtext.validation.Issue;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Conversions;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Functions.Function1;
import org.eclipse.xtext.xbase.lib.IterableExtensions;
import org.eclipse.xtext.xbase.lib.ListExtensions;
import org.eclipse.xtext.xbase.lib.Pair;
import org.eclipselabs.xtext.utils.unittesting.FluentIssueCollection;
import org.eclipselabs.xtext.utils.unittesting.XtextTest;
import org.junit.After;
import org.junit.Before;
import org.junit.ComparisonFailure;
import org.osate.aadl2.ModelUnit;
import org.osate.aadl2.modelsupport.resources.PredeclaredProperties;
import org.osate.aadl2.modelsupport.util.AadlUtil;
import org.osate.core.AadlNature;

/**
 * Add a couple of utility methods for managing files in the test workspace
 */
@SuppressWarnings("all")
public abstract class OsateTest extends XtextTest {
  @Inject
  private IScopeProvider scopeProvider;
  
  @Inject
  @SerializerScopeProviderBinding
  private IScopeProvider serializerScopeProvider;
  
  private final static Logger LOGGER = Logger.getLogger(OsateTest.class);
  
  protected final IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();
  
  private Set<String> pluginResourcesNames;
  
  @Before
  public void setUp() {
    this.createProject(this.getProjectName());
    this.buildProject("Plugin_Resources", true);
    String _projectName = this.getProjectName();
    String _plus = ("platform:/resource/" + _projectName);
    this.setResourceRoot(_plus);
  }
  
  @After
  public void cleanUp() {
    this.deleteProject(this.getProjectName());
  }
  
  public abstract String getProjectName();
  
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
              PredeclaredProperties.initPluginContributedAadl();
              final IProject plugin_resources = OsateTest.this.getPluginResources();
              boolean _exists = project.exists();
              boolean _not = (!_exists);
              if (_not) {
                project.create(monitor);
                project.open(monitor);
                final IProjectDescription description = project.getDescription();
                description.setNatureIds(new String[] { "org.eclipse.xtext.ui.shared.xtextNature", AadlNature.ID });
                description.setReferencedProjects(new IProject[] { plugin_resources });
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
    this.getPluginResources();
    final IProject project = this.workspaceRoot.getProject(name);
    Assert.isTrue(project.exists(), (("Project " + name) + " does not exist in the workspace"));
    this.buildProject(project, wait);
  }
  
  /**
   * Build a given project. Optionally wait until the build is done.
   */
  public void buildProject(final IProject project, final boolean wait) {
    this.getPluginResources();
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
  
  /**
   * Check if plugin resources exists, wait up to 10s. Return project.
   */
  public IProject getPluginResources() {
    try {
      IProject _xblockexpression = null;
      {
        final IProject project = this.workspaceRoot.getProject(PredeclaredProperties.PLUGIN_RESOURCES_PROJECT_NAME);
        int i = 0;
        for (; ((!project.exists()) && (i < 20)); i++) {
          Thread.sleep(500);
        }
        Assert.isTrue(project.exists(), 
          (("Project " + PredeclaredProperties.PLUGIN_RESOURCES_PROJECT_NAME) + " does not exist in the workspace"));
        _xblockexpression = project;
      }
      return _xblockexpression;
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Create a set of files in the workspace given as filename -> text pairs
   */
  protected void createFiles(final Pair<String, String>... models) {
    try {
      final WorkspaceModifyOperation operation = new WorkspaceModifyOperation() {
        @Override
        public void execute(final IProgressMonitor monitor) {
          for (final Pair<String, String> model : models) {
            {
              String _key = model.getKey();
              String _plus = ((OsateTest.this.resourceRoot + "/") + _key);
              final URI uri = URI.createURI(_plus);
              OsateTest.this.createFile(uri, model.getValue());
            }
          }
        }
      };
      operation.run(null);
      this.waitForBuild();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public IFile createFile(final URI uri, final String content) {
    IFile _xblockexpression = null;
    {
      String _platformString = uri.toPlatformString(true);
      Path _path = new Path(_platformString);
      final IFile file = this.workspaceRoot.getFile(_path);
      Assert.isTrue(file.getParent().exists());
      String _simpleName = this.getClass().getSimpleName();
      String _plus = ((("creating " + uri) + " in test method ") + _simpleName);
      String _plus_1 = (_plus + ".");
      String _methodName = new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName();
      String _plus_2 = (_plus_1 + _methodName);
      OsateTest.LOGGER.info(_plus_2);
      try {
        byte[] _bytes = content.getBytes(file.getCharset());
        final ByteArrayInputStream stream = new ByteArrayInputStream(_bytes);
        boolean _exists = file.exists();
        if (_exists) {
          file.setContents(stream, true, true, null);
        } else {
          file.create(stream, true, null);
        }
        stream.close();
      } catch (final Throwable _t) {
        if (_t instanceof Exception) {
          final Exception e = (Exception)_t;
          OsateTest.LOGGER.error(e);
        } else {
          throw Exceptions.sneakyThrow(_t);
        }
      }
      _xblockexpression = file;
    }
    return _xblockexpression;
  }
  
  public void waitForBuild() {
    final IJobManager jobManager = Job.getJobManager();
    try {
      jobManager.join(ResourcesPlugin.FAMILY_AUTO_BUILD, null);
      jobManager.join(ResourcesPlugin.FAMILY_MANUAL_BUILD, null);
    } catch (final Throwable _t) {
      if (_t instanceof InterruptedException) {
        final InterruptedException e = (InterruptedException)_t;
        this.waitForBuild();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
  }
  
  protected static void assertError(final EObject eObject, final List<Issue> allIssues, final FluentIssueCollection issueCollection, final String... expectedMessages) {
    OsateTest.assertIssue(eObject, allIssues, issueCollection, Severity.ERROR, expectedMessages);
  }
  
  protected static void assertWarning(final EObject eObject, final List<Issue> allIssues, final FluentIssueCollection issueCollection, final String... expectedMessages) {
    OsateTest.assertIssue(eObject, allIssues, issueCollection, Severity.WARNING, expectedMessages);
  }
  
  protected static void assertIssue(final EObject eObject, final List<Issue> allIssues, final FluentIssueCollection issueCollection, final Severity severity, final String... expectedMessages) {
    final Function1<Issue, Boolean> _function = (Issue it) -> {
      return Boolean.valueOf((Objects.equal(it.getSeverity(), severity) && Objects.equal(it.getUriToProblem(), EcoreUtil.getURI(eObject))));
    };
    final Iterable<Issue> issuesForEObject = IterableExtensions.<Issue>filter(allIssues, _function);
    final Function1<Issue, String> _function_1 = (Issue it) -> {
      return it.getMessage();
    };
    final Iterable<String> messagesForEObject = IterableExtensions.<Issue, String>map(issuesForEObject, _function_1);
    Set<String> _set = IterableExtensions.<String>toSet(messagesForEObject);
    Set<String> _set_1 = IterableExtensions.<String>toSet(((Iterable<String>)Conversions.doWrapArray(expectedMessages)));
    boolean _notEquals = (!Objects.equal(_set, _set_1));
    if (_notEquals) {
      String _join = IterableExtensions.join(((Iterable<?>)Conversions.doWrapArray(expectedMessages)), "\n");
      String _join_1 = IterableExtensions.join(messagesForEObject, "\n");
      throw new ComparisonFailure("", _join, _join_1);
    }
    final Consumer<Issue> _function_2 = (Issue it) -> {
      issueCollection.addIssue(it);
    };
    issuesForEObject.forEach(_function_2);
  }
  
  protected void assertScope(final EObject context, final EReference reference, final Iterable<String> expected) {
    this.assertScope(this.scopeProvider, context, reference, false, expected);
  }
  
  protected void assertScopeModelUnitNamesOnly(final EObject context, final EReference reference, final Iterable<String> expected) {
    this.assertScope(this.scopeProvider, context, reference, true, expected);
  }
  
  protected void assertSerializerScope(final EObject context, final EReference reference, final Iterable<String> expected) {
    this.assertScope(this.serializerScopeProvider, context, reference, false, expected);
  }
  
  private void assertScope(final IScopeProvider scopeProvider, final EObject context, final EReference reference, final boolean scopingForModelUnits, final Iterable<String> expected) {
    if ((this.pluginResourcesNames == null)) {
      final Function1<IFile, Boolean> _function = (IFile it) -> {
        return Boolean.valueOf(it.getName().toLowerCase().endsWith(".aadl"));
      };
      final Function1<IFile, String> _function_1 = (IFile it) -> {
        String _xblockexpression = null;
        {
          final URI uri = URI.createPlatformResourceURI(it.getFullPath().toString(), false);
          EObject _head = IterableExtensions.<EObject>head(context.eResource().getResourceSet().getResource(uri, true).getContents());
          final ModelUnit modelUnit = ((ModelUnit) _head);
          _xblockexpression = modelUnit.getName().toLowerCase();
        }
        return _xblockexpression;
      };
      this.pluginResourcesNames = IterableExtensions.<String>toSet(IterableExtensions.<IFile, String>map(IterableExtensions.<IFile>filter(Iterables.<IFile>filter(OsateTest.getAllMembers(this.getPluginResources()), IFile.class), _function), _function_1));
    }
    final String expectedNames = IterableExtensions.join(IterableExtensions.<String>sortWith(expected, OsateTest.CUSTOM_NAME_COMPARATOR), ", ");
    final Function1<IEObjectDescription, String> _function_2 = (IEObjectDescription it) -> {
      return it.getName().toString("::");
    };
    final Function1<String, Boolean> _function_3 = (String it) -> {
      boolean _xblockexpression = false;
      {
        final int separatorIndex = it.lastIndexOf("::");
        String _xifexpression = null;
        if ((scopingForModelUnits || (separatorIndex == (-1)))) {
          _xifexpression = it;
        } else {
          _xifexpression = it.substring(0, separatorIndex);
        }
        final String modelUnitName = _xifexpression;
        _xblockexpression = (AadlUtil.isPredeclaredPropertySet(modelUnitName) || (!this.pluginResourcesNames.contains(modelUnitName.toLowerCase())));
      }
      return Boolean.valueOf(_xblockexpression);
    };
    final String actualNames = IterableExtensions.join(IterableExtensions.<String>sortWith(IterableExtensions.<String>filter(IterableExtensions.<IEObjectDescription, String>map(scopeProvider.getScope(context, reference).getAllElements(), _function_2), _function_3), OsateTest.CUSTOM_NAME_COMPARATOR), ", ");
    org.junit.Assert.assertEquals(expectedNames, actualNames);
  }
  
  private static Iterable<IResource> getAllMembers(final IContainer container) {
    try {
      final Function1<IResource, Iterable<IResource>> _function = (IResource it) -> {
        Iterable<IResource> _xifexpression = null;
        if ((it instanceof IContainer)) {
          _xifexpression = OsateTest.getAllMembers(((IContainer)it));
        } else {
          _xifexpression = Collections.<IResource>unmodifiableList(CollectionLiterals.<IResource>newArrayList(it));
        }
        return _xifexpression;
      };
      return Iterables.<IResource>concat(ListExtensions.<IResource, Iterable<IResource>>map(((List<IResource>)Conversions.doWrapArray(container.members())), _function));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  /**
   * Compares two aadl names such that simple names are less than qualified names.
   * If the name is qualified then names in predeclared property sets are greater than names in other packages or property sets.
   * 
   * Example: "id" < "ps::id" < "Memory_Properties::Heap_Size"
   */
  private final static Comparator<String> CUSTOM_NAME_COMPARATOR = new Comparator<String>() {
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
