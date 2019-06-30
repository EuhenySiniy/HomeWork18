package com.yevhensynii.exceptions;

public class NotFoundEntityException extends Exception {
    public NotFoundEntityException() {
        super("No users with this id");
    }

    public NotFoundEntityException(Long id) {
        super("No user with id = " + id);
    }
}
