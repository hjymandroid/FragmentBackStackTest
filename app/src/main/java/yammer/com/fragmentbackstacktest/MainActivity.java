package yammer.com.fragmentbackstacktest;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    String TAG = "MAIN";
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void addFragment() {
        String title = String.valueOf(++count);
        DummyFragment dummyFragment = DummyFragment.newInstance(title);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.content_fragment, dummyFragment, title);
        ft.addToBackStack(title);

        ft.commitAllowingStateLoss();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.addlayer:
                addFragment();
                return true;
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        // Find check backstack

        FragmentManager fm = getSupportFragmentManager();
        int count = fm.getBackStackEntryCount();
        Log.e(TAG, "stack size" + String.valueOf(count));
        if (count == 0) { // only 1 view on it, lets clean it
            finish();
        } else {
            // lets roll back to previous fragment
            FragmentManager.BackStackEntry entry = fm.getBackStackEntryAt(count - 1);
            // Must use 0 here, since we want to respect the order strictly
            fm.popBackStack(entry.getName(), 0);
        }
    }
}
