package cn.enn.bigdata.dataanalysis.concurrent.threadprint;

public class ThreadVolatilTest {
    private volatile boolean flag = true;

    public void testThead(){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char i = 'A'; i <= 'Z' ; i++) {
                    while (true){
                        if(flag){
                            System.out.println(i);
                            flag = false;
                            break;
                        }
                    }
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (char i = 'a'; i <= 'z' ; i++) {
                    while (true){
                        if(!flag){
                            System.out.println(i);
                            flag = true;
                            break;
                        }
                    }
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    public static void main(String[] args) {
        ThreadVolatilTest threadVolatilTest = new ThreadVolatilTest();
        threadVolatilTest.testThead();
    }
}
