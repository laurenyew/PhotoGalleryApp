package laurenyew.photogalleryapp;

import android.opengl.Visibility;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ActionMenuView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import laurenyew.photogalleryapp.detail.PhotoDetailFragment;
import laurenyew.photogalleryapp.list.ImageListLinearLayoutManager;

public class PhotoGalleryActivity extends AppCompatActivity {

    private enum VIEW_TYPE {
        DETAIL, LIST, GALLERY
    }

    private VIEW_TYPE currentViewType = VIEW_TYPE.DETAIL;

    //TOOLBAR MENU
    private ActionMenuView toolbarMenuView;

    //DETAIL VIEW PAGER
    private ViewPager imageDetailPager;
    private PagerAdapter imageDetailPagerAdapter;

    //RECYCLER LIST VIEW
    private RecyclerView recyclerView;
    ImageListLinearLayoutManager imageListLinearLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_gallery);

        if(savedInstanceState == null) {

            //DETAIL PAGER
            imageDetailPager = (ViewPager) findViewById(R.id.image_detail_view_pager);
            imageDetailPagerAdapter = new PhotoDetailPagerAdapter(getSupportFragmentManager());
            imageDetailPager.setAdapter(imageDetailPagerAdapter);

            //RECYCLER VIEW
            recyclerView = (RecyclerView) findViewById(R.id.image_list_recycler_view);
            recyclerView.setHasFixedSize(true);
            imageListLinearLayoutManager = new ImageListLinearLayoutManager(this);
            imageListLinearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(imageListLinearLayoutManager);

            //Setup toolbar and floating action button
            Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_bottom);
            setSupportActionBar(toolbar);
            toolbarMenuView = (ActionMenuView) findViewById(R.id.toolbar_bottom_menu_container);
            toolbarMenuView.setOnMenuItemClickListener(new ActionMenuView.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    return onOptionsItemSelected(item);
                }
            });

            FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.add_photo);
            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //TODO: Add feature to add a photo
                    Snackbar.make(view, "Add a Photo", Snackbar.LENGTH_LONG)
                            .setAction("Add a Photo", null).show();
                }
            });


            //SET UP DEFAULT VIEW
            currentViewType = VIEW_TYPE.DETAIL;
            showViewType(currentViewType);
        }

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_gallery, toolbarMenuView.getMenu());
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //TODO: Modularize the views so that we don't have to load them unless they're being shown.

        VIEW_TYPE oldViewType = currentViewType;
        switch(id)
        {
            case R.id.action_gallery_page: break;
            case R.id.action_list_page: {
                System.out.println("Open List View");
                currentViewType = VIEW_TYPE.LIST;
                break;

            }
            case R.id.action_detail_page: {
                System.out.println("Open Detail Pager");
                currentViewType = VIEW_TYPE.DETAIL;
                break;
            }
            case R.id.action_settings: break;
            default: break;
        }

        if(currentViewType != oldViewType)
        {
            System.out.println("Changed view type to: " + currentViewType);
            showViewType(currentViewType);
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Temporary helper method.
     *
     * Show/Hide other views accordingly.
     *
     * TODO: We will need to change this so that instead of hiding/showing
     * views we can deal with the dffferent features in a more modular fashion.
     *
     * @param viewType
     */
    private void showViewType(VIEW_TYPE viewType)
    {
        System.out.println("Show view type: " + viewType);

        findViewById(R.id.image_detail_view_pager).setVisibility((VIEW_TYPE.DETAIL == currentViewType)? View.VISIBLE : View.INVISIBLE);
        findViewById(R.id.image_list_recycler_view).setVisibility((VIEW_TYPE.LIST == currentViewType)? View.VISIBLE : View.INVISIBLE);
    }
}
