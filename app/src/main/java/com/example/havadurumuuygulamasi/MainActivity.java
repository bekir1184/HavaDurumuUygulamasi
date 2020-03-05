package com.example.havadurumuuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.havadurumuuygulamasi.Model.HavaDurumuSonucu;
import com.example.havadurumuuygulamasi.Retrofit.ID;
import com.example.havadurumuuygulamasi.Retrofit.JsonApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText girilenSehir;
    TextView sehirAdi;
    TextView derece;
    ImageView resim;
    Button button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        girilenSehir =findViewById(R.id.sehirAdi);
        sehirAdi =findViewById(R.id.baslikSehirAdi);
        derece =findViewById(R.id.derece);
        resim =findViewById(R.id.resim);
        button=findViewById(R.id.button);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/") //Json verisi çekeceğimiz adres
                .addConverterFactory(GsonConverterFactory.create()) //Json verisinin parçalanması
                .build();

        final JsonApi jsonApi = retrofit.create(JsonApi.class);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Call<HavaDurumuSonucu> havaDurumuSonucuCall =jsonApi.getHavaDurumu(girilenSehir.getText().toString(), ID.APP_ID,"metric");

                havaDurumuSonucuCall.enqueue(new Callback<HavaDurumuSonucu>() {
                    @Override
                    public void onResponse(Call<HavaDurumuSonucu> call, Response<HavaDurumuSonucu> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(MainActivity.this, "Eror code :" + response.code(), Toast.LENGTH_LONG).show();
                        }
                        HavaDurumuSonucu havaDurumuSonucu =response.body();

                        sehirAdi.setText(havaDurumuSonucu.name);
                        derece.setText(Double.toString(havaDurumuSonucu.getMain().getTemp()));


                    }

                    @Override
                    public void onFailure(Call<HavaDurumuSonucu> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Eror code :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });


            }
        });







    }
}
