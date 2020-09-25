package cn.enn.bigdata.dataanalysis.entity;

/**
 * 状态机测试：
 * 旋转门事件：投币（锁定-->解锁）、推动（解锁-->锁定）
 */
public enum TurnstileEvents {
    COIN, PUSH, PAY, RECEIVE, CANCEL
}
