package laurenyew.photogalleryapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import laurenyew.photogalleryapp.detail.PhotoDetailFragment;

public class PhotoDetailPagerAdapter extends FragmentStatePagerAdapter {

    private FragmentManager fragmentManager;
    public static final String IMAGE_ID_KEY = "imageId";

    //Normally this should go into a cache/manager pattern, but we're shortcutting this for now.
    private Integer[] imageIds =
            {
                    R.drawable.dog,
                    R.drawable.cat,
                    R.drawable.default_img
            };

    public Integer getImageAtPosition(int position)
    {
        if(position < 0 || position > imageIds.length)
        {
            position = 0;
        }

        return imageIds[position];
    }


    public PhotoDetailPagerAdapter(FragmentManager fragmentManager)
    {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
    }

    @Override
    public Fragment getItem(int position) {

            //Setup initial fragment into layout
                PhotoDetailFragment detailFragment = new PhotoDetailFragment();

                //send detail fragment the id of the image to display
                Bundle detailBundle = new Bundle();
                detailBundle.putInt(IMAGE_ID_KEY, getImageAtPosition(position));
        detailFragment.setArguments(detailBundle);

        return detailFragment;
    }

    @Override
    public int getCount() {
        return imageIds.length;
    }
}
