package sample;


import static java.lang.Thread.sleep;

/**
 * Created by Ashiq on 5/15/2016.
 */
class product
{
    int [] array;
    int pos;
    product()
    {
        array=new int[20];
        pos=-1;
    }
    synchronized public void produce(producer p)
    {
            while(pos==array.length-1)
            {
                try {

                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            pos++;
            array[pos]=pos;
            System.out.println(p.name+" prduced "+array[pos]);
            try {
                sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            notifyAll();

    }
    synchronized public void get(consumer c)
    {
        while(pos==-1 )
        {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        System.out.println(c.name+" got "+array[pos]);
        pos--;
        try {
            sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        notifyAll();

    }

}
class producer implements Runnable
{
    String name;
    product p;
    Thread t;
    producer(String nam,product q)
    {
        name=nam;
        p=q;
        t=new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        while(true)
        {
            p.produce(this);
        }
    }
}
class consumer implements  Runnable
{
    String name;
    product p;
    Thread t;
    consumer(String nam,product q)
    {
        name=nam;
        p=q;
        t=new Thread(this);
        t.start();
    }
    consumer()
    {

    }
    @Override
    public void run() {
        while(true)
        {
            p.get(this);
        }
    }
}
public class Threadoff {
    public static void main(String[] args) {
        product p=new product();
        new producer("producer1",p);
        new consumer("consumer1",p);
        new producer("producer2",p);
        new consumer("consumer2",p);
        new producer("producer3",p);
        new consumer("consumer3",p);
        new producer("producer4",p);
        new consumer("consumer4",p);




    }


}
