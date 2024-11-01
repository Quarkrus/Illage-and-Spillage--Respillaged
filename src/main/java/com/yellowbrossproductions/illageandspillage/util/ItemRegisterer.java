package com.yellowbrossproductions.illageandspillage.util;

import com.yellowbrossproductions.illageandspillage.init.ModEntityTypes;
import com.yellowbrossproductions.illageandspillage.items.BagOfHorrorsItemBase;
import com.yellowbrossproductions.illageandspillage.items.SpellboundBookItemBase;
import com.yellowbrossproductions.illageandspillage.items.TotemOfBanishmentItemBase;
import com.yellowbrossproductions.illageandspillage.items.NothingItemBase;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegisterer {
    public static final DeferredRegister<Item> ITEMS;
    public static final RegistryObject<Item> TOTEM_OF_BANISHMENT;
    public static final RegistryObject<Item> SPELLBOUND_BOOK;
    public static final RegistryObject<Item> BAG_OF_HORRORS;
    public static final RegistryObject<Item> MAGISPELLER_DISC;
    public static final RegistryObject<Item> SPIRITCALLER_DISC;
    public static final RegistryObject<Item> FREAKAGER_DISC;
    public static final RegistryObject<Item> IGNITER_SPAWN_EGG;
    public static final RegistryObject<Item> ENGINEER_SPAWN_EGG;
    public static final RegistryObject<Item> TWITTOLLAGER_SPAWN_EGG;
    public static final RegistryObject<Item> PRESERVER_SPAWN_EGG;
    public static final RegistryObject<Item> ABSORBER_SPAWN_EGG;
    public static final RegistryObject<Item> CROCOFANG_SPAWN_EGG;
    public static final RegistryObject<Item> MAGISPELLER_SPAWN_EGG;
    public static final RegistryObject<Item> SPIRITCALLER_SPAWN_EGG;
    public static final RegistryObject<Item> FREAKAGER_SPAWN_EGG;
    public static final RegistryObject<Item> RAGNO_SPAWN_EGG;
    public static final RegistryObject<Item> FUNNYBONE_SPAWN_EGG;
    public static final RegistryObject<Item> EYESORE_SPAWN_EGG;
    public static final RegistryObject<Item> ILLASHOOTER_SPAWN_EGG;
    public static final RegistryObject<Item> TREAT1;
    public static final RegistryObject<Item> TREAT2;
    public static final RegistryObject<Item> TREAT3;
    public static final RegistryObject<Item> TREAT4;
    public static final RegistryObject<Item> TREAT5;
    public static final RegistryObject<Item> TREAT6;
    public static final RegistryObject<Item> DARK_DRINK;
    public static final RegistryObject<Item> DARK_SPLASH;
    public static final RegistryObject<Item> DARK_LINGER;
    public static final RegistryObject<Item> LOGO;
    public static final RegistryObject<Item> INVALID;

    public static void itemInit() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
    }

    static {
        ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, "illageandspillage");
        TOTEM_OF_BANISHMENT = ITEMS.register("totem_of_banishment", TotemOfBanishmentItemBase::new);
        SPELLBOUND_BOOK = ITEMS.register("spellbound_book", SpellboundBookItemBase::new);
        BAG_OF_HORRORS = ITEMS.register("bag_of_horrors", BagOfHorrorsItemBase::new);
        MAGISPELLER_DISC = ITEMS.register("magi_disc", () -> new RecordItem(6, IllageAndSpillageSoundEvents.MAGI_DISC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 1722));
        SPIRITCALLER_DISC = ITEMS.register("spiri_disc", () -> new RecordItem(6, IllageAndSpillageSoundEvents.SPIRI_DISC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 2782));
        FREAKAGER_DISC = ITEMS.register("freaky_disc", () -> new RecordItem(6, IllageAndSpillageSoundEvents.FREAKY_DISC, new Item.Properties().stacksTo(1).rarity(Rarity.RARE), 5487));
        IGNITER_SPAWN_EGG = ITEMS.register("igniter_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Igniter, 1315860, 9804699, new Item.Properties()));
        ENGINEER_SPAWN_EGG = ITEMS.register("engineer_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Engineer, 0x8e9393, 0xfed93f, new Item.Properties()));
        TWITTOLLAGER_SPAWN_EGG = ITEMS.register("twittollager_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Twittollager, 9224763, 15592941, new Item.Properties()));
        PRESERVER_SPAWN_EGG = ITEMS.register("preserver_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Preserver, 13480456, 9804699, new Item.Properties()));
        ABSORBER_SPAWN_EGG = ITEMS.register("absorber_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Absorber, 8148798, 9804699, new Item.Properties()));
        CROCOFANG_SPAWN_EGG = ITEMS.register("crocofang_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Crocofang, 6315866, 14407360, new Item.Properties()));
        MAGISPELLER_SPAWN_EGG = ITEMS.register("magispeller_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Magispeller, 3407872, 9804699, new Item.Properties()));
        SPIRITCALLER_SPAWN_EGG = ITEMS.register("spiritcaller_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Spiritcaller, 11232972, 6445926, new Item.Properties()));
        FREAKAGER_SPAWN_EGG = ITEMS.register("freakager_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Freakager, 2565927, 0xdcdcdc, new Item.Properties()));
        RAGNO_SPAWN_EGG = ITEMS.register("ragno_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Ragno, 7500402, 5855577, new Item.Properties()));
        FUNNYBONE_SPAWN_EGG = ITEMS.register("funnybone_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Funnybone, 0x658363, 0xb3d2b1, new Item.Properties()));
        EYESORE_SPAWN_EGG = ITEMS.register("eyesore_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Eyesore, 0xc20000, 0xb9a48a, new Item.Properties()));
        ILLASHOOTER_SPAWN_EGG = ITEMS.register("illashooter_spawn_egg", () -> new ForgeSpawnEggItem(ModEntityTypes.Illashooter, 16775294, 16775294, new Item.Properties()));
        TREAT1 = ITEMS.register("treat1", NothingItemBase::new);
        TREAT2 = ITEMS.register("treat2", NothingItemBase::new);
        TREAT3 = ITEMS.register("treat3", NothingItemBase::new);
        TREAT4 = ITEMS.register("treat4", NothingItemBase::new);
        TREAT5 = ITEMS.register("treat5", NothingItemBase::new);
        TREAT6 = ITEMS.register("treat6", NothingItemBase::new);
        DARK_DRINK = ITEMS.register("dark_drink", NothingItemBase::new);
        DARK_SPLASH = ITEMS.register("dark_splash", NothingItemBase::new);
        DARK_LINGER = ITEMS.register("dark_linger", NothingItemBase::new);
        LOGO = ITEMS.register("logo", NothingItemBase::new);
        INVALID = ITEMS.register("invalid", NothingItemBase::new);
    }
}