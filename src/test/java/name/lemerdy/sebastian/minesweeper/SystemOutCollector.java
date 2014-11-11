package name.lemerdy.sebastian.minesweeper;

import org.junit.rules.ExternalResource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class SystemOutCollector extends ExternalResource {

    private PrintStream original;
    private ByteArrayOutputStream out;

    @Override
    protected void before() throws Throwable {
        original = System.out;
        out = new ByteArrayOutputStream();
        System.setOut(new PrintStream(out));
    }

    public String out() {
        return out.toString();
    }

    @Override
    protected void after() {
        System.setOut(original);
    }
}
