package org.vaadin.tltv.vprocjs.gwt.client.ui;

import java.util.Map;

public interface ProcessingCode extends ProcessingCodeMethods {

    void setProcessing(VProcessing vprocessing,
            Map<Object, Object> sharedVariables);

    Map<Object, Object> getSharedVariables();
}
