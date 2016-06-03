package pvt.finalproject.parse;

public class ParseException extends Exception {

    public ParseException() {
        super();

    }

    public ParseException(String arg0, Throwable arg1, boolean arg2,
                          boolean arg3) {
        super(arg0, arg1, arg2, arg3);

    }

    public ParseException(String arg0, Throwable arg1) {
        super(arg0, arg1);

    }

    public ParseException(String arg0) {
        super(arg0);

    }

    public ParseException(Throwable arg0) {
        super(arg0);

    }
}
