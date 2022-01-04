import javax.sound.sampled.SourceDataLine;

public class Lab1 {

	// The variable timesCalled will always contain the number of times the button has been
	// pushed, so it will be 1 the first time this method is called, 2 the second time, etc.
	int timesCalled = 0;

	
	public void process(int timesCalled) {

		// get the red, green and blue pixel values of image 0 (the first image on the screen

		int[][] red = Images.getRed(0);
		int[][] blue = Images.getBlue(0);
		int[][] green = Images.getGreen(0);

		int imageHeight = red.length;
		int imageWidth = red[0].length;

		System.out.println("There are "+imageHeight+" rows and "+imageWidth +" columns");

		if (timesCalled == 1) { 
			// This happens the first time the button is pushed

			// declare new arrays for red, green and blue values for new image,
			// allocate new ones for blue and green (do red later)


			int[][] newblue = new int[imageHeight][imageWidth];
			int[][] newgreen = new int[imageHeight][imageWidth];
			int[][] newred;

			for(int row = 0; row< imageHeight ; row++) {
				for (int column= 0; column < imageWidth; column++) {
					newgreen[row][column] = green[row][column];
					newblue[row][column] = blue[row][column];

				}

			}

			newred = tintRed(red, imageHeight, imageWidth);

			redStripe(imageWidth, newblue, newgreen, newred);

			for (int col = 0; col < imageWidth; col++){
				for(int row = 39; row < 44; row++){
					newgreen[row][col] = 0;
					newblue[row][col] = 0;
					newred[row][col] = 0;
				}
			}

			for (int col = 9; col < 15; col++){
				for(int row = 0; row < imageHeight; row++){
					newgreen[row][col] = 255;
					newblue[row][col] = 0;
					newred[row][col] = 0;
				}
			}

			// add this new image as image 1 on the screen (the second image)
			Images.setImage(newred, newgreen, newblue,1);
			
		}
		else if (timesCalled == 2) {
			// code for timesCalled == 2 (second button push)
			
		}
		else {	
			// code for timesCalled greater than 2	}

			red = rotateCW(red, imageWidth, imageHeight);
			green = rotateCW(green, imageWidth, imageHeight);
			blue = rotateCW(blue, imageWidth, imageHeight);

			Images.setImage(red,green,blue,timesCalled);
		}
	}

	private void redStripe(int imageWidth, int[][] newblue, int[][] newgreen, int[][] newred) {
		for(int row = 100; row< 120 ; row++) {
			for (int column= 0; column < imageWidth; column++) {
				newred[row][column] = 255;
				newgreen[row][column] = 0;
				newblue[row][column] = 0;
			}
		}
	}

	private int[][] tintRed(int[][] red, int imageHeight, int imageWidth) {
		int[][] newred = new int[imageHeight][imageWidth];
		for(int row = 0; row< imageHeight ; row++) {
			for (int column= 0; column < imageWidth; column++) {
				newred[row][column] = red[row][column]+(255-red[row][column])/2;
			}
		}
		return newred;
	}


	private int[][] rotateCW(int[][] array, int imageWidth, int imageHeight){
		int temp = imageHeight;
		imageHeight = imageWidth;
		imageWidth = temp;
		System.out.println(imageHeight);
		int[][] newArray = new int[imageHeight][imageWidth];
		for(int row = 0; row < imageWidth; row++){
			for (int col = 0; col < imageHeight; col++){
				newArray[col][imageWidth-row-1] = array[row][col];
			}
		}

		return newArray;

	}

	private void shrinkFour(int[][] red, int[][] blue, int[][] green, int imageWidth, int imageHeight){
		imageWidth = imageWidth / 4;
		imageHeight = imageHeight / 4;
	}

}
