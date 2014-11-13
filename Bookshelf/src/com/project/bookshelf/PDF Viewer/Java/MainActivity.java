package project.bookshelf;

/**
 * Created by Kevan Lester on 11/12/2014.
 *
 */
//the import needs to be added but not sure what the package path will be
import project.bookshelf.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
public class MainActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        Button simpleViewerBtn = (Button) findViewById(R.id.simpleviewer);
        simpleViewerBtn.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        SimpleReaderActivity.class);
                startActivity(intent);
            }
        });



    }
}
