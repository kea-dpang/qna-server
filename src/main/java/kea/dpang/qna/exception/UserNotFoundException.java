package kea.dpang.qna.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String message, Throwable e) {super(message+e);}

}
