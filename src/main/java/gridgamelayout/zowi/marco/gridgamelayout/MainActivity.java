package gridgamelayout.zowi.marco.gridgamelayout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void toGrid3x3(View v) {
        Intent intent = new Intent(this, Grid3x3.class);
        startActivity(intent);
    }

    public void toBeforeAfter(View v) {
        Intent intent = new Intent(this, BeforeAfter.class);
        startActivity(intent);
    }

}
