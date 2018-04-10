package nexumcorp.projectnexum.utility;


import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import nexumcorp.projectnexum.MainActivity;
import nexumcorp.projectnexum.R;
import nexumcorp.projectnexum.favourites;
import nexumcorp.projectnexum.models.Post;
import nexumcorp.projectnexum.search;

/**
 * Created by blras on 22/03/2018.
 */

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.ViewHolder>{

    private static final String TAG="PostListAdapter";
    private static final int NUM_GRID_COLUMNS = 3;

    private ArrayList<Post> mPosts;
    private Context mContext;

    public class ViewHolder extends RecyclerView.ViewHolder{
        SquareImageView mPostImage;

        public ViewHolder(View itemView) {
            super(itemView);
            mPostImage=(SquareImageView) itemView.findViewById(R.id.post_image);

            int grdWidth =mContext.getResources().getDisplayMetrics().widthPixels;
            int imageWidth = grdWidth/NUM_GRID_COLUMNS;
            mPostImage.setMaxHeight(imageWidth);
            mPostImage.setMaxWidth(imageWidth);
        }
    }

    public PostListAdapter(Context mContext,ArrayList<Post> mPosts)  {
        this.mPosts = mPosts;
        this.mContext = mContext;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_view_post,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        UniversalImageLoader.setImage(mPosts.get(position).getImage(),holder.mPostImage);

        holder.mPostImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG,"onClick:Seected a post");
                //TODO

                //view post in more detail
                Fragment fragment= (Fragment)((MainActivity)mContext).getSupportFragmentManager()
                        .findFragmentByTag("android:switcher:"+R.id.viewpager_container+":" +
                                ((MainActivity)mContext).mViewPager.getCurrentItem());
                if(fragment != null){
                    //Search fragment
                    if(fragment.getTag().equals("android:switcher:"+R.id.viewpager_container+":0")){
                        Log.d(TAG,"onClick: Switching to: " + mContext.getString(R.string.fragment_view_post));

                        search search= (search) ((MainActivity)mContext).getSupportFragmentManager()
                                .findFragmentByTag("android:switcher:"+R.id.viewpager_container+":" +
                                        ((MainActivity)mContext).mViewPager.getCurrentItem());

                        search.viewPost(mPosts.get(position).getPost_id());
                    }
                    //favourite fragment
                    else if(fragment.getTag().equals("android:switcher:"+R.id.viewpager_container+":1")){
                        Log.d(TAG,"onClick: Switching to: " + mContext.getString(R.string.fragment_favourites));

                        favourites favourites= (nexumcorp.projectnexum.favourites) ((MainActivity)mContext).getSupportFragmentManager()
                                .findFragmentByTag("android:switcher:"+R.id.viewpager_container+":" +
                                        ((MainActivity)mContext).mViewPager.getCurrentItem());

                        favourites.viewPost(mPosts.get(position).getPost_id());
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mPosts.size();
    }


}
