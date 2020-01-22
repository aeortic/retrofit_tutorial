package com.example.retrofit_practice_1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.retrofit_tutorial.R;
import com.example.retrofit_tutorial.adapter.CustomAdapter;
import com.example.retrofit_tutorial.model.RetroPhoto;
import com.example.retrofit_tutorial.network.GetDataService;
import com.example.retrofit_tutorial.network.RetrofitClientInstance;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private CustomAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Call<List<RetroPhoto>> call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.indeterminateBar);
        progressBar.setVisibility(View.VISIBLE);

        /* Create handler for the Retrofit interface */
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);
        call = service.getAllPhotos();

        /* In this naive example, the onCreate() lifecycle method is used for the sake of berevity.
         * However, in a real-world application, the activity will likely not be the only screen
         * visited by the user. In that case, onStart() would be a more natural fit. If the scope
         * of the functionality allowed for even greater complexity, there would be a network cache
         * that would persist the user's previous fetch, so that the functionality would not be
         * hampered by poor network connectivity. Such decisions would require an analysis of the
         * intended user experience required by the app.
         */
        call.enqueue(new Callback<List<RetroPhoto>>() {
            @Override
            public void onResponse(Call<List<RetroPhoto>> call, Response<List<RetroPhoto>> response) {
                progressBar.setVisibility(View.INVISIBLE);
                /* generate the list here */
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroPhoto>> call, Throwable t) {
                progressBar.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT);
            }
        });
    }

    private void generateDataList(List<RetroPhoto> photoList) {
        recyclerView = findViewById(R.id.customRecyclerView);
        adapter = new CustomAdapter(this, photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        call.cancel();
    }
}
