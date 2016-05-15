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

    synchronized public void total(Object ob)
    {
        consumer c=new consumer();
        if(ob.getClass()==c.getClass())
        {
            get((consumer)ob);
        }
        else
        {
            produce((producer)ob);

        }
    }
     public void produce(producer p)
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
    public void get(consumer c)
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
            p.total(this);
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
            p.total(this);
        }
    }
}
public class Threadoff {
    public static void main(String[] args) {
        product p=new product();
        new producer("shorno2",p);
        new producer("shorno3",p);
        new consumer("ashiq",p);
        new producer("shorno",p);
        new producer("sadia",p);
        new producer("mahfuj",p);
        new consumer("ashiq1",p);
        new consumer("ashiq2",p);
        new consumer("ashiq3",p);
        new consumer("ashiq4",p);
        new consumer("ashiq5",p);


    }


}
