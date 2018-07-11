/*******************************************************************************
 * OSATE2-GTSE
 *
 * Copyright 2018 Carnegie Mellon University. All Rights Reserved.
 *
 * NO WARRANTY. THIS CARNEGIE MELLON UNIVERSITY AND SOFTWARE ENGINEERING INSTITUTE
 * MATERIAL IS FURNISHED ON AN "AS-IS" BASIS. CARNEGIE MELLON UNIVERSITY MAKES NO
 * WARRANTIES OF ANY KIND, EITHER EXPRESSED OR IMPLIED, AS TO ANY MATTER INCLUDING,
 * BUT NOT LIMITED TO, WARRANTY OF FITNESS FOR PURPOSE OR MERCHANTABILITY,
 * EXCLUSIVITY, OR RESULTS OBTAINED FROM USE OF THE MATERIAL. CARNEGIE MELLON
 * UNIVERSITY DOES NOT MAKE ANY WARRANTY OF ANY KIND WITH RESPECT TO FREEDOM FROM
 * PATENT, TRADEMARK, OR COPYRIGHT INFRINGEMENT.
 *
 * Released under an Eclipse Public License - v1.0-style license, please see
 * license.txt or contact permission@sei.cmu.edu for full terms.
 *
 * DM17-0002
 *******************************************************************************/

package org.osate.atsv.integration.annotation;

/**
 *
 * ATSV (since version 10.0.8) supports two additional types of configurators, added at SEI's request.
 * However, those additional configurators only work for choicepoints with floating point values.
 * Since much of the value in GTSE comes from specifying component selections that modify the validity
 * of other component selections, we require string values for these additional configurator types.
 *
 * To get around this limitation, GTSE will cache the strings to floating point values right before the
 * ATSV configuration file is generated, and then uncache the values when OSATE receives the request.
 * It's a sort of hacky workaround and has the undesirable side effect of making the ATSV graphs harder
 * to read, as some component names are replaced with floating point numbers. So, if ATSV supports
 * string comparisons for these more advanced configurators in the future, methods and fields tagged
 * with this interface should be removed.
 *
 * Any methods or fields tagged with this should be considered non-public, unstable, and may be removed
 * in future builds.
 *
 * @author sam
 *
 */
public @interface StringConfiguratorHack {

}
