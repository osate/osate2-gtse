# The Configuration Language

## Introduction

A configuration specification defines which classifiers and property values are to be used during model instantiation 
in addition to declarations already present in the AADL model. For example, if a classifier in the AADL model declares a 
subcomponent as a component type, the configuration can be used to specify an implementation of this type to be inserted
into the instance model. If there are several such implementations, the configuration defines a choice point. Similarly, 
a configuration can add or override property values.

Additionally, a configuration specification can add constraints between components used at choice points, e.g., to express that
two chosen component implementations must be the same, or that one component implementation choice implies a constraint on the
choices for the implementations of another subcomponent.

Finally the configuration defines the set of analyses to run for evaluating the model in ATSV.

## File Format

Configuration specifications are stared as text files using the file extension `.config`. In the following sections we use
the following notation to specify the syntax of config files:

  * Keywords are enclosed in single quotes
  * Identifiers are in upper case
  * Optional elements are followed by a question mark
  * Repetition (one or more) is indicated with an asterisk
  * Parentheses are used to group elements

### Root Element

The first element in a config file is the name of the top level configuration that will be instantiated. This configuration must
be defined in the same file.

### Configuration Definitions

The syntax for a configuration definition is as follows:

```
'configuration' NAME Parameters? ('extends' CNAME With? )? 
(
   '{'
      Assignments? 
   '}'
)?
```

Each configuration starts with the keyword `configuration` and must have a name. The name is a simple identifier, i.e., it starts with a 
letter and consists of digits and letters only. It must be unique in the file.

A configuration may have parameters.

Most configurations will be for a specific AADL component classifier. The `extends` clause creates this association to an AADL 
classifier. The classifier name (CNAME) must be a fully qualified name, i.e., of the form 
`package_name::component_type_name.implementation_name`.

Using a `with` clause an existing configurations can be extended, or multiple configurations can be combined into one. 

The body of a configuration is a comma separated list of assignments, where each assignment assigns a component implementation to a subcomponent or
a value to a property. The list may be empty.

A configuration C that extends a classifier CL is itself a classifier with the same category as CL. In principle, if C is not parameterized,
C could be translated into an AADL classifier that extends CL, adds property associations, and refines its subcomponents. 


#### Configuration Assignments

Each configuration assignment applies to a context element in the instance model. For an assignment that is directly contained in a configuration 
the context object is either the root object of the instance model or the subcomponent instance to which the configuration is applied to. 
Assignments are evaluated relative to this context element.

A configuration assignment can have several forms.

 * Property value assignment
   
   A property value assignment can add (or override) a property association to the context object using the syntax `#PNAME '=>' PropertyValue`.
   The property name (PNAME) must be fully qualified, i.e., of the form `property_set_name::property_name`.
   
   A property value assignment can also add a property to an AADL element contained in the context element. In this case the left hand side
   must be prefixed with the path from the context element to the contained element similar to the applies to clause in a contained property association in AADL:
   `NamedElementPath#PNAME '=>' PropertyValue`.
   For example, the assignment `sub.input#PS::P => 1` adds a property with the given value to a port named 'input' in a subcomponent named 'sub' in
   the context object.
   
 * Classifier assignment
 
   A classifier assignment can be used to refine a subcomponent declaration in an AADL model. It takes the form `NamedElementPath '=>' CNAME`. The path
   must refer to a subcomponent contained in the context element. The classifier must implement the subcomponent's type from the AADL model.
   The assigned classifier is used to instantiate the subcomponent.
 
 * Configuration assignment
 
   Similar to a classifier assignment the right hand side of the assignment can refer to a named configuration of a component implementation:
   `NamedElementPath '=>' ID`, where `ID` is the name of a configuration in the current file. The configuration must extend the subcomponent's classifier
   from the AADL model.
   
 * Nested assignment
   A nested assignment has the form
   
   ```
   NamedElementPath '=>' '{' 
       Assignments?
   '}'
   ```
  
   The AADL instance object identified by the path becomes the context element for the nested assignments.

#### Extending a Configuration

An existing configuration `CF` for a component implementation `C.i` can be extended as follows:

```
configuration CFExt extends C.i with CF {
	Assignments?
}
```

The assignments in `CFExt` can either be new assignments or override assignments provided by
configuration `CF`. If there are no assignments in `CFExt` it is identical to `CF`. In general the extended configuration must be a configuration for either `C.i` or a classifier that is extended by `C.i` (an _ancestor_ of `C.i`).

#### Combining Multiple Configurations

Multiple configurations can be combined into a single configuration. This takes the form

```
configuration CFComb extends C.i with CF1, CF2, ..., CFn 
(
	'{'
		Assignments?
	'}'
)?
```

Each combined configuration must be applicable to `C.i`, i.e., extend `C.i` or an ancestor of `C.i`.

The combination is translated into a list of assignments as follows:

 1. The list of configurations is reordered such that 
 	1. each configuration extends only configurations that occur to the right of it and
 	2. configurations that have no common ancestor configurations remain in the order in which they are written.
 2. For each assignment that occurs in a configuration all assignments to the same element that occur to the right are discarded.

Configuration combination is useful for a number of purposes. We describe two scenarios:
 1. A system should be configured with properties for safety analysis (CF1) and timing analysis (CF2). The combination of these configuration results
    with properties for both kinds of analysis.
 2. We create independent configurations for a system where each configuration refines a different sub-system. The combination, then, contains refinements
    for all these subsystems. 

The declaration of a combined configuration may also provide a list of explicit assignments. These override conflicting assignments in the combination.
Explicit assignments are effectively an extension of a combined configuration.
 	 
#### Parameters and Choice Points

A configuration can be parameterized. This allows classifiers and property values to be supplied when the configuration is used. Each parameter has 
a classifier or a property type. The actual parameter value must match this type.

In addition, a parameter be constrained to a set of valid values. The provided value must be in that set. If a configuration is used without specifying 
a value for some parameter this parameter is a choice point. The GATSE tooling automatically chooses values from the valid choices to evaluate
alternative model configurations.

The parameters are declared after the configuration name.

```
'('
	NAME ':' (CLassifierType | PropertyType) Choices?
')'
```

where the (optional) list of choices has the form

```
'from' '(' ConfigurationValue (',' ConfigrationValue)* ')'
```

each ConfigurationValue has the same form as the right hand side of an assignment. In particular, an allowed value may be a configuration for the
declared classifier type.

When using a parameterized configuration, parameter values are assigned as named parameters. The language does not support positional parameters.

```
'(' NAME '=>' ConfigurationValue (',' NAME '=>' ConfigurationValue)* ')'
```

#### Anonymous (In-line) Configurations

Wherever a configuration name can be used, it is also legal to write just a configuration definition. This is useful if this configuration is used
only in a single place. Note that core AADL 2.2 does not allow something like this.

For example, the following two configurations

```
configuration CF1 extends P::C.i with CCF1, CCF2 {
	#prop => value;
}

configuration CTop {
	sub => CF1
}
```

can be written as

```
configuration CTop {
	sub => P::C.i with CCF1, CCF2 {
		#prop => value
	}
}
```

Note that nested assignments can also be interpreted as a form of in-line configuration.

### Constraint Definitions

A configuration can be followed by a list of constraints on the selection of choices for parameter values to express that values are interdependent and
cannot be chosen independent of each other. The language supports the following constraints:

 * `p1 == p2` - the two parameters must have the same value
 * `p1 != p2` - the two parameters must have different values
 * `p1 == X requires p2 == Y ` - if p1 is X, p2 must be Y
 * `p1 == X forbids  p2 == Y ` - if p1 is X, p2 must be Y
 * `p1 == X requires p2 in {Y, Z} ` - if p1 is X, p2 must be Y
 * `p1 == X forbids  p2 in {Y, Z} ` - if p1 is X, p2 must be Y

The constraints are written as a comma separated list after a configuration:

```
'constraints' '('
	Constraint (',' Constraint)*
')'

```

### Analysis Definitions

The next section in a config file lists the analyses that should be run on the generated instance models.

```
'analyses' '{'
	PluginID (',' PluginID)?
'}'
```

An analysis is identified by the ID of the plugin (a string) that executes the analysis.

### Output Declarations

The final section defines the output variables that should be used by ATSV. An numeric output variable can have a limit
on acceptable values. 

```
'outputs' '{'
	NAME ':' Type Limit?
	(',' NAME ':' Type Limit? )*
'}'
```

The names of the output variables depend on the analyses which are executed. The type can be int, float, or string. 

A limit specifies that the value must be equal (==), not equal (!=), greater(<), 
greater or equal (>=), less or equal (<=), or less (<) than a given numeric value.

## Limitations

All the definitions for an instance model must be defined in the same config file. It is currently not possible to import
or reference definitions from other files.

The implementation performs only limited validation of the config file.
