package com.yellowbrossproductions.illageandspillage.packet;

import com.yellowbrossproductions.illageandspillage.gui.overlay.JumpscareOverlay;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class JumpscareSyncPacket {


    public JumpscareSyncPacket() {
    }

    public static void encode(JumpscareSyncPacket msg, FriendlyByteBuf buf) {

    }

    public static JumpscareSyncPacket decode(FriendlyByteBuf buf) {
        return new JumpscareSyncPacket();
    }

    public static void handle(JumpscareSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> JumpscareOverlay.JUMPSCARE_OVERLAY.show());
        ctx.get().setPacketHandled(true);
    }
}