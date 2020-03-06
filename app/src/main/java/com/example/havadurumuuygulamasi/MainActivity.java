package com.example.havadurumuuygulamasi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.havadurumuuygulamasi.AnaSayfa.AnaSayfa;
import com.example.havadurumuuygulamasi.Model.HavaDurumuSonucu;
import com.example.havadurumuuygulamasi.Retrofit.ID;
import com.example.havadurumuuygulamasi.Retrofit.JsonApi;
import com.example.havadurumuuygulamasi.Retrofit.SehirAdi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText girilenSehir;
    Button button;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = findViewById(R.id.ileri);
        girilenSehir =findViewById(R.id.gelenSehir);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SehirAdi.sehir_adi=girilenSehir.getText().toString();
                Intent intent = new Intent(MainActivity.this, AnaSayfa.class);
                startActivity(intent);
            }
        });






    }
}
