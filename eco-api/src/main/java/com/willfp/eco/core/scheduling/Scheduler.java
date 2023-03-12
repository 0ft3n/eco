package com.willfp.eco.core.scheduling;

import com.willfp.eco.core.EcoPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.jetbrains.annotations.NotNull;

/**
 * Thread scheduler to handle tasks and asynchronous code.
 */
public interface Scheduler {
    /**
     * Run the task after a specified tick delay.
     *
     * @param runnable   The lambda to run.
     * @param ticksLater The amount of ticks to wait before execution.
     * @return The created {@link UnifiedTask}.
     * @deprecated Does not work with Folia.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default UnifiedTask runLater(@NotNull Runnable runnable,
                                 long ticksLater) {
        return runLaterGlobally((int) ticksLater, runnable);
    }

    /**
     * Run the task after a specified tick delay.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable   The lambda to run.
     * @param ticksLater The amount of ticks to wait before execution.
     * @return The created {@link UnifiedTask}.
     * @deprecated Does not work with Folia.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default UnifiedTask runLater(long ticksLater,
                                 @NotNull Runnable runnable) {
        return runLater(new Location(Bukkit.getWorlds().get(0), 0, 0, 0), (int) ticksLater, runnable);
    }

    /**
     * Run the task repeatedly on a timer.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link UnifiedTask}.
     * @deprecated Does not work with Folia.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default UnifiedTask runTimer(@NotNull Runnable runnable,
                                 long delay,
                                 long repeat) {
        return runTimerGlobally((int) delay, (int) repeat, runnable);
    }

    /**
     * Run the task repeatedly on a timer.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link UnifiedTask}.
     * @deprecated Does not work with Folia.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default UnifiedTask runTimer(long delay,
                                 long repeat,
                                 @NotNull Runnable runnable) {
        return runTimerGlobally((int) delay, (int) repeat, runnable);
    }

    /**
     * Run the task repeatedly and asynchronously on a timer.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link UnifiedTask}.
     * @deprecated Does not work with Folia.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default UnifiedTask runAsyncTimer(@NotNull Runnable runnable,
                                      long delay,
                                      long repeat) {
        return runTimerAsync((int) delay, (int) repeat, runnable);
    }

    /**
     * Run the task repeatedly and asynchronously on a timer.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The created {@link UnifiedTask}.
     * @deprecated Does not work with Folia.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default UnifiedTask runAsyncTimer(long delay,
                                      long repeat,
                                      @NotNull Runnable runnable) {
        return runTimerAsync((int) delay, (int) repeat, runnable);
    }

    /**
     * Run the task.
     *
     * @param runnable The lambda to run.
     * @return The created {@link UnifiedTask}.
     * @deprecated Does not work with Folia.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default UnifiedTask run(@NotNull Runnable runnable) {
        return runGlobally(runnable);
    }

    /**
     * Schedule the task to be run repeatedly on a timer.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The id of the task.
     * @deprecated Not needed.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default int syncRepeating(@NotNull Runnable runnable,
                              long delay,
                              long repeat) {
        return runTimer(runnable, delay, repeat).getTaskId();
    }

    /**
     * Schedule the task to be ran repeatedly on a timer.
     * <p>
     * Reordered for better kotlin interop.
     *
     * @param runnable The lambda to run.
     * @param delay    The amount of ticks to wait before the first execution.
     * @param repeat   The amount of ticks to wait between executions.
     * @return The id of the task.
     * @deprecated Not needed.
     */
    @Deprecated(since = "6.53.0", forRemoval = true)
    default int syncRepeating(long delay,
                              long repeat,
                              @NotNull Runnable runnable) {
        return runTimer(runnable, delay, repeat).getTaskId();
    }

    /**
     * Cancel all running tasks from the linked {@link EcoPlugin}.
     */
    void cancelAll();

    /**
     * Run a task asynchronously.
     *
     * @param task The lambda to run.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runAsync(@NotNull Runnable task);

    /**
     * Run a task.
     *
     * @param location The location.
     * @param task     The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask run(@NotNull Location location,
                    @NotNull Runnable task);

    /**
     * Run a global task.
     *
     * @param task     The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runGlobally(@NotNull Runnable task);

    /**
     * Run a task.
     *
     * @param entity The entity.
     * @param task     The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask run(@NotNull Entity entity,
                    @NotNull Runnable task);

    /**
     * Run a task after a delay.
     *
     * @param location   The location.
     * @param ticksLater The delay.
     * @param task       The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runLater(@NotNull Location location,
                         int ticksLater,
                         @NotNull Runnable task);

    /**
     * Run a task after a delay.
     *
     * @param entity   The entity.
     * @param ticksLater The delay.
     * @param task       The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runLater(@NotNull Entity entity,
                         int ticksLater,
                         @NotNull Runnable task);

    /**
     * Run a task globally after a delay.
     *
     * @param ticksLater The delay.
     * @param task       The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runLaterGlobally(int ticksLater,
                                 @NotNull Runnable task);

    /**
     * Run a task on a timer.
     *
     * @param location The location.
     * @param delay    The delay.
     * @param repeat   The repeat delay.
     * @param task     The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runTimer(@NotNull Location location,
                         int delay,
                         int repeat,
                         @NotNull Runnable task);

    /**
     * Run a task on a timer.
     *
     * @param entity The entity.
     * @param delay    The delay.
     * @param repeat   The repeat delay.
     * @param task     The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runTimer(@NotNull Entity entity,
                         int delay,
                         int repeat,
                         @NotNull Runnable task);

    /**
     * Run a task globally on a timer.
     *
     * @param delay    The delay.
     * @param repeat   The repeat delay.
     * @param task     The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runTimerGlobally(int delay,
                                 int repeat,
                                 @NotNull Runnable task);

    /**
     * Run a task asynchronously on a timer.
     *
     * @param delay  The delay.
     * @param repeat The repeat delay.
     * @param task   The task.
     * @return The created {@link UnifiedTask}.
     */
    UnifiedTask runTimerAsync(int delay,
                              int repeat,
                              @NotNull Runnable task);
}
