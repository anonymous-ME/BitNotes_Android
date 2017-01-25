package in.bitnotes.affan.bitnotes;

/**
 * Created by affan on 28/8/16.
 */
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;
    String url;

    public MyRecyclerAdapter(Context context, List<FeedItem> feedItemList) {
        this.feedItemList = feedItemList;
        this.mContext = context;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.list_row, null);

        CustomViewHolder viewHolder = new CustomViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        FeedItem feedItem = feedItemList.get(i);

        //Download image using picasso library
        Picasso.with(mContext).load(feedItem.getThumbnail())
                .error(R.drawable.placeholder_book)
                .placeholder(R.drawable.placeholder_book)
                .into(customViewHolder.imageView);

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        customViewHolder.id.setText(Html.fromHtml(feedItem.getId()));
        customViewHolder.views.setText(Html.fromHtml(feedItem.getViews()));
        //Handle click event on both title and image click
        customViewHolder.textView.setOnClickListener(clickListener);
        customViewHolder.imageView.setOnClickListener(clickListener);

        customViewHolder.textView.setTag(customViewHolder);
        customViewHolder.imageView.setTag(customViewHolder);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            CustomViewHolder holder = (CustomViewHolder) view.getTag();
            int position = holder.getPosition();

            FeedItem feedItem = feedItemList.get(position);
            Intent myIntent = new Intent(mContext, showPage.class);
            myIntent.putExtra("url", feedItem.getUrl()); //Optional parameters
            mContext.startActivity(myIntent);
           // Toast.makeText(mContext, feedItem.getTitle(), Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }
    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected TextView id;
        protected TextView views;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.id = (TextView) view.findViewById(R.id.id);
            this.views = (TextView) view.findViewById(R.id.views);
        }
    }
    public void delete(int position) {
        feedItemList.remove(position);
        notifyItemRemoved(position);
    }
}