package com.hust.qiudai.web.service.impl;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import org.apache.cxf.message.Message;
import org.apache.cxf.phase.PhaseInterceptorChain;
import org.apache.cxf.transport.http.AbstractHTTPDestination;

import com.google.gson.Gson;
import com.hust.qiudai.web.comparator.UniversityDistanceComparator;
import com.hust.qiudai.web.db.City;
import com.hust.qiudai.web.db.Location;
import com.hust.qiudai.web.db.University;
import com.hust.qiudai.web.db.User;
import com.hust.qiudai.web.db.dao.IRegisterDao;
import com.hust.qiudai.web.service.IRegitserService;
import com.hust.qiudai.web.util.CommonUtil;
import com.hust.qiudai.web.util.VariableUtil;

@Named
public class RegisterService implements IRegitserService {
	@Inject
	private IRegisterDao iRegisterDao;

	public String register() {
		// Message message = PhaseInterceptorChain.getCurrentMessage();
		// HttpServletRequest request = (HttpServletRequest) message
		// .get(AbstractHTTPDestination.HTTP_REQUEST);

		HttpServletRequest request = CommonUtil.getHttpServletRequest();

		/** 获取账号密码 **/
		User user = getUserFromRequestParameters(request);

		/** 查询该邮箱的相关信息 **/
		int userNum = iRegisterDao.findNumOfTheUser(user.getEmail());

		return getResiterMessage(userNum, user);

	}

	/**
	 * 通过request获取客户端传来的邮箱和密码并封装在User对象中返回
	 * 
	 * @param request
	 * @return User对象
	 */
	public User getUserFromRequestParameters(HttpServletRequest request) {
		User user = new User();
		user.setEmail(request.getParameter(VariableUtil.EMAIL_PARAMETER_NAME));
		user.setPassword(request
				.getParameter(VariableUtil.PASSWORD_PARAMETER_NAME));
		return user;
	}

	/**
	 * 获取用户的注册信息
	 * 
	 * @param userNum
	 * @param user
	 * @return
	 */
	public String getResiterMessage(int userNum, User user) {
		if (userNum == 0) {
			/** 该邮箱没有注册 **/
			iRegisterDao.addUser(user);
			return user.getUserId() + "";
		} else if (userNum > 0) {
			/** 不等于0，该邮箱已被注册 **/
			return VariableUtil.REGISTER_FAILED_EMAIL_EXSIST + "";
		} else {
			/** 服务器异常 **/
			return VariableUtil.REGISTER_PROCESS_EXCEPTION + "";
		}
	}

	public String usernameCommit() {
		Message message = PhaseInterceptorChain.getCurrentMessage();
		HttpServletRequest request = (HttpServletRequest) message
				.get(AbstractHTTPDestination.HTTP_REQUEST);

		String username = request.getParameter(VariableUtil.USERNAME);
		String useridStr = request.getParameter(VariableUtil.USERID);
		String returnUniversityJson = null;

		try {
			long userid = Long.parseLong(useridStr);
			iRegisterDao.updateUserNameByUserId(username, userid);

			Location location = getLocationByRequest(request);

			if (location != null) {
				returnUniversityJson = findTheRankedUniversitiesListJsonByLocation(location);
			}
		} catch (Exception e) {
			returnUniversityJson = VariableUtil.USERNAME_COMMIT_FAIL + "";
		}
		return returnUniversityJson;
	}

	/**
	 * 查找与location最近的城市，也就是location的所在城市
	 * 
	 * @param cityList
	 * @param location
	 * @return
	 */
	public City findTheNearestCity(List<City> cityList, Location location) {
		City nearestCity = cityList.get(0);
		double nearestDistance = Double.MAX_VALUE;
		if (cityList != null && cityList.size() != 0) {
			for (City city : cityList) {
				double distanceLatitude = location.getLatitude()
						- city.getCityLatitude();
				double distanceLongitude = location.getLongitude()
						- city.getCityLongitude();
				double distance = Math.sqrt(distanceLatitude * distanceLatitude
						+ distanceLongitude * distanceLongitude);

				if (distance < nearestDistance) {
					nearestDistance = distance;
					nearestCity = city;
				}
			}
		}

		return nearestCity;
	}

	/**
	 * 查找出与传入location同在一个城市的高校列表，并将列表中的高校按照与location的举例
	 * 从近到远排序并返回其json格式
	 * 
	 * @param location
	 *            传入坐标
	 * @return 高校按举例由近到远的排序（json格式字符串）
	 */
	public String findTheRankedUniversitiesListJsonByLocation(Location location) {

		List<City> cityList = iRegisterDao.findCityInfo();

		City nearestCity = findTheNearestCity(cityList, location);

		List<University> universityList = iRegisterDao
				.findUniversitiesInfoByCityId(nearestCity.getCityId());

		universityList = findUniversityListRankByDistance(universityList,
				location);

		return CommonUtil.getJsonStringByGson(universityList);
	}

	/**
	 * 将高校列表与location坐标进行举例计算并按距离从近到远进行排序并返回排好序的列表
	 * 
	 * @param universityList
	 * @param location
	 * @return 排好序的列表
	 */
	public List<University> findUniversityListRankByDistance(
			List<University> universityList, Location location) {
		if (universityList != null && universityList.size() != 0) {
			for (University university : universityList) {

				double distanceLatitude = location.getLatitude()
						- university.getUniversityLatitude();
				double distanceLongitude = location.getLongitude()
						- university.getUniversityLongitude();
				double distanceFromLocation = Math.sqrt(distanceLatitude
						* distanceLatitude + distanceLongitude
						* distanceLongitude);
				university.setDistanceFromLocation(distanceFromLocation);
			}
			Collections
					.sort(universityList, new UniversityDistanceComparator());
		}
		return universityList;
	}

	/**
	 * 通过request获取参数经度与纬度，并封装成Location对象返回
	 * 
	 * @param request
	 * @return Location对象
	 */
	public Location getLocationByRequest(HttpServletRequest request) {
		String latitudeStr = request.getParameter(VariableUtil.LATITUDE);
		String longitudeStr = request.getParameter(VariableUtil.LONGITUDE);
		Location location = null;

		if (latitudeStr != null && longitudeStr != null) {
			double latitude = Double.parseDouble(latitudeStr);
			double longitude = Double.parseDouble(longitudeStr);
			location = new Location(longitude, latitude);
		}

		return location;

	}

}
