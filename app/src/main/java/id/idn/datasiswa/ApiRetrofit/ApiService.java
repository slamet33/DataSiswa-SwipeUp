package id.idn.datasiswa.ApiRetrofit;

import id.idn.datasiswa.ResponseServer.ResponseReadData;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by hp on 3/25/2018.
 */

public interface ApiService {
    @GET("read_data.php")
    Call<ResponseReadData> response_read_data();
}
