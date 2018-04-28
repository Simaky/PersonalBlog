package com.maximprytyka.personalblog.activities;

import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.maximprytyka.personalblog.R;
import com.maximprytyka.personalblog.adapter.RecycleViewAdapterComments;
import com.maximprytyka.personalblog.model.Comments;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity {

    private final String JSON_URL = "http://testblog.epizy.com/api/getcomments.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Comments> commentsList;
    private RecyclerView recyclerView;
    private String postId;
    private TextView nocom;
    private boolean isNocomemnts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);


        Toolbar toolbar = findViewById(R.id.myToolBar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        //get data from RecycleViewAdapter

        String title = getIntent().getExtras().getString("title");
        String text = getIntent().getExtras().getString("text");
        String image = getIntent().getExtras().getString("image");
        postId = Integer.toString(getIntent().getExtras().getInt("id"));

        //init views
        CollapsingToolbarLayout collapsingToolbarLayout = findViewById(R.id.collapsingtoolbar_id);
        collapsingToolbarLayout.setTitleEnabled(true);
        collapsingToolbarLayout.setContentScrimColor(ContextCompat.getColor(this, R.color.colorPrimary));

        TextView tvText = findViewById(R.id.aa_description);
        ImageView tvImage = findViewById(R.id.header_logo);
        nocom = findViewById(R.id.nocom);

        //setting values

        tvText.setText(text);

        collapsingToolbarLayout.setTitle(title);

        RequestOptions requestOptions = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);

        Glide.with(this).load(image).apply(requestOptions).into(tvImage);


        commentsList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycleviewcomments);
        jsonrequest();


    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Comments comments = new Comments();

                        if (postId.equals(jsonObject.getString("articles_id"))) {
                            comments.setAuthor(jsonObject.getString("author"));
                            comments.setNickname(jsonObject.getString("nickname"));
                            comments.setEmail(jsonObject.getString("email"));
                            comments.setText(jsonObject.getString("text"));
                            comments.setPubdate(jsonObject.getString("pubdate"));
                            comments.setArticles_id(jsonObject.getString("articles_id"));
                            isNocomemnts = true;
                            commentsList.add(comments);

                        } else {

                            System.out.println("NO COMMENTS");
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                if (!isNocomemnts) {
                    nocom.setVisibility(View.VISIBLE);//no comments
                } else {
                    nocom.setVisibility(View.GONE);

                }

                setupRecycleView(commentsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);

    }

    private void setupRecycleView(List<Comments> lstComments) {

        RecycleViewAdapterComments myAdapter = new RecycleViewAdapterComments(this, lstComments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recyclerView.setAdapter(myAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}