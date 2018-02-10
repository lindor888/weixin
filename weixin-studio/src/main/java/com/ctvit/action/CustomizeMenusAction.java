package com.ctvit.action;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Hashtable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountSessionBean;
import com.ctvit.bean.Logger;
import com.ctvit.bean.Waccount;
import com.ctvit.bean.WxCustomizeMenus;
import com.ctvit.dao.QueryDataBean;
import com.ctvit.service.CustomizeMenuServiceImpl;
import com.ctvit.service.LoggerServiceImpl;
import com.ctvit.service.WaccountServiceImpl;
import com.ctvit.util.ActionUtil;
import com.ctvit.util.website.ManagerMenu;
import com.opensymphony.xwork2.ActionSupport;


public class CustomizeMenusAction extends ActionSupport {
	
	

    private	CustomizeMenuServiceImpl  customizeMenuService;
	private Map<String, Object> mapJson = new Hashtable<String, Object>();
	private	QueryDataBean  queryData ;
	private WxCustomizeMenus menu;
	private String method;
	private String id;
	private String message;
	private String menu_type;
	private String menu_name;
	private String menu_key;
	private Byte typeid;
	private String parentid;
	private String menu_key_id;
	private String 	waccountids;
	
	
	
	private String initMenuJson;
	
	private	Waccount waccount;//公共账号
	private   WaccountServiceImpl	waccountService;
	 private LoggerServiceImpl loggerService;
	    private Logger log = new Logger();  
	
     private HttpSession session=ServletActionContext.getRequest().getSession();
	public static Map result_map = new LinkedHashMap();
	JSONArray jna;
	
	public JSONArray getJna() {
		return jna;
	}

	public void setJna(JSONArray jna) {
		this.jna = jna;
	}
	
	
	public String waccountBymenu(){
	//	queryData=new QueryDataBean();
		
		return "waccountBymenu";
	}
	
	public String selectBymenu(){
		waccount=new Waccount();
		waccount.setWaccountId(queryData.getWaccountId());
		return "waccountBymenu";
	}
	
	
	public String selectAll(){
		//判断是否有权限?
		
		if(method.equals("online")){
		WebApplicationContext applicationContext = ContextLoaderListener.getCurrentWebApplicationContext();
		AccountSessionBean accountSessionBean =  (AccountSessionBean)applicationContext.getBean("accountSessionBean");
		
        queryData.setWaccountId(ActionUtil.findWaccountId());
		}
		Map new_map = new LinkedHashMap();
		List<Map<String,Object>> list = customizeMenuService.selectAll(queryData);
		result_map = new LinkedHashMap();
		for (Map<String, Object> obj : list) {
			if(obj.get("parentid") == null || "".equals(obj.get("parentid"))){
				showTree(list,obj,0);
			}
		}
		List<Map> list1 = new ArrayList(result_map.values());
	   jna = new JSONArray();
	   	for(Map map : list1){
				jna.add(map);
	   		}                   
	
		return "selectAll";
	}
	
	public String selectById(){
		//取出文本name
		menu=customizeMenuService.selectById(id);
		if(menu.getContenttype()==1){
			menu.setMenutype("text");
		}else if(menu.getContenttype()==2){
			menu.setMenutype("content");
		}else if(menu.getContenttype()==3){
			menu.setMenutype("view");
		}
		mapJson.put("rows", menu);
		return "selectById";
		
	}
	
	public String addmenu(){
		Account account=(Account)session.getAttribute("user");
		menu=new WxCustomizeMenus();
		Byte i=0;
		Byte j=1;
		if(menu_type.equals("text")){
		i=1;	
		}else if (menu_type.equals("view")){
			i=3;
		}else {
			i=2;
		}
		if(!id.equals("")&&!id.equals("0"))
		menu.setId(id);	
		
		
		if(waccountids.equals(""))
		menu.setAccount(ActionUtil.findWaccountId());//菜单属于哪个公共账号
		else
		menu.setAccount(waccountids);//菜单属于哪个公共账号
		menu.setContenttype(i);
		menu.setName(menu_name);
		if(null!=parentid&&!"".equals(parentid)&&!parentid.equals("0"))
			menu.setParentid(parentid);
		else
			menu.setParentid("");
		if(null!=menu_key_id&&!"".equals(menu_key_id))
		menu.setMaterialid(menu_key_id);
		else
		menu.setMaterialid(menu_key);
		menu.setState(j);
		menu.setModifitime (new Timestamp((new Date()).getTime()));
		menu.setModifierid(account.getAccountId());//最后修改人
		
		try{
			
			
			if(customizeMenuService.menuparentCountByName(menu)>0){
				message="该菜单名称已存在！";
			
				return "selectById";
				
			}
			//需要判断一下
			if(null!=id&&!"".equals(id)&&!id.equals("0")){
				menu.setId(id);
				customizeMenuService.updateByPrimaryKeySelective(menu);
				}
			else{
				menu.setCreattime (new Timestamp((new Date()).getTime()));
				customizeMenuService.insert(menu);
			}
		   message="ok";
		
		}catch(Exception e){
		e.printStackTrace();
		}finally{
		
		log.setType("自定义菜单");
		log.setAct("insert");
		log.setValue(menu_name);
		loggerService.saveLog(log);
		mapJson.put("message", message);
		}
		return "selectById";
		
	}
	
	public String delmenu(){
		try{
			Byte i=3;
			//级联删除
			menu=new WxCustomizeMenus();
			menu.setId(id);
			menu.setState(i);
			customizeMenuService.deleteByPrimaryKey(id);
			//customizeMenuService.updateByPrimaryKeySelective(menu);
		message="ok";
		
		log.setType("自定义菜单");
		log.setAct("insert");
		log.setValue(menu_name);
		loggerService.saveLog(log);
		}catch(Exception e){
			e.printStackTrace();
		    message="error";
		
		}
		
		
		mapJson.put("message",message);
		return "delmenu";
		
	}
	
	
	
	/**
	 * //发布  
	 * @param json
	 * @return
	 * @throws Exception
	 */
	public String pubweixin(){
		    String token="";
		    
		  //  String appid="wx0fe0c45ffbfdff5a";
			//String secret="0c945c1bee5c0d92df3d2f36225c8527";
		    String appid="";
		    String secret="";
			ManagerMenu menu=new ManagerMenu();
			
			try{
			String wid=ActionUtil.findWaccountId();
			Waccount waccount=waccountService.selectByPrimaryKey(wid);
			if(null!=waccount){
			// token=menu.getaccess_token(waccount.getAppId(), waccount.getAppSecret());
				//token=menu.getaccess_token(appid, secret);
				appid=waccount.getAppId();
				secret=waccount.getAppSecret();
				}
			initMenuJson = initMenuJson.replaceAll("<!--appid-->", appid);
			String rusult= menu.createMenus(initMenuJson,appid,secret);
           JSONObject json = JSONObject.fromObject(rusult);
			
			String errcode = json.getString("errcode");
			//String errcode="0";
			if("0".equals(errcode)){
				
				message="ok";
				System.out.println("菜单创建成功！");
				
				
			}else {
				message="error";
				System.out.println("菜单创建失败！");
			}
			
			}catch(Exception e){
				message="error";
				e.printStackTrace();
			}finally{
				mapJson.put("message", message);
				log.setType("自定义菜单发布");
				log.setAct("fabuweixin");
				log.setValue(message);
				loggerService.saveLog(log);
				
			}
			
			
			
		  return "pubweixin";
		}
		
	
	
	/**
	 * 通过递归算法将一层json转换成多层json
	 * @param list
	 * @param tree
	 * @param deep
	 */
	public static void showTree(List list, Map tree, int deep) {
		if(deep==0){
			result_map.put(tree.get("id"), tree);
		}
		for (int i = 0; i < list.size(); i++) {
			if (((Map)list.get(i)).get("parentid").equals(tree.get("id"))) {
				deep++;
		
				((List)tree.get("sub_button")).add(list.get(i));
				showTree(list, (Map)list.get(i), deep);
				deep--;
			}
		
		
		}
	
	}

   public String menuparentCount(){
	   int icount=0;
	   
	   int count=customizeMenuService.menuparentCount(parentid);
	   if(!"".equals(id)&&!id.equals("0"))
		      icount=5;
		   else
			  icount=4;
		  
		  if(count>icount){
			  
			  mapJson.put("message","NO");
		  }else{
			  mapJson.put("message","OK");
		  }
		  
		  
	   return "menuparentCount";
   }
	
	
   
  public String meunCount(){
	  int icount=0;
	  if(null==waccount.getWaccountId()||"".equals(waccount.getWaccountId())){
		  String waccountId=ActionUtil.findWaccountId();
		  waccount.setWaccountId(waccountId);
	  }
	  int count=customizeMenuService.menuCount(waccount.getWaccountId());
	  
	  if(!"".equals(id)&&!id.equals("0"))
	    icount=3;
	   else
		  icount=2;
		
	  if(count>icount){
		  
		  mapJson.put("message","NO");
	  }else{
		  mapJson.put("message","OK");
	  }
	   return "meunCount";
   }
	
	

	public Map<String, Object> getMapJson() {
		return mapJson;
	}

	public void setMapJson(Map<String, Object> mapJson) {
		this.mapJson = mapJson;
	}

	public QueryDataBean getQueryData() {
		return queryData;
	}

	public void setQueryData(QueryDataBean queryData) {
		this.queryData = queryData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}



	

	public String getMenu_type() {
		return menu_type;
	}

	public void setMenu_type(String menu_type) {
		this.menu_type = menu_type;
	}

	public String getMenu_name() {
		return menu_name;
	}

	public void setMenu_name(String menu_name) {
		this.menu_name = menu_name;
	}

	public String getMenu_key() {
		return menu_key;
	}

	public void setMenu_key(String menu_key) {
		this.menu_key = menu_key;
	}

	public Byte getTypeid() {
		return typeid;
	}

	public void setTypeid(Byte typeid) {
		this.typeid = typeid;
	}

	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

	public String getMenu_key_id() {
		return menu_key_id;
	}

	public void setMenu_key_id(String menu_key_id) {
		this.menu_key_id = menu_key_id;
	}

	public String getInitMenuJson() {
		return initMenuJson;
	}

	public void setInitMenuJson(String initMenuJson) {
		this.initMenuJson = initMenuJson;
	}

	public CustomizeMenuServiceImpl getCustomizeMenuService() {
		return customizeMenuService;
	}

	public void setCustomizeMenuService(
			CustomizeMenuServiceImpl customizeMenuService) {
		this.customizeMenuService = customizeMenuService;
	}



	public HttpSession getSession() {
		return session;
	}

	public void setSession(HttpSession session) {
		this.session = session;
	}

	public WaccountServiceImpl getWaccountService() {
		return waccountService;
	}

	public void setWaccountService(WaccountServiceImpl waccountService) {
		this.waccountService = waccountService;
	}

	public LoggerServiceImpl getLoggerService() {
		return loggerService;
	}

	public void setLoggerService(LoggerServiceImpl loggerService) {
		this.loggerService = loggerService;
	}

	public WxCustomizeMenus getMenu() {
		return menu;
	}

	public void setMenu(WxCustomizeMenus menu) {
		this.menu = menu;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public Waccount getWaccount() {
		return waccount;
	}

	public void setWaccount(Waccount waccount) {
		this.waccount = waccount;
	}

	public String getWaccountids() {
		return waccountids;
	}

	public void setWaccountids(String waccountids) {
		this.waccountids = waccountids;
	}

	

	
	
	
	
}
