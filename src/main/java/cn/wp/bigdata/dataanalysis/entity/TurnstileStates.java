package cn.wp.bigdata.dataanalysis.entity;

/**
 * 状态机测试：
 * 旋转门状态
 */
public enum TurnstileStates {
    Unlocked, Locked, UNPAID, WAITING_FOR_RECEIVE, DONE, CANCEL_BUYER
}
