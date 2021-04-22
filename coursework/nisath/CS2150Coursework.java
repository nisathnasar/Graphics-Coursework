/* CS2150Coursework.java
 * 190080440@aston.ac.uk
 * First name: Nisath
 * Surname: Mohamed Nasar
 *
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [T(ballX, ballY, ballZ)] Ball
 *  |
 *  +-- [S(2f, 2f, 1f)] Back wall
 *  |	|
 *  |	+-- [T(-10f, 0f, 0f)] Back wall
 *  |
 *  +-- [S(1f, 2f, 2f) T(0f, 0f, -5f)] Side wall
 *  |	|
 *  |	+-- [T(0f, 0f, -10f)] Side wall
 *  |	|
 *  |	+-- [T(0f, 0f, -10f)] Side wall
 *  |
 *  +-- [T(-10f, 0f, 0f)] Ground plane
 *  |	|
 *  |	+-- [T(10f, 0f, 0f)] Ground plane
 *	|	|
 *  |	+-- [T(10f, 0f, 0f)] Ground plane
 *  |
 *  +-- [T(5f, 1.5f, -1f)] Holder
 *  |	|
 *  |	+--[T(0f, 1.75f, -2.4f)] Ball
 *  |	|
 *  |	+--[T(0f, 0f, 1.1f)] Ball
 *  |	|
 *  |	+--[T(0f, 0.1f, 1.2f)] Ball
 *  |	|
 *  |	+--[T(0f, -0.1f, 1.2f)] Ball
 *  |	|
 *  |	+--[T(0f, 0.1f, 1.2f)] Ball
 *  |
 *  +-- [T(-5f, 0.07f, -26)] Railing left
 *  |	|
 *  |	+-- [T(10f, 0, 0)] Railing right
 *  |
 *  +-- [T(0, 0, rowPosition1 + 12) S(0.5f, 0.7f, 0.4f)]
 *  |	|
 *  |	+-- [T(pin11X, pin11Y, pin11Z)] Pin 1 (position 1 1)
 *  |	|
 *  |	+-- [T(pin21X, pin21Y, pin21Z)] Pin 2 (position 2 1)
 *  |	|
 *  |	+-- [T(pin22X, pin22Y, pin22Z)] Pin 3 (position 2 2)
 *  |	|
 *  |	+-- [T(pin31X, pin31Y, pin31Z)] Pin 4 (position 3 1)
 *  |	|
 *  |	+-- [T(pin32X, pin32Y, pin32Z)] Pin 5 (position 3 2)
 *  |	|
 *  |	+-- [T(pin33X, pin33Y, pin33Z)] Pin 6 (position 3 3)
 *  |	|
 *  |	+-- [T(pin41X, pin41Y, pin41Z)] Pin 7 (position 4 1)
 *  |	|
 *  |	+-- [T(pin42X, pin42Y, pin42Z)] Pin 8 (position 4 2)
 *  |	|
 *  |	+-- [T(pin43X, pin43Y, pin43Z)] Pin 9 (position 4 3)
 *  |	|
 *  |	+-- [T(pin44X, pin44Y, pin44Z)] Pin 10 (position 4 4)
 *  |	
 *  +-- [T(0f, 11f, -49) S(1f, 1.4f, 1f)] TV
 *  |
 *  +-- [S(0.5f, 0.5f, 0.5f) T(4f, 10.7f, 5f) RY(180)] Man
 */

package coursework.nisath;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;
import GraphicsLab.GraphicsLab;


/**
 * This is a bowling game. This game interface lets the user roll the ball from
 * different positions. The user is also free to move the ball while it is
 * rolling. The pins will fall after taking impact. The television screen will
 * react and display the text "STRIKE!". This can be seen if the user presses
 * space bar after the ball has finished rolling. The complicated object is the
 * pin, it contains 61 vertices. The animation is that the pins will react once
 * the ball touches them by moving away and falling down due to impact. It has
 * several user controls. The camera moves closer to the pins once the ball is
 * close to the pins. For ease of reading this Java Doc, collapse all the braces
 * by pressing ctrl + shift + / on Windows eclipse.
 *
 * The game also features a low poly human model, who can be made to swing arms
 * when rolling the ball, in the future.
 * 
 * <p>
 * Controls:
 * <ul>
 * <li>Press the escape key to exit the application.
 * <li>Hold the x, y and z keys to view the scene along the x, y and z axis,
 * respectively
 * <li>While viewing the scene along the x, y or z axis, use the up and down
 * cursor keys to increase or decrease the viewpoint's distance from the scene
 * origin
 * <li>Press space bar to roll the ball or resume animation if paused, or
 * continue after ball stopped rolling.
 * <li>Press r to reset the scene.
 * <li>Press p to pause the animation.
 * <li>Press s to rotate or (spin) the scene.
 * <li>Press shift+s to rotate the scene other way.
 * <li>Press left arrow key to move the ball left.
 * <li>Press right arrow key to move the ball to its right.
 * <li>Press up arrow key to move the ball to the middle.
 * </ul>
 *
 */

public class CS2150Coursework extends GraphicsLab {
	private final int backWall = 1;
	private final int groundList = 2;
	private final int sideWall = 3;
	private final int pinList = 4;
	private final int holderList = 5;
	private final int railingList = 6;
	private final int teleList = 7;
	private final int manList = 8;
	private int count = 0;

	float shininess = 1.0f;
	private float sceneRotateSpeed = 1f;
	private float ballRadius = 0.75f;
	private float pinRadius = 0.5f;
	private float ballX = 0f;
	private float ballY = 0f;
	private float ballZ = 0f;
	private final float defaultBowlSpeed = 0.4f;
	private float bowlSpeed = defaultBowlSpeed;
	private float zGap = -2.5f;
	private float xGap = 2.9f;
	private float rowPosition1 = -35f;
	float rowPosition2 = rowPosition1 + zGap;
	private float rowPosition3 = rowPosition2 + zGap;
	private float rowPosition4 = rowPosition3 + zGap;

	private float sceneRotationAngle = 0f;
	private float pinRaiseValue1 = 0f;
	private float pinRaiseValue2 = 0f;
	private float pinRaiseValue3 = 0f;
	private float pinRaiseValue4 = 0f;
	private float pinSideMoveValue1 = 0f;
	private float pinSideMoveValue2 = 0f;
	private float pinSideMoveValue3 = 0f;
	private float pinSideMoveValue4 = 0f;

	private float pin11X, pin11Y, pin11Z;
	private float pin21X, pin21Y, pin21Z;
	private float pin22X, pin22Y, pin22Z;
	private float pin31X, pin31Y, pin31Z;
	private float pin32X, pin32Y, pin32Z;
	private float pin33X, pin33Y, pin33Z;
	private float pin41X, pin41Y, pin41Z;
	private float pin42X, pin42Y, pin42Z;
	private float pin43X, pin43Y, pin43Z;
	private float pin44X, pin44Y, pin44Z;

	float ballLeftSidetest = ballX - ballRadius;
	float ballRightSidetest = ballX + ballRadius;

	private float ballRotateValue;
	private float ballRaiseValue = ballRadius;
	private float rotationOfRow1 = 0f;
	private float rotationOfRow2 = 0f;
	private float rotationOfRow3 = 0f;
	private float rotationOfRow4 = 0f;
	private float cameraViewPointX = 7f;
	private float cameraViewPointY = 0f;
	private float cameraViewPointZ = -15f;
	private float viewerLocX = -11f;
	private float viewerLocY = 6f;
	private float viewerLocZ = 10f;
	private float viewUpVectorX = 0f;
	private float viewUpVectorY = 1f;
	private float viewUpVectorZ = 0f;

	private boolean bowlKeyRelease = false;
	private boolean passedRow4;
	private boolean isRowFall1;
	private boolean isRowFall2;
	private boolean isRowFall3;
	private boolean isRowFall4;
	private boolean isPinFallSceneOne;
	private boolean reset;
	private boolean pause;

	private boolean pin2modded;
	private boolean pin3modded;
	private boolean pin5modded;
	private boolean pin7modded;
	private boolean pin8modded;
	private boolean pin9modded;
	private boolean pin10modded;

	private boolean isPin1Hit, isPin2Hit, isPin3Hit, isPin4Hit, isPin5Hit, isPin6Hit, isPin7Hit, isPin8Hit, isPin9Hit,
			isPin10Hit;
	
	private boolean isBallMoving;

	private Texture groundTextures, sideWallTexture, tvTexture, tvStrikeTexture;
	private Colour wallColour;
	private Meshes meshes;

	public static void main(String args[]) {
		new CS2150Coursework().run(WINDOWED, "Bowling Game", 1f);
	}

	protected void initScene() throws Exception {
		groundTextures = loadTexture("coursework/nisath/textures/pexels-fwstudio-1297311.bmp");
		sideWallTexture = loadTexture("coursework/nisath/textures/whitewall.bmp");
		tvTexture = loadTexture("coursework/nisath/textures/tv screen first.bmp");
		tvStrikeTexture = loadTexture("coursework/nisath/textures/tv screen second.bmp");

		wallColour = new Colour(131, 133, 163);
		meshes = new Meshes();

		initLighting();

		pin11Z = rowPosition1;
		pin21X = -(xGap / 2);
		pin21Z = zGap + rowPosition1;
		pin22X = xGap / 2;
		pin22Z = zGap + rowPosition1;
		pin31X = -xGap;
		pin31Z = zGap * 2 + rowPosition1;
		pin32Z = (zGap * 2) + rowPosition1;
		pin33X = xGap;
		pin33Z = zGap * 2 + rowPosition1;
		pin41X = -(xGap + (xGap / 2));
		pin41Z = zGap * 3 + rowPosition1;
		pin42X = -(xGap / 2);
		pin42Z = zGap * 3 + rowPosition1;
		pin43X = xGap / 2;
		pin43Z = zGap * 3 + rowPosition1;
		pin44X = xGap + (xGap / 2);
		pin44Z = zGap * 3 + rowPosition1;

		GL11.glNewList(sideWall, GL11.GL_COMPILE);
		{
			meshes.drawUnitSideWall();
		}
		GL11.glEndList();
		GL11.glNewList(backWall, GL11.GL_COMPILE);
		{
			meshes.drawUnitBackWall();
		}
		GL11.glEndList();
		GL11.glNewList(groundList, GL11.GL_COMPILE);
		{
			meshes.drawUnitGround();
		}
		GL11.glEndList();
		GL11.glNewList(pinList, GL11.GL_COMPILE);
		{
			meshes.drawUnitPin();
		}
		GL11.glEndList();
		GL11.glNewList(holderList, GL11.GL_COMPILE);
		{
			meshes.drawUnitHolder();
		}
		GL11.glEndList();
		GL11.glNewList(railingList, GL11.GL_COMPILE);
		{
			meshes.drawUnitRailing();
		}
		GL11.glEndList();
		GL11.glNewList(teleList, GL11.GL_COMPILE);
		{
			meshes.drawUnitTV();
		}
		GL11.glEndList();
		GL11.glNewList(manList, GL11.GL_COMPILE);
		{
			meshes.drawUnitMan();
		}
		GL11.glEndList();
	}

	protected void checkSceneInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_LSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_S)
				|| Keyboard.isKeyDown(Keyboard.KEY_RSHIFT) && Keyboard.isKeyDown(Keyboard.KEY_S)) {
			sceneRotationAngle -= sceneRotateSpeed * getAnimationScale();
			if (sceneRotationAngle < 0.0f) {
				sceneRotationAngle = 360.0f;
			}
		} else if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
			sceneRotationAngle += sceneRotateSpeed * getAnimationScale();
			if (sceneRotationAngle > 360.0f) {
				sceneRotationAngle = 0.0f;
			}
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_R)) {
			reset = true;
			bowlSpeed = defaultBowlSpeed;
			sceneRotationAngle = 0f;
			bowlKeyRelease = false;
			passedRow4 = false;
			isRowFall1 = false;
			isRowFall2 = false;
			isRowFall3 = false;
			isRowFall4 = false;
			ballZ = 0f;
			pinRaiseValue1 = 0f;
			pinRaiseValue2 = 0f;
			pinRaiseValue3 = 0f;
			pinRaiseValue4 = 0f;
			pinSideMoveValue1 = 0f;
			pinSideMoveValue2 = 0f;
			pinSideMoveValue3 = 0f;
			pinSideMoveValue4 = 0f;
			ballX = 0f;
			cameraViewPointX = 7f;
			cameraViewPointY = 0f;
			cameraViewPointZ = -15f;
			viewerLocX = -11f;
			viewerLocY = 6f;
			viewerLocZ = 10f;
			viewUpVectorX = 0f;
			viewUpVectorY = 1f;
			viewUpVectorZ = 0f;

			pin11Z = rowPosition1;
			pin21Z = zGap + rowPosition1;
			pin22Z = zGap + rowPosition1;
			pin31Z = zGap * 2 + rowPosition1;
			pin32Z = (zGap * 2) + rowPosition1;
			pin33Z = zGap * 2 + rowPosition1;
			pin41Z = zGap * 3 + rowPosition1;
			pin42Z = zGap * 3 + rowPosition1;
			pin43Z = zGap * 3 + rowPosition1;
			pin44Z = zGap * 3 + rowPosition1;
			isPin1Hit = false;
			isPin2Hit = false;
			isPin3Hit = false;
			isPin4Hit = false;
			isPin5Hit = false;
			isPin6Hit = false;
			isPin7Hit = false;
			isPin8Hit = false;
			isPin9Hit = false;
			isPin10Hit = false;
			pause = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && ballZ > -50) {
			bowlKeyRelease = true;
			bowlSpeed = defaultBowlSpeed;
			pause = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			// ballX = -4;
			ballX -= 0.05f*getAnimationScale();
			// isPinFallSceneOne = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			ballX = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			// ballX = 4;
			ballX += 0.05f*getAnimationScale();
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			bowlSpeed = 0f;
			pause = true;
			isBallMoving = false;
			printFields();
		}
	}

	protected void updateScene() {
		// start rolling ball for as long as bowlKeyRelease is true, which is made true
		// once H is pressed
		if (bowlKeyRelease && ballZ > -50) {
			ballZ -= bowlSpeed * getAnimationScale();
			isBallMoving = true;
		}else {
			isBallMoving = false;
		}
		
		if(isBallMoving) {
			if(ballRotateValue == 359) {
				ballRotateValue = 0;
			}
			ballRotateValue -= 3 * getAnimationScale();
		}
		
		pinRadius = 0.5f + 0.5f - 0.2f;
		if (ballZ + (ballRadius * 4) <= rowPosition1) {
			isRowFall1 = true;
		}
		if (ballZ + (ballRadius * 4) <= rowPosition2) {
			isRowFall2 = true;
		}
		if (ballZ + (ballRadius * 4) <= rowPosition3) {
			isRowFall3 = true;
		}
		if (ballZ + (ballRadius * 4) <= rowPosition4) {
			passedRow4 = true;
			isRowFall4 = true;
		}

		if (!bowlKeyRelease) {
			isPinFallSceneOne = true;
		}
		if (isPinFallSceneOne && !pause) {
			pinsFallScene();
		}

		pinsFallPhysics();

		if (!pause) {
			updateCamera();
		}

		// h key is pressed && passed row 1 && gone past last row
		// change row 1 to last row
		if (bowlKeyRelease && passedRow4 && ballZ - ballRadius <= -50) {
			bowlKeyRelease = false;
		}

		pinsCollision();

	}

	protected void renderScene() {
		GL11.glRotatef(sceneRotationAngle, 0f, 1.0f, 0f);
		renderBall();
		renderBack();
		renderSide();
		renderGround();
		renderHolder();
		renderRailing();
		renderPins();
		renderTV();
		renderMan();

		if (reset) {
			reset = false;
		}
	}

	protected void setSceneCamera() {
		super.setSceneCamera();
		GLU.gluLookAt(viewerLocX, viewerLocY, viewerLocZ, // viewer location
				cameraViewPointX, cameraViewPointY, cameraViewPointZ, // view point loc.
				viewUpVectorX, viewUpVectorY, viewUpVectorZ); // view-up vector
	}

	/**
	 * initial lighting
	 */
	private void initLighting() {
		float globalAmbient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
		float diffuse0[] = { 0.3f, 0.3f, 0.3f, 1.0f };
		float specular0[] = { 0.5f, 0.5f, 0.6f, 1.0f };
		float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
		float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f };
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(specular0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_NORMALIZE);
	}

	/**
	 * code to render the back wall
	 */
	private void renderBack() {
		GL11.glPushMatrix();
		{
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);

			wallColour.submit();
			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, sideWallTexture.getTextureID());

			GL11.glScalef(2f, 2f, 1f);

			// right-most
			GL11.glCallList(backWall);

			GL11.glTranslatef(-10f, 0f, 0f); // left
			GL11.glCallList(backWall);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	/**
	 * code to the render the side wall
	 */
	private void renderSide() {
		GL11.glPushMatrix();
		{
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);

			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, sideWallTexture.getTextureID());

			GL11.glScalef(1f, 2f, 2f);
			GL11.glTranslatef(0f, 0f, -5f);
			GL11.glCallList(sideWall);

			GL11.glTranslatef(0f, 0f, -10f);
			GL11.glCallList(sideWall);

			GL11.glTranslatef(0f, 0f, -10f);
			GL11.glCallList(sideWall);

			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	/**
	 * code to render the ground planes
	 */
	private void renderGround() {
		GL11.glPushMatrix();
		{
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			Colour wallcol = new Colour(197, 200, 227);
			wallcol.submit();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTextures.getTextureID());

			GL11.glTranslatef(-10f, 0f, 0f);
			GL11.glCallList(groundList);

			GL11.glTranslatef(10f, 0f, 0f);
			GL11.glCallList(groundList);

			GL11.glTranslatef(10f, 0f, 0f);
			GL11.glCallList(groundList);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	/**
	 * code to render main bowling ball
	 */
	private void renderBall() {
		GL11.glPushMatrix();
		{			
			this.ballY = ballRaiseValue;
			GL11.glTranslatef(ballX, ballY, ballZ);
			float shininess = 1.0f;
			float specular[] = { 0.2f, 0.46f, 0.66f, 1.0f };
			float diffuse[] = { 0.2f, 0.46f, 0.66f, 1.0f };
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));
			new Sphere().draw(ballRadius, 30, 30);
		}
		GL11.glPopMatrix();
	}

	/**
	 * code to render the bowling balls holder
	 */
	private void renderHolder() {
		GL11.glPushMatrix();
		{
			float specular[] = { 0.26f, 0.26f, 0.26f, 1.0f };
			float diffuse[] = { 0.26f, 0.26f, 0.26f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

			GL11.glTranslatef(5, 1.5f, -1);
			GL11.glCallList(holderList);

			{
				float specular2[] = { 0.7f, 0.46f, 0.66f, 1.0f };
				float diffuse2[] = { 0.7f, 0.46f, 0.66f, 1.0f };

				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular2));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse2));

				GL11.glTranslatef(0f, 1.75f, -2.4f);
				new Sphere().draw(0.5f, 50, 50);
			}
			{
				float specular3[] = { 0.2f, 0.1f, 0.66f, 1.0f };
				float diffuse3[] = { 0.2f, 0.1f, 0.66f, 1.0f };

				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular3));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse3));

				GL11.glTranslatef(0f, 0, 1.1f);
				new Sphere().draw(0.5f, 50, 50);
			}
			{
				float specular4[] = { 0.2f, 0.2f, 0.4f, 1.0f };
				float diffuse4[] = { 0.2f, 0.2f, 0.44f, 1.0f };

				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular4));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse4));

				GL11.glTranslatef(0f, 0.1f, 1.2f);
				new Sphere().draw(0.57f, 50, 50);
			}
			{
				float specular5[] = { 0.2f, 0.46f, 0.66f, 1.0f };
				float diffuse5[] = { 0.2f, 0.46f, 0.66f, 1.0f };

				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular5));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse5));
				GL11.glTranslatef(0f, -0.1f, 1.2f);
				new Sphere().draw(0.53f, 50, 50);
			}
			{
				float specular6[] = { 0.8f, 0.55f, 0.2f, 1.0f };
				float diffuse6[] = { 0.8f, 0.55f, 0.2f, 1.0f };

				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular6));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse6));
				GL11.glTranslatef(0f, 0.1f, 1.2f);
				new Sphere().draw(0.6f, 50, 50);
			}
		}
		GL11.glPopMatrix();
	}

	/**
	 * code to render railings
	 */
	private void renderRailing() {
		GL11.glPushMatrix();
		{
			float specular[] = { 0.2f, 0.2f, 0.2f, 1.0f };
			float diffuse[] = { 0.2f, 0.2f, 0.2f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

			GL11.glTranslatef(-5, 0.07f, -26);
			GL11.glCallList(railingList);

			GL11.glTranslatef(10f, 0f, 0f);
			GL11.glCallList(railingList);
		}
		GL11.glPopMatrix();
	}

	/**
	 * code to render TV model
	 */
	private void renderTV() {
		GL11.glPushMatrix();
		{
			{
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				if (isPinFallSceneOne && (ballZ <= rowPosition1 - 5)) {
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, tvStrikeTexture.getTextureID());
				} else {
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, tvTexture.getTextureID());
				}

				GL11.glTranslatef(0f, 11f, -49f);
				GL11.glScalef(1f, 1.4f, 1f);
				GL11.glCallList(teleList);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
		}
		GL11.glPopMatrix();
	}

	/**
	 * code to render man model
	 */
	private void renderMan() {
		GL11.glPushMatrix();
		{
			GL11.glScalef(0.5f, 0.5f, 0.5f);
			GL11.glTranslatef(4f, 10.7f, 5f); // right-most
			GL11.glRotatef(180, 0f, 1f, 0f);
			GL11.glCallList(manList);
		}
		GL11.glPopMatrix();
	}

	/**
	 * Make pins rotate
	 */
	private void pinsFallScene() {
		// if not rotated enough and pin has to fall (fall is true), then fall and raise
		// a bit
		{
			// 1
			{
				if (rotationOfRow1 <= 85f && isRowFall1) {
					bowlKeyRelease = false;
					bowlSpeed = defaultBowlSpeed / 1.5f;
					rotationOfRow1 += 6 * getAnimationScale();
					if (pinRaiseValue1 <= 0.75f) {
						pinRaiseValue1 += 0.09f * getAnimationScale();
					}
					if (pinSideMoveValue1 <= 1.3f) {
						pinSideMoveValue1 += 0.09f * getAnimationScale();
					}

				}

				if (rotationOfRow1 >= 20f) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow1 >= 85f) {
					isRowFall1 = false;
					bowlKeyRelease = true;
				}
			}
			// 2
			{
				if (rotationOfRow2 <= 85f && isRowFall2) {
					bowlKeyRelease = false;
					bowlSpeed = defaultBowlSpeed / 4f;
					rotationOfRow2 += 6f * getAnimationScale();
					if (pinRaiseValue2 <= 0.75f) {
						pinRaiseValue2 += 0.09f * getAnimationScale();
					}
					if (pinSideMoveValue2 <= 1.3f) {
						pinSideMoveValue2 += 0.09f * getAnimationScale();
					}
				}
				if (rotationOfRow2 >= 20f) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow2 >= 85f) {
					isRowFall2 = false;
					bowlKeyRelease = true;
				}
			}
			// 3
			{
				if (rotationOfRow3 <= 85f && isRowFall3) {
					bowlKeyRelease = false;
					// bowlSpeed = defaultBowlSpeed/8f;
					rotationOfRow3 += 6f * getAnimationScale();
					if (pinRaiseValue3 <= 0.75f) {
						// pinRaiseValue3 += 0.05f * getAnimationScale();
					}
					if (pinSideMoveValue3 <= 1.3f) {
//						pinSideMoveValue3 += 0.05f * getAnimationScale();
						pinSideMoveValue3 += 0.09f * getAnimationScale();
					}
				}
				if (rotationOfRow3 >= 20f) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow3 >= 85f) {
					isRowFall3 = false;
					bowlKeyRelease = true;
				}
			}
			// 4
			{
				if (rotationOfRow4 <= 85f && isRowFall4) {
					bowlKeyRelease = false;
					rotationOfRow4 += 6f * getAnimationScale();
					if (pinRaiseValue4 <= 0.75f) {
						// pinRaiseValue4 += 0.05f * getAnimationScale();
					}
					if (pinSideMoveValue4 <= 1.3f) {
//						pinSideMoveValue4 += 0.05f * getAnimationScale();
						pinSideMoveValue4 += 0.09f * getAnimationScale();
					}
					if (bowlSpeed == defaultBowlSpeed) {
						bowlSpeed -= (bowlSpeed / 1.4f) * getAnimationScale();
					}
					if (ballX < 5) {
						ballX += 0.02f * getAnimationScale();
					}
				}
				if (rotationOfRow4 >= 20f) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow4 >= 85f) {
					isRowFall4 = false;
				}
			}
		}
	}

	private void updateCamera() {
		if (ballZ <= rowPosition1 + 15 && viewerLocZ >= -25f) {
			cameraViewPointX = 20;
			cameraViewPointY = -3.5f;
			cameraViewPointZ = -40;
			if (viewerLocZ <= -15f && viewerLocZ >= -25f) {
				cameraViewPointZ -= 2 * getAnimationScale();
				cameraViewPointX -= 9f * getAnimationScale();
				cameraViewPointY += 4f * getAnimationScale();
				viewerLocY += 0.5f * getAnimationScale();
			}
			viewerLocZ -= 2f * getAnimationScale();
		}

		if (ballZ <= -50) {
			cameraViewPointZ = -50f;
			cameraViewPointX = 3.5f;
			cameraViewPointY = 4f;
			viewerLocZ = -23f;
			viewerLocX = -1f;
			viewerLocY = 4f;
		}
	}

	/**
	 * reset rotation of the pins
	 * 
	 * @param yRotate
	 */
	private void resetRotation1(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow1 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow1 = 0f;
			pinRaiseValue1 = 0f;
		}
	}

	/**
	 * reset rotation of the pins
	 * 
	 * @param yRotate
	 */
	private void resetRotation2(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow2 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow2 = 0f;
			pinRaiseValue2 = 0f;
		}
	}

	/**
	 * reset rotation of the pins
	 * 
	 * @param yRotate
	 */
	private void resetRotation3(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow3 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow3 = 0f;
			pinRaiseValue3 = 0f;
		}
	}

	/**
	 * reset rotation of the pins
	 * 
	 * @param yRotate
	 */
	private void resetRotation4(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow4 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow4 = 0f;
			pinRaiseValue4 = 0f;
		}
	}

	/**
	 * print fields, for debugging purposes.
	 */
	private void printFields() {
		count++;
		System.out.println("printing " + count);
		System.out.println("ballZ: " + ballZ);
		System.out.println("ballZ - radius: " + (ballZ - (ballRadius)));
		System.out.println("scene 1: " + isPinFallSceneOne);
		System.out.println("1 row: " + rowPosition1);
		// System.out.println("2 row: " + rowPosition2);
		// System.out.println("3 row: " + rowPosition3);
		// System.out.println("4 row: " + rowPosition4);
//		System.out.println("passedRow1: " + passedRow1);
//		System.out.println("passedRow2: " + passedRow2);
//		System.out.println("passedRow3: " + passedRow3);
//		System.out.println("passedRow4: " + passedRow4);
//		System.out.println("1RowFall: " + isRowFall1);
//		System.out.println("2RowFall: " + isRowFall2);
//		System.out.println("3RowFall: " + isRowFall3);
//		System.out.println("4RowFall: " + isRowFall4);
		System.out.println("rot1: " + rotationOfRow1);
		System.out.println("rot2: " + rotationOfRow2);
		System.out.println("rot3: " + rotationOfRow3);
		System.out.println("rot4: " + rotationOfRow4);

		System.out.println("pin11Z: " + (pin11Z + 0.5f));
	}

	/**
	 * position and render all the 10 pins
	 */
	private void renderPins() {
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0f, 0f, rowPosition1 + 12f);
			GL11.glScalef(0.5f, 0.7f, 0.4f);
			float shininess = 1.0f;
			float specular[] = { 0.9f, 0.9f, 0.9f, 1.0f };
			float diffuse[] = { 1f, 1f, 1f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

			{
				// 1, 1 - pin 1
				GL11.glPushMatrix();
				{
					resetRotation1(1f);
					GL11.glTranslatef(pin11X, pin11Y, pin11Z);
					if (isPin1Hit) {
						GL11.glTranslatef(pinSideMoveValue1 * 5, pinRaiseValue1, pinRaiseValue1 * -10);
						GL11.glRotatef(rotationOfRow1, -45, 0f, -10);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 2, 1 - pin 2
				GL11.glPushMatrix();
				{
					resetRotation2(xGap / 2);
					GL11.glTranslatef(pin21X, pin21Y, pin21Z);
					if (isPin2Hit) {
						GL11.glRotatef(rotationOfRow2, -10f, 0f, 10f);
						GL11.glTranslatef(0, pinRaiseValue2, pinRaiseValue2);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 2, 2 - pin 3
				GL11.glPushMatrix();
				{
					resetRotation2(-zGap);
					GL11.glTranslatef(pin22X, pin22Y, pin22Z);
					if (isPin3Hit) {
						GL11.glRotatef(rotationOfRow2, -10f, 0f, -10f);
						GL11.glTranslatef(0, pinRaiseValue2, pinRaiseValue2);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();
				// 3, 1 - pin 4
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					GL11.glTranslatef(pin31X, pin31Y, pin31Z);
					if (isPin4Hit) {
						GL11.glRotatef(rotationOfRow3, -30f, 0f, 10f);
						GL11.glTranslatef(pinSideMoveValue3, pinRaiseValue3, pinRaiseValue3);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 3, 2 - pin 5
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					GL11.glTranslatef(pin32X, pin32Y, pin32Z);
					if (isPin5Hit) {
						GL11.glRotatef(rotationOfRow3, -45f, 0f, 0f);
						GL11.glTranslatef(pinSideMoveValue3 * -2f, pinRaiseValue3, (pinRaiseValue3 * -10));
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 3, 3 - pin 6
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					GL11.glTranslatef(pin33X, pin33Y, pin33Z);
					if (isPin6Hit) {
						GL11.glRotatef(rotationOfRow3, -30f, 0f, -10f);
						GL11.glTranslatef(0, pinRaiseValue3, pinRaiseValue3);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 1 - pin 7
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef(pin41X, pin41Y, pin41Z);
					if (isPin7Hit) {
						GL11.glRotatef(rotationOfRow4, -45f, 0f, 5f);
						GL11.glTranslatef(0f, pinRaiseValue4, pinRaiseValue4);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 2
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef(pin42X, pin42Y, pin42Z);
					if (isPin8Hit) {
						GL11.glRotatef(rotationOfRow4, -35f, 0f, 25f);
						GL11.glTranslatef((pinSideMoveValue4 * -1), pinRaiseValue4, (pinRaiseValue4 * -2));
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 3
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef(pin43X, pin43Y, pin43Z);
					if (isPin9Hit) {
						GL11.glRotatef(rotationOfRow4, -30f, 0f, -30f);
						GL11.glTranslatef(0f, pinRaiseValue4, pinRaiseValue4);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 4
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef(pin44X, pin44Y, pin44Z);
					if (isPin10Hit) {
						GL11.glRotatef(rotationOfRow4, -25f, 0f, -5f);
						GL11.glTranslatef(0f, pinRaiseValue4, pinRaiseValue4);
					}
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}

	/**
	 * performs pins falling animation once they have been hit.
	 */
	private void pinsFallPhysics() {
		if (isCollided(pin11X, pin11Y, pin11Z)) {
			pin11Z = ballZ - 0.5f;
			isPin1Hit = true;
		}
		if (isCollided(pin21X, pin21Y, pin21Z)) {
			pin21Z = ballZ - 0.5f;
			isPin2Hit = true;
		}
		if (isCollided(pin22X, pin22Y, pin22Z)) {
			pin22Z = ballZ - 0.5f;
			isPin3Hit = true;
		}
		if (isCollided(pin31X, pin31Y, pin31Z)) {
			pin31Z = ballZ - 0.5f;
			isPin4Hit = true;
		}
		if (isCollided(pin32X, pin32Y, pin32Z)) {
			pin32Z = ballZ - 0.5f;
			isPin5Hit = true;
		}
		if (isCollided(pin33X, pin33Y, pin33Z)) {
			pin33Z = ballZ - 0.5f;
			isPin6Hit = true;
		}
		if (isCollided(pin41X, pin41Y, pin41Z)) {
			pin41Z = ballZ - 0.5f;
			isPin7Hit = true;
		}
		if (isCollided(pin42X, pin42Y, pin42Z)) {
			pin42Z = ballZ - 0.5f;
			isPin8Hit = true;
		}
		if (isCollided(pin43X, pin43Y, pin43Z)) {
			pin43Z = ballZ - 0.5f;
			isPin9Hit = true;
		}
		if (isCollided(pin44X, pin44Y, pin44Z)) {
			pin44Z = ballZ - 0.5f;
			isPin10Hit = true;
		}

	}

	/**
	 * check if pin has collided with the ball
	 * 
	 * @param pinMidX1    x axis of pin
	 * @param pinBottomy1 y axis of pin
	 * @param pinMidz1    z axis of pin
	 * @return
	 */
	private boolean isCollided(float pinMidX1, float pinBottomy1, float pinMidz1) {
		float ballOffset = ballRadius;
		float pinOffset = pinRadius;

		float ballBackSide = ballZ - ballOffset;
		float ballFrontSide = ballZ + ballOffset;

		float ballRightSide = ballX + ballOffset;
		float ballLeftSide = ballX - ballOffset;

		float pinBackSide = pinMidz1 - pinOffset;
		float pinFrontSide = pinMidz1 + pinOffset;

		float pinRightSide = pinMidX1 + pinOffset;
		float pinLeftSide = pinMidX1 - pinOffset;

		if (ballBackSide <= pinFrontSide && ballFrontSide >= pinBackSide) {
			// | ( ) |
			if (ballRightSide >= pinLeftSide && ballLeftSide <= pinRightSide) {
				return true;
			}
			// | ) |
			if (ballRightSide <= pinRightSide && ballRightSide >= pinLeftSide) {
				return true;
			}
			// | ( |
			if (ballLeftSide >= pinLeftSide && ballLeftSide <= pinRightSide) {
				return true;
			}
			// ( | | )
			if (ballLeftSide <= pinLeftSide && ballRightSide >= pinRightSide) {
				return true;
			}
		}
		return false;
	}

	/**
	 * calculates distance between two objects.
	 * 
	 * @param x1 x axis of object 1
	 * @param z1 z axis of object 1
	 * @param x2 x axis of object 2
	 * @param z2 z axis of objext 3
	 * @return
	 */
	private float distanceBetween(float x1, float z1, float x2, float z2) {
		return (float) Math.sqrt((Math.pow(x2, 2) - Math.pow(x1, 2)) + (Math.pow(z2, 2) - Math.pow(z1, 2)));
	}

	/**
	 * Makes certain pins move away from certain other pins to avoid going inside
	 * other objects.
	 */
	private void pinsCollision() {
		if (distanceBetween(pin11X, pin11Z, pin21X, pin21Z) <= pinRadius * 2 && !pin2modded) {
			pin21Z -= pinRadius;
			pin2modded = true;
		}

		if (distanceBetween(pin11X, pin11Z, pin22X, pin22Z) <= pinRadius * 2 && !pin3modded) {
			pin22Z -= pinRadius;
			pin3modded = true;
		}

		if (distanceBetween(pin11X, pin11Z, pin32X, pin32Z) <= pinRadius * 2 && !pin5modded) {
			pin32Z -= pinRadius;
			pin5modded = true;
		}

		if (distanceBetween(pin11X, pin11Z, pin42X, pin42Z) <= pinRadius * 2 && !pin8modded) {
			pin42Z -= pinRadius;
			pin8modded = true;
		}

		if (distanceBetween(pin11X, pin11Z, pin43X, pin43Z) <= pinRadius * 2 && !pin9modded) {
			pin43Z -= pinRadius;
			pin9modded = true;
		}

		if (distanceBetween(pin32X, pin32Z, pin42X, pin42Z) <= pinRadius * 2 && !pin8modded) {
			pin42Z -= pinRadius;
			pin8modded = true;
		}

		if (distanceBetween(pin32X, pin32Z, pin43X, pin43Z) <= pinRadius * 2 && !pin9modded) {
			pin43Z -= pinRadius;
			pin9modded = true;
		}

		if (distanceBetween(pin31X, pin31Z, pin41X, pin41Z) <= pinRadius * 2 && !pin7modded) {
			pin21Z -= pinRadius;
			pin7modded = true;
		}

		if (distanceBetween(pin33X, pin33Z, pin44X, pin44Z) <= pinRadius * 2 && !pin10modded) {
			pin44Z -= pinRadius;
			pin10modded = true;
		}

	}

}