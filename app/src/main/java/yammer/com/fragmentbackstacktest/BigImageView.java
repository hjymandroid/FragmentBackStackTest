package yammer.com.fragmentbackstacktest;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 * Created by hongjiedong on 7/18/16.
 */
public class BigImageView extends ImageView {
    String TAG = BigImageView.this.getClass().getSimpleName();
    static int count = 0;

    public BigImageView(Context context) {
        this(context, null, 0, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BigImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        count++;
        Log.e(TAG, "image " + count);
    }

    public BigImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public BigImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0, 0);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        count--;
        Log.e(TAG, "image claimed " + count);
    }
}
