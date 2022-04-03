package lab3.num1.Main;

class MyThread extends Thread {
    Naming naming;

    MyThread(Naming naming, String name) {
        this.naming = naming;
        setName(name);
    }

    @Override
    public void run() {
        while (true) {
            synchronized (naming) {
                try {
                    System.out.println(getName());
                    Thread.sleep(2000);
                    naming.notify();
                    naming.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

class Naming {
}

class Main {
    public static void main(String[] args) {
        Naming naming = new Naming();
        new MyThread(naming, "Поток 1").start();
        new MyThread(naming, "Поток 2").start();
    }
}