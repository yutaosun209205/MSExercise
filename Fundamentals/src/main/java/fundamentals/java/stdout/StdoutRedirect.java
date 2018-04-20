package fundamentals.java.stdout;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import java.io.OutputStream;
import java.io.PrintStream;

public class StdoutRedirect {

    private static final Logger logger = Logger.getLogger(StdoutRedirect.class);

    private static final PrintStream infoPrintStream = createLogStream(System.out, Level.INFO);
    private static final PrintStream errorPrintStream = createLogStream(System.out, Level.ERROR);

    public static void redirectStdOut(){
        System.setOut(infoPrintStream);
        System.setErr(errorPrintStream);
    }


    private static LogStream createLogStream(final PrintStream printStream, final Level level){
        return new LogStream(printStream, level);
    }

    private static class LogStream extends PrintStream{

        private final Level level;
        public LogStream(final OutputStream fileOutputStream, Level level){
            super(fileOutputStream);
            this.level = level;
        }

        public void write(final String x){
            logger.log(level, x);
        }

        @Override
        public void print(final String s) {
            write(s);
        }

        @Override
        public void println(String x) {
            print(x);
        }
    }

    public static void main(String[] args) {
        StdoutRedirect.redirectStdOut();

        System.out.println("Hello World");
    }

}


