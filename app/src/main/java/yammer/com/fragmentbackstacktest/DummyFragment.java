package yammer.com.fragmentbackstacktest;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by hongjiedong on 7/18/16.
 */
public class DummyFragment extends Fragment {

    ImageView bigimg;
    TextView textView;
    // A big memory chunk to make oom happens faster
    int[][] memo = new int[2048][2048];

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        bigimg = (ImageView) view.findViewById(R.id.image);
        textView = (TextView) view.findViewById(R.id.title);
        bigimg.setImageDrawable(getResources().getDrawable(R.drawable.image));
        textView.setText(text);
    }

    private static final String DESC = "DESC";
    private String text;
    private String TAG = this.getClass().getSimpleName();
    private static int count = 0;

    public static DummyFragment newInstance(String desc) {
        DummyFragment fragment = new DummyFragment();
        Bundle b = new Bundle();
        b.putString(DESC, desc);
        fragment.setArguments(b);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        text = getArguments().getString(DESC);
        count++;
        Log.e(TAG, "count " + count);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        Log.e(TAG, this.toString() + " finalize " + text);
        count--;
        Log.e(TAG, "count " + count);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.e(TAG, this.toString() + " onDestroyView " + text);
        bigimg = null;
        textView = null;
        // TODO Warning, if any view reference is not claimed here, the whole view will be in the memory!!
    }
}
