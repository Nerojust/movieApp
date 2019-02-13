package com.nerojust.movieapp;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    private List<Slide> lstSlides;
    private ViewPager sliderpager;
    private TabLayout indicator;
    private List<Movie> movieList;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sliderpager = findViewById(R.id.slider_pager);
        indicator = findViewById(R.id.indicator);
        recyclerView = findViewById(R.id.movies_recyclerview);
        movieList = new ArrayList<>();

        // prepare a list of slides ..
        lstSlides = new ArrayList<>();
        lstSlides.add(new Slide(R.drawable.slide1, "Slide Title \n more text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide Title \n more text here"));
        lstSlides.add(new Slide(R.drawable.slide1, "Slide Title \n more text here"));
        lstSlides.add(new Slide(R.drawable.slide2, "Slide Title \n more text here"));

        SliderPagerAdapter adapter = new SliderPagerAdapter(this, lstSlides);
        sliderpager.setAdapter(adapter);

        // setup timer
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new SliderTimer(), 4000, 6000);

        indicator.setupWithViewPager(sliderpager, true);


        movieList.add(new Movie("Abduction", R.drawable.abduction));
        movieList.add(new Movie("Aquaman", R.drawable.aquaman));
        movieList.add(new Movie("Beauty and the Beast", R.drawable.beautyandbeast));
        movieList.add(new Movie("Cutting Edge", R.drawable.cuttingedge));
        movieList.add(new Movie("Avengers", R.drawable.avengers));
        movieList.add(new Movie("BumbleBee", R.drawable.bumblebee));
        movieList.add(new Movie("Shazam", R.drawable.shazam));


        MoviesAdapter moviesAdapter = new MoviesAdapter(this, movieList);
        recyclerView.setAdapter(moviesAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

        recyclerView.setLayoutManager(linearLayoutManager);

    }

    class SliderTimer extends TimerTask {

        @Override
        public void run() {

            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    if (sliderpager.getCurrentItem() < lstSlides.size() - 1) {
                        sliderpager.setCurrentItem(sliderpager.getCurrentItem() + 1);
                    } else
                        sliderpager.setCurrentItem(0);
                }
            });


        }
    }


}
