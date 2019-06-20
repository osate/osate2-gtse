# GATSE Examples

This directory contains example systems for GATSE. Right now, the only example is the Wheel Braking System, which is used in the MODELS19 GATSE paper. 

## The Wheel Brake System

Explaining the Wheel Braking System is beyond the scope of this readme, but interested readers are referred to the sources cited in the MODELS19 paper:

* [ARP4754: Certification Considerations for Highly-Integrated or Complex Aircraft Systems](https://saemobilus.sae.org/content/arp4754)
* [ARP4761: Guidelines and Methods for Conducting the Safety Assessment Process on Civil Airborne Systems and Equipment](https://saemobilus.sae.org/content/arp4761)
* [Formal Design and Safety Analysis of AIR6110 Wheel Brake System](https://link.springer.com/chapter/10.1007/978-3-319-21690-4_36)
* [Architectural Modeling and Analysis for Safety Engineering](https://link.springer.com/chapter/10.1007/978-3-319-64119-5_7)

### Running the Example

1. OSATE, ATSV, and GATSE should be downloaded / installed per [the instructions](../readme.md) in this project's root
2. Import the "GTSE-Examples" project into the GATSE-enabled OSATE. The wheel brake system is in the "ARP4761" directory.
3. Continue following [the instructions](../readme.md), using the file `arp4761.config`.