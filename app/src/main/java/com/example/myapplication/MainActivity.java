package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    GridView gridView ;
    private RequestQueue queue;
    private StringRequest string_request;

    ArrayList<String> pokemonNames=new ArrayList<>();

    ArrayList<String> pokemonImages=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPokemons();

        gridView =findViewById(R.id.gridView);


        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                MainActivity3.namePokemon=pokemonNames.get(position);
                Intent intent= new Intent(MainActivity.this,MainActivity3.class);
                startActivity(intent);
            }
        });
    }
    public void getPokemons()
    {
        String url="http://10.0.2.2/scrapping_api/pokemon.php";
        queue= Volley.newRequestQueue(getApplicationContext());
        string_request=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {
                try
                {
                    JSONObject json=new JSONObject(response);
                    JSONArray jsonPokemons=json.getJSONArray("pokemons");
                    for (int i = 0; i <jsonPokemons.length() ; i++)
                    {
                        JSONObject Jsoncode=jsonPokemons.getJSONObject(i);
                        String name=Jsoncode.getString("name");
                        String image=Jsoncode.getString("img");
                        pokemonNames.add(name);
                        pokemonImages.add(image);
                    }
                    GridAdapter gridAdapter = new GridAdapter(MainActivity.this,pokemonNames,pokemonImages);
                    gridView.setAdapter(gridAdapter);
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