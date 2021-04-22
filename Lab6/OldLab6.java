/* Lab6.java
 * A simple scene consisting of a lit house and a textured ground plane
 * 27/08/2007
 * 
 * Scene Graph:
 *  Scene origin
 *  |
 *  +-- [S(20,1,20) T(0,-1,-10)] Ground plane
 *  |
 *  +-- [S(20,1,10) Rx(90) T(0,4,-20)] Sky plane
 *  |
 *  +-- [T(4,currentMoonY,-19)] Moon
 *  |
 *  +-- [T(2.0,-0.5,-10)] Bee hive
 *  |
 *  +-- [T(0,-1,-12)] Tree
 *  |   |
 *  |   +-- [Rx(-90)] Trunk
 *  |   |
 *  |   +-- [T(0,2,0)] Leafy head
 *  |
 *  +-- [Ry(35) S(2,2,2) T(-2.5,0,-10)] House
 *      |
 *      +-- [S(1,0.5,1) T(0,0.75,0)] Roof
 */
package Lab6;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Sphere;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.*;

/**
 * This sample demonstrates the use of user input and various types of animation
 * to add a dynamic aspect to a 3D scene
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
 * <li>Press L to lower the sun
 * <li>Press R to raise the sun
 * </ul>
 *
 * <p>
 * Adapted from Mark Bernard's LWJGL NeHe samples
 *
 * @author Anthony Jones and Dan Cornford
 */
public class OldLab6 extends GraphicsLab {
	private final int houseList = 1;
	private final int roofList = 2;
	private final int planeList = 3;
	private final int hiveList = 4;

	private float currentMoonY = 7.0f;
	private final float highestMoonY = currentMoonY;
	private final float lowestMoonY = -2.0f;
	private boolean risingMoon = true;
	private float currentSunY = -2.0f;
	private final float highestSunY = currentMoonY;
	private final float lowestSunY = -2.0f;
	private boolean risingSun = false;
	private Texture groundTextures;
	private Texture skyDayTextures;
	private Texture skyNightTextures;

	public static void main(String args[]) {
		new OldLab6().run(WINDOWED, "Lab 6 - Animation", 0.01f);
	}

	protected void initScene() throws Exception {
		groundTextures = loadTexture("Lab6/textures/grass.bmp");
		skyDayTextures = loadTexture("Lab6/textures/daySky.bmp");
		skyNightTextures = loadTexture("Lab6/textures/nightSky.bmp");

		float globalAmbient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

		float diffuse0[] = { 0.2f, 0.2f, 0.4f, 1.0f };
		float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
		float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f };

		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));

		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);
		GL11.glEnable(GL11.GL_LIGHTING);
		GL11.glEnable(GL11.GL_NORMALIZE);

		GL11.glNewList(houseList, GL11.GL_COMPILE);
		{
			drawUnitCube();
		}
		GL11.glEndList();
		GL11.glNewList(roofList, GL11.GL_COMPILE);
		{
			drawUnitRoof();
		}
		GL11.glEndList();
		GL11.glNewList(planeList, GL11.GL_COMPILE);
		{
			drawUnitPlane();
		}
		GL11.glEndList();
		GL11.glNewList(hiveList, GL11.GL_COMPILE);
		{
			drawUnitPyramid();
		}
		GL11.glEndList();
	}

	protected void checkSceneInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_R) && !risingSun) {
			risingMoon = true;
		} 
		else if (Keyboard.isKeyDown(Keyboard.KEY_L)) {
			risingMoon = false;
		} 
		else if (Keyboard.isKeyDown(Keyboard.KEY_S) && !risingMoon) {
			risingSun = true;
		} 
		else if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
			risingSun = false;
		}
		else if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			resetAnimations();
		} 
	}

	protected void updateScene() {
		if (risingMoon && currentMoonY < highestMoonY) {
			currentMoonY += 1.0f * getAnimationScale();
			turnOnMoonLight();
		}
		else if (!risingMoon && currentMoonY > lowestMoonY) {
			currentMoonY -= 1.0f * getAnimationScale();
			turnOffMoonLight();
		}
		else if (risingSun && currentSunY < highestSunY) {
			currentSunY += 1.0f * getAnimationScale();
			turnOnSunLight();
		}
		else if (!risingSun && currentSunY > lowestSunY) {
			currentSunY -= 1.0f * getAnimationScale();
			turnOffMoonLight();
		}
	}

	protected void renderScene() {
		drawGround();
		drawBackPlane();

		// draw the sun
		if (risingSun) {
			GL11.glPushMatrix();
			{
//				float globalAmbient[] = { 0.7f, 0.7f, 0.7f, 1.0f };
				
				float globalAmbient[] = { 0.09f, 0.09f, 0.09f, 1.0f };
				
				GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

				float diffuse0[] = { 0.9f, 0.9f, 0.1f, 1.0f };
				float ambient0[] = { 0.9f, 0.9f, 0.9f, 1.0f };
				float position0[] = { 0.0f, 0f, 0.2f, 1.0f };
				float emission0[] = {1f, 1f, 1f, 1.0f};
				
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
//				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_EMISSION, FloatBuffer.wrap(emission0));
				
				// enable the first light
				GL11.glEnable(GL11.GL_LIGHT0);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_NORMALIZE);
				GL11.glEnable(GL11.GL_EMISSION);
				// end of changing light.

				float sunFrontShininess = 1.0f;
				float sunFrontSpecular[] = { 0.1f, 0.1f, 0.0f, 1.0f };
				float sunFrontDiffuse[] = { 0.9f, 0.9f, 0.1f, 1.0f };

				// set the material properties for the sun using OpenGL
				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, sunFrontShininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(sunFrontSpecular));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(sunFrontDiffuse));
				
				
				
				// position and draw the sun using a sphere quadric object
				GL11.glTranslatef(4.0f, currentSunY, -19.0f);
				new Sphere().draw(0.6f, 50, 50);
			}
			GL11.glPopMatrix();
		} else {
			// draw the moon
			GL11.glPushMatrix();
			{
				// changing moon lighting
				float globalAmbient[] = { 0.2f, 0.2f, 0.2f, 1.0f };
				GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

				float diffuse0[] = { 0.9f, 0.9f, 0.9f, 1.0f };
				float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
				float position0[] = { 0.0f, 0f, 0.0f, 1.0f };

				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
				GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));

				GL11.glEnable(GL11.GL_LIGHT0);
				GL11.glEnable(GL11.GL_LIGHTING);
				GL11.glEnable(GL11.GL_NORMALIZE);

				// end of changing moon lighting

				float moonFrontShininess = 2.0f;
				float moonFrontSpecular[] = { 0.6f, 0.6f, 0.6f, 1.0f };
				float moonFrontDiffuse[] = { 0.6f, 0.6f, 0.6f, 1.0f };

				GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, moonFrontShininess);
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(moonFrontSpecular));
				GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(moonFrontDiffuse));

				GL11.glTranslatef(4.0f, currentMoonY, -19.0f);
				new Sphere().draw(0.5f, 50, 50);
			}
			GL11.glPopMatrix();
		}

		drawHive();
		drawTree();
		drawHouse();
	}

	private void drawGround() {
		GL11.glPushMatrix();
		{
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			Colour.WHITE.submit();
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, groundTextures.getTextureID());
			GL11.glTranslatef(0.0f, -1.0f, -10.0f);
			GL11.glScalef(25.0f, 1.0f, 20.0f);
			GL11.glCallList(planeList);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void drawBackPlane() {
		GL11.glPushMatrix();
		{
			// disable lighting calculations so that they don't affect
			// the appearance of the texture
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING);
			// change the geometry colour to white so that the texture
			// is bright and details can be seen clearly
			Colour.WHITE.submit();
			// enable texturing and bind an appropriate texture
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			// GL11.glBindTexture(GL11.GL_TEXTURE_2D,skyNightTextures.getTextureID());

			if (risingSun) {
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, skyDayTextures.getTextureID());
			} else {
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, skyNightTextures.getTextureID());
			}

			// position, scale and draw the back plane using its display list
			GL11.glTranslatef(0.0f, 4.0f, -20.0f);
			GL11.glRotatef(90.0f, 1.0f, 0.0f, 0.0f);
			GL11.glScalef(25.0f, 1.0f, 10.0f);
			GL11.glCallList(planeList);

			// disable textures and reset any local lighting changes
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glPopAttrib();
		}
		GL11.glPopMatrix();
	}

	private void drawHouse() {
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(-2.5f, 0.0f, -10.0f);
			GL11.glScalef(2.0f, 2.0f, 2.0f);
			GL11.glRotatef(35.0f, 0.0f, 1.0f, 0.0f);

			float houseFrontShininess = 2.0f;
			float houseFrontSpecular[] = { 0.1f, 0.0f, 0.0f, 1.0f };
			float houseFrontDiffuse[] = { 0.6f, 0.2f, 0.2f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, houseFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(houseFrontSpecular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(houseFrontDiffuse));

			GL11.glCallList(houseList);

			GL11.glTranslatef(0.0f, 0.75f, 0.0f);
			GL11.glScalef(1.0f, 0.5f, 1.0f);

			float roofFrontShininess = 2.0f;
			float roofFrontSpecular[] = { 0.1f, 0.1f, 0.1f, 1.0f };
			float roofFrontDiffuse[] = { 0.6f, 0.4f, 0.2f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, roofFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(roofFrontSpecular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(roofFrontDiffuse));

			GL11.glCallList(roofList);
		}
		GL11.glPopMatrix();
	}

	private void drawTree() {
		GL11.glPushMatrix();
		{
			float trunkFrontShininess = 20.0f;
			float trunkFrontSpecular[] = { 0.2f, 0.2f, 0.1f, 1.0f };
			float trunkFrontDiffuse[] = { 0.38f, 0.29f, 0.07f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, trunkFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(trunkFrontSpecular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(trunkFrontDiffuse));

			GL11.glTranslatef(0.0f, -1.0f, -12.0f);

			GL11.glPushMatrix();
			{
				GL11.glRotatef(-90.0f, 1.0f, 0.0f, 0.0f);
				new Cylinder().draw(0.25f, 0.25f, 1.5f, 10, 10);
			}
			GL11.glPopMatrix();

			float headFrontShininess = 20.0f;
			float headFrontSpecular[] = { 0.1f, 0.2f, 0.1f, 1.0f };
			float headFrontDiffuse[] = { 0.0f, 0.5f, 0.0f, 1.0f };

			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, headFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(headFrontSpecular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(headFrontDiffuse));

			GL11.glTranslatef(0.0f, 2.0f, 0.0f);
			new Sphere().draw(0.8f, 10, 10);
		}
		GL11.glPopMatrix();
	}

	private void drawHive() {
		GL11.glPushMatrix();
		{
			// position, scale and draw the bee hive
			GL11.glTranslatef(2.5f, -0.5f, -10.0f);

			// how shiny are the front faces of the bee hive (specular exponent)
			float hiveFrontShininess = 2.0f;
			// specular reflection of the front faces of the bee hive
			float hiveFrontSpecular[] = { 0.0f, 0.0f, 0.0f, 1.0f };
			// diffuse reflection of the front faces of the bee hive
			float hiveFrontDiffuse[] = { 0.8f, 0.8f, 0.0f, 1.0f };

			// set the material properties for the bee hive using OpenGL
			GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, hiveFrontShininess);
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(hiveFrontSpecular));
			GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(hiveFrontDiffuse));

			// draw the bee hive using its display list
			GL11.glCallList(hiveList);
		}
		GL11.glPopMatrix();
	}

	protected void cleanupScene() {// empty
	}

	private void resetAnimations() {
		// reset all attributes that are modified by user controls or animations
		currentMoonY = highestMoonY;
		risingMoon = true;
	}

	/**
	 * Draws a plane aligned with the X and Z axis, with its front face toward
	 * positive Y. The plane is of unit width and height, and uses the current
	 * OpenGL material settings for its appearance
	 */
	private void drawUnitPlane() {
		Vertex v1 = new Vertex(-0.5f, 0.0f, -0.5f); // left, back
		Vertex v2 = new Vertex(0.5f, 0.0f, -0.5f); // right, back
		Vertex v3 = new Vertex(0.5f, 0.0f, 0.5f); // right, front
		Vertex v4 = new Vertex(-0.5f, 0.0f, 0.5f); // left, front

		// draw the plane geometry. order the vertices so that the plane faces up
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v3.toVector(), v2.toVector(), v1.toVector()).submit();

			GL11.glTexCoord2f(0.0f, 0.0f);
			v4.submit();

			GL11.glTexCoord2f(1.0f, 0.0f);
			v3.submit();

			GL11.glTexCoord2f(1.0f, 1.0f);
			v2.submit();

			GL11.glTexCoord2f(0.0f, 1.0f);
			v1.submit();
		}
		GL11.glEnd();

		// if the user is viewing an axis, then also draw this plane
		// using lines so that axis aligned planes can still be seen
		if (isViewingAxis()) {
			// also disable textures when drawing as lines
			// so that the lines can be seen more clearly
			GL11.glPushAttrib(GL11.GL_TEXTURE_2D);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glBegin(GL11.GL_LINE_LOOP);
			{
				v4.submit();
				v3.submit();
				v2.submit();
				v1.submit();
			}
			GL11.glEnd();
			GL11.glPopAttrib();
		}
	}

	/**
	 * Draws a pyramid of unit length, width and height. The pyramid uses the
	 * current OpenGL material settings for its appearance
	 */
	private void drawUnitPyramid() {
		Vertex v1 = new Vertex(0.0f, 0.5f, 0.0f);
		Vertex v2 = new Vertex(-0.5f, -0.5f, -0.5f);
		Vertex v3 = new Vertex(0.5f, -0.5f, -0.5f);
		Vertex v4 = new Vertex(0.5f, -0.5f, 0.5f);
		Vertex v5 = new Vertex(-0.5f, -0.5f, 0.5f);

		// draw the bottom face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v4.toVector(), v5.toVector()).submit();

			v2.submit();
			v3.submit();
			v4.submit();
			v5.submit();
		}
		GL11.glEnd();
		// draw the near face:
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			new Normal(v1.toVector(), v5.toVector(), v4.toVector()).submit();

			v1.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// draw the right face:
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			new Normal(v1.toVector(), v4.toVector(), v3.toVector()).submit();

			v1.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// draw the far face:
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			new Normal(v1.toVector(), v3.toVector(), v2.toVector()).submit();

			v1.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();
		// draw the left face:
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			new Normal(v1.toVector(), v2.toVector(), v5.toVector()).submit();

			v1.submit();
			v2.submit();
			v5.submit();
		}
		GL11.glEnd();
	}

	/**
	 * Draws a roof geometry of unit length, width and height aligned along the x
	 * axis. The roof uses the current OpenGL material settings for its appearance
	 */
	private void drawUnitRoof() {
		Vertex v1 = new Vertex(-0.5f, -0.5f, -0.5f);
		Vertex v2 = new Vertex(-0.5f, 0.5f, 0.0f);
		Vertex v3 = new Vertex(-0.5f, -0.5f, 0.5f);
		Vertex v4 = new Vertex(0.5f, -0.5f, -0.5f);
		Vertex v5 = new Vertex(0.5f, 0.5f, 0.0f);
		Vertex v6 = new Vertex(0.5f, -0.5f, 0.5f);

		// left gable
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			new Normal(v3.toVector(), v2.toVector(), v1.toVector()).submit();

			v3.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();

		// back slope
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v5.toVector(), v4.toVector()).submit();

			v1.submit();
			v2.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();

		// front slope
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v2.toVector(), v3.toVector(), v6.toVector()).submit();

			v5.submit();
			v2.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();

		// right gable
		GL11.glBegin(GL11.GL_TRIANGLES);
		{
			new Normal(v5.toVector(), v6.toVector(), v4.toVector()).submit();

			v5.submit();
			v6.submit();
			v4.submit();
		}
		GL11.glEnd();
	}

	/**
	 * Draws a cube of unit length, width and height using the current OpenGL
	 * material settings
	 */
	private void drawUnitCube() {
		// the vertices for the cube (note that all sides have a length of 1)
		Vertex v1 = new Vertex(-0.5f, -0.5f, 0.5f);
		Vertex v2 = new Vertex(-0.5f, 0.5f, 0.5f);
		Vertex v3 = new Vertex(0.5f, 0.5f, 0.5f);
		Vertex v4 = new Vertex(0.5f, -0.5f, 0.5f);
		Vertex v5 = new Vertex(-0.5f, -0.5f, -0.5f);
		Vertex v6 = new Vertex(-0.5f, 0.5f, -0.5f);
		Vertex v7 = new Vertex(0.5f, 0.5f, -0.5f);
		Vertex v8 = new Vertex(0.5f, -0.5f, -0.5f);

		// draw the near face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v2.toVector(), v1.toVector(), v4.toVector()).submit();

			v3.submit();
			v2.submit();
			v1.submit();
			v4.submit();
		}
		GL11.glEnd();

		// draw the left face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v6.toVector(), v5.toVector(), v1.toVector()).submit();

			v2.submit();
			v6.submit();
			v5.submit();
			v1.submit();
		}
		GL11.glEnd();

		// draw the right face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v3.toVector(), v4.toVector(), v8.toVector()).submit();

			v7.submit();
			v3.submit();
			v4.submit();
			v8.submit();
		}
		GL11.glEnd();

		// draw the top face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v6.toVector(), v2.toVector(), v3.toVector()).submit();

			v7.submit();
			v6.submit();
			v2.submit();
			v3.submit();
		}
		GL11.glEnd();

		// draw the bottom face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v1.toVector(), v5.toVector(), v8.toVector()).submit();

			v4.submit();
			v1.submit();
			v5.submit();
			v8.submit();
		}
		GL11.glEnd();

		// draw the far face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v7.toVector(), v8.toVector(), v5.toVector()).submit();

			v6.submit();
			v7.submit();
			v8.submit();
			v5.submit();
		}
		GL11.glEnd();
	}

	private void turnOffMoonLight() {

		// global ambient light level
		float globalAmbient[] = { 0.1f, 0.1f, 0.1f, 1.0f };

		// set the global ambient lighting
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

		// the first light for the scene is soft blue...
		float diffuse0[] = { 0.2f, 0.2f, 0.4f, 1.0f };
		// ...with a very dim ambient contribution...
		float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
		// ...and is positioned above the viewpoint
		float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f };

		// supply OpenGL with the properties for the first light
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));

		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);

		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
		GL11.glEnable(GL11.GL_NORMALIZE);
	}

	private void turnOnMoonLight() {

		// global ambient light level
		float globalAmbient[] = { 0.5f, 0.5f, 0.5f, 1.0f };
		// set the global ambient lighting
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

		// the first light for the scene is soft blue...
		float diffuse0[] = { 0.9f, 0.9f, 0.9f, 1.0f };
		// ...with a very dim ambient contribution...
		float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
		// ...and is positioned above the viewpoint
		float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f };

		// supply OpenGL with the properties for the first light
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));

		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);

		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
		GL11.glEnable(GL11.GL_NORMALIZE);
	}

	private void turnOnSunLight() {

		// global ambient light level
		float globalAmbient[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		// set the global ambient lighting
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));

		// the first light for the scene is soft blue...
		float diffuse0[] = { 0.2f, 0.2f, 0.4f, 1.0f };
		// ...with a very dim ambient contribution...
		float ambient0[] = { 0.6f, 0.6f, 0.1f, 1.0f };
		// ...and is positioned above the viewpoint
		float position0[] = { 0.0f, 10.0f, 0.0f, 1.0f };

		// supply OpenGL with the properties for the first light
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));

		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);

		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
		GL11.glEnable(GL11.GL_NORMALIZE);
	}



}
