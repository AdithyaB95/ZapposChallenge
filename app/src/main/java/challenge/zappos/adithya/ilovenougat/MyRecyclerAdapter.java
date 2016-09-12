package challenge.zappos.adithya.ilovenougat;

/**
 * Created by adithya95 on 09-10-2016.
 * This class acts as a RecyclerView adapter. It also compares prices between Zappos and 6pm!
 */
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.squareup.picasso.Picasso;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

public class MyRecyclerAdapter extends RecyclerView.Adapter<MyRecyclerAdapter.CustomViewHolder> {
    private List<FeedItem> feedItemList;
    private Context mContext;
    private static final String TAG = "RecyclerViewExample";


    private String pm;
    String r;
   public Double zapposRate;
    String u;



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
                .error(R.drawable.zappos)
                .placeholder(R.drawable.zappos)
                .into(customViewHolder.imageView);

        //Setting text view title
        customViewHolder.textView.setText(Html.fromHtml(feedItem.getTitle()));
        customViewHolder.priceView.setText(Html.fromHtml(feedItem.getPrice()));





        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomViewHolder holder = (CustomViewHolder) view.getTag();
                int position = holder.getPosition();
                FeedItem feedItem = feedItemList.get(position);


                pm = feedItem.getTitle();

                if(pm.contains("[®]")){
                    pm = pm.replaceAll("[®]","");
                }


                r = feedItem.getPrice();

                String result = r.replaceAll("[$]","");

                zapposRate = Double.parseDouble(result);





                u = "https://api.6pm.com/Search?term=%3C"+pm+"%3E&key=524f01b7e2906210f7bb61dcbe1bfea26eb722eb";

                //Toast.makeText(mContext, u, Toast.LENGTH_SHORT).show();

                new AsyncHttpTask().execute(u);





            }
        };

        customViewHolder.textView.setOnClickListener(clickListener);
        customViewHolder.imageView.setOnClickListener(clickListener);
        customViewHolder.priceView.setOnClickListener(clickListener);

        customViewHolder.textView.setTag(customViewHolder);
        customViewHolder.priceView.setTag(customViewHolder);
        customViewHolder.imageView.setTag(customViewHolder);
    }

    @Override
    public int getItemCount() {


        return (null != feedItemList ? feedItemList.size() : 0);


    }

    public class AsyncHttpTask extends AsyncTask<String, Void, String> {

        @Override
        protected void onPreExecute() {

        }

        @Override
        protected String doInBackground(String... params) {
            Integer result = 0;
            HttpURLConnection urlConnection;
            try {
                URL url = new URL(params[0]);
                urlConnection = (HttpURLConnection) url.openConnection();
                int statusCode = urlConnection.getResponseCode();

                // 200 represents HTTP OK
                if (statusCode == 200) {
                    BufferedReader r = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = r.readLine()) != null) {
                        response.append(line);
                    }


                   result = 1;
                    return response.toString();
                                // Successful
                } else {
                    result = 0; //"Failed to fetch data!";
                }


            } catch (Exception e) {
                Log.d(TAG, e.getLocalizedMessage());
            }
            return  ""; //"Failed to fetch data!";
        }

        @Override
        protected void onPostExecute(String res) {

             //   Toast.makeText(mContext, res, Toast.LENGTH_SHORT).show();

            comparePrices(res);

        }
    }

    private void comparePrices(String res){

        String pM_result = res;
        int flag = 0;
        Double zapp_price = zapposRate;
        Double pm_rate;

        try {
            JSONObject response = new JSONObject(pM_result);
            JSONArray posts = response.optJSONArray("results");

            if(posts.length()==0){
                Toast.makeText(mContext, "The product was not found on 6pm", Toast.LENGTH_SHORT).show();
            }
            else {

                for (int i = 0; i < posts.length(); i++) {
                    JSONObject post = posts.optJSONObject(i);


                   String pm_price = post.optString("price");

                    String pr = pm_price.replaceAll("[$]","");

                    pm_rate = Double.parseDouble(pr);

                    if(zapp_price>pm_rate){
                        flag = 1;
                    }


                }
            }

            if(flag==1){
                Toast.makeText(mContext, "The product is cheaper on 6pm!", Toast.LENGTH_SHORT).show();
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }
    }



    public class CustomViewHolder extends RecyclerView.ViewHolder {
        protected ImageView imageView;
        protected TextView textView;
        protected TextView priceView;

        public CustomViewHolder(View view) {
            super(view);
            this.imageView = (ImageView) view.findViewById(R.id.thumbnail);
            this.textView = (TextView) view.findViewById(R.id.title);
            this.priceView = (TextView) view.findViewById(R.id.price);
        }
    }





}







