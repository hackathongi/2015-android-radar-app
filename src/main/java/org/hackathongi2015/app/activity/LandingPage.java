package org.hackathongi2015.app.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.TextView;
import com.nostra13.universalimageloader.core.ImageLoader;
import org.hackathongi2015.R;
import org.hackathongi2015.app.util.Global;

import java.util.Date;

/**
 * Created by imartin on 28/03/15.
 */
public class LandingPage extends Activity {
  @Override
  protected void onCreate(Bundle savedInstanceState) {
    // setTheme(SampleList.THEME);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.landing_page);

    Bundle extras = getIntent().getExtras();
    if (extras == null) {
      this.finish();
      return;
    }

    View vi = this.getWindow().getDecorView();
    WebView webview =(WebView)vi.findViewById(R.id.webview);
    WebSettings webSettings = webview.getSettings();
    webSettings.setJavaScriptEnabled(true);
    webview.setWebViewClient(new WebViewClient());

    // Assign the appropriate data from our task object above
    String url = extras.getString("JOB_URL");
    webview.loadUrl(url);
  }

}
