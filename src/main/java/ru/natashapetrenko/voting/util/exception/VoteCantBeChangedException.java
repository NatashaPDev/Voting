package ru.natashapetrenko.voting.util.exception;

public class VoteCantBeChangedException extends RuntimeException {
    public VoteCantBeChangedException() {
        super("It is too late, vote can't be changed");
    }
}