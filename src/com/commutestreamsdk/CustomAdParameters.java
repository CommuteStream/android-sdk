package com.commutestreamsdk;

import java.util.Date;

import com.commutestreamsdk.http.RequestParams;

import android.location.Location;
import android.util.Log;

public class CustomAdParameters {
	private String advertiser_id;
	private String banner_height;
	private String banner_width;
	private String sdk_ver;
	private String host_app_ver;
	private String agency_id;
	private String stop_id;
	private String route_id;
	private String lat;
	private String lon;
	private String acc;
	private String fix_time;
	private String aid_sha;
	private String aid_md5;
	
	private Location location;
	
	private Date lastParameterChange;
	
	RequestParams http_params = new RequestParams();
	
	public void setAgency_id(String agency_id) {
		Log.v("CS_SDK", "Agency changed to: " + agency_id);
		this.agency_id = agency_id;
		http_params.put("agency_id", agency_id);
		this.parameterChange();
	}

	public String getAgency_id() {
		return agency_id;
	}

	public void setStop_id(String stop_id) {
		this.stop_id = stop_id;
		http_params.put("stop_id", stop_id);
		this.parameterChange();
	}

	public String getStop_id() {
		return stop_id;
	}

	public void setRoute_id(String route_id) {
		this.route_id = route_id;
		http_params.put("route_id", route_id);
		this.parameterChange();
	}

	public String getRoute_id() {
		return route_id;
	}

	public Date getLastParameterChangeTime() {
		return this.lastParameterChange;
	}

	public void parameterChange() {
		this.lastParameterChange = new Date();
	}

	public RequestParams getHttpParams() {
		return http_params;
	}

	public String getAdvertiser_id() {
		return advertiser_id;
	}

	public void setAdvertiser_id(String advertiser_id) {
		this.advertiser_id = advertiser_id;
		http_params.put("advertiser_id", advertiser_id);
	}

	public String getBanner_height() {
		return banner_height;
	}

	public void setBanner_height(String banner_height) {
		this.banner_height = banner_height;
		http_params.put("banner_height", banner_height);
	}

	public String getBanner_width() {
		return banner_width;
	}

	public void setBanner_width(String banner_width) {
		this.banner_width = banner_width;
		http_params.put("banner_width", banner_width);
	}

	public String getHost_app_ver() {
		return host_app_ver;
	}

	public void setHost_app_ver(String host_app_ver) {
		this.host_app_ver = host_app_ver;
		http_params.put("host_app_ver", host_app_ver);
	}

	public String getSdk_ver() {
		return sdk_ver;
	}

	public void setSdk_ver(String sdk_ver) {
		this.sdk_ver = sdk_ver;
		http_params.put("sdk_ver", sdk_ver);
	}

	public String getLocation() {
		return lat;
	}

	public void setLocation(Location location) {
		this.location = location;
		this.lat = Double.toString(location.getLatitude());
		this.lon = Double.toString(location.getLongitude());
		this.acc = Double.toString(location.getAccuracy());
		this.fix_time = Long.toString(location.getTime());
		http_params.put("lat", lat);
		http_params.put("lon", lon);
		http_params.put("acc", acc);
		http_params.put("fix_time", fix_time);
		this.parameterChange();
	}

	public String getAid_sha() {
		return aid_sha;
	}

	public void setAid_sha(String aid_sha) {
		this.aid_sha = aid_sha;
		http_params.put("aid_sha", aid_sha);
	}

	public String getAid_md5() {
		return aid_md5;
	}

	public void setAid_md5(String aid_md5) {
		this.aid_md5 = aid_md5;
		http_params.put("aid_md5", aid_md5);
	}



}
