package com.willfp.eco.util.tuplets;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@SuppressWarnings("DeprecatedIsStillUsed")
@ToString
@Deprecated
public class Triplet<A, B, C> {
    /**
     * The first item in the triplet.
     */
    @Getter
    @Setter
    @Nullable
    private A first;

    /**
     * The second item in the triplet.
     */
    @Getter
    @Setter
    @Nullable
    private B second;

    /**
     * The third item in the triplet.
     */
    @Getter
    @Setter
    @Nullable
    private C third;

    /**
     * Create a triplet.
     * <p>
     * This is deprecated because I forgot how to spell Tuples before putting this into production.
     *
     * @param first  The first item in the triplet.
     * @param second The second item in the triplet.
     * @param third  The third item in the triplet.
     */
    public Triplet(@Nullable final A first,
                   @Nullable final B second,
                   @Nullable final C third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}
