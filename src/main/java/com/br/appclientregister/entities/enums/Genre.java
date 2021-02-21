package com.br.appclientregister.entities.enums;

public enum Genre {
    MALE(0),
    FEMALE(1);
    private int value;
    Genre(int genre) {this.value = genre;}
    public int getValue() { return value; }
}
