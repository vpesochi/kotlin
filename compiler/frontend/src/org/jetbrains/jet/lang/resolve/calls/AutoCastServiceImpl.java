package org.jetbrains.jet.lang.resolve.calls;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.jet.lang.resolve.BindingContext;
import org.jetbrains.jet.lang.resolve.scopes.receivers.ReceiverDescriptor;
import org.jetbrains.jet.lang.types.DataFlowInfo;

import java.util.List;

/**
* @author abreslav
*/
public class AutoCastServiceImpl implements AutoCastService {
    private final DataFlowInfo dataFlowInfo;
    private final BindingContext bindingContext;

    AutoCastServiceImpl(DataFlowInfo dataFlowInfo, BindingContext bindingContext) {
        this.dataFlowInfo = dataFlowInfo;
        this.bindingContext = bindingContext;
    }

    @Override
    public List<ReceiverDescriptor> getVariantsForReceiver(ReceiverDescriptor receiverDescriptor) {
        return AutoCastUtils.getAutoCastVariants(bindingContext, dataFlowInfo, receiverDescriptor);
    }

    @Override
    public DataFlowInfo getDataFlowInfo() {
        return dataFlowInfo;
    }

    @Override
    public boolean isNotNull(@NotNull ReceiverDescriptor receiver) {
        if (!receiver.getType().isNullable()) return true;

        List<ReceiverDescriptor> autoCastVariants = AutoCastUtils.getAutoCastVariants(bindingContext, dataFlowInfo, receiver);
        for (ReceiverDescriptor autoCastVariant : autoCastVariants) {
            if (!autoCastVariant.getType().isNullable()) return true;
        }
        return false;
    }
}