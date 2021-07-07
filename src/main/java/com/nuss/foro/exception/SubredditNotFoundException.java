package com.nuss.foro.exception;

public class SubredditNotFoundException extends SpringRedditException {
    public SubredditNotFoundException(String s) {
        super(s);
    }
}
