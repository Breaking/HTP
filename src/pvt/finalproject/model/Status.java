package pvt.finalproject.model;

public enum Status {
    NO_ERROR (0, "Successfull downloading", true),
    INTERNET_ERROR (1, "Unable to connect to the internet", false),
    PARSER_ERROR (2, "Parsing Error", false);

    private int code;
    private String message;
    private boolean isSuccessfull;

    private Status(int code, String message, boolean isSuccessfull) {
        this.code = code;
        this.message = message;
        this.isSuccessfull = isSuccessfull;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public boolean isSuccessfull() {
        return isSuccessfull;
    }


}