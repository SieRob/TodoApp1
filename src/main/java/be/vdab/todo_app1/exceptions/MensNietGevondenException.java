package be.vdab.todo_app1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MensNietGevondenException extends RuntimeException{
    public MensNietGevondenException() {
        super("Mens niet gevonden");
    }
}
