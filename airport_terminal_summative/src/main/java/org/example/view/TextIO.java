package org.example.view;

public interface TextIO {

    public void print(String prompt);

    public String getNonEmptyString(String prompt);

    public int getInt(String prompt, int min, int max);

    public void printMenu();
}
