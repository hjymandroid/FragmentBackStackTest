package yammer.com.fragmentbackstacktest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    private void addFragment() {
        String title = String.valueOf(++count);
        DummyFragment dummyFragment = DummyFragment.newInstance(title);
        getSupportFragmentManager().beginTransaction().add(R.id.content_fragment, dummyFragment, title).addToBackStack(title).commit();
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
}
