package com.yellowbrossproductions.illageandspillage.packet;

import com.yellowbrossproductions.illageandspillage.capability.WebbedProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class WebbedSyncPacket {
    private final int entityId;
    private final boolean isWebbed;

    public WebbedSyncPacket(int entityId, boolean isWebbed) {
        this.entityId = entityId;
        this.isWebbed = isWebbed;
    }

    public static void encode(WebbedSyncPacket msg, FriendlyByteBuf buf) {
        buf.writeInt(msg.entityId);
        buf.writeBoolean(msg.isWebbed);
    }

    public static WebbedSyncPacket decode(FriendlyByteBuf buf) {
        return new WebbedSyncPacket(buf.readInt(), buf.readBoolean());
    }

    public static void handle(WebbedSyncPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            if (Minecraft.getInstance().level != null) {
                Entity entity = Minecraft.getInstance().level.getEntity(msg.entityId);
                if (entity != null) {
                    entity.getCapability(WebbedProvider.WEBBED_CAPABILITY).ifPresent(webbed -> webbed.setWebbed(msg.isWebbed));
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }
}