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
public class CourseworkTester1 extends GraphicsLab {

	private final int backWall = 1;
	private final int groundList = 2;
	private final int sideWall = 3;
	private final int pinList = 4;
	private final int holderList = 5;
	private final int railingList = 6;
	private final int teleList = 7;
	private final int manList = 8;
	private int count = 0;

	private float sceneRotateSpeed = 1f;
	private float ballDiameter = 0.75f;
	private float ballX = 0f;
	private final float defaultBowlSpeed = 0.4f;
	private float bowlSpeed = defaultBowlSpeed;
	private float zGap = -2.5f;
	private float xGap = 2.9f;
	private float rowPosition1 = -35f;
	private float rowPosition2 = rowPosition1 + zGap;
	private float rowPosition3 = rowPosition2 + zGap;
	private float rowPosition4 = rowPosition3 + zGap;
	private float ballPosition = 0f;
	private float sceneRotationAngle = 0f;
	private float pinRaiseValue1 = 0f;
	private float pinRaiseValue2 = 0f;
	private float pinRaiseValue3 = 0f;
	private float pinRaiseValue4 = 0f;
	private float pinSideMoveValue1 = 0f;
	private float pinSideMoveValue2 = 0f;
	private float pinSideMoveValue3 = 0f;
	private float pinSideMoveValue4 = 0f;
	private float ballRaiseValue = ballDiameter;
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

	private Texture groundTextures, sideWallTexture, tvTexture, tvStrikeTexture;
	private Colour ballColour, wallColour;
	private Meshes meshes;

	public static void main(String args[]) {
		new CourseworkTester1().run(WINDOWED, "Coursework Tester", 1f);
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
			ballPosition = 0f;
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
			if (ballX == 0) {
				isPinFallSceneOne = true;
			} else {
				isPinFallSceneOne = false;
			}
			pause = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE) && ballPosition > rowPosition4) {
			bowlKeyRelease = true;
			bowlSpeed = defaultBowlSpeed;
			pause = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
			ballX = -4;
			isPinFallSceneOne = false;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
			ballX = 0;
			isPinFallSceneOne = true;
			leftLegZ += 1f;
			rightHandZ += 1f;
		}
		if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
			ballX = 4;
			isPinFallSceneOne = false;
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
		if (bowlKeyRelease) {
			ballPosition -= bowlSpeed * getAnimationScale();
		}
		if (ballPosition + -(ballDiameter) <= rowPosition1) {
			passedRow1 = true;
			isRowFall1 = true;
		}
		if (ballPosition - (ballDiameter * 2) <= rowPosition2) {
			passedRow2 = true;
			isRowFall2 = true;
		}
		if (ballPosition - (ballDiameter * 5) <= rowPosition3) {
			passedRow3 = true;
			isRowFall3 = true;
		}
		if (ballPosition - (ballDiameter * 6) <= rowPosition4) {
			passedRow4 = true;
			isRowFall4 = true;
		}
		if (ballX == 0 && !bowlKeyRelease) {
			isPinFallSceneOne = true;
		}
		if (isPinFallSceneOne && !pause) {
			pinsFallScene();
		}
		if (!pause) {
			updateCamera();
		}

		// h key is pressed && passed row 1 && gone past last row
		// change row 1 to last row
		if (bowlKeyRelease && passedRow4 && ballPosition < rowPosition4) {
			bowlKeyRelease = false;
		}
	}

	protected void renderScene() {
		GL11.glRotatef(sceneRotationAngle, 0, 1.0f, 0);

		renderBack();
		renderSide();
		renderGround();
		renderBall();
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
		// global ambient light level
		float globalAmbient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		// set the global ambient lighting
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

		// the first light for the scene
		float diffuse0[] = { 0.3f, 0.3f, 0.3f, 1.0f };

		// the first light for the scene
		float specular0[] = { 0.5f, 0.5f, 0.6f, 1.0f };
		// float specular0[] = { 0.88f, 0.34f, 0.36f, 1.0f };
		// ...with an ambient contribution...
		float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
		// ...and is positioned above the viewpoint
		float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f };

		// supply OpenGL with the properties for the first light
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(specular0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));

		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);

		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
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

			Colour wallcol = new Colour(131, 133, 163);
			// wallcol.submit();
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
			// GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			// GL11.glDisable(GL11.GL_LIGHTING);
			{
				GL11.glTranslatef(ballX, ballRaiseValue, ballPosition);
				ballColour.submit();

				// how shiny are the front faces of the mesh (specular exponent)
				float shininess = 1.0f;
				// specular reflection of the front faces of the mesh
				float specular[] = { 0.2f, 0.46f, 0.66f, 1.0f };
				// diffuse reflection of the front faces of the mesh
				float diffuse[] = { 0.2f, 0.46f, 0.66f, 1.0f };

				// set the material properties for the sun using OpenGL
				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

				new Sphere().draw(ballDiameter, 30, 30);
			}
			// GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void renderHolder() {
		GL11.glPushMatrix();
		{
			// GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			// GL11.glDisable(GL11.GL_LIGHTING);

			// how shiny are the front faces of the mesh (specular exponent)
			float shininess = 1.0f;
			// specular reflection of the front faces of the mesh
			float specular[] = { 0.26f, 0.26f, 0.26f, 1.0f };
			// diffuse reflection of the front faces of the mesh
			float diffuse[] = { 0.26f, 0.26f, 0.26f, 1.0f };

			// set the material properties for the sun using OpenGL
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

			GL11.glTranslatef(5, 1.5f, -1);
			GL11.glCallList(holderList);

			{
				// how shiny are the front faces of the mesh (specular exponent)
				float shininess2 = 1.0f;
				// specular reflection of the front faces of the mesh
				float specular2[] = { 0.7f, 0.46f, 0.66f, 1.0f };
				// diffuse reflection of the front faces of the mesh
				float diffuse2[] = { 0.7f, 0.46f, 0.66f, 1.0f };

				// set the material properties for the sun using OpenGL
				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess2);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular2));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse2));

				GL11.glTranslatef(0, 1.75f, -2.4f);
				new Sphere().draw(0.5f, 50, 50);
			}
			{
				// how shiny are the front faces of the mesh (specular exponent)
				float shininess3 = 1.0f;
				// specular reflection of the front faces of the mesh
				float specular3[] = { 0.2f, 0.1f, 0.66f, 1.0f };
				// diffuse reflection of the front faces of the mesh
				float diffuse3[] = { 0.2f, 0.1f, 0.66f, 1.0f };

				// set the material properties for the sun using OpenGL
				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess3);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular3));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse3));

				GL11.glTranslatef(0f, 0, 1.1f);
				new Sphere().draw(0.5f, 50, 50);
			}
			{
				// how shiny are the front faces of the mesh (specular exponent)
				float shininess4 = 1.0f;
				// specular reflection of the front faces of the mesh
				float specular4[] = { 0.2f, 0.2f, 0.4f, 1.0f };
				// diffuse reflection of the front faces of the mesh
				float diffuse4[] = { 0.2f, 0.2f, 0.44f, 1.0f };

				// set the material properties for the sun using OpenGL
				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess4);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular4));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse4));

				GL11.glTranslatef(0f, 0.1f, 1.2f);
				new Sphere().draw(0.57f, 50, 50);
			}
			{
				// how shiny are the front faces of the mesh (specular exponent)
				float shininess5 = 1.0f;
				// specular reflection of the front faces of the mesh
				float specular5[] = { 0.2f, 0.46f, 0.66f, 1.0f };
				// diffuse reflection of the front faces of the mesh
				float diffuse5[] = { 0.2f, 0.46f, 0.66f, 1.0f };

				// set the material properties for the sun using OpenGL
				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess5);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular5));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse5));
				GL11.glTranslatef(0f, -0.1f, 1.2f);
				new Sphere().draw(0.53f, 50, 50);
			}
			{
				// how shiny are the front faces of the mesh (specular exponent)
				float shininess6 = 1.0f;
				// specular reflection of the front faces of the mesh
				float specular6[] = { 0.8f, 0.55f, 0.2f, 1.0f };
				// diffuse reflection of the front faces of the mesh
				float diffuse6[] = { 0.8f, 0.55f, 0.2f, 1.0f };

				// set the material properties for the sun using OpenGL
				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess6);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular6));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse6));
				GL11.glTranslatef(0f, 0.1f, 1.2f);
				new Sphere().draw(0.6f, 50, 50);
			}
			GL11.glPopAttrib();

			// GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void renderRailing() {
		GL11.glPushMatrix();
		{
			// GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			// GL11.glDisable(GL11.GL_LIGHTING);

			// how shiny are the front faces of the mesh (specular exponent)
			float shininess = 1.0f;
			// specular reflection of the front faces of the mesh
			float specular[] = { 0.2f, 0.2f, 0.2f, 1.0f };
			// diffuse reflection of the front faces of the mesh
			float diffuse[] = { 0.2f, 0.2f, 0.2f, 1.0f };

			// set the material properties for the sun using OpenGL
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

			GL11.glTranslatef(-5, 0.07f, -26);
			GL11.glCallList(railingList);

			GL11.glTranslatef(10, 0.f, 0);
			GL11.glCallList(railingList);

			// GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void renderPins() {
		GL11.glPushMatrix();
		{
			// how shiny are the front faces of the mesh (specular exponent)
			float shininess = 1.0f;
			// specular reflection of the front faces of the mesh
			float specular[] = { 0.9f, 0.9f, 0.9f, 1.0f };
			// diffuse reflection of the front faces of the mesh
			float diffuse[] = { 1f, 1f, 1f, 1.0f };

			// set the material properties for the sun using OpenGL
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));

			{
				GL11.glTranslatef(0, 0, rowPosition1);
				GL11.glScalef(0.5f, 0.7f, 0.4f);
				// 1, 1 - pin 1
				GL11.glPushMatrix();
				{
					resetRotation1(1f);
					GL11.glTranslatef(pinSideMoveValue1 * 5, pinRaiseValue1, pinRaiseValue1 * -10);
					GL11.glRotatef(rotationOfRow1, -45, 0f, -10);
					GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTextures.getTextureID());
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 2, 1 - pin 2
				GL11.glPushMatrix();
				{
					resetRotation2(xGap / 2);
					GL11.glTranslatef(-(xGap / 2), pinRaiseValue2, pinRaiseValue2 + zGap);
					GL11.glRotatef(rotationOfRow2, -10, 0f, 10);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 2, 2 - pin 3
				GL11.glPushMatrix();
				{
					resetRotation2(-zGap);
					GL11.glTranslatef((xGap / 2), pinRaiseValue2, pinRaiseValue2 + zGap);
					GL11.glRotatef(rotationOfRow2, -10, 0f, -10);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 3, 1 - pin 4
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					GL11.glTranslatef(pinSideMoveValue3 - xGap, pinRaiseValue3, pinRaiseValue3 + (zGap * 2));
					GL11.glRotatef(rotationOfRow3, -30, 0f, 10);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 3, 2 - pin 5
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					GL11.glTranslatef(pinSideMoveValue3 * -2, pinRaiseValue3, (pinRaiseValue3 * -10) + (zGap * 2));
					GL11.glRotatef(rotationOfRow3, -45, 0f, 0);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 3, 3 - pin 6
				GL11.glPushMatrix();
				{
					resetRotation3(-(zGap * 2));
					GL11.glTranslatef(xGap, pinRaiseValue3, pinRaiseValue3 + (zGap * 2));
					GL11.glRotatef(rotationOfRow3, -30, 0f, -10);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 1 - pin 7
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef(-(xGap + (xGap / 2)), pinRaiseValue4, pinRaiseValue4 + (zGap * 3));
					GL11.glRotatef(rotationOfRow4, -45, 0f, 5);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 2
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef((pinSideMoveValue4 * -1) - (xGap / 2), pinRaiseValue4,
							(pinRaiseValue4 * -2) + zGap * 3);
					GL11.glRotatef(rotationOfRow4, -35, 0f, 25);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 3
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef(xGap / 2, pinRaiseValue4, pinRaiseValue4 + (zGap * 3));
					GL11.glRotatef(rotationOfRow4, -30, 0f, -30);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();

				// 4, 4
				GL11.glPushMatrix();
				{
					resetRotation4(-(zGap * 3));
					GL11.glTranslatef(xGap + (xGap / 2), pinRaiseValue4, pinRaiseValue4 + (zGap * 3));
					GL11.glRotatef(rotationOfRow4, -25, 0f, -5);
					GL11.glCallList(pinList);
				}
				GL11.glPopMatrix();
			}
		}
		GL11.glPopMatrix();
	}

	private void renderTV() {
		GL11.glPushMatrix();
		{
			{
				GL11.glEnable(GL11.GL_TEXTURE_2D);
				if (isPinFallSceneOne && (ballPosition <= rowPosition1 - 5)) {
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
		if (ballPosition <= rowPosition1 + 15 && viewerLocZ >= -25f) {
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

		if (ballPosition <= rowPosition4) {
			cameraViewPointZ = -50f;
			cameraViewPointX = 3.5f;
			cameraViewPointY = 4f;
			viewerLocZ = -23f;
			viewerLocX = -1f;
			viewerLocY = 4f;
		}
	}

	private void resetRotation1(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow1 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow1 = 0f;
			pinRaiseValue1 = 0f;
		}
	}

	private void resetRotation2(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow2 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow2 = 0f;
			pinRaiseValue2 = 0f;
		}
	}

	private void resetRotation3(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow3 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow3 = 0f;
			pinRaiseValue3 = 0f;
		}
	}

	private void resetRotation4(float yRotate) {
		if (reset) {
			GL11.glRotatef(rotationOfRow4 * -1, -45f + yRotate, 0.75f, 0f);
			rotationOfRow4 = 0f;
			pinRaiseValue4 = 0f;
		}
	}

	private void printFields() {
		count++;
		System.out.println("printing " + count);
		System.out.println("ballPosition: " + ballPosition);
		System.out.println("scene 1: " + isPinFallSceneOne);
		System.out.println("1 row: " + rowPosition1);
		System.out.println("2 row: " + rowPosition2);
		System.out.println("3 row: " + rowPosition3);
		System.out.println("4 row: " + rowPosition4);
		System.out.println("passedRow1: " + passedRow1);
		System.out.println("passedRow2: " + passedRow2);
		System.out.println("passedRow3: " + passedRow3);
		System.out.println("passedRow4: " + passedRow4);
		System.out.println("1RowFall: " + isRowFall1);
		System.out.println("2RowFall: " + isRowFall2);
		System.out.println("3RowFall: " + isRowFall3);
		System.out.println("4RowFall: " + isRowFall4);
		System.out.println("rot1: " + rotationOfRow1);
		System.out.println("rot2: " + rotationOfRow2);
		System.out.println("rot3: " + rotationOfRow3);
		System.out.println("rot4: " + rotationOfRow4);
	}

}