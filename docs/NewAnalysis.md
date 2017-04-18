# Converting a new analysis to work with GTSE

Adding a new analysis to GTSE is fairly straightforward, but there are some minor "gotchas" that can slow things down. At a high level, in addition to a "base" (ie, non-GTSE-aware) analysis, you'll need to:

1. Create a GTSE-adapter plugin fragment specific to the analysis
2. Create a feature plugin specific to the analysis
3. Modify a number of existing files to reference the new plugin and feature.

## Creating a GTSE-Adapter Plugin Fragment

Here you'll create a new "adapter" class to automatically run the base analysis on a supplied instance model. Since this adapter class simply calls the base plugin, it is often very simple, sometimes less than 10 lines of actual logic.

1. Create a new plugin fragment using ``org.osate.atsv.integration`` as the host.
    * [Optional] Configure the other settings for the fragment:
        * ``ID``: ``org.osate.atsv.integration.plugin-name``
        * ``Version``: ``1.0.0.qualifier``
        * ``Name``: ``Plugin-Name``
2. Create a new class that extends ``org.osate.atsv.integration.AbstractAnalysis``
3. In your new class, you'll be required to create / specify the ``runAnalysis(...)`` method. The AbstractAnalysis javadoc may be a useful reference for this.
4. Using the ``org.osate.atsv.integration`` extension point, set the value of the ``AnalyzerClass`` field to the class you created in step 3.
5. In specifying the ``runAnalysis(...)`` method, you'll probably be prompted to add necessary dependencies to your fragment, but it's a good idea to verify that the analysis plugin you're adapting shows up in the new fragment's ``Require-Bundle`` clause.
6. Save the file.

## Creating a Feature Plugin

Since analysis plugins are individually installable, we'll create a feature project to expose the previously-created plugin fragment to users.

1. Create a new feature plugin.
    * It can be useful to align the properties of the feature with those specified for the GTSE-Adapter plugin.
2. Fill in the fields in the ``Information`` tab
    1. Under ``Feature Description``, briefly explain what the analysis does.
    2. Fill in the text areas of the ``Copyright Notice`` and ``License Agreement`` tabs.
3. On the ``Included Plug-ins`` tab add the GTSE-adapter plugin fragment you created in the previous task.
4. On the ``Included Features`` tab add the ``org.osate.atsv.integration.feature.base`` feature
5. On the ``Dependencies`` tab, click ``Compute`` to compute the feature's dependencies.
    * Also, it's a good idea to check the ``Recompute when feature plug-ins change`` box.
6. Save the file.

## Creating/Modifying supporting files

Most of the work is done, but you'll need to modify a few files to ensure that the new plugin is included in the automated building and testing processes.

### Adding automated tests

It's a good idea to test your new plugin, which can be done by adapting one of the existing test setup in the ``org.osate.atsv.integration.tests`` project.

1. Modify the AADL file in the ``src/test/resources`` directory to be analyzable by your new analysis.
2. Copy one of the existing test methods in ``org.osate.atsv.integration.tests.IntegrationTests`` and rename it appropriately.
3. Change the pluginid to match the id of your newly-created plugin
4. Add various asserts to verify your analysis plugin is generating correct values.
5. It's also a good idea to check that everything ran correctly, ie, check that no exceptions were thrown and the model is considered valid.

### Automating building with Maven Tycho

The existing Maven Tycho infrastructure is already set up, but you'll need to hook your new plugin into it.

1. Create a ``pom.xml`` file for the fragment.
    * Generally, you can copy a pom from another fragment in the project and only need to modify the ``<artifactId>...</artifactId>`` value to match the id of your project.
    * Note that the value of the ``<packaging>`` element should be ``eclipse-plugin``.
2. Create a ``pom.xml`` file for the feature.
    * Again, you should be able to copy/adapt the pom from another feature in the project and modify the ``<artifactId>``
    * Note that the value of the ``<packaging>`` element should be ``eclipse-feature``
3. Modify ``org.osate.atsv.integration.releng/pom.xml`` to reference both the new fragment and the new feature.
    * Each project should be referenced in a ``<module>...</module>`` line in the ``<modules>...</modules>`` section.

### Automating testing with Tycho Surefire

Finally, Eclipse is a little bit more intelligent than Tycho's surefire when setting up the test environment, so you'll need to modify a test-only feature to include your new feature.

1. In ``org.osate.atsv.integration.testfeature/feature.xml``, open the ``Included Plug-ins`` tab.
2. Add your new GTSE-adapter plug-in fragment.
