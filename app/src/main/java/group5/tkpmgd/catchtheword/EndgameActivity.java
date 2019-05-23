package group5.tkpmgd.catchtheword;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class EndgameActivity extends AppCompatActivity {
    ImageView btnBack;
    TextView tvScore,tvCurScore,tvBestScore;
    Button btnReplay,btnHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_endgame);
        AddControl();
        AddEvents();
        CapNhatVaoCSDL();
    }
    private void AddControl() {
        btnBack = this.<ImageView>findViewById(R.id.btnBack);
        tvScore= this.<TextView>findViewById(R.id.tvScore);
        tvCurScore= this.<TextView>findViewById(R.id.txtCursScore);
        tvBestScore= this.<TextView>findViewById(R.id.txtBestScore);
        btnReplay= this.<Button>findViewById(R.id.btnReplay);
        btnHome= this.<Button>findViewById(R.id.btnHome);
        tvScore.setText(String.valueOf(MainActivity.totalscore));
        tvCurScore.setText(String.valueOf(MainActivity.totalscore));
    }

    private void AddEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
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
        btnHome.setOnClickListener(new View.OnClickListener() {
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
    }

    private void CapNhatVaoCSDL()
    {
        ContentValues row = new ContentValues();
        row.put("current",0);
        MainActivity.database.update("score",row,"playtimes=?",
                new String[]{String.valueOf(MainActivity.playtime)});


        //tìm điểm cao nhất
        Cursor cursor = MainActivity.database.query("score",null,null,
                null,null,null,null);
        int MaxScore=0;
        while (cursor.moveToNext())
        {
            if (cursor.getInt(1)>MaxScore)
                MaxScore=cursor.getInt(1);
        }
        tvBestScore.setText("YOUR BEST SCORE "+String.valueOf(MaxScore));
    }
}
