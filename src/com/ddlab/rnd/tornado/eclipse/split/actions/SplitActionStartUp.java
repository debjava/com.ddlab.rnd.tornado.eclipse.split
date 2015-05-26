package com.ddlab.rnd.tornado.eclipse.split.actions;

import org.eclipse.jface.action.StatusLineManager;
import org.eclipse.ui.IStartup;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.internal.WorkbenchWindow;

/**
 * This class is used as a startup in eclipse platoform.
 * @author <a href="mailto:debadatta.mishra@gmail.com"> Debadatta Mishra (PIKU)
 * @since 2013
 * 
 */
@SuppressWarnings("restriction")
public class SplitActionStartUp implements IStartup {

	/**
	 * Method to initialize the start up action
	 */
	public void init() {
		try {
			final IWorkbench workbench = PlatformUI.getWorkbench();
			workbench.getDisplay().asyncExec(new Runnable() {
				public void run() {
					final IWorkbenchWindow window = workbench
							.getActiveWorkbenchWindow();
					if (window != null) {
						StatusLineManager sm = ((WorkbenchWindow) PlatformUI
								.getWorkbench().getActiveWorkbenchWindow())
								.getStatusLineManager();
						sm.add(new SplitContributionItem("SPLIT"));
						sm.update(true);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see org.eclipse.ui.IStartup#earlyStartup()
	 */
	@Override
	public void earlyStartup() {
		init();
	}
}
