package com.yellowbrossproductions.illageandspillage.config;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import com.electronwill.nightconfig.core.io.WritingMode;
import com.yellowbrossproductions.illageandspillage.IllageAndSpillage;
import java.io.File;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class Config {
    private static final ForgeConfigSpec.Builder common_builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec common_config;
    private static final ForgeConfigSpec.Builder client_builder = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec client_config;

    public Config() {
    }

    public static void loadConfig(ForgeConfigSpec config, String path) {
        IllageAndSpillage.LOGGER.info("Loading config: " + path);
        CommentedFileConfig file = (CommentedFileConfig)CommentedFileConfig.builder(new File(path)).sync().autosave().writingMode(WritingMode.REPLACE).build();
        IllageAndSpillage.LOGGER.info("Built config: " + path);
        file.load();
        IllageAndSpillage.LOGGER.info("Loaded config: " + path);
        config.setConfig(file);
    }

    static {
        IllageAndSpillageConfig.init(common_builder, client_builder);
        common_config = common_builder.build();
        client_config = client_builder.build();
    }
}
