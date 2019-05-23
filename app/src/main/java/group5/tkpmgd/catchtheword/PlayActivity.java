package group5.tkpmgd.catchtheword;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

import group5.tkpmgd.model.Question;

public class PlayActivity extends AppCompatActivity {
    ImageView btnBack, btnKeyRed, btnKeyGreen, btnKeyBlue;
    public static ImageView imgQuestion;
    public static TextView tvScore;
    TextView tvQuestion;
    public static TextView tvKey;
    public static EditText txtAnswer;
    Button btnSubmit;
    public static ArrayList<Question> dsCauHoi;
    public static Question question;
    public static int pos,totalnumber,red,blue,green;

    static PlayActivity playActivity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        playActivity=this;
        AddControls();
        AddEvents();
        LayDanhSachCauHoi();
        LayCauHoi();
    }
    public static PlayActivity getInstance(){
        return  playActivity;
    }

    private void AddControls() {
        btnBack = this.<ImageView>findViewById(R.id.btnBack);
        imgQuestion = this.<ImageView>findViewById(R.id.imgQuestion);
        btnKeyRed = this.<ImageView>findViewById(R.id.btnKeyRed);
        btnKeyGreen= this.<ImageView>findViewById(R.id.btnKeyGreen);
        btnKeyBlue= this.<ImageView>findViewById(R.id.btnKeyBlue);
        tvQuestion = this.<TextView>findViewById(R.id.tvQuestion);
        tvScore = this.<TextView>findViewById(R.id.tvScore);
        tvKey= this.<TextView>findViewById(R.id.tvKey);
        txtAnswer= this.<EditText>findViewById(R.id.txtAnswer);
        btnSubmit= this.<Button>findViewById(R.id.btnSubmit);
        tvKey.setText("");
        tvScore.setText(String.valueOf(MainActivity.totalscore));


    }

    private void AddEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        btnKeyRed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (red==1) {
                    Toast.makeText(PlayActivity.this, "Bạn đã sử dụng gợi ý này!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MainActivity.totalscore<10)
                {
                    Toast.makeText(PlayActivity.this, "Số điểm bạn không đủ để thực " +
                            "hiện quyền gợi ý này!", Toast.LENGTH_SHORT).show();
                }
                else {
                    red=1;
                    MainActivity.totalscore -= 10;
                    tvKey.setText(question.getSuggestion1());
                    tvKey.setTextColor(Color.RED);
                    tvScore.setText(String.valueOf(MainActivity.totalscore));

                    ContentValues row = new ContentValues();
                    row.put("score",MainActivity.totalscore);
                    MainActivity.database.update("score",row,"playtimes=?",
                            new String[]{String.valueOf(MainActivity.playtime)});
                }
            }
        });
        btnKeyGreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (green==1) {
                    Toast.makeText(PlayActivity.this, "Bạn đã sử dụng gợi ý này!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MainActivity.totalscore<10)
                {
                    Toast.makeText(PlayActivity.this, "Số điểm bạn không đủ để thực " +
                            "hiện quyền gợi ý này!", Toast.LENGTH_SHORT).show();
                }
                else {
                    green=1;
                    MainActivity.totalscore -= 10;
                    tvKey.setText(question.getSuggestion2());
                    tvKey.setTextColor(Color.parseColor("#056905"));
                    tvScore.setText(String.valueOf(MainActivity.totalscore));

                    ContentValues row = new ContentValues();
                    row.put("score",MainActivity.totalscore);
                    MainActivity.database.update("score",row,"playtimes=?",
                            new String[]{String.valueOf(MainActivity.playtime)});
                }
            }
        });
        btnKeyBlue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blue==1) {
                    Toast.makeText(PlayActivity.this, "Bạn đã sử dụng gợi ý này!", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MainActivity.totalscore<10)
                {
                    Toast.makeText(PlayActivity.this, "Số điểm bạn không đủ để thực " +
                            "hiện quyền gợi ý này!", Toast.LENGTH_SHORT).show();
                }
                else {
                    blue=1;
                    MainActivity.totalscore -= 10;
                    tvKey.setText(question.getSuggestion3());
                    tvKey.setTextColor(Color.BLUE);
                    tvScore.setText(String.valueOf(MainActivity.totalscore));

                    ContentValues row = new ContentValues();
                    row.put("score",MainActivity.totalscore);
                    MainActivity.database.update("score",row,"playtimes=?",
                            new String[]{String.valueOf(MainActivity.playtime)});
                }
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String DapAn = question.getVocabulary();
                String Answer=txtAnswer.getText().toString().toLowerCase();
                if (Answer.equals(DapAn))
                {
                    MainActivity.totalscore+=10;
                    tvScore.setText(String.valueOf(MainActivity.totalscore));

                    ContentValues row = new ContentValues();
                    row.put("finished",1);
                    row.put("current",0);
                    long i= MainActivity.database.update("question",row,"idquestion=?",new String[]{String.valueOf(question.getIdquestion())});
                    dsCauHoi.remove(pos);
                    txtAnswer.setText("");
                    if (totalnumber==0)
                    {
                        ContentValues row1 = new ContentValues();
                        row1.put("finished",0);
                        MainActivity.database.update("question",row1,null,null);
                        Intent intent = new Intent(PlayActivity.this, CompleteActivity.class);
                        startActivity(intent);
                    }
                    else {
                        Intent intent = new Intent(PlayActivity.this, ResultActivity.class);
                        startActivity(intent);
                    }
                }
                else {
                    ContentValues row = new ContentValues();
                    row.put("finished",0);
                    long i = MainActivity.database.update("question",row,null,null);
                    Intent intent = new Intent(PlayActivity.this,EndgameActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    public static void LayDanhSachCauHoi()
    {
        tvScore.setText(String.valueOf(MainActivity.totalscore));
        Cursor cursor = MainActivity.database.query("question",null,"finished=?",new String[]{"0"},null,null,null);
        dsCauHoi = new ArrayList<>();
        dsCauHoi.clear();
        while (cursor.moveToNext())
        {
            Question question = new Question();
            question.setIdquestion(cursor.getInt(0));
            question.setImgquestion(cursor.getBlob(1));
            question.setSuggestion1(cursor.getString(2));
            question.setSuggestion2(cursor.getString(3));
            question.setSuggestion3(cursor.getString(4));
            question.setVocabulary(cursor.getString(5));
            question.setPronounce(cursor.getString(6));
            question.setMeaning(cursor.getString(7));
            question.setFinished(cursor.getInt(8));
            question.setCurrent(cursor.getInt(9));
            question.setSaved(cursor.getInt(10));
            dsCauHoi.add(question);
        }
        totalnumber=dsCauHoi.size();
        cursor.close(); //Đóng kết nối
    }

    public static void LayCauHoi()
    {
        pos=-1;
        for (int i = 0; i < dsCauHoi.size(); i++) {
            if (dsCauHoi.get(i).getCurrent()==1)
            {
                pos=i;
                break;
            }
        }
        Random ran;
        if (pos==-1) {
            ran = new Random();
            pos = ran.nextInt(totalnumber);

            while (dsCauHoi.get(pos).getFinished() == 1) {
                pos = ran.nextInt(totalnumber);
            }
        }
        totalnumber--;
        question = dsCauHoi.get(pos);
        byte[] byteArray = question.getImgquestion();
        Bitmap bm = BitmapFactory.decodeByteArray(byteArray, 0 ,byteArray.length);
        imgQuestion.setImageBitmap(bm);

        ContentValues row = new ContentValues();
        row.put("current",1);
        MainActivity.database.update("question",row,"idquestion=?",new String[]{String.valueOf(question.getIdquestion())});
    }
}
