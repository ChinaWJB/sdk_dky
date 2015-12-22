package org.caeit.cs.model.dky.service;

import org.apache.commons.lang3.StringUtils;
import org.codehaus.jackson.annotate.JsonIgnore;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.annotate.JsonUnwrapped;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA. User: wushaobo Date: 2012-9-11 Time: 14:00:07 To
 * change this template use File | Settings | File Templates.
 */
@JsonSerialize(using = ServiceJsonSerializer.class)
@JsonDeserialize(using = ServiceJsonDeserializer.class)
public class Service implements Accessible {
	public static final String RTS_TYPE = "rts";
	public static final String ARTS_TYPE = "arts";

	@JsonProperty
	@JsonUnwrapped
	Map<String, String> properties = new HashMap<String, String>();

	public static final String ID = "id";
	public static final String NAME = "name";
	public static final String TYPE = "serv_type";// 取值：'arts', '应用服务'
	public static final String DOMAIN = "domain";// 服务原本所在的域
	public static final String ORI_ADDRESS = "oriAddress";// 源地址
	public static final String PUB_ADDRESS = "pubAddress";// 信息服务中心再次发布服务后的地址
	private static final String RELAY_COUNT = "relayCount";// 中继数，如果服务来自其他信息服务中心，该值大于0
	public static final String RELAY_ISC_ID = "relayIscId";//
	public static final String ADD_TIME = "addTime";
	private static final String UPDATE_TIME = "updateTime";
	public static final String RELEASE_TIME = "releaseTime";
	public static final String BUS_TYPE = "businessType";
	public static final String DESC = "description";

	public Service() {
	}

	@JsonIgnore
	public List<String> getNames() {
		// TODO Auto-generated method stub
		List<String> names = new ArrayList<String>();
		for (String name : properties.keySet()) {
			names.add(name);
		}
		return names;
	}

	public void set(String name, String value) {
		if (!StringUtils.isEmpty(value))
			properties.put(name, value);
		else
			properties.remove(name);
	}

	public String get(String name) {
		String rs = properties.get(name);
		if (rs == null) {
			rs = "";
		}
		return rs;
	}

	@JsonIgnore
	public short getRelayCount() {
		String realayCount = get(RELAY_COUNT);
		return realayCount.length() == 0 ? 0 : Short.parseShort(realayCount);
	}

	@JsonIgnore
	public void setRelayCount(short relayCount) {
		set(RELAY_COUNT, String.valueOf(relayCount));
	}

	@JsonIgnore
	public Date getAddTime() {
		try {
			String addTime = get(ADD_TIME);
			return addTime.length() == 0 ? null : getDateFormat()
					.parse(addTime);
		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return null;
	}

	@JsonIgnore
	public void setReleaseTime(Date releaseTime) {
		set(RELEASE_TIME, getDateFormat().format(releaseTime));
	}

	@JsonIgnore
	public Date getReleaseTime() {
		// return get(RELEASE_TIME);
		try {
			String releaseTime = get(RELEASE_TIME);
			SimpleDateFormat format = new SimpleDateFormat(
					"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
			return releaseTime.length() == 0 ? null : format.parse(releaseTime);
		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return null;
	}

	@JsonIgnore
	public void setAddTime(Date addTime) {
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		set(ADD_TIME, format.format(addTime));
	}

	@JsonIgnore
	public Date getUpdateTime() {
		try {
			String updateTime = get(UPDATE_TIME);
			return updateTime.length() == 0 ? null : getDateFormat().parse(
					updateTime);
		} catch (ParseException e) {
			e.printStackTrace(); // To change body of catch statement use File |
									// Settings | File Templates.
		}
		return null;
	}

	@JsonIgnore
	public void setUpdateTime(Date updateTime) {
		set(UPDATE_TIME, getDateFormat().format(updateTime));
	}

	@JsonIgnore
	private DateFormat getDateFormat() {
		return DateFormat.getInstance();
	}

	public Accessible copy() {
		Service s = new Service();
		s.properties.putAll(properties);
		return s;
	}

	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat(
				"yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
		System.out.print(format.format(date));
	}
}
