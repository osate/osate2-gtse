package org.osate.atsv.integration.preparser;

import org.eclipse.core.runtime.IProgressMonitor;
import org.osate.aadl2.modelsupport.modeltraversal.AadlProcessingSwitchWithProgress;

/**
 * This class walks over the declarative model of an AADL element in order to 
 * configure the ATSV-adapter jar. 
 * 
 * @author sprocter
 *
 */
public class Preparser extends AadlProcessingSwitchWithProgress {

	public Preparser(final IProgressMonitor monitor) {
		super(monitor, PROCESS_PRE_ORDER_ALL);
	}

	@Override
	protected final void initSwitches() {
		aadl2Switch = new PreparseSwitch();
	}

}
