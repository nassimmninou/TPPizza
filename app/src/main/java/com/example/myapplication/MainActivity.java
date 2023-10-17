package com.example.myapplication;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ShareCompat;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.adapter.StarAdapter;
import com.example.myapplication.beans.Star;
import com.example.myapplication.service.StarService;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private List<Star> stars;
    private RecyclerView recyclerView;
    private StarAdapter starAdapter = null;
    private Toolbar myToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        stars = new ArrayList<>();
        StarService service = StarService.getInstance();

        service.create(new Star("kate bosworth", "https://www.stars-photos.com/image.php?id=801", 3.5f));
        service.create(new Star("george clooney", "https://www.stars-photos.com/image.php?id=1191", 3));
        service.create(new Star("michelle rodriguez", "https://www.stars-photos.com/image.php?id=1120", 5));
        service.create(new Star("george clooney", "https://www.stars-photos.com/image.php?id=1193", 1));
        service.create(new Star("louise bouroin", "https://www.stars-photos.com/image.php?id=1185", 5));
        service.create(new Star("Ayoub Rakine", "https://media.licdn.com/dms/image/D4E03AQFnwGA7PMBQUg/profile-displayphoto-shrink_800_800/0/1684875485271?e=2147483647&v=beta&t=1IVXuukFVTEyYFMGsP5LjkIOumoM1tAVR462zjZ3eM0", 1));


        recyclerView = findViewById(R.id.hmida);
        starAdapter = new StarAdapter(this, service.findAll());
        recyclerView.setAdapter(starAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        MenuItem menuItem = menu.findItem(R.id.app_bar_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                if (starAdapter != null){
                    starAdapter.getFilter().filter(newText);
                }
                return true;
            }
        });
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.share){
            String txt = "Stars";
            String mimeType = "text/plain";
            ShareCompat.IntentBuilder
                    .from(this)
                    .setType(mimeType)
                    .setChooserTitle("Stars")
                    .setText(txt)
                    .startChooser();
        }
        return super.onOptionsItemSelected(item);
    }



}