package group5.tkpmgd.adapter;

import android.app.Activity;
import android.content.ContentValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import group5.tkpmgd.catchtheword.MainActivity;
import group5.tkpmgd.catchtheword.R;
import group5.tkpmgd.catchtheword.SavedvocActivity;
import group5.tkpmgd.model.Question;

public class VocabularyAdapter extends ArrayAdapter<Question> {
    Activity context;
    int resource;
    List<Question> objects;
    ImageView btnDelete;
    public VocabularyAdapter(Activity context, int resource, List<Question> objects) {
        super(context, resource, objects);
        this.context=context;
        this.resource=resource;
        this.objects=objects;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View row = inflater.inflate(resource,null);
        TextView txtVoc = (TextView)row.findViewById(R.id.tvVocabulary);
        TextView txtPro = row.<TextView>findViewById(R.id.tvPronounce);
        TextView txtMeaning = row.<TextView>findViewById(R.id.tvMeaning);
        btnDelete = row.<ImageView>findViewById(R.id.btnDelete);

        final Question question = this.objects.get(position);

        txtVoc.setText(question.getVocabulary());
        txtPro.setText(question.getPronounce());
        txtMeaning.setText(question.getMeaning());

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                XuLyXoaTuVung(question);
            }
        });
        return row;
    }

    private void XuLyXoaTuVung(Question question) {
        ContentValues row = new ContentValues();
        row.put("saved",0);
        MainActivity.database.update("question",row,"idquestion=?",new String[]{String.valueOf(question.getIdquestion())});
        notifyDataSetChanged();
        SavedvocActivity.LayDanhSachTuVungDaLuu();
    }
}
