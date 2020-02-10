package gregtech.integration.theoneprobe.provider;

import gregtech.api.capability.GregtechTileCapabilities;
import gregtech.api.capability.impl.AbstractRecipeLogic;
import mcjty.theoneprobe.api.ElementAlignment;
import mcjty.theoneprobe.api.IProbeInfo;
import mcjty.theoneprobe.api.NumberFormat;
import mcjty.theoneprobe.api.TextStyleClass;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RecipeInfoProvider extends CapabilityInfoProvider<AbstractRecipeLogic> {

    @Override
    public String getID() {
        return "gregtech:recipe_logic_provider";
    }

    @Override
    protected Capability<AbstractRecipeLogic> getCapability() {
        return GregtechTileCapabilities.CAPABILITY_ABSTRACT_RECIPE_LOGIC;
    }

    @Override
    protected void addProbeInfo(AbstractRecipeLogic capability, IProbeInfo probeInfo, TileEntity tileEntity, EnumFacing sideHit) {
        if (capability != null && capability.getRecipeEUt() > 0) {
            IProbeInfo horizontalPane = probeInfo.horizontal(probeInfo.defaultLayoutStyle().alignment(ElementAlignment.ALIGN_CENTER));
            Method getMaxVoltage = ObfuscationReflectionHelper.findMethod(AbstractRecipeLogic.class, "getMaxVoltage", long.class);
            long maxVoltage;
            try {
                maxVoltage = (long) getMaxVoltage.invoke(capability);
            } catch (IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
                return ;
            }
            horizontalPane.progress(capability.getRecipeEUt(), maxVoltage, probeInfo.defaultProgressStyle()
                .numberFormat(NumberFormat.NONE)
                .suffix(String.format("%d EU/t", capability.getRecipeEUt()))
                .borderColor(0xFF000000)
                .backgroundColor(0x00000000)
                .filledColor(0xFF0000FF));
        }
    }
}
