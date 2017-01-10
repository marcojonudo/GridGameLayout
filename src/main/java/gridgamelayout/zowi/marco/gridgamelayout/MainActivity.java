package gridgamelayout.zowi.marco.gridgamelayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[][] coordinates = new int[10][2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_grid_3x3);

        GridLayout gameGrid = (GridLayout) findViewById(R.id.game_grid);

        gameGrid.getViewTreeObserver().addOnGlobalLayoutListener(
            new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    GridLayout gameGrid = (GridLayout) findViewById(R.id.game_grid);

                    coordinates[0][0] = gameGrid.getLeft();
                    coordinates[0][1] = gameGrid.getTop();
                    // 492,36

                    int halfCell = gameGrid.getWidth()/6;

                    for (int i=0; i<gameGrid.getChildCount(); i++) {
                        View gameGridChild = gameGrid.getChildAt(i);

                        coordinates[i+1][0] = gameGridChild.getLeft() + gameGridChild.getWidth()/2;
                        coordinates[i+1][1] = gameGridChild.getTop() + gameGridChild.getHeight()/2;

                        coordinates[i+1][0] = coordinates[0][0] + (((i%3)*2 + 1)*halfCell) - 75;
                        coordinates[i+1][1] = coordinates[0][1] + (((i/3)*2 + 1)*halfCell) - 75;
                    }
                    insertZowi();
                    gameGrid.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
    }

    private void insertZowi() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.game_grid_3x3);

        ImageView zowiImage = new ImageView(this);
        zowiImage.setImageResource(R.drawable.zowi_pointer);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(150, 150);
        zowiImage.setLayoutParams(layoutParams);
        zowiImage.setX(coordinates[7][0]);
        zowiImage.setY(coordinates[7][1]);
        zowiImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                TranslateAnimation animation = new TranslateAnimation(0, coordinates[8][0]-coordinates[7][0], 0, 0);
                animation.setDuration(1000);
                animation.setFillAfter(true);

                v.startAnimation(animation);
            }
        });

        relativeLayout.addView(zowiImage);
    }

}
