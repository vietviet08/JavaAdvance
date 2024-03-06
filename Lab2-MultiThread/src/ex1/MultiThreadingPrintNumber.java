package ex1;

import java.util.Scanner;

public class MultiThreadingPrintNumber implements Runnable {

	private int n;
	private boolean oddNumber;

	public MultiThreadingPrintNumber(int n, boolean oddNumber) {
		this.n = n;
		this.oddNumber = oddNumber;
	}

	@Override
	public void run() {

		if (this.oddNumber == true)
			printOddNumber(n);
		else
			printEvenNumber(n);

	}

	private void printOddNumber(int n) {
		for (int i = 0; i < n; i++)
			if (i % 2 == 1)
				System.out.println(i);
	}

	private void printEvenNumber(int n) {
		for (int i = 0; i < n; i++)
			if (i % 2 == 0)
				System.out.println(i);
	}

	public static void main(String[] args) {
		int n;
		System.out.println("Nhap n: ");
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		MultiThreadingPrintNumber mtpn1 = new MultiThreadingPrintNumber(n, true);
		MultiThreadingPrintNumber mtpn2 = new MultiThreadingPrintNumber(n, false);

		Thread t1 = new Thread(mtpn1);
		t1.start();
		Thread t2 = new Thread(mtpn2);
		t2.start();

		sc.close();

	}

}
