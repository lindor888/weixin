package com.ctvit.epg;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.time.DateUtils;

import com.ctvit.bean.Reservation;
import com.ctvit.dao.ReservationDao;
import com.ctvit.dao.ReservationSubDao;

/**
 * epg 节目单的呈现的servlet
 * 
 * @author guosidi
 * @email guosidi@ctvit.com.cn
 * @date 2015年9月2日 上午10:21:06
 */
public class EpgdataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EpgdataServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		JSONObject dataJson = new JSONObject();
		DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String epg_day = request.getParameter("epg_day");
		String openid = request.getParameter("openid");
		ReservationSubDao subDao = new ReservationSubDao();

		try {
			Date start;
			if (StringUtils.isEmpty(epg_day)) {
				start = sdf.parse(sdf.format(new Date()));
			} else {
				start = sdf.parse(epg_day);
			}
			Date end = DateUtils.addDays(start, 1);
			ReservationDao reservationDao = new ReservationDao();
			List<Reservation> rows = reservationDao.getListByDate(sdf.format(start), sdf.format(end));

			if (rows.size() == 0) {
				dataJson.put("result", false);
				dataJson.put("msg", "该日暂无节目");
			} else {
				dataJson.put("result", true);
				dataJson.put("msg", "");
				dataJson.put("normalconf", getNormalconf());
				JSONArray epglist = new JSONArray();
				for (Reservation row : rows) {
					JSONObject jObject = new JSONObject();
					jObject.put("id", row.getReservation_id());
					jObject.put("title", row.getProgram_name());
					jObject.put("start_time", row.getStart_time().getTime() / 1000);
					jObject.put("end_time", row.getEnd_time().getTime() / 1000);
					jObject.put("push_time", row.getPush_time().getTime() / 1000);
					jObject.put("live_url", row.getLive_url());
					jObject.put("label", 0);
					String subid = subDao.getSubid(row.getReservation_id(), openid);
					if (StringUtils.isEmpty(subid)) {
						jObject.put("issub", 0);
						jObject.put("subid", "");
					} else {
						jObject.put("issub", 1);// 是否已经预约 ??? 此处要修改
						jObject.put("subid", subid);
					}
					epglist.add(jObject);
				}
				dataJson.put("epglist", epglist);
			}

			// String data =
			//
			// "{  \"result\": true,    \"msg\": \"\",    \"normalconf\": {        \"id\": \"80\",        \"token\": \"8e8cae5ba3d5\",        \"channel_id\": \"21497\",        \"english_channel\": \"CCTV5AHD\",        \"bg_img\": \"/static/images/epg/img/epg_default.jpg\",        \"mid\": \"CRTVF0NU9\",        \"push_time\": \"10\",        \"title\": \"节目单预约\",        \"url\": null,        \"share_title\": \"CCTV5+节目预约，精彩赛事不错过！\",        \"share_img\": \"http://iwmh.mtq.tvm.cn/data/upload/2015-04-03/1428063103-1000.jpg\",        \"share_desc\": \"CCTV5+赛事预约，从此不再错过比赛，快邀请小伙伴一起来体验吧！\",        \"font_color\": \"#000000\",        \"page_version\": \"1\",        \"sid\": \"150403200117214cf3a5342df57212733e_0\",        \"show_type\": \"1\",        \"constant_epg_day\": \"2015-08-04\",        \"redirect_name\": \"赛事直播\",        \"redirect_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&token=8e8cae5ba3d5&s_id=150403200117214cf3a5342df57212733e_0\"    },    \"epglist\": [        {            \"id\": \"2426757\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-2015年世界击剑锦标赛-女子重剑团体决赛(中国-罗马尼亚)\",            \"start_time\": \"1438793100\",            \"end_time\": \"1438796700\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438792500,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426756\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2015年亚洲男排锦标赛复赛2\",            \"start_time\": \"1438796700\",            \"end_time\": \"1438801500\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438796100\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"502vt2\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426755\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-2015年高尔夫美巡赛集锦28--贵肯信贷全美锦标赛\",            \"start_time\": \"1438801500\",            \"end_time\": \"1438804800\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438800900,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426754\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2014/2015赛季欧洲足球冠军联赛半决赛第一回合巴塞罗那—拜仁慕尼黑\",            \"start_time\": \"1438804800\",            \"end_time\": \"1438810200\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438804200\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"THYAw2\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426753\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2015年东亚杯女子足球赛日本队—韩国队\",            \"start_time\": \"1438810200\",            \"end_time\": \"1438815600\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438809600,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426752\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-2015年世界击剑锦标赛-女子重剑团体决赛(中国-罗马尼亚)\",            \"start_time\": \"1438815600\",            \"end_time\": \"1438819200\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438815000,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426751\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-2015年沙滩足球世界杯比赛集锦1\",            \"start_time\": \"1438819200\",            \"end_time\": \"1438822800\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438818600,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426750\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2015年东亚杯女子足球赛中国队—朝鲜队\",            \"start_time\": \"1438822800\",            \"end_time\": \"1438828200\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438822200,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426749\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2015年全国田径冠军赛男女接力\",            \"start_time\": \"1438828200\",            \"end_time\": \"1438829100\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438827600\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"UDzxA1\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426748\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2015年斯坦科维奇杯洲际篮球赛委内瑞拉队—中国队\",            \"start_time\": \"1438829100\",            \"end_time\": \"1438833600\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438828500\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"YP9nZ\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426747\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2015年东亚杯男子足球赛日本队—韩国队\",            \"start_time\": \"1438833600\",            \"end_time\": \"1438839000\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438833000\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"cLRmn\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426746\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-国际足球杂志2015-13\",            \"start_time\": \"1438839000\",            \"end_time\": \"1438840800\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438838400\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"HBzv01\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426745\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-2015年世界击剑锦标赛-男子花剑团体铜牌赛(中国-法国)\",            \"start_time\": \"1438840800\",            \"end_time\": \"1438844400\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438840200,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426744\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-2015年沙滩足球世界杯比赛集锦1\",            \"start_time\": \"1438844400\",            \"end_time\": \"1438848000\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438843800,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426743\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-2015年高尔夫美巡赛精选28--贵肯信贷全美锦标赛\",            \"start_time\": \"1438848000\",            \"end_time\": \"1438855380\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438847400\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"LHZO9\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426742\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"足球华彩-14-15赛季德甲超级进球3团队配合\",            \"start_time\": \"1438855380\",            \"end_time\": \"1438857000\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438854780\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"cQfcp\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426741\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"实况录像-2015年东亚杯男子足球赛中国队—朝鲜队\",            \"start_time\": \"1438857000\",            \"end_time\": \"1438862400\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438856400\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"UV7h23\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426740\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"赛事集锦-国际足球杂志2015-13\",            \"start_time\": \"1438862400\",            \"end_time\": \"1438864200\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": 1438861800,            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": 0,            \"issub\": 0,            \"subid\": \"\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426739\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"现场直播：2015亚洲男排锦标赛-1/4决赛(E2--F3)\",            \"start_time\": \"1438864200\",            \"end_time\": \"1438871400\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438863600\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"p5Mh32\",            \"appo_mid_url\": \"\"        },        {            \"id\": \"2426738\",            \"channel_cname\": \"CCTV5+\",            \"title\": \"现场直播：2015亚洲男排锦标赛-1/4决赛(F2--E3)\",            \"start_time\": \"1438871400\",            \"end_time\": \"1438876800\",            \"is_private\": \"0\",            \"mid\": \"CRTVF0NU9\",            \"push_time\": \"1438870800\",            \"live_url\": \"http://wtopic.mtq.tvm.cn/wtopic/sso/oauth?go=livetalk&to=wechat2topic&s_id=150403200117214cf3a5342df57212733e_0&token=8e8cae5ba3d5\",            \"label\": \"0\",            \"issub\": 0,            \"subid\": \"oq0MM4\",            \"appo_mid_url\": \"\"        }    ]}";
			// mapJson.put("rows", data);

		} catch (Exception e) {
			e.printStackTrace();
			dataJson.put("rows", "error");
		}
		PrintWriter pw = response.getWriter();
		pw.write(dataJson.toString());
	}

	private JSONObject getNormalconf() {
		JSONObject jObject = new JSONObject();
		jObject.put("token", "8e8cae5ba3d5");
		jObject.put("channel_id", "btvfazhi");
		jObject.put("bg_img", "resource/epg_default.jpg");
		jObject.put("push_time", "10");
		jObject.put("title", "节目单预约");
		jObject.put("show_type", "1");
		jObject.put("constant_epg_day", "2015-08-13");
		return jObject;
	}

}
