package kea.dpang.qna.exception;

public class FeignException extends RuntimeException{
    public FeignException(String message, Throwable e) {super(message+e);}

}
