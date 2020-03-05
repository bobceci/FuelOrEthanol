package com.bobfatec.fuelorethanol;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    private static final NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();

    private TextView priceGasolinaText;
    private TextView priceEtanolText;

    private TextView bestFuelText;
    private ImageView imageFuel;

    private SeekBar seekBarGasolina;
    private SeekBar seekBarEtanol;

    private double priceGasolina = 4;

    private double priceEtanol = 4;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // Toast.makeText(this, "Versão final", Toast.LENGTH_SHORT).show(); // Não aparecer ao iniciar tela


        priceGasolinaText = findViewById(R.id.priceGasolinaText);

        priceEtanolText   = findViewById(R.id.priceEtanolText);

        seekBarGasolina   = findViewById(R.id.seekBarGasolina);

        seekBarEtanol     = findViewById(R.id.seekBarEtanol);

        bestFuelText      = findViewById(R.id.bestFuelText);

        imageFuel         = findViewById(R.id.imageFuel);

        priceGasolinaText.setText(currencyFormat.format(priceGasolina));

        priceEtanolText.setText(currencyFormat.format(priceEtanol));

        calcular ();

         // SEEK BAR DA GASOLINA, CALCULA VALOR DO COMBUSTíVEL

        seekBarGasolina.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                priceGasolina = progress / 100d;
                priceGasolinaText.setText(currencyFormat.format(priceGasolina));
                calcular();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // SEEK BAR DO ETANOL, CALCULA VALOR DO COMBUSTÍVEL

        seekBarEtanol.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
            priceEtanol = progress / 100d;

            priceEtanolText.setText(currencyFormat.format(priceEtanol));
            calcular();

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    // Método que calcula melhor combustível para usar

    private void calcular (){

        if (priceEtanol / priceGasolina >= 0.7){
            bestFuelText.setText(R.string.gasolina);
            imageFuel.setImageResource(R.drawable.gasoline);
        }
        else if (priceEtanol == 0 && priceGasolina == 0){
            bestFuelText.setText(R.string.vazio);

        }

        else{
            bestFuelText.setText(R.string.etanol);
            imageFuel.setImageResource(R.drawable.ethanol);
        }
    }


}
