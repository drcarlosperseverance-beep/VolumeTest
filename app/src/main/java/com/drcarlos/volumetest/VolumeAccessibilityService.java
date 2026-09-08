package com.drcarlos.volumetest;

import android.accessibilityservice.AccessibilityService;
import android.graphics.Color;
import android.graphics.PixelFormat;
import android.os.Handler;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

public class VolumeAccessibilityService extends AccessibilityService {
    private WindowManager wm; private TextView card; private final Handler handler = new Handler();
    @Override protected void onServiceConnected() { super.onServiceConnected(); wm = (WindowManager)getSystemService(WINDOW_SERVICE); }
    @Override public boolean onKeyEvent(KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && (event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP || event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN)) {
            showCard(event.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP ? "Volumen + detectado" : "Volumen − detectado");
        }
        return false;
    }
    private void showCard(String text) {
        if (wm == null) return;
        if (card == null) { card = new TextView(this); card.setTextColor(Color.WHITE); card.setTextSize(16); card.setGravity(Gravity.CENTER); card.setPadding(36, 24, 36, 24); card.setBackgroundColor(Color.rgb(21,101,192)); }
        card.setText(text);
        if (card.getParent() == null) { WindowManager.LayoutParams p = new WindowManager.LayoutParams(WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.WRAP_CONTENT, WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY, WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN, PixelFormat.TRANSLUCENT); p.gravity = Gravity.CENTER_VERTICAL | Gravity.RIGHT; p.x = 18; wm.addView(card, p); }
        handler.removeCallbacksAndMessages(null); handler.postDelayed(() -> { if (card != null && card.getParent() != null) wm.removeView(card); }, 1300);
    }
    @Override public void onInterrupt() { }
    @Override public void onDestroy() { if (card != null && card.getParent() != null) wm.removeView(card); super.onDestroy(); }
}
