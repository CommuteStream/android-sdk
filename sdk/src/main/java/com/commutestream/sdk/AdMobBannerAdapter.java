package com.commutestream.sdk;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;

import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.mediation.MediationAdRequest;
import com.google.android.gms.ads.mediation.customevent.CustomEventBanner;
import com.google.android.gms.ads.mediation.customevent.CustomEventBannerListener;


/**
 * AdMobBannerAdapter provides an implementation of an AdMob CustomEventBanner
 * allowing CommuteStream to deliver banner advertisements directly using
 * AdMob Mediation.
 * @see com.google.android.gms.ads.mediation.customevent.CustomEventBanner
 */
public class AdMobBannerAdapter implements CustomEventBanner {

    private WebView adView;

    @Override
    public void requestBannerAd(final Context context, final CustomEventBannerListener listener,
                                final String serverParameter, final AdSize size,
                                final MediationAdRequest mediationAdRequest,
                                final Bundle customEventExtras) {
        if(!CommuteStream.isInitialized()) {
            CommuteStream.init(context, serverParameter);
        }
        CommuteStream.setBannerHeight(size.getHeightInPixels(context));
        CommuteStream.setBannerWidth(size.getWidthInPixels(context));
        CommuteStream.requestAd(new AdResponseHandler() {
            @Override
            public void onSuccess(AdResponse response, double requestTime) {
                // if there is something that the server wants us to
                // display we generate a webview for it and pass it
                // on to admob
                if (response.getHtml() != null) {
                    Log.v("CS_SDK", "BANNER REQUEST SUCCEESS, TOOK: " + requestTime + "ms");
                    adView = generateWebView(listener, context,
                            size,
                            response.getHtml(),
                            requestTime);
                    listener.onAdLoaded(adView);
                } else if (response.getError() != null) {
                    Log.v("CS_SDK", "Response had an error " + response.getError());
                    listener.onAdFailedToLoad(AdRequest.ERROR_CODE_INVALID_REQUEST);
                } else {
                    Log.v("CS_SDK", "Response had no error or html");
                    listener.onAdFailedToLoad(AdRequest.ERROR_CODE_NO_FILL);
                }
            }

            @Override
            public void onError(Throwable error) {
                Log.v("CS_SDK", "FAILED_FETCH");
                listener.onAdFailedToLoad(AdRequest.ERROR_CODE_NETWORK_ERROR);
            }
        });
    }

    // does the actual update of the activity
    @SuppressLint("SetJavaScriptEnabled")
    private WebView generateWebView(final CustomEventBannerListener listener,
                                    final Context context,
                                    final AdSize size,
                                    final String html,
                                    final double requestTime) {
        Log.v("CS_SDK", "Generating Ad WebView");
        AdMobEventForwarder forwarder = new AdMobEventForwarder(listener);
        return new BannerView(context, requestTime, size.getWidthInPixels(context), size.getHeightInPixels(context), forwarder, html);
    }

    @Override
    public void onDestroy() {
        // Do Nothing
    }

    @Override
    public void onResume() {
        // Do nothing
    }

    @Override
    public void onPause() {
        // Do nothing
    }
}