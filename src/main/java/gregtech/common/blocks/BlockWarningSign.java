package gregtech.common.blocks;

import gregtech.api.GregTech_API;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.util.IStringSerializable;

public class BlockWarningSign extends VariantBlock<BlockWarningSign.SignType> {

    public BlockWarningSign() {
        super(Material.IRON, SignType.class);
        setHardness(2.0f);
        setResistance(1.0f);
        setSoundType(SoundType.METAL);
        setHarvestLevel("pickaxe", 2);
        setCreativeTab(GregTech_API.TAB_GREGTECH);
    }

    public enum SignType implements IStringSerializable {

        YELLOW_STRIPES("yellow_stripes"),
        SMALL_YELLOW_STRIPES("small_yellow_stripes"),
        RADIOACTIVE_HAZARD("radioactive_hazard"),
        BIO_HAZARD("bio_hazard"),
        EXPLOSION_HAZARD("explosion_hazard"),
        FIRE_HAZARD("fire_hazard"),
        ACID_HAZARD("acid_hazard"),
        MAGIC_HAZARD("magic_hazard"),
        FROST_HAZARD("frost_hazard"),
        NOISE_HAZARD("noise_hazard");

        private final String name;

        SignType(String name) {
            this.name = name;
        }

        @Override
        public String getName() {
            return this.name;
        }

    }
    
}