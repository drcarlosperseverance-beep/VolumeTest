package com.drcarlos.volumetest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override public void onCreate(Bundle state) {
        super.onCreate(state);
        LinearLayout box = new LinearLayout(this);
        box.setOrientation(LinearLayout.VERTICAL); box.setPadding(40, 50, 40, 40);
        TextView title = new TextView(this); title.setText("Prueba de botones de volumen"); title.setTextSize(22); title.setGravity(Gravity.CENTER);
        TextView info = new TextView(this); info.setText("Activa el servicio y luego presiona Volumen + o −. Si aparece una tarjeta azul, Android 16 permite detectar el botón."); info.setTextSize(17); info.setPadding(0, 30, 0, 30);
        Button open = new Button(this); open.setText("Activar accesibilidad"); open.setOnClickListener(v -> startActivity(new Intent(Settings.ACTION_ACCESSIBILITY_SETTINGS)));
        box.addView(title); box.addView(info); box.addView(open); setContentView(box);
    }
}
