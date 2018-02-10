package com.ctvit.action;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.ctvit.bean.Account;
import com.ctvit.bean.AccountRelation;
import com.ctvit.bean.DepartRelation;
import com.ctvit.bean.Logger;
import com.ctvit.service.DepartRelationServiceImpl;
import com.ctvit.service.LoggerServiceImpl;
import com.ctvit.service.RoleServiceImpl;
import com.ctvit.util.KeyGenerator;

public class DepartRelationAction {
		private DepartRelationServiceImpl departRelationService;
		private  DepartRelation record;
		private LoggerServiceImpl loggerService;
		private Logger log = new Logger();
		private Map<String, Object> mapJson = new Hashtable<String, Object>();
		private RoleServiceImpl roleService;
		private String departId;
		private List selectedIds;
		private KeyGenerator KeyGenerator;
		private String message;
		private HttpSession session = ServletActionContext.getRequest().getSession();
		
		  private List<DepartRelation> departlist;
	        private String id;

		public String getDepartId() {
			return departId;
		}

		public void setDepartId(String departId) {
			this.departId = departId;
		}

		public List getSelectedIds() {
			return selectedIds;
		}

		public void setSelectedIds(List selectedIds) {
			this.selectedIds = selectedIds;
		}

		public Map<String, Object> getMapJson() {
			return mapJson;
		}

		public void setMapJson(Map<String, Object> mapJson) {
			this.mapJson = mapJson;
		}

		public DepartRelation getRecord() {
			return record;
		}

		public void setRecord(DepartRelation record) {
			this.record = record;
		}

		public String getMessage() {
			return message;
		}

		public void setMessage(String message) {
			this.message = message;
		}

		public DepartRelationServiceImpl getDepartRelationService() {
			return departRelationService;
		}

		public void setDepartRelationService(
				DepartRelationServiceImpl departRelationService) {
			this.departRelationService = departRelationService;
		}

		public LoggerServiceImpl getLoggerService() {
			return loggerService;
		}

		public void setLoggerService(LoggerServiceImpl loggerService) {
			this.loggerService = loggerService;
		}

		public RoleServiceImpl getRoleService() {
			return roleService;
		}

		public void setRoleService(RoleServiceImpl roleService) {
			this.roleService = roleService;
		}

		public KeyGenerator getKeyGenerator() {
			return KeyGenerator;
		}

		public void setKeyGenerator(KeyGenerator keyGenerator) {
			KeyGenerator = keyGenerator;
		}
		
		public String findByrole(){
			List<DepartRelation> list=departRelationService.selectByExample(record);
			message="OK";
			mapJson.put("message", message);
			mapJson.put("list", list);
			return "add";
		}
		
		
		public String addrelation() {
				try{
					Account account=(Account)session.getAttribute("user");
					deleterelation(selectedIds);
					LoginAction login=new LoginAction();
					
			if(null!=selectedIds&&selectedIds.size()>0){
				for (int i=0;i<selectedIds.size();i++){
					DepartRelation  relation=new DepartRelation();
					relation.setDepartId(departId.trim());
					relation.setRoleId(selectedIds.get(i).toString().trim());
					relation.setOperateAccount(account.getAccountId());//修改人
					String id=KeyGenerator.getKey(relation);
					relation.setRelationId(id);
					relation.setOperateTime(new Timestamp((new Date()).getTime()));
					departRelationService.insert(relation);
					
					}
				
					}
				log.setType("分配角色");
				log.setAct("insert");
				loggerService.saveLog(log);
				message="OK";
				}catch(Exception e){
					message="ERROR";
					e.printStackTrace();
				}
				
				mapJson.put("message", message);
				return "add";
			}
			
			
			
			public void deleterelation(List<String> list){
				
				try{
					DepartRelation departrelation=new DepartRelation();
					departrelation.setDepartId(departId.trim());
					departRelationService.deleteBydepart(departrelation);
				
			}catch(Exception e){
				e.printStackTrace();
			     }
				
			}
			
			
			public String selectdepartment(){
				
				
				
				departlist=departRelationService.selectByDepartment(id);
				
				mapJson.put("relation", departlist);
				
				return "departsuccess";
			}

			public List<DepartRelation> getDepartlist() {
				return departlist;
			}

			public void setDepartlist(List<DepartRelation> departlist) {
				this.departlist = departlist;
			}

			public String getId() {
				return id;
			}

			public void setId(String id) {
				this.id = id;
			}
			
			
			
			
		
}
