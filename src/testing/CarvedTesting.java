package testing;

import static org.junit.Assert.*;
import learning.SeamCarver;

import org.junit.Test;

import edu.neumont.ui.Picture;

public class CarvedTesting {

	@Test
	public void test() {
		//fail("Not yet implemented");
	}
	@Test
	public void HorizontalCarveTest()
	{
		Picture pic = new Picture("overlayimagewithhiddenmessage.png");
		SeamCarver carve =  new SeamCarver(pic);
		carve.removeHorizontalSeam(carve.findHorizontalSeam());
		pic.save("horizontalCarved.png");
	}

}
