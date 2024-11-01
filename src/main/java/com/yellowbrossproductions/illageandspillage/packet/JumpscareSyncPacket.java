package com.yellowbrossproductions.illageandspillage.packet;

import com.yellowbrossproductions.illageandspillage.gui.overlay.JumpscareOverlay;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class JumpscareSyncPacket {
    private final boolean showJumpscare;

    public JumpscareSyncPacket(boolean showJumpscare) {
        this.showJumpscare = showJumpscare;
    }

    public static void encode(JumpscareSyncPacket msg, FriendlyByteBuf buf) {
        buf.writeBoolean(msg.showJumpscare);
    }

    public static JumpscareSyncPacket decode(FriendlyByteBuf buf) {
        return new JumpscareSyncPacket(buf.readBoolean());
    }

    public static void handle(JumpscareSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (msg.showJumpscare) {
                JumpscareOverlay.JUMPSCARE_OVERLAY.show();
            }
        });
        ctx.get().setPacketHandled(true);
    }
}