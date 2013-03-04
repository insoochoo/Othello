// The "Othello_Final_Summative" class.
import java.applet.*;
import java.awt.*;
import java.awt.event.*;


public class Othello_Final_Summative2 extends Applet implements MouseListener, MouseMotionListener
{
    // Place instance variables here
    final static int tile_size = 70; //the size of each tile
    final static int white = -1; //value of white tile
    final static int black = 1; //value of black tile
    final static int blank = 0; //value of blank tile
    final static int startX = 20; //the size of borders
    final static int startY = 40; //the size of borders
    final static int board = 8; // number of tiles in one direction
    final static int width = 1000; // the width of the applet
    final static int height = 650; // the height of the applet

    private Image dbImage;
    private Graphics dbg;

    boolean startpage = true; //initiate the starting page

    int turn = 1; // sets the default turn to black's turn
    // -1 = white turn
    // 1 = black turn
    int count = 0; // counts how many available spots the player has
    int count2 = 0;
    int available = 4; // default value of available spots to play
    int message = 0; // message values
    // 0 = nothing
    // 1 = no places to put (change turn automatically)
    // 2 = white won
    // 3 = black won
    // 4 = draw

    int gamemode = 1; //default game mode (original)

    int original = 1; //game mode values
    int spongebob = 2; //game mode values

    int times_played = 4; // default value of how many a tile has been put on the board
    int n_white = 2; //default value of how many white tiles are on the board
    int n_black = 2; //default value of how many black tiles are on the board

    int p_white; //number of white tiles at the end in previous game
    int p_black; //number of black tiles at the end in previous game

    int xpos; //x position of the mouse
    int ypos; //y position of the mouse

    boolean tilesActive[] [] = new boolean [board] [board]; // position that the mouse is on
    int tiles[] [] = new int [board] [board]; // tiles
    // 0 = blank
    // -1 = white
    // 1 = black
    boolean playable[] [] = new boolean [board] [board]; // positions that are playable

    Image white_tile; //names an image called white_tile (white tile)
    Image black_tile; //names an image called black_tile (black tile)
    Image mouse_tile; //names an image called mouse_tile (mouse tile when the mouse is on top of a position)
    Image available_tile_blk; //names an image called available_tile (tiles that can be clicked) when its blk's turn
    Image available_tile_wht; //names an image called available_tile (tiles that can be clicked) when its white's turn
    Image background_pic;

    Image s_white_tile; //names an image called white_tile (white tile)
    Image s_black_tile; //names an image called black_tile (black tile)
    Image s_mouse_tile; //names an image called mouse_tile (mouse tile when the mouse is on top of a position)
    Image s_available_tile_blk; //names an image called available_tile (tiles that can be clicked) when its blk's turn
    Image s_available_tile_wht; //names an image called available_tile (tiles that can be clicked) when its white's turn
    Image s_background_pic;
    Image s_krab;

    Image start_page;
    Image loading;

    public void init ()
    {
	start_page = getImage (getCodeBase (), "TitlePage.JPG"); //gets the image called "TItlePage.JPG" in the same folder
	loading = getImage (getCodeBase (), "loading.GIF");

	white_tile = getImage (getCodeBase (), "untitled.PNG");
	black_tile = getImage (getCodeBase (), "untitled2.PNG");
	mouse_tile = getImage (getCodeBase (), "untitled3.PNG");
	available_tile_blk = getImage (getCodeBase (), "untitled4.PNG");
	available_tile_wht = getImage (getCodeBase (), "untitled5.PNG");

	s_white_tile = getImage (getCodeBase (), "untitled-.PNG");
	s_black_tile = getImage (getCodeBase (), "untitled2-.PNG");
	s_mouse_tile = getImage (getCodeBase (), "s_mouse.PNG");
	s_available_tile_blk = getImage (getCodeBase (), "untitled4.PNG");
	s_available_tile_wht = getImage (getCodeBase (), "untitled5.PNG");
	s_background_pic = getImage (getCodeBase (), "BG.PNG");
	s_krab = getImage (getCodeBase (), "MrKrabs.png");

	times_played = 4; // since there are 4 tiles already placed, the default value is 4
	for (int i = 0 ; i < board ; i++) //loops all the x values (8 tiles)
	{
	    for (int j = 0 ; j < board ; j++) //loops all the y values ( 8 tiles)
	    {
		tiles [i] [j] = 0; // sets every tiles in the board to blank (initializing)
	    }
	}
	tiles [3] [3] = white; //sets the default white tile in the middle
	tiles [4] [3] = black; //sets the default black tile in the middle
	tiles [3] [4] = black; //sets the default black tile in the middle
	tiles [4] [4] = white; //sets the default white tile in the middle

	addMouseListener (this);
	addMouseMotionListener (this);
	update_board ();

    } // init method



    public void paint (Graphics g)
    {
	if (startpage == true) //if the stating page has to be initialized
	{
	    g.drawImage (loading, 300, 70, this); //places the loading picture
	    g.drawImage (start_page, 0, 0, this);
	    g.setColor (Color.black); //sets the color to black
	    g.drawRect (750, 200, 240, 100); //draws the rectangle (spongebob banner)
	    g.drawRect (750, 310, 240, 100); //draws the rectangle (default banner)
	}
	else if (startpage == false) //if startpage is finished
	{
	    if (gamemode == original) //classic simple mode
	    {
		Color background = new Color (86, 86, 86); // background color to grey
		g.setColor (background); //sets the color to greyish color

		g.fillRect (0, 0, width, height); //draws the whole background
		g.setFont (new Font ("sanserif", Font.BOLD, 15));
		//g.drawImage (background_pic, 0, 0, this); //draws the black tile on the tile
		//g.fillRect (startX, startY, tile_size * 8, tile_size * 8); // makes a rectangle with that color (draws the background board)

		g.setColor (Color.black);
		g.fillRoundRect (600, 40, 380, 275, 30, 30); //draws the profile (black round square)

		g.setColor (Color.white);
		g.fillRoundRect (600, 325, 380, 275, 30, 30);

		if (turn == 1) //if turn value is 1
		{
		    g.setColor (Color.white);
		    g.setFont (new Font ("Algerian", Font.BOLD, 50));
		    g.drawString ("Turn", 720, 190); //writes "Black's Turn" on the top right corner
		}
		else //if turn value is not 1 ( if turn value is -1)
		{
		    g.setColor (Color.black);
		    g.setFont (new Font ("Algerian", Font.BOLD, 50));
		    g.drawString ("Turn", 720, 470); //writes "White's Turn" on the top right corner

		}
		if (message == 1)
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 20));
		    g.setColor (Color.red);
		    g.drawString ("Auto-Turn changed : NO PLACES TO PLAY", 580, 630); //when the player has no spots to play, turn changes
		}
		else if (message == 2) //white wins
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 40));
		    g.setColor (Color.white);
		    g.drawString ("LOST", 870, 300);
		    g.drawString ("" + p_black, 915, 255);
		    g.setColor (Color.black);
		    g.drawString ("WON", 870, 360);
		    g.drawString ("" + p_white, 915, 400); //shows the previous score

		}
		else if (message == 3) //black wins
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 40));
		    g.setColor (Color.white);
		    g.drawString ("WON", 870, 300);
		    g.drawString ("" + p_black, 915, 255);
		    g.setColor (Color.black);
		    g.drawString ("LOST", 870, 360);
		    g.drawString ("" + p_white, 915, 400);
		}
		else if (message == 4) //draws
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 40));
		    g.setColor (Color.white);
		    g.drawString ("DRAW", 870, 300);
		    g.drawString ("" + p_black, 915, 255);
		    g.setColor (Color.black);
		    g.drawString ("DRAW", 870, 360);
		    g.drawString ("" + p_white, 915, 400);
		}
		g.setFont (new Font ("sanserif", Font.BOLD, 20));
		g.setColor (Color.white);
		g.drawString ("Score : " + n_black, 610, 305);
		g.setColor (Color.black);
		g.drawString ("Score : " + n_white, 610, 350);



		for (int j = 0 ; j < board ; j++) //loops 8 times since there are 8 tiles one side
		{
		    for (int k = 0 ; k < board ; k++) //loops 8 times
		    {
			if (tilesActive [j] [k] == true) //if mouse is on the tile
			{
			    g.drawImage (mouse_tile, startX + j * tile_size, startY + k * tile_size, this); //draws the image on that tile
			}
			if (tiles [j] [k] == black) //if tile value on the tile is 1 (black)
			{
			    g.drawImage (black_tile, startX + j * tile_size, startY + k * tile_size, this); //draws the black tile on the tile
			}
			else if (tiles [j] [k] == white) //if the tile value on the tile is -1 (white)
			{
			    g.drawImage (white_tile, startX + j * tile_size, startY + k * tile_size, this); //draws the white tile on the tile
			}
			if (playable [j] [k] == true && tiles [j] [k] == blank && turn == black) //if a position is true(playable) and is empty
			{
			    g.drawImage (available_tile_blk, startX + j * tile_size, startY + k * tile_size, this); //it colors that place to available spot
			}
			else if (playable [j] [k] == true && tiles [j] [k] == blank && turn == white) //if a position is true(playable) and is empty
			{
			    g.drawImage (available_tile_wht, startX + j * tile_size, startY + k * tile_size, this); //it colors that place to available spot
			}

		    }
		}
	    }

	    else if (gamemode == spongebob) //if the game mode is 2 (spongebob)
	    {
		Color background = new Color (196, 226, 255); // background color to light blue
		g.setColor (background); //sets the color to the color

		//g.fillRect (0, 0, 1000, 650);
		g.drawImage (s_background_pic, 0, 0, this);
		g.drawImage (s_krab, 800, 50, this);
		g.setFont (new Font ("sanserif", Font.BOLD, 15));

		g.setColor (Color.black);
		g.drawRoundRect (600, 40, 380, 275, 30, 30); //draws the profile rectangle
		g.setColor (Color.white);
		g.drawRoundRect (600, 325, 380, 275, 30, 30);

		if (turn == black) //if turn value is 1 (black) in this case, when its Mr.Krab's turn
		{
		    g.setColor (Color.black);
		    g.setFont (new Font ("Algerian", Font.BOLD, 50));
		    g.drawString ("Turn", 720, 190); //writes "Turn" on the top right corner
		}
		else //if turn value is not 1 ( if turn value is -1) when its plankton's turn
		{
		    g.setColor (Color.black);
		    g.setFont (new Font ("Algerian", Font.BOLD, 50));
		    g.drawString ("Turn", 720, 470);
		}
		if (message == 1) //when the user has no places to put his/her tile
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 20));
		    g.setColor (Color.red);
		    g.drawString ("Auto-Turn changed : NO PLACES TO PLAY", 580, 630);
		}
		else if (message == 2) //white wins (Plankton Wins)
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 40));
		    g.setColor (Color.white);
		    g.drawString ("LOST", 870, 300);
		    g.drawString ("" + p_black, 915, 255); //writes the previous score
		    g.drawString ("WON", 870, 360);
		    g.drawString ("" + p_white, 915, 400);

		}
		else if (message == 3) //black wins (Mr Krab wins)
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 40));
		    g.setColor (Color.white);
		    g.drawString ("WON", 870, 300);
		    g.drawString ("" + p_black, 915, 255);
		    g.drawString ("LOST", 870, 360);
		    g.drawString ("" + p_white, 915, 400);
		}
		else if (message == 4) //when its a draw
		{
		    g.setFont (new Font ("sanserif", Font.BOLD, 40));
		    g.setColor (Color.white);
		    g.drawString ("DRAW", 870, 300);
		    g.drawString ("" + p_black, 915, 255);
		    g.drawString ("DRAW", 870, 360);
		    g.drawString ("" + p_white, 915, 400);
		}
		g.setFont (new Font ("sanserif", Font.BOLD, 20));
		g.setColor (Color.black);
		g.drawString ("Score : " + n_black, 610, 305);
		g.drawString ("Score : " + n_white, 610, 350); //write the score

		for (int j = 0 ; j < board ; j++) //loops 8 times since there are 8 tiles across the board
		{
		    for (int k = 0 ; k < board ; k++) //loops 8 times
		    {
			if (tilesActive [j] [k] == true) //if mouse is on the tile
			{
			    g.drawImage (s_mouse_tile, startX + j * tile_size, startY + k * tile_size, this); //draws the image on that tile (when the mouse is on the tile)
			}
			if (tiles [j] [k] == black) //if tile value on the tile is 1 (black)
			{
			    g.drawImage (s_black_tile, startX + j * tile_size, startY + k * tile_size, this); //draws the mrKrab tile on the tile
			}
			else if (tiles [j] [k] == white) //if the tile value on the tile is -1 (white)
			{
			    g.drawImage (s_white_tile, startX + j * tile_size, startY + k * tile_size, this); //draws the Planton tile on the tile
			}
			if (playable [j] [k] == true && tiles [j] [k] == 0 && turn == black) //if a position is true(playable) and is empty
			{
			    g.drawImage (s_available_tile_blk, startX + j * tile_size, startY + k * tile_size, this); //it colors that place to available spot
			}
			else if (playable [j] [k] == true && tiles [j] [k] == 0 && turn == white) //if a position is true(playable) and is empty
			{
			    g.drawImage (s_available_tile_wht, startX + j * tile_size, startY + k * tile_size, this); //it colors that place to available spot
			}

		    }
		}
	    }
	    g.setColor (Color.pink); //sets the color to pink
	    for (int l = 0 ; l < 560 ; l = l + tile_size)
	    {
		for (int i = 0 ; i < 560 ; i = i + tile_size)
		{
		    g.drawRect (startX + i, startY + l, tile_size, tile_size); //then draws all the tiles
		}
	    }
	    update_board ();
	}
	// Place the body of the drawing method here
    } // paint method


    public void update_board ()  //shows all the available spots to play, counts how many white, black tiles there are on the board.
    {
	if (startpage == false) //is starting page is passed by
	{
	    int tempX; //the X position of the other same color tile it finds when going across every direction
	    int tempY; //the Y position of the other same color tile it finds
	    for (int m = 0 ; m < board ; m++) //loops the whole board
	    {
		for (int n = 0 ; n < board ; n++)
		{
		    playable [m] [n] = false; //sets every tiles to false (no tiles can be played)
		}
	    }
	    count = 0; //count variable that counts how many playable tiles there are
	    for (int i = 0 ; i < board ; i++)
	    {
		for (int j = 0 ; j < board ; j++) //loops through the whole board (tiles)
		{
		    available = 0; //spots that the user can place his/her tile
		    //====== checking towards right
		    tempX = i + 1; //the x value of the tile that is being checked
		    tempY = j; //the y value of the tile that is being checked
		    while (tempX < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking toward right
			// keeps checking towards right if the color of the tile is the opposite
			// if tempX is on the border, doesnt check
			{
			    tempX++; //tempX increases
			}
		    if (tempX < board && tiles [tempX] [tempY] == turn) //if tempX is less than 8 and the tile being checked is same as the turn's color
		    {
			for (int c = i + 1 ; c < tempX ; c++) //everything that has to be changed to the turn's color
			{
			    available++; //this means that the a tile can be placed
			}
		    }
		    //======checking towards left
		    tempX = i - 1; //the x value of the tile that is being checked
		    tempY = j; //the y value of the tile that is being checked
		    while (tempX > 0 && tiles [tempX] [tempY] == turn * -1) //checking toward left
			//if tempX is less than 0, doesnt check
			{
			    tempX--;
			}
		    if (tempX >= 0 && tiles [tempX] [tempY] == turn) //checks towards left
		    {
			for (int c = tempX + 1 ; c < i ; c++) //everything that has to be changed to the turn's color
			{
			    available++;
			}

		    }
		    //======checking upwards
		    tempX = i; //the x value of the tile that is being checked
		    tempY = j - 1; //the y value of the tile that is being checked
		    while (tempY > 0 && tiles [tempX] [tempY] == turn * -1) //checking upward
			//if tempY is less than 0, doesnt check
			{
			    tempY--;
			}
		    if (tempY >= 0 && tiles [tempX] [tempY] == turn) //checks upward
		    {
			for (int c = tempY + 1 ; c < j ; c++) //everything that has to be changed to the turn's color
			{
			    available++;
			}
		    }

		    //======= checking downwards
		    tempX = i; //the x value of the tile that is being checked
		    tempY = j + 1; //the y value of the tile that is being checked
		    while (tempY < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking downward
			//if tempY is greater than 7, doesnt check because its on the bottom border and there's nothing below it
			{
			    tempY++;
			}
		    if (tempY < board && tiles [tempX] [tempY] == turn) //checks upward
		    {
			for (int c = j + 1 ; c < tempY ; c++) //(everything between) everything that has to be changed to the turn's color
			{
			    available++;
			}
		    }
		    //======= checking diagonal (left/up)
		    tempX = i - 1; //the x value of the tile that is being checked
		    tempY = j - 1; //the y value of the tile that is being checked
		    int temp_i = 1;
		    while (tempY > 0 && tempX > 0 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (left/up)
		    {
			tempY--;
			tempX--;
		    }
		    if (tempY >= 0 && tempX >= 0 && tiles [tempX] [tempY] == turn) //checks diagonal (left/up)
		    {
			for (int c = tempY + 1 ; c < j ; c++) //everything that has to be changed to the turn's color
			{
			    available++;
			}
		    }
		    //======= checking diagonal (right/up)
		    tempX = i + 1; //the x value of the tile that is being checked
		    tempY = j - 1; //the y value of the tile that is being checked
		    temp_i = 1;
		    while (tempY > 0 && tempX < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (right/up)
		    {
			tempY--;
			tempX++;
		    }
		    if (tempY >= 0 && tempX < board && tiles [tempX] [tempY] == turn) //checks diagonal (right/up)
		    {
			for (int c = j - 1 ; c > tempY ; c--) //everything that has to be changed to the turn's color
			{
			    available++;
			}
		    }
		    //======= checking diagonal (right/down)
		    tempX = i + 1; //the x value of the tile that is being checked
		    tempY = j + 1; //the y value of the tile that is being checked
		    temp_i = 1;
		    while (tempY < board - 1 && tempX < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (right/down)
		    {
			tempY++;
			tempX++;
		    }
		    if (tempY < board && tempX < board && tiles [tempX] [tempY] == turn) //checks diagonal (right/down)
		    {
			for (int c = j + 1 ; c < tempY ; c++) //everything that has to be changed to the turn's color
			{
			    available++;
			}
		    }
		    //======= checking diagonal (left/down)
		    tempX = i - 1; //the x value of the tile that is being checked
		    tempY = j + 1; //the y value of the tile that is being checked
		    temp_i = 1;
		    while (tempY < board - 1 && tempX > 0 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (right/up)
		    {
			tempY++;
			tempX--;
		    }
		    if (tempY < board && tempX >= 0 && tiles [tempX] [tempY] == turn) //checks diagonal (right/up)
		    {
			for (int c = j + 1 ; c < tempY ; c++) //everything that has to be changed to the turn's color
			{
			    available++;
			}
		    }
		    if (available > 0 && tiles [i] [j] == 0) //if there are more than 0 tiles that can be changed color and is empty
		    {
			playable [i] [j] = true; //that place is allowed to be played on (becomes true)
		    }
		}
	    }

	    n_white = 0; //sets the number of white tiles to zero
	    n_black = 0; //sets the number of black tiles to zero

	    for (int i = 0 ; i < board ; i++)
	    {
		for (int j = 0 ; j < board ; j++) //goes through the whole board
		{
		    if (tiles [i] [j] == black) // if the value is 1 (black)
		    {
			n_black++; //number of black tiles go up
		    }
		    else if (tiles [i] [j] == white)
		    {
			n_white++; //number of white tiles go up
		    }
		}
	    }
	    if (n_black == 0) //if the number of black tiles is zero
	    {
		gameover (); //game over
		times_played = 64; //makes this 64 which just ends the game becuase this is saying 64 tiles have been places on the board which means its full
	    }


	    else if (n_white == 0) //if the number of white tiles is zero
	    {
		gameover ();
		times_played = 64;
	    }


	    for (int i = 0 ; i < board ; i++)
	    {
		for (int j = 0 ; j < board ; j++) //loops through the whole board 8 by 8
		{
		    if (playable [i] [j] == true) //if its playable
		    {
			count++; //count variable goes up by 1
		    }
		}
	    }


	    //System.out.println (count + "," + times_played + "," + count2);
	    if (count == 0 && times_played < 64) //if there are no places to put and if the board isnt full
	    {
		message = 1;
		turn = turn * -1;
		repaint ();
		count2++; //counts how many times this if statement is repeated
		if (count2 > 2) //if its greater than 2, which means that both players has no spots to play
		{
		    times_played = 64; //ends the game (game ends when 64 tiles has been filled)
		    gameover (); //goes to gameover method
		}
		update_board (); //update the board again
	    }
	}
    }


    public void update (Graphics g)
    {

	// initialize buffer
	if (dbImage == null)
	{
	    dbImage = createImage (this.getSize ().width, this.getSize ().height);
	    dbg = dbImage.getGraphics ();
	}


	// clear screen in background
	dbg.setColor (getBackground ());
	dbg.fillRect (0, 0, this.getSize ().width, this.getSize ().height);

	// draw elements in background
	dbg.setColor (getForeground ());
	paint (dbg);

	// draw image on the screen
	g.drawImage (dbImage, 0, 0, this);

    }


    public void mouseClicked (MouseEvent me)
    {
	xpos = me.getX (); //x-position of the mouse cursor
	ypos = me.getY (); //y position
	if (startpage == true) //if its the starting page
	{
	    if (xpos > 750 && xpos < 990 && ypos > 200 && ypos < 300) //and it clicks the spongebob banner
	    {
		startpage = false; //starting page goes false
		gamemode = spongebob; //gamemode becomes spongebob
	    }
	    else if (xpos > 750 && xpos < 990 && ypos > 310 && ypos < 410) //if it clicks the default game banner
	    {
		startpage = false;
		gamemode = original; //gamemode becomes original
	    }
	}
	else if (startpage == false) //if starting page is false
	{
	    int tempX;
	    int tempY;
	    int count = 0; //sets the amount of available spots to zero (default value)
	    count2 = 0; //sets this to zero (default value)
	    message = 0; //message to 0 (no message) ( default value)
	    for (int i = 0 ; i < board ; i++) //loops through the whole board 8x8
	    {
		for (int j = 0 ; j < board ; j++)
		{
		    if ((xpos > startX + i * tile_size && xpos < startX + (i + 1) * tile_size) && (ypos > startY + j * tile_size && ypos < startY + (j + 1) * tile_size) && tiles [i] [j] == 0)
		    { //if the mouse cursor is in one of the tile boxes and if the tile box is blank

			//====== checking towards right
			tempX = i + 1; //the x value of the tile that is being checked
			// this is i+1 because we are checking toward the right from the next one of the tile the mouse clicked
			tempY = j; //the y value of the tile that is being checked
			while (tempX < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking toward right (while the next tile is the enemy tile)
			    // if tempX is on the border, doesnt check
			    {
				tempX++; //tempX increases
			    }
			if (tempX < board && tiles [tempX] [tempY] == turn) //if tempX is less than 8 and the last tile after all the enemy tiles is same as the turn's color
			{
			    for (int c = i + 1 ; c < tempX ; c++) //everything that has to be changed to the turn's color
			    {
				tiles [c] [j] = turn; //changes their color
				count++;
			    }

			}
			//======checking towards left
			tempX = i - 1; //the x value of the tile that is being checked
			tempY = j; //the y value of the tile that is being checked
			while (tempX > 0 && tiles [tempX] [tempY] == turn * -1) //checking toward left
			    //if tempX is less than 0, doesnt check
			    {
				tempX--;
			    }
			if (tempX >= 0 && tiles [tempX] [tempY] == turn) //checks towards left
			{
			    for (int c = tempX + 1 ; c < i ; c++) //everything that has to be changed to the turn's color
			    {
				tiles [c] [j] = turn; //changes their color
				count++;
			    }

			}
			//======checking upwards
			tempX = i; //the x value of the tile that is being checked
			tempY = j - 1; //the y value of the tile that is being checked
			while (tempY > 0 && tiles [tempX] [tempY] == turn * -1) //checking upward
			    //if tempY is less than 0, doesnt check
			    {
				tempY--;
			    }
			if (tempY >= 0 && tiles [tempX] [tempY] == turn) //checks upward
			{
			    for (int c = tempY + 1 ; c < j ; c++) //everything that has to be changed to the turn's color
			    {
				tiles [i] [c] = turn; //changes their color
				count++;
			    }

			}

			//======= checking downwards
			tempX = i; //the x value of the tile that is being checked
			tempY = j + 1; //the y value of the tile that is being checked
			while (tempY < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking downward
			    //if tempY is greater than 7, doesnt check because its on the bottom border and there's nothing below it
			    {
				tempY++;
			    }
			if (tempY < board && tiles [tempX] [tempY] == turn) //checks upward
			{
			    for (int c = j + 1 ; c < tempY ; c++) //(everything between) everything that has to be changed to the turn's color
			    {
				tiles [i] [c] = turn; //changes their color
				count++;
			    }

			}

			//======= checking diagonal (left/up)
			tempX = i - 1; //the x value of the tile that is being checked
			tempY = j - 1; //the y value of the tile that is being checked
			int temp_i = 1;
			while (tempY > 0 && tempX > 0 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (left/up)
			{
			    tempY--;
			    tempX--;
			}
			if (tempY >= 0 && tempX >= 0 && tiles [tempX] [tempY] == turn) //checks diagonal (left/up)
			{
			    for (int c = tempY + 1 ; c < j ; c++) //everything that has to be changed to the turn's color
			    {
				tiles [tempX + temp_i] [c] = turn; //changes their color
				temp_i++;
				count++;
			    }

			}
			//======= checking diagonal (right/up)
			tempX = i + 1; //the x value of the tile that is being checked
			tempY = j - 1; //the y value of the tile that is being checked
			temp_i = 1;
			while (tempY > 0 && tempX < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (right/up)
			{
			    tempY--;
			    tempX++;
			}
			if (tempY >= 0 && tempX < board && tiles [tempX] [tempY] == turn) //checks diagonal (right/up)
			{
			    for (int c = j - 1 ; c > tempY ; c--) //everything that has to be changed to the turn's color
			    {
				tiles [i + temp_i] [c] = turn; //changes their color
				temp_i++;
				count++;
			    }
			}
			//======= checking diagonal (right/down)
			tempX = i + 1; //the x value of the tile that is being checked
			tempY = j + 1; //the y value of the tile that is being checked
			temp_i = 1;
			while (tempY < board - 1 && tempX < board - 1 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (right/down)
			{
			    tempY++;
			    tempX++;
			}
			if (tempY < board && tempX < board && tiles [tempX] [tempY] == turn) //checks diagonal (right/down)
			{
			    for (int c = j + 1 ; c < tempY ; c++) //everything that has to be changed to the turn's color
			    {
				tiles [i + temp_i] [c] = turn; //changes their color
				temp_i++;
				count++;
			    }
			}
			//======= checking diagonal (left/down)
			tempX = i - 1; //the x value of the tile that is being checked
			tempY = j + 1; //the y value of the tile that is being checked
			temp_i = 1;
			while (tempY < board - 1 && tempX > 0 && tiles [tempX] [tempY] == turn * -1) //checking diagonal (right/up)
			{
			    tempY++;
			    tempX--;
			}
			if (tempY < board && tempX >= 0 && tiles [tempX] [tempY] == turn) //checks diagonal (right/up)
			{
			    for (int c = j + 1 ; c < tempY ; c++) //everything that has to be changed to the turn's color
			    {
				tiles [i - temp_i] [c] = turn; //changes their color
				temp_i++;
				count++;
			    }
			}

			if (count > 0)//if there are more than one available spots to play,
			{
			    tiles [i] [j] = turn;//that place's value becomes whoever's value
			    turn = turn * -1;//turn gets changed
			    //its  *-1 because the turns are 1 or -1 which is changed by just multiplying by -1
			    times_played++;//the number of tiles on the board increases by 1
			    update_board ();
			    if (times_played == 64)//if the number of tiles on the board is 64 (full)
			    {
				gameover ();//goes to game over
				times_played = 4;//the number of default tiles are 4 tiles
				p_white = n_white;//previous score becomes the score of the game
				p_black = n_black;
				for (int z = 0 ; z < board ; z++) //loops all the columms of the board
				{
				    for (int x = 0 ; x < board ; x++) //loops all the rows of the board
				    {
					tiles [z] [x] = 0; // sets every tiles in the board to blank (initializing)
				    }
				}
				tiles [3] [3] = -1; //sets the default white tile in the middle
				tiles [4] [3] = 1; //sets the default black tile in the middle
				tiles [3] [4] = 1; //sets the default black tile in the middle
				tiles [4] [4] = -1; //sets the default white tile in the middle//starts again after a game finishes
				update_board ();
			    }
			    repaint ();
			}
			//System.out.println (count);
		    }
		}
	    }
	}
    }


    public void gameover ()
    {
	if (n_white > n_black) //if there are more white tiles than black tiles
	{
	    message = 2; //shows white won message
	    repaint (); //paint
	}


	else if (n_white < n_black)
	{
	    message = 3; //black won sign
	    repaint ();
	}


	else //if the number of white tiles are same as the number of black tiles
	{
	    message = 4; //draw sign
	    repaint ();
	}
    }


    public void mouseEntered (MouseEvent me)
    {
    }


    public void mouseMoved (MouseEvent me)
    {
	xpos = me.getX (); //gets the x position of the mouse
	ypos = me.getY (); //gets the y position of the mouse when its moved
	if (startpage == true)
	{
	}
	else if (startpage == false)
	{
	    for (int i = 0 ; i < board ; i++)
	    {
		for (int j = 0 ; j < board ; j++)
		{
		    if ((xpos > startX + i * tile_size && xpos < startX + (i + 1) * tile_size) &&
			    (ypos > startY + j * tile_size && ypos < startY + (j + 1) * tile_size) && (tiles [i] [j] == 0) && !tilesActive [i] [j])
			    //if the xpos and y pos of the cursor is in the tile and if the tile is false which means that it's not already filled
		    {
			for (int l = 0 ; l < board ; l++)
			{
			    for (int k = 0 ; k < board ; k++)
			    {
				tilesActive [l] [k] = false;//sets everytile to false
			    }
			}
			tilesActive [i] [j] = true; //[x][y]//then sets that one tile to true
			repaint ();//repaint
		    }
		    if (xpos < startX || xpos > startX + board * tile_size || ypos < startY || ypos > startY + board * tile_size)
		    //if the mouse cursor goes out the borders
		    {
			for (int l = 0 ; l < board ; l++)
			{
			    for (int k = 0 ; k < board ; k++)
			    {
				tilesActive [l] [k] = false;//sets everytile to false
				repaint ();//repaint
			    }
			}
		    }
		}
	    }
	}
    }


    public void mouseExited (MouseEvent me)
    {
    }


    public void mousePressed (MouseEvent me)
    {
    }


    public void mouseReleased (MouseEvent me)
    {
    }


    public void mouseDragged (MouseEvent me)
    {
    }
} // Othello_Final_Summative class


