package life.domacitempeh.mqttsensorcollector.model;

import java.util.EnumSet;
import java.util.NavigableSet;
import java.util.Objects;
import java.util.TreeSet;

public enum EFullscreenRotaryFunction {

    VOL,
    SEEK,
    SCROLL
    ;

    private static final NavigableSet<EFullscreenRotaryFunction> set =
            new TreeSet<>(EnumSet.allOf(EFullscreenRotaryFunction.class)); // EnumSet.allOf() generates a set of enum-constants of the specified type

    public EFullscreenRotaryFunction next() {
        return Objects.requireNonNullElseGet(
                set.higher(this), set::first
        );
    }

    public EFullscreenRotaryFunction previous() {
        return Objects.requireNonNullElseGet(
                set.lower(this), set::last
        );
    }
}
