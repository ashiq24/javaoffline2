package sample;
class sample extends Thread
{
    sample(String nam)
    {
        super(nam);
        start();
    }
    public void run()
    {
        for( int n=1;n<=10;n++)
        {
            System.out.println(n+this.getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println(this.getName()+" "+"finished");
    }
}
public class Controller {
    public static void main(String[] args) {
        Thread t=Thread.currentThread();
        sample s1=new sample("kkkkkkk");
        sample s2=new sample("mmmmmmm");
        new sample("Ashiq");

        try {
            s1.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            s2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
