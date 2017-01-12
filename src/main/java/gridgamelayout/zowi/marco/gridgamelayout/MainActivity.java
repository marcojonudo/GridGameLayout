package gridgamelayout.zowi.marco.gridgamelayout;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private int[][] coordinates = new int[10][2];
    private MainActivity context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_grid_3x3);
        this.context = this;

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

        Button controls = (Button) findViewById(R.id.controls);
        controls.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        float x = event.getX();
                        float y = event.getY();
                        float rightCorner = v.getWidth();

                        if (x>y) {
                            if (y < (rightCorner-x)) {
                                Toast.makeText(context, "Zona 1", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Zona 2", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            if (y > (rightCorner-x)) {
                                Toast.makeText(context, "Zona 3", Toast.LENGTH_SHORT).show();
                            }
                            else {
                                Toast.makeText(context, "Zona 4", Toast.LENGTH_SHORT).show();
                            }
                        }
                        break;
                    default:
                        break;
                }

                return true;
            }
        });
    }

    private void insertZowi() {
        RelativeLayout relativeLayout = (RelativeLayout) findViewById(R.id.content);

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
                AnimationSet set = new AnimationSet(true);

                TranslateAnimation translateAnimation = new TranslateAnimation(0, 0, 0, coordinates[7][0]-coordinates[8][0]);
                translateAnimation.setDuration(1000);
                set.addAnimation(translateAnimation);

                RotateAnimation rotateAnimation = new RotateAnimation(0, 90, coordinates[4][0]+v.getWidth()/2, coordinates[4][1]+v.getHeight()/2);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setStartOffset(1000);
                set.addAnimation(rotateAnimation);

                translateAnimation = new TranslateAnimation(0, coordinates[8][0]-coordinates[7][0], 0, 0);
                translateAnimation.setDuration(1000);
                translateAnimation.setStartOffset(2000);
                set.addAnimation(translateAnimation);

                rotateAnimation = new RotateAnimation(0, -90, coordinates[5][0]+v.getWidth()/2, coordinates[5][1]+v.getHeight()/2);
                rotateAnimation.setDuration(1000);
                rotateAnimation.setStartOffset(3000);
                set.addAnimation(rotateAnimation);

                translateAnimation = new TranslateAnimation(0, 0, 0, coordinates[7][0]-coordinates[8][0]);
                translateAnimation.setDuration(1000);
                translateAnimation.setStartOffset(4000);
                set.addAnimation(translateAnimation);

                set.setFillAfter(true);

                v.startAnimation(set);
            }
        });

        relativeLayout.addView(zowiImage);
    }

    public void moveZowi(View v) {
        Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
    }

}
