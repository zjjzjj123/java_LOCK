package thread;

public class Thread_Lambda {

    public static void main(String[] args) {

        new Thread(new Runnable(){
            @Override
            public void run()
            {
                System.out.println("hehe");
            }
        },"A").start();


    }
}
