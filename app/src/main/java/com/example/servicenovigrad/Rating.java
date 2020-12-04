package com.example.servicenovigrad;

public class Rating {
    public String UsernameofCustomer;
    public String UserNameofEmployee;
    public int Score;
    public String Comment;

    public Rating(String usernameofCustomer, String userNameofEmployee, int score, String comment) {
        UsernameofCustomer = usernameofCustomer;
        UserNameofEmployee = userNameofEmployee;
        Score = score;
        Comment = comment;
    }
    
    public Rating(){
    }
}