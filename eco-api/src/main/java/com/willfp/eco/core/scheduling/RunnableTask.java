package com.willfp.eco.core.scheduling;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitTask;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * Bukkit Task handler.
 */
public interface RunnableTask extends Runnable {
    /**
     * Run the task.
     *
     * @return The created {@link UnifiedTask}.
     */
    @NotNull UnifiedTask runTask();

    /**
     * Run the task asynchronously.
     *
     * @return The created {@link UnifiedTask}
     */
    @NotNull UnifiedTask runTaskAsynchronously();

    /**
     * Run the task after a specified number of ticks.
     *
     * @param delay The number of ticks to wait.
     * @return The created {@link UnifiedTask}
     */
    @NotNull UnifiedTask runTaskLater(long delay);

    /**
     * Run the task asynchronously after a specified number of ticks.
     *
     * @param delay The number of ticks to wait.
     * @return The created {@link UnifiedTask}
     */
    @NotNull UnifiedTask runTaskLaterAsynchronously(long delay);

    /**
     * Run the task repeatedly on a timer.
     *
     * @param delay  The delay before the task is first ran (in ticks).
     * @param period The ticks elapsed before the task is ran again.
     * @return The created {@link UnifiedTask}
     */
    @NotNull UnifiedTask runTaskTimer(long delay, long period);

    /**
     * Run the task repeatedly on a timer asynchronously.
     *
     * @param delay  The delay before the task is first ran (in ticks).
     * @param period The ticks elapsed before the task is ran again.
     * @return The created {@link UnifiedTask}
     */
    @NotNull UnifiedTask runTaskTimerAsynchronously(long delay, long period);

    /**
     * Run the task on an entity thread (if present).
     *
     * @param entity The entity.
     * @return The created {@link UnifiedTask}.
     */
    @Nullable default UnifiedTask runTask(@NotNull Entity entity) {
        return runTask();
    }

    /**
     * Run the task on the location thread (if present).
     *
     * @param location The location.
     * @return The created {@link UnifiedTask}.
     */
    @NotNull default UnifiedTask runTask(@NotNull Location location) {
        return runTask();
    }

    /**
     * Run the on an entity thread (if present) task after a specified number of ticks.
     *
     * @param entity The entity.
     * @param delay The number of ticks to wait.
     * @return The created {@link UnifiedTask}
     */
    @Nullable default UnifiedTask runTaskLater(@NotNull Entity entity, long delay) {
        return runTaskLater(delay);
    }

    /**
     * Run the on the location thread (if present) task after a specified number of ticks.
     *
     * @param location The location.
     * @param delay The number of ticks to wait.
     * @return The created {@link UnifiedTask}
     */
    @NotNull default UnifiedTask runTaskLater(@NotNull Location location, long delay) {
        return runTaskLater(delay);
    }

    /**
     * Run the task repeatedly on an entity thread (if present) on a timer.
     *
     * @param entity The entity.
     * @param delay  The delay before the task is first ran (in ticks).
     * @param period The ticks elapsed before the task is ran again.
     * @return The created {@link UnifiedTask}
     */
    @Nullable default UnifiedTask runTaskTimer(@NotNull Entity entity, long delay, long period) {
        return runTaskTimer(delay, period);
    }

    /**
     * Run the task repeatedly the location thread (if present) on a timer.
     *
     * @param location The location.
     * @param delay  The delay before the task is first ran (in ticks).
     * @param period The ticks elapsed before the task is ran again.
     * @return The created {@link UnifiedTask}
     */
    @NotNull default UnifiedTask runTaskTimer(@NotNull Location location, long delay, long period) {
        return runTaskTimer(delay, period);
    }

    /**
     * Cancel the task.
     */
    void cancel();
}
