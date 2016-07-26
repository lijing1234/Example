package com.jt.jterp.model;

import java.util.List;

/**
 * @author 王立强
 * @Date 2016/7/20.
 */
public class TaskInfo {

    /**
     * Success : true
     * Code : 1
     * Message : null
     * TaskInfo : [{"ID":"25b14c55-d793-4e77-a98a-98bd57b28040","ProOrderID":"fff0b386-d4fe-4400-af51-cf2542c47e91","ProCompanyID":"31b05701-60ef-405c-87ba-af47049e3f48","ProCompanyName":"烟台金蕊女性用品有限公司","MaterialID":"0712010F-547C-45D2-841A-AB7C6FE49975","MaterialCode":"P-P-A0604","MaterialName":"雪莲通经护理垫","MaterialCategoryName":"产品 - 产成品 - 福贴牌","Model":"1*4","UnitName":"盒","TaskAmount":1,"DeliveryDate":"2016-04-19T00:00:00","ActualAmount":0,"CompletionRate":0,"QualifiedRate":0,"Status":1,"CreatedDate":"2016-04-15T13:30:48.52","Createdor":"超级管理员","SortCode":10000044,"Memo":"&nbsp;","TaskManager":"c68f0e71-a5c2-467e-a42b-31e28cc14ea5","TaskManagerName":"黄利云","TaskNumber":"201604063","StartTaskDate":"2016-04-15T00:00:00","EndTaskDate":"2016-05-05T00:00:00","TaskTime":21,"WorkShopName":"&nbsp;","ProductionLineName":"&nbsp;","ProductionLineID":"&nbsp;","EquName":null,"EquID":null,"ShiftsName":",","ShiftsID":"&nbsp;,","TaskType":3,"WorkShopManager":"&nbsp;","ShiftsManager":"&nbsp;","BATNO":"&nbsp;","RealDeliveryDate":null,"SeeBoard":0}]
     */

    private boolean Success;
    private String Code;
    private Object Message;
    /**
     * ID : 25b14c55-d793-4e77-a98a-98bd57b28040
     * ProOrderID : fff0b386-d4fe-4400-af51-cf2542c47e91
     * ProCompanyID : 31b05701-60ef-405c-87ba-af47049e3f48
     * ProCompanyName : 烟台金蕊女性用品有限公司
     * MaterialID : 0712010F-547C-45D2-841A-AB7C6FE49975
     * MaterialCode : P-P-A0604
     * MaterialName : 雪莲通经护理垫
     * MaterialCategoryName : 产品 - 产成品 - 福贴牌
     * Model : 1*4
     * UnitName : 盒
     * TaskAmount : 1
     * DeliveryDate : 2016-04-19T00:00:00
     * ActualAmount : 0
     * CompletionRate : 0
     * QualifiedRate : 0
     * Status : 1
     * CreatedDate : 2016-04-15T13:30:48.52
     * Createdor : 超级管理员
     * SortCode : 10000044
     * Memo : &nbsp;
     * TaskManager : c68f0e71-a5c2-467e-a42b-31e28cc14ea5
     * TaskManagerName : 黄利云
     * TaskNumber : 201604063
     * StartTaskDate : 2016-04-15T00:00:00
     * EndTaskDate : 2016-05-05T00:00:00
     * TaskTime : 21
     * WorkShopName : &nbsp;
     * ProductionLineName : &nbsp;
     * ProductionLineID : &nbsp;
     * EquName : null
     * EquID : null
     * ShiftsName : ,
     * ShiftsID : &nbsp;,
     * TaskType : 3
     * WorkShopManager : &nbsp;
     * ShiftsManager : &nbsp;
     * BATNO : &nbsp;
     * RealDeliveryDate : null
     * SeeBoard : 0
     */

    private List<TaskInfoBean> TaskInfo;

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

    public Object getMessage() {
        return Message;
    }

    public void setMessage(Object Message) {
        this.Message = Message;
    }

    public List<TaskInfoBean> getTaskInfo() {
        return TaskInfo;
    }

    public void setTaskInfo(List<TaskInfoBean> TaskInfo) {
        this.TaskInfo = TaskInfo;
    }

    public static class TaskInfoBean {
        private String ID;
        private String ProOrderID;
        private String ProCompanyID;
        private String ProCompanyName;
        private String MaterialID;
        private String MaterialCode;
        private String MaterialName;
        private String MaterialCategoryName;
        private String Model;
        private String UnitName;
        private int TaskAmount;
        private String DeliveryDate;
        private int ActualAmount;
        private int CompletionRate;
        private int QualifiedRate;
        private int Status;
        private String CreatedDate;
        private String Createdor;
        private int SortCode;
        private String Memo;
        private String TaskManager;
        private String TaskManagerName;
        private String TaskNumber;
        private String StartTaskDate;
        private String EndTaskDate;
        private int TaskTime;
        private String WorkShopName;
        private String ProductionLineName;
        private String ProductionLineID;
        private Object EquName;
        private Object EquID;
        private String ShiftsName;
        private String ShiftsID;
        private int TaskType;
        private String WorkShopManager;
        private String ShiftsManager;
        private String BATNO;
        private Object RealDeliveryDate;
        private int SeeBoard;

        public String getID() {
            return ID;
        }

        public void setID(String ID) {
            this.ID = ID;
        }

        public String getProOrderID() {
            return ProOrderID;
        }

        public void setProOrderID(String ProOrderID) {
            this.ProOrderID = ProOrderID;
        }

        public String getProCompanyID() {
            return ProCompanyID;
        }

        public void setProCompanyID(String ProCompanyID) {
            this.ProCompanyID = ProCompanyID;
        }

        public String getProCompanyName() {
            return ProCompanyName;
        }

        public void setProCompanyName(String ProCompanyName) {
            this.ProCompanyName = ProCompanyName;
        }

        public String getMaterialID() {
            return MaterialID;
        }

        public void setMaterialID(String MaterialID) {
            this.MaterialID = MaterialID;
        }

        public String getMaterialCode() {
            return MaterialCode;
        }

        public void setMaterialCode(String MaterialCode) {
            this.MaterialCode = MaterialCode;
        }

        public String getMaterialName() {
            return MaterialName;
        }

        public void setMaterialName(String MaterialName) {
            this.MaterialName = MaterialName;
        }

        public String getMaterialCategoryName() {
            return MaterialCategoryName;
        }

        public void setMaterialCategoryName(String MaterialCategoryName) {
            this.MaterialCategoryName = MaterialCategoryName;
        }

        public String getModel() {
            return Model;
        }

        public void setModel(String Model) {
            this.Model = Model;
        }

        public String getUnitName() {
            return UnitName;
        }

        public void setUnitName(String UnitName) {
            this.UnitName = UnitName;
        }

        public int getTaskAmount() {
            return TaskAmount;
        }

        public void setTaskAmount(int TaskAmount) {
            this.TaskAmount = TaskAmount;
        }

        public String getDeliveryDate() {
            return DeliveryDate;
        }

        public void setDeliveryDate(String DeliveryDate) {
            this.DeliveryDate = DeliveryDate;
        }

        public int getActualAmount() {
            return ActualAmount;
        }

        public void setActualAmount(int ActualAmount) {
            this.ActualAmount = ActualAmount;
        }

        public int getCompletionRate() {
            return CompletionRate;
        }

        public void setCompletionRate(int CompletionRate) {
            this.CompletionRate = CompletionRate;
        }

        public int getQualifiedRate() {
            return QualifiedRate;
        }

        public void setQualifiedRate(int QualifiedRate) {
            this.QualifiedRate = QualifiedRate;
        }

        public int getStatus() {
            return Status;
        }

        public void setStatus(int Status) {
            this.Status = Status;
        }

        public String getCreatedDate() {
            return CreatedDate;
        }

        public void setCreatedDate(String CreatedDate) {
            this.CreatedDate = CreatedDate;
        }

        public String getCreatedor() {
            return Createdor;
        }

        public void setCreatedor(String Createdor) {
            this.Createdor = Createdor;
        }

        public int getSortCode() {
            return SortCode;
        }

        public void setSortCode(int SortCode) {
            this.SortCode = SortCode;
        }

        public String getMemo() {
            return Memo;
        }

        public void setMemo(String Memo) {
            this.Memo = Memo;
        }

        public String getTaskManager() {
            return TaskManager;
        }

        public void setTaskManager(String TaskManager) {
            this.TaskManager = TaskManager;
        }

        public String getTaskManagerName() {
            return TaskManagerName;
        }

        public void setTaskManagerName(String TaskManagerName) {
            this.TaskManagerName = TaskManagerName;
        }

        public String getTaskNumber() {
            return TaskNumber;
        }

        public void setTaskNumber(String TaskNumber) {
            this.TaskNumber = TaskNumber;
        }

        public String getStartTaskDate() {
            return StartTaskDate;
        }

        public void setStartTaskDate(String StartTaskDate) {
            this.StartTaskDate = StartTaskDate;
        }

        public String getEndTaskDate() {
            return EndTaskDate;
        }

        public void setEndTaskDate(String EndTaskDate) {
            this.EndTaskDate = EndTaskDate;
        }

        public int getTaskTime() {
            return TaskTime;
        }

        public void setTaskTime(int TaskTime) {
            this.TaskTime = TaskTime;
        }

        public String getWorkShopName() {
            return WorkShopName;
        }

        public void setWorkShopName(String WorkShopName) {
            this.WorkShopName = WorkShopName;
        }

        public String getProductionLineName() {
            return ProductionLineName;
        }

        public void setProductionLineName(String ProductionLineName) {
            this.ProductionLineName = ProductionLineName;
        }

        public String getProductionLineID() {
            return ProductionLineID;
        }

        public void setProductionLineID(String ProductionLineID) {
            this.ProductionLineID = ProductionLineID;
        }

        public Object getEquName() {
            return EquName;
        }

        public void setEquName(Object EquName) {
            this.EquName = EquName;
        }

        public Object getEquID() {
            return EquID;
        }

        public void setEquID(Object EquID) {
            this.EquID = EquID;
        }

        public String getShiftsName() {
            return ShiftsName;
        }

        public void setShiftsName(String ShiftsName) {
            this.ShiftsName = ShiftsName;
        }

        public String getShiftsID() {
            return ShiftsID;
        }

        public void setShiftsID(String ShiftsID) {
            this.ShiftsID = ShiftsID;
        }

        public int getTaskType() {
            return TaskType;
        }

        public void setTaskType(int TaskType) {
            this.TaskType = TaskType;
        }

        public String getWorkShopManager() {
            return WorkShopManager;
        }

        public void setWorkShopManager(String WorkShopManager) {
            this.WorkShopManager = WorkShopManager;
        }

        public String getShiftsManager() {
            return ShiftsManager;
        }

        public void setShiftsManager(String ShiftsManager) {
            this.ShiftsManager = ShiftsManager;
        }

        public String getBATNO() {
            return BATNO;
        }

        public void setBATNO(String BATNO) {
            this.BATNO = BATNO;
        }

        public Object getRealDeliveryDate() {
            return RealDeliveryDate;
        }

        public void setRealDeliveryDate(Object RealDeliveryDate) {
            this.RealDeliveryDate = RealDeliveryDate;
        }

        public int getSeeBoard() {
            return SeeBoard;
        }

        public void setSeeBoard(int SeeBoard) {
            this.SeeBoard = SeeBoard;
        }
    }
}
