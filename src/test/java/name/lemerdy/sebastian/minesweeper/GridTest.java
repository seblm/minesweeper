package name.lemerdy.sebastian.minesweeper;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class GridTest {
    @Test
    public void should_notify_when_cell_is_discovered() {
        final CalledContainer discovered = new CalledContainer();
        Grid grid = new Grid(1, 1).setDiscoveredListener(0, 0, cell -> discovered.call());

        grid.discover(0, 0);

        assertThat(discovered.called).isTrue();
    }

    private class CalledContainer {
        private boolean called = false;
        private void call() {
            called = true;
        }
    }
}