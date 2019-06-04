package com.example.codezero.stu_pedia;

/**
 * Created by Harshit Saraswat on 17-03-2018.
 */

public class QueryData {

    String mName,mUsername,mQuestion,mAnswer;

    public QueryData(String name,String username,String question,String answer){
        mName= name;
        mUsername=username;
        mQuestion=question;
        mAnswer=answer;
    }

    public String getName(){return mName;}

    public String getUsername(){return mUsername;}

    public String getQuestion(){return mQuestion;}

    public String getAnswer(){return mAnswer;}

}
