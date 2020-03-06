package com.example.havadurumuuygulamasi.AnaSayfa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.havadurumuuygulamasi.MainActivity;
import com.example.havadurumuuygulamasi.Model.HavaDurumuSonucu;
import com.example.havadurumuuygulamasi.R;
import com.example.havadurumuuygulamasi.Retrofit.ID;
import com.example.havadurumuuygulamasi.Retrofit.JsonApi;
import com.example.havadurumuuygulamasi.Retrofit.SehirAdi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AnaSayfa extends AppCompatActivity {

    TextView sehir_adi,derece,durum,sicaklik,hissedilen,min,max,basinc,nem,ruzgar,deniz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ana_sayfa);

        sehir_adi =findViewById(R.id.sehir_adi);
        derece =findViewById(R.id.derece);
        durum =findViewById(R.id.durum);
        sicaklik =findViewById(R.id.sicaklikD);
        hissedilen=findViewById(R.id.hissedilenD);
        min=findViewById(R.id.min_sicaklikD);
        max=findViewById(R.id.max_sicaklikD);
        basinc=findViewById(R.id.basincD);
        nem=findViewById(R.id.nemD);
        ruzgar=findViewById(R.id.ruzgarD);
        deniz=findViewById(R.id.deniz_seviyesiD);





        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.openweathermap.org/data/2.5/") //Json verisi çekeceğimiz adres
                .addConverterFactory(GsonConverterFactory.create()) //Json verisinin parçalanması
                .build();

        final JsonApi jsonApi = retrofit.create(JsonApi.class);




                Call<HavaDurumuSonucu> havaDurumuSonucuCall =jsonApi.getHavaDurumu(SehirAdi.sehir_adi, ID.APP_ID,"metric");

                havaDurumuSonucuCall.enqueue(new Callback<HavaDurumuSonucu>() {
                    @Override
                    public void onResponse(Call<HavaDurumuSonucu> call, Response<HavaDurumuSonucu> response) {

                        if(!response.isSuccessful()){
                            Toast.makeText(AnaSayfa.this, "Eror code :" + response.code(), Toast.LENGTH_LONG).show();
                        }
                        HavaDurumuSonucu havaDurumuSonucu =response.body();

                        sehir_adi.setText(SehirAdi.sehir_adi);
                        derece.setText(havaDurumuSonucu.getMain().getTemp()+" C");
                        durum.setText(havaDurumuSonucu.getWeather().get(0).getMain());
                        sicaklik.setText(havaDurumuSonucu.getMain().getTemp()+" C");
                        hissedilen.setText(havaDurumuSonucu.getMain().getFeels_like()+" C");
                        min.setText(havaDurumuSonucu.getMain().getTemp_min()+" C");
                        max.setText(havaDurumuSonucu.getMain().getTemp_max()+" C");
                        basinc.setText(havaDurumuSonucu.getMain().getPressure()+"");
                        nem.setText(havaDurumuSonucu.getMain().getHumidity()+"");
                        ruzgar.setText(havaDurumuSonucu.getWind().getSpeed()+"");
                        deniz.setText(havaDurumuSonucu.getMain().getSea_level()+"");



                    }

                    @Override
                    public void onFailure(Call<HavaDurumuSonucu> call, Throwable t) {
                        Toast.makeText(AnaSayfa.this, "Eror code :" + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                    }
                });







    }
}
