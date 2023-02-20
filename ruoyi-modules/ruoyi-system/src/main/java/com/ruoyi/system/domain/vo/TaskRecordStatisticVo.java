package com.ruoyi.system.domain.vo;

/**
 * @filename: TaskRecordStatisticVo
 * @author: HXJ
 * @date: 2023/2/19 12:49 PM
 * @version: V1.0
 * @description: 考勤中心统计数据实体
 */
public class TaskRecordStatisticVo {

    /**
     * 组织人数
     */
    private Integer memberNum;

    /**
     * 打卡记录条数
     */
    private Integer recordNum;

    /**
     * 出勤率
     */
    private Double attendanceRate;

    /**
     * 异常情况
     */
    private Integer exceptionNum;

    public Integer getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(Integer memberNum) {
        this.memberNum = memberNum;
    }

    public Integer getRecordNum() {
        return recordNum;
    }

    public void setRecordNum(Integer recordNum) {
        this.recordNum = recordNum;
    }

    public Double getAttendanceRate() {
        return attendanceRate;
    }

    public void setAttendanceRate(Double attendanceRate) {
        this.attendanceRate = attendanceRate;
    }

    public Integer getExceptionNum() {
        return exceptionNum;
    }

    public void setExceptionNum(Integer exceptionNum) {
        this.exceptionNum = exceptionNum;
    }

    @Override
    public String toString() {
        return "TaskRecordStatisticVo{" +
                "memberNum=" + memberNum +
                ", recordNum=" + recordNum +
                ", attendanceRate=" + attendanceRate +
                ", exceptionNum=" + exceptionNum +
                '}';
    }
}
