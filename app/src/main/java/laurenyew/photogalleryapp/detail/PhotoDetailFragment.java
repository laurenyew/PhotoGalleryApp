package laurenyew.photogalleryapp.detail;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.AppCompatImageView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import laurenyew.photogalleryapp.PhotoDetailPagerAdapter;
import laurenyew.photogalleryapp.PhotoGalleryActivity;
import laurenyew.photogalleryapp.R;

/**
 * This Fragment will show a given picture in the view
 *
 * //TODO: Check fragment lifecyle to make sure that imageId passing is correct
 */
public class PhotoDetailFragment extends Fragment {

    public enum SWIPE_DIRECTION{
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }
    //id of the drawable picture that this fragment will show
    private Integer imageId = -1;

    private GestureDetector gestureDetector = new GestureDetector(getActivity(), new GestureDetector.SimpleOnGestureListener()
    {
        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {

            final int MAX_THRESHOLD_FOR_HORIZONTAL_SWIPE = 200;

            //This is a horizontal swipe
            if((e2.getY() - e1.getY()) < MAX_THRESHOLD_FOR_HORIZONTAL_SWIPE)
            {
                //swipe left to right
                if((e2.getX() - e1.getX()) > 0)
                {

                }
                //swipe right to left
                else
                {

                }
            }
            return super.onFling(e1, e2, velocityX, velocityY);
        }
    });

    public PhotoDetailFragment()
    {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if(container == null)
        {
            return null;
        }

        //Load the given image into the image view
        imageId = getArguments().getInt(PhotoDetailPagerAdapter.IMAGE_ID_KEY, R.drawable.default_img);
        View fragmentView = inflater.inflate(R.layout.fragment_photo_detail, container, false);
        fragmentView.findViewById(R.id.image_detail).setBackgroundResource(imageId);
        TextView textView = (TextView) fragmentView.findViewById(R.id.image_info);
        textView.setText(String.valueOf(imageId));

        return fragmentView;
    }



}
