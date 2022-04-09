package com.devsuperior.bds03.services.exceptions;

public class ResourcesNotFoundException extends RuntimeException{
    public ResourcesNotFoundException(String message) {
        super(message);
    }
}
