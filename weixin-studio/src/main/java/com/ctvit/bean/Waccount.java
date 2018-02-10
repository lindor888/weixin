package com.ctvit.bean;

import java.io.Serializable;
import java.util.Date;

import com.ctvit.util.BaseData;

public class Waccount  implements BaseData,Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String waccountId;
	
	private String originalId;


	private String name;

    private String token;

    private String code;

    private Byte type;

    private String welcomeContent;

    private String noReplyContent;

    private Byte ifCompressImage;

    private String url;

    private String appId;

    private String appSecret;

    private Date createTime;

    private String createAccount;

    private String operateAccount;

    private Date operateTime;

    private Byte state;
    private Byte  urgentState;//启动应急模式
    private String   createtimestring;//创建时间     显示
    private String   operatetimestring;//最后修改时间     显示
    private String    createname;//创建人名称
    private String    operatname;//最后修改人名称
    
    private String comcatalogTitle;//欢迎语内容
    
    private String nocatalogTitle;//无回复内容 
    
    private String bridgeToken;//天脉提供的token
    
    
    public String getOriginalId() {
		return originalId;
	}

	public void setOriginalId(String originalId) {
		this.originalId = originalId;
	}

    
    
    public String getComcatalogTitle() {
		return comcatalogTitle;
	}

	public void setComcatalogTitle(String comcatalogTitle) {
		this.comcatalogTitle = comcatalogTitle;
	}

	public String getNocatalogTitle() {
		return nocatalogTitle;
	}

	public void setNocatalogTitle(String nocatalogTitle) {
		this.nocatalogTitle = nocatalogTitle;
	}

	public Byte getUrgentState() {
		return urgentState;
	}

	public void setUrgentState(Byte urgentState) {
		this.urgentState = urgentState;
	}

	public String getCreatename() {
		return createname;
	}

	public void setCreatename(String createname) {
		this.createname = createname;
	}

	public String getOperatname() {
		return operatname;
	}

	public void setOperatname(String operatname) {
		this.operatname = operatname;
	}

	public String getCreatetimestring() {
		return createtimestring;
	}

	public void setCreatetimestring(String createtimestring) {
		this.createtimestring = createtimestring;
	}

	public String getOperatetimestring() {
		return operatetimestring;
	}

	public void setOperatetimestring(String operatetimestring) {
		this.operatetimestring = operatetimestring;
	}

	public String getWaccountId() {
        return waccountId;
    }

    public void setWaccountId(String waccountId) {
        this.waccountId = waccountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public String getWelcomeContent() {
        return welcomeContent;
    }

    public void setWelcomeContent(String welcomeContent) {
        this.welcomeContent = welcomeContent;
    }

    public String getNoReplyContent() {
        return noReplyContent;
    }

    public void setNoReplyContent(String noReplyContent) {
        this.noReplyContent = noReplyContent;
    }

    public Byte getIfCompressImage() {
        return ifCompressImage;
    }

    public void setIfCompressImage(Byte ifCompressImage) {
        this.ifCompressImage = ifCompressImage;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateAccount() {
        return createAccount;
    }

    public void setCreateAccount(String createAccount) {
        this.createAccount = createAccount;
    }

    public String getOperateAccount() {
        return operateAccount;
    }

    public void setOperateAccount(String operateAccount) {
        this.operateAccount = operateAccount;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public Byte getState() {
        return state;
    }

    public void setState(Byte state) {
        this.state = state;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Waccount other = (Waccount) that;
        return (this.getWaccountId() == null ? other.getWaccountId() == null : this.getWaccountId().equals(other.getWaccountId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getWelcomeContent() == null ? other.getWelcomeContent() == null : this.getWelcomeContent().equals(other.getWelcomeContent()))
            && (this.getNoReplyContent() == null ? other.getNoReplyContent() == null : this.getNoReplyContent().equals(other.getNoReplyContent()))
            && (this.getIfCompressImage() == null ? other.getIfCompressImage() == null : this.getIfCompressImage().equals(other.getIfCompressImage()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getAppId() == null ? other.getAppId() == null : this.getAppId().equals(other.getAppId()))
            && (this.getAppSecret() == null ? other.getAppSecret() == null : this.getAppSecret().equals(other.getAppSecret()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateAccount() == null ? other.getCreateAccount() == null : this.getCreateAccount().equals(other.getCreateAccount()))
            && (this.getOperateAccount() == null ? other.getOperateAccount() == null : this.getOperateAccount().equals(other.getOperateAccount()))
            && (this.getOperateTime() == null ? other.getOperateTime() == null : this.getOperateTime().equals(other.getOperateTime()))
            && (this.getState() == null ? other.getState() == null : this.getState().equals(other.getState()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getWaccountId() == null) ? 0 : getWaccountId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getWelcomeContent() == null) ? 0 : getWelcomeContent().hashCode());
        result = prime * result + ((getNoReplyContent() == null) ? 0 : getNoReplyContent().hashCode());
        result = prime * result + ((getIfCompressImage() == null) ? 0 : getIfCompressImage().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getAppId() == null) ? 0 : getAppId().hashCode());
        result = prime * result + ((getAppSecret() == null) ? 0 : getAppSecret().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateAccount() == null) ? 0 : getCreateAccount().hashCode());
        result = prime * result + ((getOperateAccount() == null) ? 0 : getOperateAccount().hashCode());
        result = prime * result + ((getOperateTime() == null) ? 0 : getOperateTime().hashCode());
        result = prime * result + ((getState() == null) ? 0 : getState().hashCode());
        return result;
    }

	public String getBridgeToken() {
		return bridgeToken;
	}

	public void setBridgeToken(String bridgeToken) {
		this.bridgeToken = bridgeToken;
	}
}