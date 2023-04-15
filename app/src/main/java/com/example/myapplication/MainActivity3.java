package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity3 extends AppCompatActivity {
    public static String namePokemon;
    public String j_type;
    public String j_height;
    public String j_weight;
    public String j_catchRate;
    public String j_eggGroup;
    public String j_img;
    public String j_gender;
    ImageView image;
    TextView name;
    TextView desc;
    TextView height;
    TextView weight;
    TextView gender;
    TextView category;
    private RequestQueue queue;
    private StringRequest string_request;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        getPokemon();
        image=findViewById(R.id.pokemonImage);
        name=findViewById(R.id.name);
        desc=findViewById(R.id.description);
        height=findViewById(R.id.height);
        weight=findViewById(R.id.weight);
        gender=findViewById(R.id.gender);
        category=findViewById(R.id.category);

    }
    public void getPokemon()
    {
        String url="http://10.0.2.2/scrapping_api/onepokemon.php?name="+namePokemon;
        queue= Volley.newRequestQueue(getApplicationContext());
        string_request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject json=new JSONObject(response);
                    j_type=json.getString("type");
                    j_img=json.getString("img");
                    j_catchRate=json.getString("catchRate") ;
                    j_height=json.getString("height");
                    j_weight=json.getString("weight");
                    j_eggGroup=json.getString("eggGroup");
                    j_gender=json.getString("gender");
                    Glide.with(MainActivity3.this).load(j_img).into(image);
                    name.setText(namePokemon);
                    height.setText(j_height);
                    weight.setText(j_weight);
                    gender.setText(j_gender);
                    category.setText(j_type);
                    desc.setText(j_eggGroup);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                    Log.i("json size:", "Error :" + e.toString());
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("json error","Error : "+error.toString());
            }
        });
        queue.add(string_request);
    }
}