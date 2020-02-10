package gregtech.common.covers;

import gregtech.api.capability.tool.IdleTracker;
import gregtech.api.cover.CoverBehavior;
import gregtech.api.cover.ICoverable;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ITickable;

public abstract class AdaptiveCoverBehaviors extends CoverBehavior implements ITickable {

    private IdleTracker idle = new IdleTracker(5, 60, 2);

    public AdaptiveCoverBehaviors(ICoverable coverHolder, EnumFacing attachedSide) {
        super(coverHolder, attachedSide);
    }

    @Override
    public void update() {
        idle.update();
        if (idle.canAction()) {
            if (innerUpdate()) {
                idle.dec();
            } else {
                idle.inc();
            }
        }
    }

    protected abstract boolean innerUpdate();
}
