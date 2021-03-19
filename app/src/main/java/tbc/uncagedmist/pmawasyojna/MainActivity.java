package tbc.uncagedmist.pmawasyojna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.LoadAdError;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.play.core.review.ReviewInfo;
import com.google.android.play.core.review.ReviewManager;
import com.google.android.play.core.review.ReviewManagerFactory;
import com.google.android.play.core.tasks.OnSuccessListener;
import com.google.android.play.core.tasks.Task;

import am.appwise.components.ni.NoInternetDialog;

public class MainActivity extends AppCompatActivity {

    AdView bottomBanner,aboveBanner;
    NoInternetDialog noInternetDialog;
    private InterstitialAd mInterstitialAd;

    ReviewManager manager;
    ReviewInfo reviewInfo;

    ListView listView;
    String[] yojnaName = {
            "आवास योजना लिस्ट-ग्रामीण",
            "ग्रामीण आवास लाभार्थी सूची",
            "आवास योजना लिस्ट-शहरी",
            "आधार कार्ड से देखें-शहरी",
            "शौचालय योजना लिस्ट",
            "उज्ज्वला योजना लिस्ट",
            "BPL लिस्ट (सभी राज्य)",
            "ग्राम पंचायत रिपोर्ट",
            "नरेगा जॉब कार्ड",
            "पेंशन सूची देखें",
            "पेंशन पेमेंट स्थिती",
            "राशन कार्ड"
    };

    String[] yojnaURL = {
            "https://awaassoft.nic.in/netiay/PhysicalProgressReport/physicalprogressreportPhaseWise.aspx",
            "https://rhreporting.nic.in/netiay/benificiary.aspx",
            "https://pmaymis.gov.in/",
            "https://pmaymis.gov.in/Open/Find_Beneficiary_Details.aspx",
            "https://sbm.gov.in/sbmReport/Report/Physical/SBM_TargetVsAchievementWithout1314.aspx",
            "https://www.india.gov.in/spotlight/pradhan-mantri-ujjwala-yojana#tab=tab-1",
            "https://nrega.nic.in/netnrega/home.aspx",
            "https://nregade.nic.in/netnrega/statepage.aspx?check=BPD&level=HomeBP",
            "https://nrega.nic.in/netnrega/home.aspx",
            "https://nsap.nic.in/statedashboard.do?method=intialize",
            "https://nsap.nic.in/ledgerHistroy.do?method=intialize",
            "https://nfsa.gov.in/portal/State_Food_Portals"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        noInternetDialog = new NoInternetDialog.Builder(MainActivity.this).build();
        manager = ReviewManagerFactory.create(MainActivity.this);

        mInterstitialAd = new InterstitialAd(this);
        mInterstitialAd.setAdUnitId("ca-app-pub-7920815986886474/2911711943");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdClosed() {
                // Load the next interstitial.
                mInterstitialAd.loadAd(new AdRequest.Builder().build());
            }

        });

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("सरकारी सेवाएं");

        listView = findViewById(R.id.listView);

        aboveBanner =findViewById(R.id.aboveBanner);
        bottomBanner = findViewById(R.id.belowBanner);

        AdRequest adRequest = new AdRequest.Builder().build();

        aboveBanner.loadAd(adRequest);
        bottomBanner.loadAd(adRequest);


        CustomAdapter customAdapter = new CustomAdapter();
        listView.setAdapter(customAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if (mInterstitialAd.isLoaded()) {
                    mInterstitialAd.show();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(),ResultActivity.class);
                    intent.putExtra("yojnaName",yojnaName[i]);
                    intent.putExtra("yojnaURL",yojnaURL[i]);
                    startActivity(intent);
                }
            }
        });
        adMethod();
    }

    private void adMethod() {
        aboveBanner.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        bottomBanner.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        });

        mInterstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                // Code to be executed when an ad finishes loading.
            }

            @Override
            public void onAdFailedToLoad(LoadAdError adError) {
                // Code to be executed when an ad request fails.
            }

            @Override
            public void onAdOpened() {
                // Code to be executed when the ad is displayed.
            }

            @Override
            public void onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            @Override
            public void onAdLeftApplication() {
                // Code to be executed when the user has left the app.
            }

            @Override
            public void onAdClosed() {
                // Code to be executed when the interstitial ad is closed.
            }
        });
    }

    private class CustomAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return yojnaName.length;
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            View itemView = getLayoutInflater().inflate(R.layout.my_item_list,null);

            TextView name = itemView.findViewById(R.id.txtYojna);

            name.setText(yojnaName[i]);
            return itemView;
        }
    }

    @Override
    public void onBackPressed() {
        Task<ReviewInfo> request = manager.requestReviewFlow();

        request.addOnCompleteListener(new com.google.android.play.core.tasks.OnCompleteListener<ReviewInfo>() {
            @Override
            public void onComplete(@NonNull Task<ReviewInfo> task) {
                if (task.isSuccessful())    {
                    reviewInfo = task.getResult();

                    Task<Void> flow = manager.launchReviewFlow(MainActivity.this,reviewInfo);

                    flow.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void result) {

                        }
                    });
                }
                else {
                    Toast.makeText(MainActivity.this, "ERROR...", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        noInternetDialog.onDestroy();
    }
}