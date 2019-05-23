package group5.tkpmgd.catchtheword;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;

import group5.tkpmgd.adapter.VocabularyAdapter;
import group5.tkpmgd.model.Question;

public class SavedvocActivity extends AppCompatActivity {

    ListView lvVocabulary;
    public static ArrayList<Question> dsVocabulary;
    public static VocabularyAdapter adapterVocabulary;

    ImageView btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_savedvoc);
        AddControls();
        AddEvents();
    }

    private void AddControls() {
        btnBack = this.<ImageView>findViewById(R.id.btnBack);
        lvVocabulary = this.<ListView>findViewById(R.id.lvVoc);
        dsVocabulary = new ArrayList<>();
        adapterVocabulary = new VocabularyAdapter(SavedvocActivity.this,R.layout.item_vocabulary,dsVocabulary);
        lvVocabulary.setAdapter(adapterVocabulary);
        LayDanhSachTuVungDaLuu();
    }

    public static void LayDanhSachTuVungDaLuu() {
        Cursor cursor = MainActivity.database.query("question",null,"saved=?",new String[]{"1"},null,null,null);
        dsVocabulary.clear();
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
            dsVocabulary.add(question);
        }
        cursor.close(); //Đóng kết nối
        adapterVocabulary.notifyDataSetChanged();

    }

    private void AddEvents() {
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}
