package learning;

import java.awt.Color;

import edu.neumont.ui.Picture;

public class SeamCarver {
	
	
	private Picture pic;
	private int width;
	private int height;
	
	public SeamCarver(Picture pic) {
		this.pic = pic;
		this.width = pic.width();
		this.height = pic.height();
	}

	Picture getPicture() {
		return pic;
	} // get the current image

	public int width() {
		return width;
	}

	public int height() {
		return height;
	}

	private Color getTop(int x, int y)
	{
		//double energy = 0;
		int topX = x;
		int topY = y - 1;
		if(topY < 0)
		{
			topY = (height) - 1;
		}
		else
		{
			
		}
		Color color = pic.get(topX, topY);
//		int blue = color.getBlue();
//		int green = color.getGreen();
//		int red = color.getRed();
//		energy = (blue^2) + (green^2) + (red^2);
		return color;	
	}

	private Color getLeft(int x, int y) {
		//double energy = 0;
		int leftX = x-1;
		int leftY = y;
		if(leftX < 0)
		{
			leftX = width-1;
		}
		Color color = pic.get(leftX, leftY);
//		int blue = color.getBlue();
//		int green = color.getGreen();
//		int red = color.getRed();
//		energy = (blue^2) + (green^2) + (red^2);
			
		return color;
	}

	private Color getRight(int x, int y) {
		//double energy = 0;
		int rightX = x+1;
		int rightY = y;
		if(rightX > (width-1))
		{
			rightX = 0;
		}
		Color color = pic.get(rightX, rightY);
//		int blue = color.getBlue();
//		int green = color.getGreen();
//		int red = color.getRed();
//		energy = (blue^2) + (green^2) + (red^2);
			
		return color;
	}

	private Color getBottom(int x, int y) {
		double energy = 0;
		int bottomX = x;
		int bottomY = y + 1;
		if(bottomY > (height-1))
		{
			bottomY = 0;
		}
		Color color = pic.get(bottomX, bottomY);
//		int blue = color.getBlue();
//		int green = color.getGreen();
//		int red = color.getRed();
//energy = (blue^2) + (green^2) + (red^2);
			
		return color;
	}
	
	private double DeltaXChange(Color leftColor, Color rightColor)
	{
		int pixelRedChange = 0;
		int pixelGreenChange = 0;
		int pixelBlueChange = 0;
		double DeltaX = 0;
		pixelRedChange = leftColor.getRed() - rightColor.getRed();
		pixelGreenChange = leftColor.getGreen() - rightColor.getGreen();
		pixelBlueChange = leftColor.getBlue() - rightColor.getBlue();
		DeltaX = Math.pow(pixelRedChange, 2) + Math.pow(pixelGreenChange, 2) + Math.pow(pixelBlueChange, 2);
		
		return DeltaX;
	}
	private double DeltaYChange(Color topColor, Color bottomColor)
	{
		int pixelRedChange = 0;
		int pixelGreenChange = 0;
		int pixelBlueChange = 0;
		double DeltaY = 0;
		pixelRedChange = topColor.getRed() - bottomColor.getRed();
		pixelGreenChange = topColor.getGreen() - bottomColor.getGreen();
		pixelBlueChange = topColor.getBlue() - bottomColor.getBlue();
		DeltaY = Math.pow(pixelRedChange, 2) + Math.pow(pixelGreenChange, 2) + Math.pow(pixelBlueChange, 2);//(pixelBlueChange^2);
		
		return DeltaY;
	}

	public double energy(int x, int y) 
	{	
		
		double energy = 0;
		if(x < width && y < height)
		{
			//Color pixelColor = pic.get(x, y);
			
			Color topColor = getTop(x, y);
			Color bottomColor = getBottom(x, y);
			Color rightColor = getRight(x, y);
			Color leftColor = getLeft(x, y);
			double DeltaX = DeltaXChange(leftColor, rightColor);
			double DeltaY = DeltaYChange(topColor, bottomColor);
			
			energy = Math.pow(DeltaX, 2) + Math.pow(DeltaY, 2);
						
		}
		else
		{
			throw new java.lang.IndexOutOfBoundsException();
		}
		return energy;
	} // the energy of a pixel at (x,y)

	
	public int[] findHorizontalSeam() {

		int bestHorizontalSeam[] = new int[width];
		double LowestEnergy = Double.MAX_VALUE;
		double topRight = 0;
		double right = 0;
		double bottomRight = 0;
		
		for (int i = 0; i < height; i++) 
		{
			int x = 0;
			int y = i;
			int currentEnergy = 0;
			int currentHorizontalSeam[] = new int[width];
			currentHorizontalSeam[x] = y;
			while(x < width-1)
			{
				if (y == 0) 
				{
					right = energy(x+1, y);
					bottomRight = energy(x+1, y+1);
					if(right < bottomRight)
					{
						x++;
						currentHorizontalSeam[x] = y;
						//x++;
					}
					else
					{
						x++;
						y++;
						currentHorizontalSeam[x] = y;
						//x++;
						//y+=1;
					}
				} 
				else if (y == (height - 1)) 
				{
					right = energy(x+1, y);
					topRight = energy(x+1, y-1);
					if(right < topRight)
					{
						x++;
						currentHorizontalSeam[x] = y;
						//x++;
					}
					else
					{
						x++;
						y-=1;
						currentHorizontalSeam[x] = y;
//						x++;
//						y -= 1;
					}
				} 
				else 
				{
					right = energy(x+1, y);
					topRight = energy(x+1, y -1);
					bottomRight = energy(x+1, y+1);
					if(right < topRight && right < bottomRight)
					{
						x++;
						currentHorizontalSeam[x] = y;
						//x++;
					}
					else if(topRight < right && topRight < bottomRight)
					{
						x++;
						y -=1;
						currentHorizontalSeam[x] = y;
						//x++;
						//y -= 1;
					}
					else//bottomRightSmallest
					{
						x++;
						y++;
						currentHorizontalSeam[x] = y;
//						x++;
//						y+=1;
					}
				}
			}
			if(currentEnergy < LowestEnergy)
			{
				LowestEnergy = currentEnergy;
				bestHorizontalSeam = currentHorizontalSeam;
			}
			
		}
		return bestHorizontalSeam;
	} // the sequence of indices for a horizontal seam
	

	
	

	public int[] findVerticalSeam() {
		int bestVerticalSeam[] = new int[height];
		double LowestEnergy = Double.MAX_VALUE;
		double bottomRight = 0;
		double bottom = 0;
		double bottomLeft = 0;
		
		for (int i = 0; i < width; i++) 
		{
			int x = i;
			int y = 0;
			int currentEnergy = 0;
			int currentVerticalSeam[] = new int[height];
			currentVerticalSeam[y] = x;
			while(y < height-1)
			{
				if (x == 0) 
				{
					bottom = energy(x, y+1);
					bottomRight = energy(x+1, y+1);
					if(bottom < bottomRight)
					{
						y++;
						currentVerticalSeam[y] = x;
						//y++;
					}
					else
					{
						y++;
						x++;
						currentVerticalSeam[y] = x;
//						x++;
//						y+=1;
					}
				} 
				else if (x == (width - 1)) 
				{
					bottom = energy(x, y+1);
					bottomLeft = energy(x-1, y+1);
					if(bottom < bottomLeft)
					{
						y++;
						currentVerticalSeam[y] = x;
						
					}
					else
					{
						x-=1;
						y++;
						currentVerticalSeam[y] = x;
						
					}
				} 
				else 
				{
					bottom = energy(x, y+1);
					bottomRight = energy(x+1, y+1);
					bottomLeft = energy(x-1, y+1);
					if(bottom < bottomRight && bottom < bottomLeft)
					{
						y++;
						currentVerticalSeam[y] = x;
						
					}
					else if(bottomRight < bottom && bottomRight < bottomLeft)
					{
						y++;
						x++;
						currentVerticalSeam[y] = x;
					}
					else//bottomLeftSmallest
					{
						x-=1;
						y++;
						currentVerticalSeam[y] = x;
					}
				}
			}
			if(currentEnergy < LowestEnergy)
			{
				LowestEnergy = currentEnergy;
				bestVerticalSeam = currentVerticalSeam;
			}
			
		}
		return bestVerticalSeam;
	} // the sequence of indices for a vertical seam

	public void removeHorizontalSeam(int[] indices) {
		if(indices.length == width)
		{
			int y = 0;
			for(int x = 0; x < width; x ++)
			{
				y = indices[x];
				changePixelOfRow(x,y);			
			}
			createHorizontalNewPicture();
			
		}
		else
		{
			throw new java.lang.IllegalArgumentException ();
		}
		
	}
	private void createHorizontalNewPicture() 
	{
		Picture picture = new Picture(height -1, width);
		for(int x = 0; x < width-1; x++)
		{
			for(int y = 0; y < (height-1); y++)
			{
				picture.set(x, y, pic.get(x, y));
			}
		}
		pic = picture;
		
		
		// TODO Auto-generated method stub
		
	}
	private void createVerticalNewPicture() 
	{
		Picture picture = new Picture(height, width-1);
		for(int x = 0; x < width - 1; x++)
		{
			for(int y = 0; y < (height); y++)
			{
				picture.set(x, y, pic.get(x, y));
				///
				
				
				
			}
		}
		pic = picture;
		
		
		// TODO Auto-generated method stub
		
	}

	public void changePixelOfRow(int x, int y)
	{
		while(y < height)
		{
			pic.set(x, y, pic.get(x, y++));			
			//y++;
		}
	}

	public void changePixelOfColumn(int x,int y)
	{
		while(x < width)
		{
			pic.set(x, y, pic.get(x++, y));
		}
	}
	public void removeVerticalSeam(int[] indices) {
		if(indices.length == height)
		{
			int x = 0;
			for(int y = 0; y < height; y++)
			{
				x = indices[y];
				changePixelOfColumn(x,y);			
			}
			createVerticalNewPicture();
		}
		else
		{
			throw new java.lang.IllegalArgumentException();
		}
		
	}
}
