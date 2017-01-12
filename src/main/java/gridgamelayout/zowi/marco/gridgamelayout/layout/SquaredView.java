package gridgamelayout.zowi.marco.gridgamelayout.layout;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Marco on 12/01/2017.
 */
public class SquaredView extends View {

    public SquaredView(Context context) {
        super(context);
    }

    public SquaredView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquaredView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int size;
        if (widthMeasureSpec > heightMeasureSpec) {
            size = widthMeasureSpec;
        }
        else {
            size = heightMeasureSpec;
        }
        super.onMeasure(size, size);
    }

}
