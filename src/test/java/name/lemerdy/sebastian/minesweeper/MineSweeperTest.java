package name.lemerdy.sebastian.minesweeper;

import org.junit.Rule;
import org.junit.Test;

import static name.lemerdy.sebastian.minesweeper.Status.WIN;
import static org.assertj.core.api.Assertions.assertThat;

public class MineSweeperTest {
    @Rule
    public SystemOutCollector system = new SystemOutCollector();

    @Test
    public void acceptance_test() {
        Grid grid = new Grid(5, 5).mine(0, 0).mine(1, 0).mine(2, 2);

        Status status = grid.discover(4, 4).print().status();

        assertThat(status).isEqualTo(WIN);
        assertThat(system.out()).isEqualTo("" +
                "# # 1    \n" +
                "2 3 2 1  \n" +
                "  1 # 1  \n" +
                "  1 1 1  \n" +
                "         \n");
    }
}
