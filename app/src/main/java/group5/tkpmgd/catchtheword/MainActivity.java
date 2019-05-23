package group5.tkpmgd.catchtheword;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import group5.tkpmgd.model.Score;

public class MainActivity extends AppCompatActivity {
    Button btnPlay, btnGuide, btnSaved;
    public  static String DATABASE_NAME = "dbcatchword.sqlite";
    String DB_PATH_SUFFIX = "/databases/";
    public  static SQLiteDatabase database = null;

    public static int totalscore;

    public static int playtime=0;

    public static Score score;

    public static Boolean isCurrent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        xuLySaoChepSQLTuAssetVaoHeThongMobile();
        MoKetNoiCSDL();
        AddControls();
        AddEvents();
    }

    private void AddControls() {
        btnPlay = this.<Button>findViewById(R.id.btnPlay);
        btnSaved= this.<Button>findViewById(R.id.btnsave);
        btnGuide = this.<Button>findViewById(R.id.btnguide);
        totalscore=30;
        Cursor cursor = MainActivity.database.query("score",null,null,null,null,null,null);
        isCurrent=false;
        while (cursor.moveToNext())
        {
            playtime+=1;
            if (cursor.getInt(2)==1)
            {
                isCurrent = true;
                score = new Score();
                score.setPlaytime(cursor.getInt(0));
                score.setScore(cursor.getInt(1));
                score.setCurrent(cursor.getInt(2));

                totalscore=score.getScore();
                break;
            }
        }
        if (!isCurrent) playtime+=1;

        cursor.close();

    }

    private void AddEvents() {
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCurrent) {
                    ContentValues row = new ContentValues();
                    row.put("score", MainActivity.totalscore);
                    row.put("current", 1);
                    MainActivity.database.insert("score", null, row);
                }
                Intent intent = new Intent(MainActivity.this,PlayActivity.class);
                startActivity(intent);
            }
        });
        btnSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SavedvocActivity.class);
                startActivity(intent);
            }
        });
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,GuideActivity.class);
                startActivity(intent);
            }
        });
    }
    //Hàm xao chép file sqlite từ thư mục Asset vào hệ thống điện thoại
    private void xuLySaoChepSQLTuAssetVaoHeThongMobile() {
        File dbFile = getDatabasePath(DATABASE_NAME);
        //Kiểm tra có tồn tại database
        if (!dbFile.exists())
        {
            try
            {
                CopyDatabaseFromAsset();
                /*Toast.makeText(this,"Sao chép CSDL vào hệ thống thành công",Toast.LENGTH_LONG).show();*/
            }
            catch (Exception e)
            {
                Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
            }
        }
    }

    private void CopyDatabaseFromAsset() {
        try
        {
            //Lấy dữ liệu trong asset
            InputStream myInput = getAssets().open(DATABASE_NAME);
            //Lấy đường dẫn output;
            String outFileName = layDuongDanLuuTru();

            File f = new File(getApplicationInfo().dataDir+DB_PATH_SUFFIX);
            //Kiểm tra file có tồn tại đường dẫn
            if (!f.exists())
            {
                f.mkdir(); //Không tồn tại thì tạo mới
            }

            OutputStream myOutput = new FileOutputStream(outFileName);
            byte[] buffer = new byte[1024];
            int length;
            //Chép input vào ouput mỗi lần 1024 byte
            while ((length=myInput.read(buffer))>0) {
                myOutput.write(buffer, 0, length);
            }
            //Đóng kết nối
            myOutput.flush();
            myOutput.close();
            myInput.close();
        }
        catch (Exception ex)
        {
            Log.e("Lỗi sao chép:",ex.toString());
        }
    }
    private String layDuongDanLuuTru() {
        return getApplicationInfo().dataDir+DB_PATH_SUFFIX+DATABASE_NAME;
    }

    private void MoKetNoiCSDL()
    {
        //Bước 1: mở CSDL
        database = openOrCreateDatabase(DATABASE_NAME,MODE_PRIVATE,null);
    }
}
