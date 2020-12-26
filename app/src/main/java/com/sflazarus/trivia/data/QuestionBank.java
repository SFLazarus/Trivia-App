package com.sflazarus.trivia.data;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.sflazarus.trivia.controller.AppController;
import com.sflazarus.trivia.model.Question;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class QuestionBank {

    private String URL="https://raw.githubusercontent.com/curiousily/simple-quiz/master/script/statements.json";

    ArrayList<Question> questionArrayList=new ArrayList<>();
    public List<Question> getQuestions(){

        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(
                Request.Method.GET,
                URL,
                (JSONArray) null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        Log.d("JSON stuff", "onResponse: "+response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }

        );
        AppController.getInstance().addToRequestQueue(jsonArrayRequest);

        return null;
    }

}
