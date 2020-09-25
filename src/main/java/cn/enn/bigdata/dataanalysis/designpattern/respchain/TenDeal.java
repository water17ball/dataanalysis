package cn.enn.bigdata.dataanalysis.designpattern.respchain;

/**
 * 处理10以内的数字
 */
public class TenDeal extends BaseChain {
    @Override
    public boolean doTask(Task task) {
        if(task.getValue() <= 10){
            System.out.println(this.getClass().getName() + " do the task: " + task);
        }else {
            if(this.getNext() != null){
                this.getNext().doTask(task);
            }
        }
        return true;
    }


}
