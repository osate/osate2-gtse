# Osate Guided Tradespace Exploration (GTSE)

This plugin enables users to automatically explore the tradespace of a system by connecting [OSATE](http://osate.org/) to Penn State's [Trade Space Visualizer](http://www.atsv.psu.edu/) tool.

## Installing the GTSE Plugin

1. [Download OSATE](http://aadl.info/aadl/osate/stable/latest/products/), and install it according to the [online instructions](http://osate.org/download-and-install.html).
2. Add the OSATE experimental update site by selecting:
    1. `Help`
    2. `Install New Software...`
    3. `Add...`
    4. Type `Osate Experimental` for the Name
    5. Copy/Paste `http://aadl.info/aadl/osate/experimental/` for the Location
    6. Click `OK`
3. Check the box next to `Guided Tradespace Exploration` to install all available analyzers, or expand the row and select those you want.
4. Click `Next >`
5. Click `Next >` again
6. Accept the License. The full license for this software is in [license.txt](https://github.com/osate/osate2-gtse/blob/master/license.txt).
    1. Select the radio button next to `I accept the terms of the license agreement`
    2. Click `Finish`
7. When the 'Security Warning' pops up, select `OK`
8. If prompted, check the box next to the Certificate to trust it, then select `OK`
9. When the 'Software Update' window asks to restart OSATE2, select `Yes`

## Installing ATSV

1. [Download ATSV](http://www.atsv.psu.edu/download.html) and extract it.

## Generating the ATSV Pre-requisites

1. Configure the GTSE Plugin.
    1. Open Eclipse's preferences (`Window`, `Preferences` on Linux/Windows, or `OSATE2`, `Preferences` on Mac OS X).
    2. Expand the `OSATE Preferences` item in the list on the left.
    3. Click on `ATSV Integration`.
    4. Each option can be left at its default, but the `Directory for ATSV Files` can be set to a more easily-accessed directory if need be.
    5. Click `OK`.
    6. If you made any changes, restart OSATE.
2. Create, or import, an GTSE-compatible project.
    * Example projects are available in the GTSE-Examples directory of this repository.
3. Select the *PackageName*.property file, where *PackageName* is the name of a package in this project.
    * In the GTSE-Examples project, you can select `SimpleComponentChoice.properties`, which contains choicepoint information for the `SimpleComponentChoice` package in `SimpleComponentChoice.aadl`.
4. Select `Sample Menu`, then select `Build ATSV-Jar`.
5. [Optional] Verify that the following files have been created in the directory you specified as part of step 1.4:
    * `ATSVConfig.ecf`
    * `connector.jar`
    * `input.txt`
    * `output.txt`
    * `parser.jar`
    * `request.properties`
    * `run.sh`

## Running ATSV

1. Launch OSATE.
2. Open the project containing the GTSE-compatible packages.
3. [Optional] Test the generated setup.
    1. Note the contents of `output.txt`.
    2. [Optional] Modify the contents of `input.txt`.
    3. Note the contents of `input.txt`.
    4. Run a single execution of the GTSE plugin by executing `run.sh` on Linux / Mac OS or `run.bat` on Windows.
4. Launch ATSV according to the [instructions](http://www.atsv.psu.edu/download.html):
    * Windows: Use the batch file in the base directory to start the application.
    * Mac OS and linux: Double click ATSV.jar in the /dist folder.
5. Load the generated engine configuration file into ATSV.
    1. Click `File`.
    2. Click `Link to Exploration Engine`.
    3. Verify `Link to existing engine configuration` is selected.
    4. Click `OK`.
    5. Navigate to and select the `ATSVConfig.ecf` file generated previously.
        * This file will be in the directory you set in the ATSV Integration preferences in OSATE.
    6. Click `Open`.
6. In the window titled `Engine Frame: CommandLineProblem`, click `Start`.
7. Wait for the runs to complete. Depending on model size, analyses selected, and number of runs, this may take a while.
8. In the window titled `Trade Space Visualizer`, click `Plots`.
9. Select the type of plot you would like to view.
