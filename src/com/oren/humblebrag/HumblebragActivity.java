package com.oren.humblebrag;

import java.net.URL;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/* Notes:
 * For this assignment we'll be iterating through a JSON array of objects that look like this . . .
  {
  "in_reply_to_user_id_str": null,
  "id_str": "212401168461217793",
  "in_reply_to_screen_name": null,
  "in_reply_to_user_id": null,
  "in_reply_to_status_id": null,
  "retweeted": false,
  "retweet_count": 40,
  "truncated": false,
  "created_at": "Tue Jun 12 04:29:19 +0000 2012",
  "retweeted_status": {
    "in_reply_to_user_id_str": null,
    "id_str": "212150002372444160",
    "in_reply_to_screen_name": null,
    "in_reply_to_user_id": null,
    "in_reply_to_status_id": null,
    "retweeted": false,
    "retweet_count": 40,
    "truncated": false,
    "created_at": "Mon Jun 11 11:51:16 +0000 2012",
    "user": {
      "id": 18713254,
      "statuses_count": 8401,
      "profile_background_image_url_https": "https:\/\/si0.twimg.com\/profile_background_images\/391143336\/pegg.jpg",
      "default_profile_image": false,
      "favourites_count": 10,
      "profile_background_image_url": "http:\/\/a0.twimg.com\/profile_background_images\/391143336\/pegg.jpg",
      "following": false,
      "profile_link_color": "009999",
      "utc_offset": 0,
      "name": "Simon Pegg",
      "notifications": false,
      "profile_use_background_image": true,
      "contributors_enabled": false,
      "geo_enabled": false,
      "protected": false,
      "profile_text_color": "333333",
      "id_str": "18713254",
      "default_profile": false,
      "profile_image_url": "http:\/\/a0.twimg.com\/profile_images\/2262775119\/image_normal.jpg",
      "show_all_inline_media": false,
      "followers_count": 2396558,
      "profile_sidebar_border_color": "eeeeee",
      "description": "Actor\/writer\/dog owner\/winner of 50m flat race 1977-1981",
      "url": "http:\/\/twitter.com\/simonpegg",
      "screen_name": "simonpegg",
      "profile_background_tile": true,
      "created_at": "Wed Jan 07 06:19:34 +0000 2009",
      "listed_count": 32129,
      "friends_count": 438,
      "lang": "en",
      "profile_sidebar_fill_color": "efefef",
      "verified": true,
      "time_zone": "London",
      "is_translator": false,
      "location": "London, UK",
      "profile_image_url_https": "https:\/\/si0.twimg.com\/profile_images\/2262775119\/image_normal.jpg",
      "profile_background_color": "131516",
      "follow_request_sent": false
    },
    "entities": {
      "urls": [
        
      ],
      "media": [
        
      ],
      "user_mentions": [
        {
          "id_str": "15279429",
          "name": "VANITY FAIR",
          "indices": [
            27,
            38
          ],
          "screen_name": "VanityFair",
          "id": 15279429
        }
      ],
      "hashtags": [
        
      ]
    },
    "coordinates": null,
    "geo": null,
    "place": null,
    "favorited": false,
    "source": "\u003Ca href=\"http:\/\/www.tweetdeck.com\" rel=\"nofollow\"\u003ETweetDeck\u003C\/a\u003E",
    "in_reply_to_status_id_str": null,
    "contributors": null,
    "id": 212150002372444160,
    "text": "I look hilariously glum in @vanityfair's Paramount Pictures 100th anniversary photo. I can assure you, I was over the moon to be there."
  },
  "user": {
    "id": 214680621,
    "statuses_count": 1613,
    "profile_background_image_url_https": "https:\/\/si0.twimg.com\/profile_background_images\/170659335\/786134-bragging.gif",
    "default_profile_image": false,
    "favourites_count": 0,
    "profile_background_image_url": "http:\/\/a0.twimg.com\/profile_background_images\/170659335\/786134-bragging.gif",
    "following": false,
    "profile_link_color": "0084B4",
    "utc_offset": -32400,
    "name": " Humblebrag",
    "notifications": false,
    "profile_use_background_image": true,
    "contributors_enabled": false,
    "geo_enabled": false,
    "protected": false,
    "profile_text_color": "333333",
    "id_str": "214680621",
    "default_profile": false,
    "profile_image_url": "http:\/\/a0.twimg.com\/profile_images\/1165068872\/Pat_on_the_back_1__normal.jpg",
    "show_all_inline_media": true,
    "followers_count": 223945,
    "profile_sidebar_border_color": "C0DEED",
    "description": "Email Humblebrag@gmail.com with any leads on any humblebrags. Do not send brags. (NOTE: Humblebrag re-tweets may be used in upcoming book). Thanks!",
    "url": null,
    "screen_name": "Humblebrag",
    "profile_background_tile": true,
    "created_at": "Fri Nov 12 00:01:26 +0000 2010",
    "listed_count": 2555,
    "friends_count": 1,
    "lang": "en",
    "profile_sidebar_fill_color": "DDEEF6",
    "verified": false,
    "time_zone": "Alaska",
    "is_translator": false,
    "location": "Hollywood",
    "profile_image_url_https": "https:\/\/si0.twimg.com\/profile_images\/1165068872\/Pat_on_the_back_1__normal.jpg",
    "profile_background_color": "C0DEED",
    "follow_request_sent": false
  },
  "entities": {
    "urls": [
      
    ],
    "media": [
      
    ],
    "user_mentions": [
      {
        "id_str": "18713254",
        "name": "Simon Pegg",
        "indices": [
          3,
          13
        ],
        "screen_name": "simonpegg",
        "id": 18713254
      },
      {
        "id_str": "15279429",
        "name": "VANITY FAIR",
        "indices": [
          42,
          53
        ],
        "screen_name": "VanityFair",
        "id": 15279429
      }
    ],
    "hashtags": [
      
    ]
  },
  "coordinates": null,
  "geo": null,
  "place": null,
  "favorited": false,
  "source": "web",
  "in_reply_to_status_id_str": null,
  "contributors": null,
  "id": 212401168461217793,
  "text": "RT @simonpegg: I look hilariously glum in @vanityfair's Paramount Pictures 100th anniversary photo. I can assure you, I was over the moo ..."
}

.. now, looking through that data, it becomes clear that the field we are interested in displaying in the List is:
  		[rootArrayItem].retweeted_status.user.profile_image_url

Q1: Can we ditch include_entities=true?
Q2: Can i go ahead and make the list look a little cleaner, by placing the profile picture nicely left of the Tweet, with its original author's name most prominent? This seems to me 
	like it will make the better presentation/representation of the Wall of Shame concept? 
 */

public class HumblebragActivity extends ListActivity {
	
	//private String URI_GET_TWEETS = "https://api.twitter.com/1/statuses/retweeted_by_user.json?screen_name=humblebrag&count=100&include_entities=true";
	private String URI_GET_TWEETS = "https://api.twitter.com/1/statuses/retweeted_by_user.json?screen_name=humblebrag&count=100"; // note: i'm going with removing include_entities=true
	private ArrayList<Tweet> tweets = new ArrayList<Tweet>();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new BackgroundTask().execute();
	}

	// This next method is not used. It came along when i generated the project.
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_humblebrag, menu);
		return true;
	}
	
	private class BackgroundTask extends AsyncTask<Void, Void, Void> {
		private ProgressDialog progressDialog;
		protected void onPreExecute() {
			progressDialog = ProgressDialog.show(HumblebragActivity.this,
					"", "Loading Humblebrag Wall of Shame. Please wait...", true);
		}
		@Override
		protected Void doInBackground(Void... arg0) {
			try {
				HttpClient hc = new DefaultHttpClient();
				HttpGet get = new HttpGet(URI_GET_TWEETS);
				HttpResponse rp = hc.execute(get);
				if (rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					String result = EntityUtils.toString(rp.getEntity());
					
					JSONArray rootArray = new JSONArray(result);
					int len = rootArray.length();
					for(int i = 0; i < len; ++i) {
					    JSONObject obj = rootArray.getJSONObject(i).getJSONObject("retweeted_status");
					    Tweet tweet = new Tweet(
					    		obj.getJSONObject("user").getString("name"),
					    		obj.getString("text"),
					    		obj.getJSONObject("user").getString("profile_image_url")
					    		);
						tweets.add(tweet);
					}
				}
			} catch (Exception e) {
				Log.e("TwitterFeedActivity", "Error loading JSON", e);
			}
			//return tweets;
			return null; // because it's in the callback now!
		}
		@Override
		protected void onPostExecute(Void result) {
			progressDialog.dismiss();
			setListAdapter(new TweetListAdaptor(
					HumblebragActivity.this, R.layout.list_item, tweets));
		}
	}
	
	private class TweetListAdaptor extends ArrayAdapter<Tweet> {
		private ArrayList<Tweet> tweets;

		public TweetListAdaptor(Context context, int textViewResourceId, ArrayList<Tweet> items) {
			super(context, textViewResourceId, items);
			this.tweets = items;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View v = convertView;
			if (v == null) {
				LayoutInflater vi = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				v = vi.inflate(R.layout.list_item, null);
			}
			Tweet tweet = tweets.get(position);
			if (tweet != null) {
				TextView username = (TextView) v.findViewById(R.id.username);
				TextView message = (TextView) v.findViewById(R.id.message);
				ImageView image = (ImageView) v.findViewById(R.id.avatar);

				if (username != null) {
					username.setText(tweet.username);
				}

				if(message != null) {
					message.setText(tweet.message);
				}
				
				if(image != null) {
					image.setImageBitmap(getBitmap(tweet.image_url));
				}
			}
			return v;
		};
	}
	
	public Bitmap getBitmap(String bitmapUrl) {
		try {
			URL url = new URL(bitmapUrl);
			return BitmapFactory.decodeStream(url.openConnection() .getInputStream()); 
		}
		catch(Exception ex) {
			return null;
		}
	}

	
}
