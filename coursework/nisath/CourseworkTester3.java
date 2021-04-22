/* CS2150Coursework.java
 * TODO: put your university username and full name here
 *
 *
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(2, 2, 1) T(0, 0, 0)] Back wall
 *  |
 *  +-- [T(-10, 0, 0)] Back wall
 *  |
 *  +-- [S(1, 2, 2) T(0, 0, -5)] Side wall
 *  |
 *  +-- [T(0, 0, -10)] Side wall
 *  |
 *  +-- [T(0, 0, -10)] Side wall
 *  |
 *  +-- [T(-10, 0, 0)] Ground plane left most
 *  |
 *  +-- [T(10, 0, 0)] Ground plane middle
 *  |
 *  +-- [T(10, 0, 0)] Ground plane right  most
 *  |
 *  +-- [T(ballX, ballRaiseValue, ballPosition)] Ball
 *  |
 *  +-- [T(5, 1.5f, -1)] Holder
 *  |	|
 *  |	+--[T(0, 1.75f, -2.4f)] Ball
 *  |	|
 *  |	+--[T(0f, 0, 1.1f)] Ball
 *  |	|
 *  |	+--[T(0f, 0.1f, 1.2f)] Ball
 *  |	|
 *  |	+--[T(0f, -0.1f, 1.2f)] Ball
 *  |	|
 *  |	+--[T(0f, 0.1f, 1.2f)] Ball
 *  |
 *  +-- [T(-5, 0.07f, -26)] Railing
 *  |
 *  +-- [T(10, 0.f, 0)] Railing left
 *  |
 *  +-- [T(10, 0.f, 0)] Railing right
 *  |
 *  +-- [T(0, 0, rowPosition1) S(0.5f, 0.7f, 0.4f)]
 *  |	|
 *  |	+--	[T(pinSideMoveValue1, pinRaiseValue1, pinRaiseValue1 * -4)] Pin1
 *  |	|
 *  |	+-- [T(-(xGap / 2), pinRaiseValue2, pinRaiseValue2 + zGap)] Pin2
 *  |	|
 *  |	+-- [T((xGap / 2), pinRaiseValue2, pinRaiseValue2+zGap)] Pin3
 *  |	|
 *  |	+-- [T(pinSideMoveValue3-xGap, pinRaiseValue3, pinRaiseValue3 + (zGap*2))] Pin4
 *  |	|
 *  |	+-- [T(pinSideMoveValue3 * -2, pinRaiseValue3, (pinRaiseValue3 * -3) + (zGap*2))] Pin5
 *  |	|
 *  |	+-- [T(xGap, pinRaiseValue3, pinRaiseValue3 + (zGap*2))] Pin6
 *  |	|
 *  |	+-- [T(-(xGap + (xGap / 2)), pinRaiseValue4, pinRaiseValue4 + (zGap * 3))] Pin7
 *  |	|
 *  |	+-- [T((pinSideMoveValue4 * -1) - (xGap / 2), pinRaiseValue4, (pinRaiseValue4 * -2) + zGap * 3)] Pin8
 *  |	|
 *  |	+-- [T(xGap / 2, pinRaiseValue4, pinRaiseValue4 + (zGap * 3))] Pin9
 *  |	|
 *  |	+-- [T(xGap + (xGap / 2), pinRaiseValue4, pinRaiseValue4 + (zGap * 3))] Pin10
 *  |
 *  +-- [T(0, 11, -49) S(1, 1.4f, 0)] TV
 *
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
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

/**
 * This is a bowling game. This game interface lets you see how it is like to
 * hit a strike. The pins will fall after taking impact. The television screen
 * will react and display the text "STRIKE!". The complicated object is the pin,
 * it contains 61 vertices. The animation is that the pins will react once the
 * ball touches them by moving away and falling down due to impact. It has
 * several user controls. The camera moves closer to the pins once the ball is
 * close to the pins. The camera also shows the TV after the ball stops moving
 * to show score.
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
 * <li>Press space bar to throw the ball or resume animation if paused.
 * <li>Press r to reset the scene.
 * <li>Press p to pause the animation.
 * <li>Press s to rotate or (spin) the scene.
 * <li>Press shift+s to rotate the scene other way.
 * <li>Press left arrow key to move the ball to its left corner.
 * <li>Press right arrow key to move the ball to its right corner.
 * <li>Press up arrow key to move the ball to the middle.
 * </ul>
 *
 */
public class CourseworkTester3 extends GraphicsLab {

	private final int backWall = 1;
	private final int groundList = 2;
	private final int sideWall = 3;
	// private final int pinList = 4;
	private final int holderList = 5;
	private final int railingList = 6;
	private final int teleList = 7;
	private final int manList = 8;
	private int count = 0;

	float shininess = 1.0f;
	private float sceneRotateSpeed = 1f;
	private float ballRadius = 0.75f;
	private float pinRadius = 1f;
	private float ballX = 0f;
	private float ballY = 0f;
	private float ballZ = 0f;
	private final float defaultBowlSpeed = 0.4f;
	private float bowlSpeed = defaultBowlSpeed;
	private float zGap = -2.5f;
	private float xGap = 2.9f;
	// private float rowPosition1 = -35f;
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

	private float pin1x;
	private float pin1y;
	private float pin1z = rowPosition1;// + goBack;

	private float pin2x = -(xGap / 2);
	private float pin2y;
	private float pin2z = zGap + rowPosition1;

	private float pin3x = xGap / 2;
	private float pin3y;
	private float pin3z = zGap + rowPosition1;

	private float pin4x = -xGap;
	private float pin4y;
	private float pin4z = zGap * 2 + rowPosition1;

	private float pin5x;
	private float pin5y;
	private float pin5z = (zGap * 2) + rowPosition1;// + goBack;

	private float pin6x = xGap;
	private float pin6y;
	private float pin6z = zGap * 2 + rowPosition1;

	private float pin7x = -(xGap + (xGap / 2));
	private float pin7y;
	private float pin7z = zGap * 3 + rowPosition1;

	private float pin8x = -(xGap / 2);
	private float pin8y;
	private float pin8z = zGap * 3 + rowPosition1;

	private float pin9x = xGap / 2;
	private float pin9y;
	private float pin9z = zGap * 3 + rowPosition1;

	private float pin10x = xGap + (xGap / 2);
	private float pin10y;
	private float pin10z = zGap * 3 + rowPosition1;

	float ballLeftSidetest = ballX - ballRadius;
	float ballRightSidetest = ballX + ballRadius;

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
	private float leftLegZ = 0f;
	private float rightHandZ = 0f;

	private boolean bowlKeyRelease = false;
	private boolean passedRow1 = false;
	private boolean passedRow2 = false;
	private boolean passedRow3 = false;
	private boolean passedRow4 = false;
	private boolean isRowFall1 = false;
	private boolean isRowFall2 = false;
	private boolean isRowFall3 = false;
	private boolean isRowFall4 = false;
	private boolean isPinFallSceneOne = false;
	private boolean reset = false;
	private boolean pause = false;

	private boolean pin1modded = false;
	private boolean pin2modded = false;
	private boolean pin3modded = false;
	private boolean pin4modded = false;
	private boolean pin5modded = false;
	private boolean pin6modded = false;
	private boolean pin7modded = false;
	private boolean pin8modded = false;
	private boolean pin9modded = false;
	private boolean pin10modded = false;

	private boolean isPin1Hit;
	private boolean isPin2Hit;
	private boolean isPin3Hit;
	private boolean isPin4Hit;
	private boolean isPin5Hit;
	private boolean isPin6Hit;
	private boolean isPin7Hit;
	private boolean isPin8Hit;
	private boolean isPin9Hit;
	private boolean isPin10Hit;

	private float[] pin1Arr = new float[3];
	private float[] pin2Arr = new float[3];
	private float[] pin3Arr = new float[3];
	private float[] pin4Arr = new float[3];
	private float[] pin5Arr = new float[3];
	private float[] pin6Arr = new float[3];
	private float[] pin7Arr = new float[3];
	private float[] pin8Arr = new float[3];
	private float[] pin9Arr = new float[3];
	private float[] pin10Arr = new float[3];

	private Texture groundTextures, sideWallTexture, tvTexture, tvStrikeTexture;
	private Colour ballColour, wallColour;
	private Meshes meshes;
	private Pin pin1, pin2, pin3, pin4, pin5, pin6, pin7, pin8, pin9, pin10;

	public static void main(String args[]) {
		new CourseworkTester3().run(WINDOWED, "Coursework Tester 3", 1f);
	}

	protected void initScene() throws Exception {
		groundTextures = loadTexture("coursework/nisath/textures/pexels-fwstudio-129731.jpg");
		sideWallTexture = loadTexture("coursework/nisath/textures/whitewall.bmp");
		tvTexture = loadTexture("coursework/nisath/textures/tv screen first.bmp");
		tvStrikeTexture = loadTexture("coursework/nisath/textures/tv screen second.bmp");

		wallColour = new Colour(131, 133, 163);
		ballColour = new Colour(6, 63, 156);
		meshes = new Meshes();

		initLighting();
		Pin.createNewPinSet();
		pin1 = new Pin(0, 0, rowPosition1);
		pin2 = new Pin(-(xGap / 2), 0, zGap + rowPosition1);
		pin3 = new Pin((xGap / 2f), 0, zGap + rowPosition1);
		pin4 = new Pin(-xGap, 0, zGap * 2 + rowPosition1);
		pin5 = new Pin(0, 0, (zGap * 2) + rowPosition1);
		pin6 = new Pin(xGap, 0, zGap * 2 + rowPosition1);
		pin7 = new Pin(-(xGap + (xGap / 2)), 0, zGap * 3 + rowPosition1);
		pin8 = new Pin(-(xGap / 2), 0, zGap * 3 + rowPosition1);
		pin9 = new Pin(xGap / 2, 0, zGap * 3 + rowPosition1);
		pin10 = new Pin(xGap + (xGap / 2), 0, zGap * 3 + rowPosition1);

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
		GL11.glEndList();/*
							 * GL11.glNewList(pinList, GL11.GL_COMPILE); { meshes.drawUnitPin(); }
							 * GL11.glEndList();
							 */
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
		/*
		 * GL11.glNewList(manList, GL11.GL_COMPILE); { meshes.drawUnitMan(); }
		 * GL11.glEndList();
		 */
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
			passedRow1 = false;
			passedRow2 = false;
			passedRow3 = false;
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
			leftLegZ = 0f;
			rightHandZ = 0;

			pin1.translate(0, 0, rowPosition1);
			pin2.translate(0, 0, zGap + rowPosition1);
			pin3.translate(0, 0, zGap + rowPosition1);
			pin4.translate(0, 0, (zGap * 2) + rowPosition1);
			pin5.translate(0, 0, (zGap * 2) + rowPosition1);
			pin6.translate(0, 0, (zGap * 2) + rowPosition1);
			pin7.translate(0, 0, (zGap * 3) + rowPosition1);
			pin8.translate(0, 0, (zGap * 3) + rowPosition1);
			pin9.translate(0, 0, (zGap * 3) + rowPosition1);
			pin10.translate(0, 0, (zGap * 3) + rowPosition1);

			pin1Arr[2] = rowPosition1;
			pin2Arr[2] = zGap + rowPosition1;
			pin3Arr[2] = zGap + rowPosition1;
			pin4Arr[2] = (zGap * 2) + rowPosition1;
			pin5Arr[2] = (zGap * 2) + rowPosition1;
			pin6Arr[2] = (zGap * 2) + rowPosition1;
			pin7Arr[2] = (zGap * 3) + rowPosition1;
			pin8Arr[2] = (zGap * 3) + rowPosition1;
			pin9Arr[2] = (zGap * 3) + rowPosition1;
			pin10Arr[2] = (zGap * 3) + rowPosition1;

			
			
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
			ballX -= 0.05f;
			// isPinFallSceneOne = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			ballX = 0;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			// ballX = 4;
			ballX += 0.05f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_P)) {
			bowlSpeed = 0f;
			pause = true;
			printFields();
		}
	}

	protected void updateScene() {
		// start rolling ball for as long as bowlKeyRelease is true, which is made true
		// once H is pressed
		if (bowlKeyRelease && ballZ > -50) {
			ballZ -= bowlSpeed * getAnimationScale();
		}
		// pinRadius = 0.5f + 0.5f - 0.2f;
		pinRadius = pin1.getRadius();

		if (ballZ + (ballRadius * 4) <= rowPosition1) {
			passedRow1 = true;
			isRowFall1 = true;
		}
		if (ballZ + (ballRadius * 4) <= rowPosition2) {
			passedRow2 = true;
			isRowFall2 = true;
		}
		if (ballZ + (ballRadius * 4) <= rowPosition3) {
			passedRow3 = true;
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
			//pinsFallScene();
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
	}

	protected void renderScene() {
		GL11.glRotatef(sceneRotationAngle, 0, 1.0f, -10);
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

	protected void cleanupScene() {
	}

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

	private void renderBack() {
		GL11.glPushMatrix();
		{
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);

			wallColour.submit();
			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, sideWallTexture.getTextureID());

			GL11.glScalef(2, 2, 1);

			GL11.glTranslatef(0, 0, 0); // right-most
			GL11.glCallList(backWall);

			GL11.glTranslatef(-10, 0, 0); // left
			GL11.glCallList(backWall);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void renderSide() {
		GL11.glPushMatrix();
		{
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);

			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, sideWallTexture.getTextureID());

			GL11.glScalef(1, 2, 2);
			GL11.glTranslatef(0, 0, -5); // upper
			GL11.glCallList(sideWall);

			GL11.glTranslatef(0, 0, -10); // upper
			GL11.glCallList(sideWall);

			GL11.glTranslatef(0, 0, -10); // upper
			GL11.glCallList(sideWall);

			GL11.glDisable(GL11.GL_TEXTURE_2D);

			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void renderGround() {
		GL11.glPushMatrix();
		{
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			Colour wallcol = new Colour(197, 200, 227);
			wallcol.submit();

			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTextures.getTextureID());

			GL11.glTranslatef(-10, 0, 0);
			GL11.glCallList(groundList);

			GL11.glTranslatef(10, 0, 0);
			GL11.glCallList(groundList);

			GL11.glTranslatef(10, 0, 0);
			GL11.glCallList(groundList);

			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void renderBall() {
		GL11.glPushMatrix();
		{
			this.ballY = ballRaiseValue;
			GL11.glTranslatef(ballX, ballY, ballZ);

			ballColour.submit();

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

				GL11.glTranslatef(0, 1.75f, -2.4f);
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

			GL11.glTranslatef(10, 0.f, 0);
			GL11.glCallList(railingList);
		}
		GL11.glPopMatrix();
	}

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

				GL11.glTranslatef(0, 11, -49);
				GL11.glScalef(1, 1.4f, 1);
				GL11.glCallList(teleList);
				GL11.glDisable(GL11.GL_TEXTURE_2D);
			}
		}
		GL11.glPopMatrix();
	}

	private void renderMan() {
		GL11.glPushMatrix();
		{

			GL11.glScalef(0.5f, 0.5f, 0.5f);

			GL11.glTranslatef(4, 10.7f, 5); // right-most
			GL11.glRotatef(180, 0, 1, 0);
			// GL11.glCallList(manList);
			meshes.drawUnitMan();
		}
		GL11.glPopMatrix();
	}

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
						pinRaiseValue1 += 0.05f * getAnimationScale();
					}
					if (pinSideMoveValue1 <= 1.3f) {
						pinSideMoveValue1 += 0.05f * getAnimationScale();
					}

				}

				if (rotationOfRow1 >= 20) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow1 >= 85) {
					isRowFall1 = false;
					bowlKeyRelease = true;
				}
			}
			// 2
			{
				if (rotationOfRow2 <= 85f && isRowFall2) {
					bowlKeyRelease = false;
					// bowlSpeed = defaultBowlSpeed/4f;
					rotationOfRow2 += 6 * getAnimationScale();
					if (pinRaiseValue2 <= 0.75f) {
						pinRaiseValue2 += 0.05f * getAnimationScale();
					}
					if (pinSideMoveValue2 <= 1.3f) {
						pinSideMoveValue2 += 0.05f * getAnimationScale();
					}
				}
				if (rotationOfRow2 >= 20) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow2 >= 85) {
					isRowFall2 = false;
					bowlKeyRelease = true;
				}
			}
			// 3
			{
				if (rotationOfRow3 <= 85f && isRowFall3) {
					bowlKeyRelease = false;
					// bowlSpeed = defaultBowlSpeed/8f;
					rotationOfRow3 += 6 * getAnimationScale();
					if (pinRaiseValue3 <= 0.75f) {
						pinRaiseValue3 += 0.05f * getAnimationScale();
					}
					if (pinSideMoveValue3 <= 1.3f) {
						pinSideMoveValue3 += 0.05f * getAnimationScale();
					}
				}
				if (rotationOfRow3 >= 20) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow3 >= 85) {
					isRowFall3 = false;
					bowlKeyRelease = true;
				}
			}
			// 4
			{
				if (rotationOfRow4 <= 85f && isRowFall4) {
					bowlKeyRelease = false;
					rotationOfRow4 += 6 * getAnimationScale();
					if (pinRaiseValue4 <= 0.75f) {
						pinRaiseValue4 += 0.05f * getAnimationScale();
					}
					if (pinSideMoveValue4 <= 1.3f) {
						pinSideMoveValue4 += 0.05f * getAnimationScale();
					}
					if (bowlSpeed == defaultBowlSpeed) {
						bowlSpeed -= (bowlSpeed / 1.4) * getAnimationScale();
					}
					if (ballX < 5) {
						ballX += 0.02f * getAnimationScale();
					}
				}
				if (rotationOfRow4 >= 20) {
					bowlKeyRelease = true;
				}
				// stop falling if rotated enough (85 degrees)
				if (rotationOfRow4 >= 85) {
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
		System.out.println("rot1: " + rotationOfRow1);
		System.out.println("rot2: " + rotationOfRow2);
		System.out.println("rot3: " + rotationOfRow3);
		System.out.println("rot4: " + rotationOfRow4);
	}

	/**
	 * position and render all the 10 pins
	 */
	private void renderPins() {
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0, 0, rowPosition1 + 12+10);

			float shininess = 1.0f;
			float specular[] = { 0.9f, 0.9f, 0.9f, 1.0f };
			float diffuse[] = { 1f, 1f, 1f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

			{
				GL11.glScalef(0.5f, 0.7f, 0.4f);
				// 1, 1 - pin 1
				GL11.glPushMatrix();
				{
					resetRotation1(1f);
					pin1Arr = pin1.translate(pin1x, pin1y, pin1z);
					GL11.glTranslatef(pin1Arr[0], pin1Arr[1], pin1Arr[2]);
					if (isPin1Hit) {
						GL11.glTranslatef(pinSideMoveValue1 * 5, pinRaiseValue1, pinRaiseValue1 * -10);
						GL11.glRotatef(rotationOfRow1, -45, 0f, -10);
					}
					pin1.draw();
				}
				GL11.glPopMatrix();

				// 2, 1 - pin 2
				GL11.glPushMatrix();
				{
					resetRotation2(xGap / 2);
					pin2Arr = pin2.translate(pin2x, pin2y, pin2z);
					GL11.glTranslatef(pin2Arr[0], pin2Arr[1], pin2Arr[2]);
					if (isPin2Hit) {
						GL11.glRotatef(rotationOfRow2, -10, 0f, 10);
						GL11.glTranslatef(0, pinRaiseValue2, pinRaiseValue2);
					}
					pin2.draw();
				}
				GL11.glPopMatrix();

				// 2, 2 - pin 3
				GL11.glPushMatrix();
				{
					resetRotation2(-zGap);
					pin3Arr = pin3.translate(pin3x, pin3y, pin3z);
					GL11.glTranslatef(pin3Arr[0], pin3Arr[1], pin3Arr[2]);
					if (isPin3Hit) {
						GL11.glRotatef(rotationOfRow2, -10, 0f, -10);
						GL11.glTranslatef(0, pinRaiseValue2, pinRaiseValue2);
					}
					pin3.draw();
				}
				GL11.glPopMatrix();
				// 3, 1 - pin 4
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					// GL11.glTranslatef(pin4x, pin4y, pin4z);
					pin4Arr = pin4.translate(pin4x, pin4y, pin4z);
					GL11.glTranslatef(pin4Arr[0], pin4Arr[1], pin4Arr[2]);
					if (isPin4Hit) {
						GL11.glRotatef(rotationOfRow3, -30, 0f, 10);
						GL11.glTranslatef(pinSideMoveValue3, pinRaiseValue3, pinRaiseValue3);
					}
					pin4.draw();
				}
				GL11.glPopMatrix();

				// 3, 2 - pin 5
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					pin5Arr = pin5.translate(pin5x, pin5y, pin5z);
					GL11.glTranslatef(pin5Arr[0], pin5Arr[1], pin5Arr[2]);
					if (isPin5Hit) {
						GL11.glRotatef(rotationOfRow3, -45, 0f, 0);
						GL11.glTranslatef(pinSideMoveValue3 * -2, pinRaiseValue3, (pinRaiseValue3 * -10));
					}
					pin5.draw();
				}
				GL11.glPopMatrix();

				// 3, 3 - pin 6
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					pin6Arr = pin6.translate(pin6x, pin6y, pin6z);
					GL11.glTranslatef(pin6Arr[0], pin6Arr[1], pin6Arr[2]);
					if (isPin6Hit) {
						GL11.glRotatef(rotationOfRow3, -30, 0f, -10);
						GL11.glTranslatef(0, pinRaiseValue3, pinRaiseValue3);
					}
					pin6.draw();
				}
				GL11.glPopMatrix();

				// 4, 1 - pin 7
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					pin7Arr = pin7.translate(pin7x, pin7y, pin7z);
					GL11.glTranslatef(pin7Arr[0], pin7Arr[1], pin7Arr[2]);
					if (isPin7Hit) {
						GL11.glRotatef(rotationOfRow4, -45, 0f, 5);
						GL11.glTranslatef(0, pinRaiseValue4, pinRaiseValue4);
					}
					pin7.draw();
				}
				GL11.glPopMatrix();

				// 4, 2
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					pin8Arr = pin8.translate(pin8x, pin8y, pin8z);
					GL11.glTranslatef(pin8Arr[0], pin8Arr[1], pin8Arr[2]);
					if (isPin8Hit) {
						GL11.glRotatef(rotationOfRow4, -35, 0f, 25);
						GL11.glTranslatef((pinSideMoveValue4 * -1), pinRaiseValue4, (pinRaiseValue4 * -2));
					}
					pin8.draw();
				}
				GL11.glPopMatrix();

				// 4, 3
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					pin9Arr = pin9.translate(pin9x, pin9y, pin9z);
					GL11.glTranslatef(pin9Arr[0], pin9Arr[1], pin9Arr[2]);
					if (isPin9Hit) {
						GL11.glRotatef(rotationOfRow4, -30, 0f, -30);
						GL11.glTranslatef(0, pinRaiseValue4, pinRaiseValue4);
					}
					pin9.draw();
				}
				GL11.glPopMatrix();

				// 4, 4
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					pin10Arr = pin10.translate(pin10x, pin10y, pin10z);
					GL11.glTranslatef(pin10Arr[0], pin10Arr[1], pin10Arr[2]);
					if (isPin10Hit) {
						GL11.glRotatef(rotationOfRow4, -25, 0f, -5);
						GL11.glTranslatef(0, pinRaiseValue4, pinRaiseValue4);
					}
					pin10.draw();
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
		if (isCollidedWithBall(pin1x, pin1y, pin1z) ) {
			pin1z = ballZ - 0.5f;
			isPin1Hit = true;
		}
		if (isCollidedWithBall(pin2x, pin2y, pin2z) ) {
			System.out.println("pin 2 hit");
			pin2z = ballZ - 0.5f;
			isPin2Hit = true;
		}
		if (isCollidedWithBall(pin3x, pin3y, pin3z) ) {
			System.out.println("pin 3 hit");
			pin3z = ballZ - 0.5f;
			isPin3Hit = true;
		}
		if (isCollidedWithBall(pin4x, pin4y, pin4z) ) {
			System.out.println("pin 4 hit");
			pin4z = ballZ - 0.5f;
			isPin4Hit = true;
		}
		if (isCollidedWithBall(pin5x, pin5y, pin5z) ) {
			System.out.println("pin 5 hit, moving");
			pin5z = ballZ - 0.5f;
			isPin5Hit = true;
		}
		if (isCollidedWithBall(pin6x, pin6y, pin6z) ) {
			System.out.println("pin 6 hit");
			pin6z = ballZ - 0.5f;
			isPin6Hit = true;
		}
		if (isCollidedWithBall(pin7x, pin7y, pin7z) ) {
			System.out.println("pin 7 hit");
			pin7z = ballZ - 0.5f;
			isPin7Hit = true;
		}
		if (isCollidedWithBall(pin8x, pin8y, pin8z) ) {
			System.out.println("pin 8 hit");
			pin8z = ballZ - 0.5f;
			isPin8Hit = true;
		}
		if (isCollidedWithBall(pin9x, pin9y, pin9z) ) {
			System.out.println("pin 9 hit");
			pin9z = ballZ - 0.5f;
			isPin9Hit = true;
		}
		if (isCollidedWithBall(pin10x, pin10y, pin10z) && !pin10.checkIfCollidingWithAnyOtherPin(0, 0, ballZ)) {
			System.out.println("pin 10 hit");
			pin10z = ballZ - 0.5f;
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
	private boolean isCollidedWithBall(float pinMidX1, float pinBottomy1, float pinMidz1) {
		float ballOffset = ballRadius;
		float pinOffset = pinRadius;

		float ballFrontSide = ballZ - ballOffset;
		float ballBackSide = ballZ + ballOffset;

		float ballRightSide = ballX + ballOffset;
		float ballLeftSide = ballX - ballOffset;

		float pinBackSide = pinMidz1 - pinOffset;
		float pinFrontSide = pinMidz1 + pinOffset;

		float pinRightSide = pinMidX1 + pinOffset;
		float pinLeftSide = pinMidX1 - pinOffset;

		// z axis
		if (ballFrontSide <= pinFrontSide && ballBackSide >= pinBackSide) {
			// x axis
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
	/*
	 * private void pinsCollision() { if (distanceBetween(pin1x, pin1z, pin2x,
	 * pin2z) <= pinRadius * 2 && !pin2modded) { pin2z -= pinRadius; pin2modded =
	 * true; }
	 * 
	 * if (distanceBetween(pin1x, pin1z, pin3x, pin3z) <= pinRadius * 2 &&
	 * !pin3modded) { pin3z -= pinRadius; pin3modded = true; }
	 * 
	 * if (distanceBetween(pin1x, pin1z, pin5x, pin5z) <= pinRadius * 2 &&
	 * !pin5modded) { pin5z -= pinRadius; pin5modded = true; }
	 * 
	 * if (distanceBetween(pin1x, pin1z, pin8x, pin8z) <= pinRadius * 2 &&
	 * !pin8modded) { pin8z -= pinRadius; pin8modded = true; }
	 * 
	 * if (distanceBetween(pin1x, pin1z, pin9x, pin9z) <= pinRadius * 2 &&
	 * !pin9modded) { pin9z -= pinRadius; pin9modded = true; }
	 * 
	 * if (distanceBetween(pin5x, pin5z, pin8x, pin8z) <= pinRadius * 2 &&
	 * !pin8modded) { pin8z -= pinRadius; pin8modded = true; }
	 * 
	 * if (distanceBetween(pin5x, pin5z, pin9x, pin9z) <= pinRadius * 2 &&
	 * !pin9modded) { pin9z -= pinRadius; pin9modded = true; }
	 * 
	 * if (distanceBetween(pin4x, pin4z, pin7x, pin7z) <= pinRadius * 2 &&
	 * !pin7modded) { pin2z -= pinRadius; pin7modded = true; }
	 * 
	 * if (distanceBetween(pin6x, pin6z, pin10x, pin10z) <= pinRadius * 2 &&
	 * !pin10modded) { pin10z -= pinRadius; pin10modded = true; }
	 * 
	 * }
	 */
}