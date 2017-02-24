package in.bitnotes.affan.bitnotes.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

import in.bitnotes.affan.bitnotes.R;
import in.bitnotes.affan.bitnotes.api.BitnotesService;
import in.bitnotes.affan.bitnotes.model.Practical;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by betterclever on 2/24/2017.
 */

public class MainActivity extends AppCompatActivity {
    
    private static final String TAG = MainActivity.class.getSimpleName() ;
    RecyclerView recyclerView;
    
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    
        Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://bitnotes.in/v1/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    
        BitnotesService bitnotesService = retrofit.create(BitnotesService.class);
    
        Call<List<Practical>> call = bitnotesService.getPracticals();
        call.enqueue(new Callback<List<Practical>>() {
            @Override
            public void onResponse(Call<List<Practical>> call, Response<List<Practical>> response) {
                List<Practical> practicals = response.body();
                for(Practical p: practicals){
                    Log.d(TAG,p.toString());
                }
            }
    
            @Override
            public void onFailure(Call<List<Practical>> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }
}
