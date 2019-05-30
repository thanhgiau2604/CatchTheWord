package group5.tkpmgd.catchtheword;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class GuideActivity extends AppCompatActivity {
    Button btnbackhome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        AddControl();
        AddEvents();
    }
    private void AddControl() {
        btnbackhome= this.<Button>findViewById(R.id.btnbackhome);
    }

    private void AddEvents() {
        btnbackhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
