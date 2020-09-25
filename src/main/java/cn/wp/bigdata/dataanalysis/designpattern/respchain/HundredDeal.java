package cn.wp.bigdata.dataanalysis.designpattern.respchain;

/**
 * 处理百以内的数字
 */
public class HundredDeal extends BaseChain {
    @Override
    public boolean doTask(Task task) {
        if(task.getValue() <= 100){
            System.out.println(this.getClass().getName() + " do the task: " + task);
        }else {
            if(this.getNext() != null){
                this.getNext().doTask(task);
            }
        }
        return true;
    }
}
