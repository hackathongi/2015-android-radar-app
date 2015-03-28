package org.hackathongi2015.app.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.SslErrorHandler;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import org.hackathongi2015.R;

/**
 * Created by pgarriga on 28/3/15.
 */
public class WallyWebView extends Activity {
  /**
   * Called when the activity is first created.
   */

  android.webkit.WebView web;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.landing_page);

    Bundle extras = getIntent().getExtras();
    if (extras == null) {
      this.finish();
      return;
    }

    View vi = this.getWindow().getDecorView();
    web = (WebView) vi.findViewById(R.id.webview);
    web.setWebViewClient(new myWebClient());
    web.getSettings().setJavaScriptEnabled(true);

    String url = extras.getString("JOB_URL");
    web.loadUrl(url);
  }

  public class myWebClient extends WebViewClient {
    @Override
    public void onPageStarted(android.webkit.WebView view, String url, Bitmap favicon) {
      // TODO Auto-generated method stub
      super.onPageStarted(view, url, favicon);
    }

    @Override
    public boolean shouldOverrideUrlLoading(android.webkit.WebView view, String url) {
      // TODO Auto-generated method stub

      view.loadUrl(url);
      return true;

    }

    @Override
    public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
      handler.proceed(); // Ignore SSL certificate errors
    }
  }

  // To handle "Back" key press event for WebView to go back to previous screen.
  @Override
  public boolean onKeyDown(int keyCode, KeyEvent event) {
    if ((keyCode == KeyEvent.KEYCODE_BACK) && web.canGoBack()) {
      web.goBack();
      return true;
    }
    return super.onKeyDown(keyCode, event);
  }
}
