package com.willfp.eco.proxy.v1_15_R1;

import com.willfp.eco.proxy.proxies.ChatComponentProxy;
import org.jetbrains.annotations.NotNull;

public final class ChatComponent implements ChatComponentProxy {
    @Override
    public Object modifyComponent(@NotNull final Object object) {
        return object;
    }
}
