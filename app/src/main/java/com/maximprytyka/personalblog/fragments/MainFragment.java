package com.maximprytyka.personalblog.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.app.FragmentManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.maximprytyka.personalblog.R;
import com.maximprytyka.personalblog.adapter.RecycleViewAdapter;
import com.maximprytyka.personalblog.model.Posts;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MainFragment extends Fragment {
    private final String JSON_URL = "http://testblog.epizy.com/api/getarticles.php";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<Posts> postsList;
    private RecyclerView recyclerView;

    @TargetApi(Build.VERSION_CODES.M)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_fragment,container,false);

        postsList = new ArrayList<>();
        recyclerView = v.findViewById(R.id.recycleviewid);
        jsonrequest();




//        final FragmentManager fm = getFragmentManager();
//        final FloatingActionButton fab = (FloatingActionButton) v.findViewById(R.id.fab);
//        fab.setImageDrawable(ContextCompat.getDrawable(getActivity(), R.drawable.edit));
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                //fm.beginTransaction().replace(R.id.content_frame, new EditFragment()).commit(); //Заміна фрагмента по нажаттю кнопки
//            }
//        });

        return v;
    }

    private void jsonrequest() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {

                    try {
                        jsonObject = response.getJSONObject(i);
                        Posts posts = new Posts();
                        posts.setTitle(jsonObject.getString("title"));
                        posts.setText(jsonObject.getString("text"));
                        posts.setImage(jsonObject.getString("image"));
                        posts.setPubdate(jsonObject.getString("pubdate"));
                        posts.setViews(jsonObject.getString("views"));
                        posts.setCategorie_id(jsonObject.getString("categorie_id"));

                        postsList.add(posts);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                setupRecycleView(postsList);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });


        requestQueue = Volley.newRequestQueue(getActivity());
        requestQueue.add(request);

    }

    private void setupRecycleView(List<Posts> lstPosts){

        RecycleViewAdapter myAdapter = new RecycleViewAdapter(getActivity(),lstPosts);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.setAdapter(myAdapter);
    }
}