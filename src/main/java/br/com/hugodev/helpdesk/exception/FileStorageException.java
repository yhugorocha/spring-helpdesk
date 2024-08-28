package br.com.hugodev.helpdesk.exception;

public class FileStorageException extends RuntimeException{

    public FileStorageException(){
        super("File Exception");
    }
    public FileStorageException(String message){
        super(message);
    }
}
