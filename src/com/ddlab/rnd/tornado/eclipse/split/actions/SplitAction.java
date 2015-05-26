package com.ddlab.rnd.tornado.eclipse.split.actions;

import org.eclipse.e4.ui.model.application.ui.MElementContainer;
import org.eclipse.e4.ui.model.application.ui.MUIElement;
import org.eclipse.e4.ui.model.application.ui.basic.MBasicFactory;
import org.eclipse.e4.ui.model.application.ui.basic.MPart;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainer;
import org.eclipse.e4.ui.model.application.ui.basic.MPartSashContainerElement;
import org.eclipse.e4.ui.model.application.ui.basic.MPartStack;
import org.eclipse.e4.ui.workbench.modeling.EPartService;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

/**
 * This class is based upon the concept taken from akikhtenko, great eclipse
 * developer. I am very much thankful to akikhtenko. I am not using his code
 * for any commercial use. I have used his code as free and open source.
 * I got a lot of learning from his code.
 * 
 * https://github.com/akikhtenko/HandySplit
 * 
 * @author akikhtenko
 * @author Debadatta Mishra (PIKU)
 *
 */
public class SplitAction {

	private static final String HALF_SIZE = "5000";
	private static EPartService partService;

	public static void splitEditor() {

		IWorkbenchWindow wnd = PlatformUI.getWorkbench()
				.getActiveWorkbenchWindow();
		partService = (EPartService) wnd.getService(EPartService.class);
		MPart activeEditor = partService.getActivePart();

		MElementContainer<MUIElement> oldEditorStack = activeEditor.getParent();
		if (oldEditorStack.getChildren().size() < 2) {
			return;
		}

		MElementContainer<MUIElement> outerSash = oldEditorStack.getParent();

		MPartSashContainer splitSash = getSplitSashContainer();

		replace_in(outerSash, oldEditorStack, splitSash);

		MPartStack newEditorStack = createNewPartStack(oldEditorStack);

		splitSash.getChildren().add((MPartSashContainerElement) oldEditorStack);
		splitSash.getChildren().add(newEditorStack);

		resize(splitSash, oldEditorStack, newEditorStack);

		move_editor_into_new_stack(activeEditor, oldEditorStack, newEditorStack);

		activate(activeEditor);

	}

	private static void resize(MPartSashContainer splitSash,
			MElementContainer<MUIElement> oldEditorStack,
			MPartStack newEditorStack) {
		splitSash.setContainerData(oldEditorStack.getContainerData());
		newEditorStack.setContainerData(HALF_SIZE);
		oldEditorStack.setContainerData(HALF_SIZE);
	}

	private static void move_editor_into_new_stack(MPart activeEditor,
			MElementContainer<MUIElement> oldEditorStack, MPartStack newStack) {
		oldEditorStack.getChildren().remove(activeEditor);
		activate((MPart) oldEditorStack.getSelectedElement());
		newStack.getChildren().add(activeEditor);
	}

	private static void replace_in(MElementContainer<MUIElement> container,
			MUIElement old, MPartSashContainerElement nw) {
		int oldIndex = container.getChildren().indexOf(old);
		container.getChildren().remove(old);
		container.getChildren().add(oldIndex, nw);
	}

	private static MPartSashContainer getSplitSashContainer() {
		MPartSashContainer splitSash = MBasicFactory.INSTANCE
				.createPartSashContainer();
		splitSash.setHorizontal(true);
		return splitSash;
	}

	private static MPartStack createNewPartStack(
			MElementContainer<MUIElement> oldEditorStack) {
		MPartStack newStack = MBasicFactory.INSTANCE.createPartStack();
		newStack.getTags().addAll(oldEditorStack.getTags());
		return newStack;
	}

	private static void activate(MPart newPart) {
		partService.activate(newPart);
	}

}
