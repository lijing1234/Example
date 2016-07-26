package com.jt.jterp.model;

/**
 * Created by admin on 2016/6/21.
 */
public class UserInfo {

    /**
     * Success : true
     * Code : 3
     * Message : 登陆成功、欢迎使用ERP系统！
     * Info : {"UserId":"System","Code":"System","UserName":"超级管理员","Account":"System","Password":null,"LogTime":"2016-07-20T13:33:23.4704253+08:00","Secretkey":null,"Gender":"男","CompanyId":"系统","DepartmentId":"系统","ObjectId":null,"IPAddress":"222.173.59.170","IPAddressName":"山东省济南市电信","IsSystem":true}
     */

    private boolean Success;
    private String Code;
    private String Message;
    /**
     * UserId : System
     * Code : System
     * UserName : 超级管理员
     * Account : System
     * Password : null
     * LogTime : 2016-07-20T13:33:23.4704253+08:00
     * Secretkey : null
     * Gender : 男
     * CompanyId : 系统
     * DepartmentId : 系统
     * ObjectId : null
     * IPAddress : 222.173.59.170
     * IPAddressName : 山东省济南市电信
     * IsSystem : true
     */

    private InfoBean Info;

    public boolean isSuccess() {
        return Success;
    }

    public void setSuccess(boolean Success) {
        this.Success = Success;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String Code) {
        this.Code = Code;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String Message) {
        this.Message = Message;
    }

    public InfoBean getInfo() {
        return Info;
    }

    public void setInfo(InfoBean Info) {
        this.Info = Info;
    }

    public static class InfoBean {
        private String UserId;
        private String Code;
        private String UserName;
        private String Account;
        private Object Password;
        private String LogTime;
        private Object Secretkey;
        private String Gender;
        private String CompanyId;
        private String DepartmentId;
        private Object ObjectId;
        private String IPAddress;
        private String IPAddressName;
        private boolean IsSystem;

        public String getUserId() {
            return UserId;
        }

        public void setUserId(String UserId) {
            this.UserId = UserId;
        }

        public String getCode() {
            return Code;
        }

        public void setCode(String Code) {
            this.Code = Code;
        }

        public String getUserName() {
            return UserName;
        }

        public void setUserName(String UserName) {
            this.UserName = UserName;
        }

        public String getAccount() {
            return Account;
        }

        public void setAccount(String Account) {
            this.Account = Account;
        }

        public Object getPassword() {
            return Password;
        }

        public void setPassword(Object Password) {
            this.Password = Password;
        }

        public String getLogTime() {
            return LogTime;
        }

        public void setLogTime(String LogTime) {
            this.LogTime = LogTime;
        }

        public Object getSecretkey() {
            return Secretkey;
        }

        public void setSecretkey(Object Secretkey) {
            this.Secretkey = Secretkey;
        }

        public String getGender() {
            return Gender;
        }

        public void setGender(String Gender) {
            this.Gender = Gender;
        }

        public String getCompanyId() {
            return CompanyId;
        }

        public void setCompanyId(String CompanyId) {
            this.CompanyId = CompanyId;
        }

        public String getDepartmentId() {
            return DepartmentId;
        }

        public void setDepartmentId(String DepartmentId) {
            this.DepartmentId = DepartmentId;
        }

        public Object getObjectId() {
            return ObjectId;
        }

        public void setObjectId(Object ObjectId) {
            this.ObjectId = ObjectId;
        }

        public String getIPAddress() {
            return IPAddress;
        }

        public void setIPAddress(String IPAddress) {
            this.IPAddress = IPAddress;
        }

        public String getIPAddressName() {
            return IPAddressName;
        }

        public void setIPAddressName(String IPAddressName) {
            this.IPAddressName = IPAddressName;
        }

        public boolean isIsSystem() {
            return IsSystem;
        }

        public void setIsSystem(boolean IsSystem) {
            this.IsSystem = IsSystem;
        }
    }
}
