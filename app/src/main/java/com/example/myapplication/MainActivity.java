package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;




public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnStart;
    private ConstraintLayout layout;
    private Button btnBackground;
    private TextView tvTextValue;
    private int colors = 0;
    //private ActivityResultLauncher<Intent> activityResultLaunch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btnStart = findViewById(R.id.btnStartActivity);
        tvTextValue = findViewById(R.id.tvText);
        btnBackground = findViewById(R.id.backgroundColorButton);
        btnStart.setOnClickListener((View.OnClickListener) this);
        layout=findViewById(R.id.main);

        btnBackground.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (colors==0) {
                    layout.setBackgroundColor(0xFF143141);
                    colors++;
                }
                else{
                    layout.setBackgroundColor(255);
                    colors=0;
                }

            }


        });


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) ->

        {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }




  public void onClick(View v){
            if(v.getId()==R.id.btnStartActivity) {
                Intent intent = new Intent(this, CalcActivity.class);
                activityResultLaunch.launch(intent);
            }

        }
    ActivityResultLauncher<Intent> activityResultLaunch = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {
                    if (result.getData() != null) {
                        float res = result.getData().getFloatExtra("data", 0);
                        Log.d("simple_app_tag", "Result: " + res);
                        tvTextValue.setText("Деление чисел: " + res);
                    }
                }
            }
    );




}


