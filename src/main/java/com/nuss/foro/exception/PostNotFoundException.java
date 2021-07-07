package com.nuss.foro.exception;

public class PostNotFoundException extends  SpringRedditException{
    public PostNotFoundException(String s) {
        super(s);
    }
}
