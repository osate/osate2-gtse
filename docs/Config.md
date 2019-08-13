# The Configuration Language

## Introduction

A configuration specification defines which classifiers and property values are to be used during model instantiation 
in addition to declarations already present in the AADL model. For example, if a classifier in the AADL model declares a 
subcomponent as a component type, the configuration can be used to specify an implementation of this type to be inserted
into the instance model. If there are several such implementations, the configuration defines a choice point. Similarly, 
a configuration can add or override property values.

Additionally, a configuration specification can add constraints between components used at choice points, e.g., to express that
two chosen comonent implementations must be the same, or that one component implementation choice implies a constraint on the
choices for the implementations of another subcomponent.

Finally the configuration defines the set of analyses to run for evaluating the model in ATSV.

## File format

Configuration specifications are stared as text files using the file extension `.config`.

### Root Element

The first element in a config file is the name of the top level configuration that will be instantiated. This configuration must
be defined in the same file.

###

## Limitations

All configuration definitions for an instance model must be defined in the same config file. There is no means implemented to
import definitions from other files.
