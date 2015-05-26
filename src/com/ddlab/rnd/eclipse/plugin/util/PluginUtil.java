package com.ddlab.rnd.eclipse.plugin.util;

import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;

import com.ddlab.util.images.SplitScreenImages;

/**Utility class for the plugin.
 * @author <a href="mailto:debadatta.mishra@gmail.com"> Debadatta Mishra (PIKU)
 *
 */
public class PluginUtil {

	public static Image getSplitImage() {
		Image image = new Image(Display.getCurrent(),
				SplitScreenImages.class.getResourceAsStream("editorspilit.png"));
		return image;
	}

}
