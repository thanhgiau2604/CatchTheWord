package group5.tkpmgd.catchtheword;

import android.content.ContentValues;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CompleteActivity extends AppCompatActivity {
    TextView tvScore,tvTotalScore;
    ImageView btnBack;
    Button btnReplay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete);
        AddControls();
        AddEvents();
    }

    private void AddControls() {
        btnBack= this.<ImageView>findViewById(R.id.btnBack);
        tvScore= this.<TextView>findViewById(R.id.tvScore);
        btnReplay=findViewById(R.id.btnRep);
        tvTotalScore=findViewById(R.id.tvTotalScore);
        tvScore.setText(String.valueOf(MainActivity.totalscore));
        tvTotalScore.setText("TOTAL SCORE: "+String.valueOf(MainActivity.totalscore));
    }

    private void AddEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.totalscore=30;
                PlayActivity.tvKey.setText("");
                PlayActivity.txtAnswer.setText("");
                ContentValues row = new ContentValues();
                row.put("score", MainActivity.totalscore);
                row.put("current", 1);
                MainActivity.database.insert("score", null, row);
                onBackPressed();
                PlayActivity.getInstance().finish();
            }
        });
        btnReplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MainActivity.totalscore=30;
                PlayActivity.tvKey.setText("");
                PlayActivity.txtAnswer.setText("");
                PlayActivity.LayDanhSachCauHoi();
                PlayActivity.LayCauHoi();
                ContentValues row = new ContentValues();

                row.put("score", MainActivity.totalscore);
                row.put("current", 1);
                MainActivity.database.insert("score", null, row);
                onBackPressed();
            }
        });
    }
}
