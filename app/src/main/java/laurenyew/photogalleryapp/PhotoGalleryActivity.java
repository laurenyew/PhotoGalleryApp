package laurenyew.photogalleryapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import laurenyew.photogalleryapp.detail.PhotoDetailFragment;

public class PhotoGalleryActivity extends AppCompatActivity {

    public static final String IMAGE_ID_KEY = "imageId";

    private Integer[] imageIds =
            {
                    R.drawable.dog,
                    R.drawable.cat,
                    R.drawable.default_img
            };

    private int currentImageIndex = 0;

    private Integer getCurrentImage()
    {
        if(currentImageIndex < 0 || currentImageIndex > imageIds.length)
        {
            currentImageIndex = 0;
        }

        return imageIds[currentImageIndex];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        if(savedInstanceState == null) {
            //Setup initial fragment into layout
            if(findViewById(R.id.detail_fragment_container) != null)
            {
                PhotoDetailFragment detailFragment = new PhotoDetailFragment();

                //send detail fragment the id of the image to display
                Bundle detailBundle = new Bundle();
                detailBundle.putInt(IMAGE_ID_KEY, getCurrentImage());
                detailFragment.setArguments(detailBundle);

                //Replace fragment container view with fragment
                android.support.v4.app.FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.detail_fragment_container, detailFragment);
                fragmentTransaction.addToBackStack(null);
                fragmentTransaction.commit();
            }

            //Setup toolbar and floating action button
            //TODO: Add to toolbar
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
            setSupportActionBar(toolbar);

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_photo);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Add feature to add a photo
                    Snackbar.make(view, "Add a Photo", Snackbar.LENGTH_LONG)
                            .setAction("Add a Photo", null).show();
                }
            });
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_gallery, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
