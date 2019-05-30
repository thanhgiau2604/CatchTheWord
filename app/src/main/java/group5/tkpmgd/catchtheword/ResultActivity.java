package group5.tkpmgd.catchtheword;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {
    ImageView btnAdd, btnNotAdd;
    TextView tvThongBao, tvVoc, tvPro, tvMean, tvScore;
    Button btnContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        AddControl();
        AddEvents();
    }
    private void AddControl() {
        btnAdd= this.<ImageView>findViewById(R.id.btnAdd);
        btnNotAdd= this.<ImageView>findViewById(R.id.btnNotAdd);
        tvThongBao= this.<TextView>findViewById(R.id.tvThongBao);
        tvVoc= this.<TextView>findViewById(R.id.tvVoc);
        tvPro= this.<TextView>findViewById(R.id.tvPro);
        tvMean= this.<TextView>findViewById(R.id.tvMean);
        btnContinue= this.<Button>findViewById(R.id.btnContinue);
        tvScore = this.<TextView>findViewById(R.id.tvScore);
        tvScore.setText(String.valueOf(MainActivity.totalscore));
        LayDuLieu();

    }

    private void AddEvents() {
        btnContinue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (PlayActivity.totalnumber==0)
                {
                    Toast.makeText(ResultActivity.this, "Bạn đã hoàn thành, tổng số " +
                            "điểm của bạn là "+String.valueOf(MainActivity.totalscore), Toast.LENGTH_SHORT).show();

                    ContentValues row = new ContentValues();
                    row.put("finished",0);
                    long i = MainActivity.database.update("question",row,null,null);
                }
                else
                {
                    onBackPressed();
                    PlayActivity.txtAnswer.setText("");
                    PlayActivity.tvKey.setText("");
                    PlayActivity.red=PlayActivity.blue=PlayActivity.green=0;
                    PlayActivity.LayCauHoi();
                }
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd.setVisibility(View.GONE);
                btnNotAdd.setVisibility(View.VISIBLE);
                ContentValues row = new ContentValues();
                row.put("saved",1);
                MainActivity.database.update("question",row,"idquestion=?",
                        new String[]{String.valueOf(PlayActivity.question.getIdquestion())});
                Toast.makeText(ResultActivity.this, "Đã lưu từ vựng", Toast.LENGTH_SHORT).show();
            }
        });
        btnNotAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                btnAdd.setVisibility(View.VISIBLE);
                btnNotAdd.setVisibility(View.GONE);
                ContentValues row = new ContentValues();
                row.put("saved",0);
                MainActivity.database.update("question",row,"idquestion=?",
                        new String[]{String.valueOf(PlayActivity.question.getIdquestion())});
                Toast.makeText(ResultActivity.this, "Đã hủy lưu từ vựng", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void LayDuLieu()
    {
        tvVoc.setText(PlayActivity.question.getVocabulary());
        tvPro.setText(PlayActivity.question.getPronounce());
        tvMean.setText(PlayActivity.question.getMeaning());
        tvThongBao.setText("Congratulation, 10 point for you");

        ContentValues row = new ContentValues();
        row.put("totalscore",MainActivity.totalscore);
        long i = MainActivity.database.update("score",row,"playtimes=?",
                new String[]{String.valueOf(MainActivity.playtime)});

        Cursor cursor = MainActivity.database.query("question",null,
                "idquestion=?",new String[]{String.valueOf(PlayActivity.question.getIdquestion())},
                null,null,null);

        int Saved=0;
        if (cursor.moveToNext())
        {
            Saved = cursor.getInt(10);
        }
        if (Saved==1)
        {
            btnAdd.setVisibility(View.GONE);
            btnNotAdd.setVisibility(View.VISIBLE);
        }
        else
        {
            btnAdd.setVisibility(View.VISIBLE);
            btnNotAdd.setVisibility(View.GONE);
        }
    }
}
