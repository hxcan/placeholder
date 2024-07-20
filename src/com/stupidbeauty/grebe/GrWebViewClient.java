package com.stupidbeauty.grebe;

import com.huiti.clipcms.SbWebViewClientInterface;

import android.net.Uri;
//import android.support.annotation.Nullable;
import android.util.Log;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

@SuppressWarnings({"FieldCanBeLocal", "ConstantConditions"})
public class GrWebViewClient extends WebViewClient
{
	@SuppressWarnings("CanBeFinal")
	private static String TAG="GrWebViewClient"; //!<输出调试信息时使用的标记。

    @Override
    public void onPageFinished(WebView view, String url)
    {
        browserActivity.onPageFinished(view, url); //告知页面载入完毕。

        super.onPageFinished(view, url);
    }

    @SuppressWarnings("ConstantConditions")
//	@Nullable
	@Override
	public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request)
	{

        Uri uri= null; //获取网址。
		if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP)
		{
            Log.d(TAG, "shouldInterceptRequest, request url: "+request.getUrl()); //Debug.
			uri = request.getUrl();
		}

        judgeResouceByUri(uri, view);


		return super.shouldInterceptRequest(view, request);
	}

    private void judgeResouceByUri(Uri uri, WebView webView)
    {
        Log.d(TAG, "judgeResouceByUri, url: "+uri); //Debug.

        String host=uri.getHost(); //获取主机。

        if (host.equals("video.twimg.com")) //是视频主机。
        {
            String path=uri.getPath(); //获取路径。

//            Log.d(TAG, "judgeResouceByUri, path: "+path); //报告路径。

            browserActivity.onFoundVideoUrl(uri, webView); //报告，找到了视频网址。

        } //if (host.equals("video.twimg.com")) //是视频主机。
    }

    public GrWebViewClient(SbWebViewClientInterface browserActivity)
	{
		super();
		this.browserActivity = browserActivity;
	}

	@SuppressWarnings("CanBeFinal")
	private SbWebViewClientInterface browserActivity; //!<浏览器活动。

}
