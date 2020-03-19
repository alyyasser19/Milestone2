package tests;
import java.io.IOException;

import model.heroes.*;

public class mainTest {

	public static void main(String[] args) throws IOException {
		Hero a=  new Hunter();
		if (a instanceof Hero)
		    System.out.println("it works");

	}

}
