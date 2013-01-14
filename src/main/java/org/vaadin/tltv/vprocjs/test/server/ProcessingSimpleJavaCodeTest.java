package org.vaadin.tltv.vprocjs.test.server;

import org.vaadin.tltv.vprocjs.component.Processing;
import org.vaadin.tltv.vprocjs.gwt.client.ui.test.ProcessingCodeImplTest;

import com.vaadin.ui.Label;
import com.vaadin.ui.VerticalLayout;

/**
 * Sample to show how the Processing component can be used to create Processing
 * sketch by its own Java API.
 * 
 * @author Tltv
 * 
 */
public class ProcessingSimpleJavaCodeTest extends VerticalLayout {

    public ProcessingSimpleJavaCodeTest() {
        Processing pro = new Processing();
        CustomProcessingCodeExtension codeExtension = new CustomProcessingCodeExtension();
        codeExtension.extend(pro);

        final Label codeLabel = new Label();

        codeExtension.setProcessingJavaCodeClass(ProcessingCodeImplTest.class
                .getName());
        codeLabel.setValue(ProcessingCodeImplTest.class.getName());

        addComponent(pro);
        addComponent(codeLabel);
    }
}
