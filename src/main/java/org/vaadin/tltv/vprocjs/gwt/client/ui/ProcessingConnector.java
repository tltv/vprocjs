package org.vaadin.tltv.vprocjs.gwt.client.ui;

import org.vaadin.tltv.vprocjs.component.Processing;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.Widget;
import com.vaadin.client.communication.RpcProxy;
import com.vaadin.client.communication.StateChangeEvent;
import com.vaadin.client.ui.AbstractComponentConnector;
import com.vaadin.shared.ui.Connect;

@Connect(Processing.class)
public class ProcessingConnector extends AbstractComponentConnector {

    ProcessingServerRpc rpc;
    ProcessingEventServerRpc eventrpc;

    public ProcessingConnector() {
        rpc = RpcProxy.create(ProcessingServerRpc.class, this);
        eventrpc = RpcProxy.create(ProcessingEventServerRpc.class, this);
    }

    @Override
    protected Widget createWidget() {
        VProcessing widget = GWT.create(VProcessing.class);
        widget.setRpc(rpc);
        widget.setEventRpc(eventrpc);
        return widget;
    }

    @Override
    public void onStateChanged(StateChangeEvent stateChangeEvent) {
        super.onStateChanged(stateChangeEvent);

        // TODO use @DelegateToWidget("setSomething")
        ProcessingState state = getState();
        VProcessing widget = getWidget();
        widget.setUId(getConnectorId());
        widget.setProcessingCode(getState().getProcessingCode());
        widget.setMouseClickListened(state.isMouseClickListened());
        widget.setMouseEnterListened(state.isMouseEnterListened());
        widget.setMouseLeaveListened(state.isMouseLeaveListened());
        widget.setMousePressListened(state.isMousePressListened());
        widget.setMouseReleaseListened(state.isMouseReleaseListened());
        widget.setMouseWheelListened(state.isMouseWheelListened());
        widget.setKeyPressListened(state.isKeyPressListened());
        widget.setKeyReleaseListened(state.isKeyReleaseListened());
        widget.stateChanged();
    }

    @Override
    public VProcessing getWidget() {
        return (VProcessing) super.getWidget();
    }

    @Override
    public ProcessingState getState() {
        return (ProcessingState) super.getState();
    }
}
