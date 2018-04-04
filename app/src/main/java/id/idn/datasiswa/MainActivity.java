package id.idn.datasiswa;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import id.idn.datasiswa.ApiRetrofit.ApiService;
import id.idn.datasiswa.ApiRetrofit.InstanceRetrofit;
import id.idn.datasiswa.ResponseServer.DataItem;
import id.idn.datasiswa.ResponseServer.ResponseReadData;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView rcView;
    SwipeRefreshLayout swipeRefreshLayout;
    CustomAdapter adapter;
    List<DataItem> dataItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);
        rcView = (RecyclerView) findViewById(R.id.rcView);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshLayout();
            }
        });
        rcView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        getData();
    }

    private void refreshLayout() {
        getData();
        onItemsLoadComplete();
    }

    private void onItemsLoadComplete() {
        adapter = new CustomAdapter(MainActivity.this, dataItems);
        rcView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(false);
    }

    private void getData() {
        ApiService api = InstanceRetrofit.getInstance   ();
        retrofit2.Call<ResponseReadData> call = api.response_read_data();
        call.enqueue(new Callback<ResponseReadData>() {
            @Override
            public void onResponse(retrofit2.Call<ResponseReadData> call, Response<ResponseReadData> response) {
                Log.d("response", response.body().toString());
                Boolean status = response.body().isSuccess();
                if (status){
                    dataItems = response.body().getData();

                    adapter = new CustomAdapter(MainActivity.this, dataItems);
                    rcView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(retrofit2.Call<ResponseReadData> call, Throwable t) {

            }
        });
    }

}
