package com.example.simplepaint;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private PaintView paintView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        paintView = findViewById(R.id.paintView);

        findViewById(R.id.btnFreeHand).setOnClickListener(v -> paintView.setShapeType(PaintView.ShapeType.FREE_HAND));
        findViewById(R.id.btnRect).setOnClickListener(v -> paintView.setShapeType(PaintView.ShapeType.RECTANGLE));
        findViewById(R.id.btnCircle).setOnClickListener(v -> paintView.setShapeType(PaintView.ShapeType.CIRCLE));
        findViewById(R.id.btnLine).setOnClickListener(v -> paintView.setShapeType(PaintView.ShapeType.LINE));
        findViewById(R.id.btnClear).setOnClickListener(v -> paintView.clear());

        findViewById(R.id.btnColorRed).setOnClickListener(v -> paintView.setColor(Color.RED));
        findViewById(R.id.btnColorBlue).setOnClickListener(v -> paintView.setColor(Color.BLUE));
        findViewById(R.id.btnColorGreen).setOnClickListener(v -> paintView.setColor(Color.GREEN));
        findViewById(R.id.btnColorBlack).setOnClickListener(v -> paintView.setColor(Color.BLACK));
        findViewById(R.id.btnColorYellow).setOnClickListener(v -> paintView.setColor(Color.YELLOW));
    }
}
