package com.ddlab.rnd.tornado.eclipse.split.actions;

import org.eclipse.jface.action.ContributionItem;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;

import com.ddlab.rnd.eclipse.plugin.util.PluginUtil;

/**
 * This class is used as an eclipse contribution item in the status line
 * 
 * @author <a href="mailto:debadatta.mishra@gmail.com"> Debadatta Mishra (PIKU)
 *
 */
public class SplitContributionItem extends ContributionItem {

	private Button splitBtn = null;

	/**
	 * @param id
	 */
	public SplitContributionItem(String id) {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.eclipse.jface.action.ContributionItem#fill(org.eclipse.swt.widgets
	 * .Composite)
	 */
	public void fill(final Composite parent) {

		splitBtn = new Button(parent, SWT.NONE);
		splitBtn.setToolTipText("Click here to split editor");
		ImageDescriptor ids = ImageDescriptor.createFromImage(PluginUtil
				.getSplitImage());
		splitBtn.setImage(ids.createImage());
		splitBtn.addSelectionListener(new SelectionAdapter() {

			@Override
			public void widgetSelected(SelectionEvent e) {

				SplitAction.splitEditor();
			}

		});

	}

}