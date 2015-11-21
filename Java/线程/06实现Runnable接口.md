1.第二种方式实现多线程：实现Runnable接口

	/*
	 * 方式2：实现Runnable接口
	 * 步骤：
	 * 		A:自定义类MyRunnable实现Runnable接口
	 * 		B:重写run()方法
	 * 		C:创建MyRunnable类的对象
	 * 		D:创建Thread类的对象，并把C步骤的对象作为构造参数传递
	 */
	public class MyRunnableDemo {
		public static void main(String[] args) {
			// 创建MyRunnable类的对象
			MyRunnable my = new MyRunnable();

			// 创建Thread类的对象，并把C步骤的对象作为构造参数传递
			// Thread(Runnable target)
			// Thread t1 = new Thread(my);
			// Thread t2 = new Thread(my);
			// t1.setName("林青霞");
			// t2.setName("刘意");

			// Thread(Runnable target, String name)
			Thread t1 = new Thread(my, "A");
			Thread t2 = new Thread(my, "B");

			t1.start();
			t2.start();
		}
	}
	
	public class MyRunnable implements Runnable {
		@Override
		public void run() {
			for (int x = 0; x < 100; x++) {
				//由于实现接口的方式就不能直接使用Thread类的方法了,但是可以间接的使用
				System.out.println(Thread.currentThread().getName() + ":" + x);
			}
		}

	}