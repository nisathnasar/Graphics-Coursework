package exam;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.opengl.Texture;
import GraphicsLab.FloatBuffer;
import GraphicsLab.GraphicsLab;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

public class Robot2 extends GraphicsLab {
	// beginning of code
	// global variables
	private float leftLegRotAngle;
	private float rightLegRotAngle;
	private float leftHandRotAngle;
	private float rightHandRotAngle;
	private boolean stepForward;
	private int stepTaken;
	private int maxSteps;
	private int cubeList;
	private Texture angryFaceTexture;

	public static void main(String args[]) {
		new Robot2().run(WINDOWED, "Robot 2", 0.1f);
	}

	// initScene method, initialises the fields and lighting
	protected void initScene() throws Exception {
		leftLegRotAngle = 0f;
		rightLegRotAngle = 0f;
		leftHandRotAngle = 0f;
		rightHandRotAngle = 0f;
		stepForward = true;
		stepTaken = 0;
		maxSteps = 5;
		cubeList = 1;
		angryFaceTexture = loadTexture("exam/textures/anger-1428042__340.png");
		float globalAmbient[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		GL11.glLightModel(GL11.GL_LIGHT_MODEL_AMBIENT, FloatBuffer.wrap(globalAmbient));
		float diffuse0[] = { 0.7f, 0.7f, 0.7f, 1.0f };
		float specular0[] = { 0.5f, 0.5f, 0.6f, 1.0f };
		float ambient0[] = { 0.05f, 0.05f, 0.05f, 1.0f };
		float position0[] = { 0.0f, 5.0f, 5.0f, 1.0f };
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_AMBIENT, FloatBuffer.wrap(ambient0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_SPECULAR, FloatBuffer.wrap(specular0));
		GL11.glLight(GL11.GL_LIGHT0, GL11.GL_POSITION, FloatBuffer.wrap(position0));
		// enable the first light in the scene
		GL11.glEnable(GL11.GL_LIGHT0);
		// enable lighting calculations for the scene
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
		GL11.glEnable(GL11.GL_NORMALIZE);

		GL11.glNewList(cubeList, GL11.GL_COMPILE);
		{
			drawUnitCube();
		}
		GL11.glEndList();
	}

	// updateScene method performs the animation logic
	protected void updateScene() {
		// animation for legs and hands
		if (stepTaken < maxSteps) {
			// swing legs and hands opposite to each other 30 degrees
			if (stepForward && leftLegRotAngle < 30) {
				leftLegRotAngle += 1 * getAnimationScale();
				rightLegRotAngle -= 1 * getAnimationScale();
				leftHandRotAngle -= 2 * getAnimationScale();
				rightHandRotAngle += 2 * getAnimationScale();
			}
			// swing legs and hands opposite to each other 30 degrees other way
			if (!stepForward && leftLegRotAngle > -30) {
				leftLegRotAngle -= 1 * getAnimationScale();
				rightLegRotAngle += 1 * getAnimationScale();
				leftHandRotAngle += 2 * getAnimationScale();
				rightHandRotAngle -= 2 * getAnimationScale();
			}
			if (leftLegRotAngle >= 30) {
				stepForward = false;
				stepTaken++; // increment cycle or 1 full step
			}
			if (leftLegRotAngle <= -30) {
				stepForward = true;
			}
		} else {
			// after cycle is completed
			if (leftLegRotAngle != 0) {
				// if not at rest position
				leftLegRotAngle -= 1 * getAnimationScale();
				rightLegRotAngle += 1 * getAnimationScale();
				rightHandRotAngle -= 1 * getAnimationScale();
				leftHandRotAngle += 1 * getAnimationScale();
				// swing towards rest position
				rightLegRotAngle += 1 * getAnimationScale();
				if (leftLegRotAngle < 0) {
					// if gone past rest position
					// set the angle to 0
					leftLegRotAngle = 0;
					rightLegRotAngle = 0;
					leftHandRotAngle = 0;
					rightHandRotAngle = 0;
				}
			}
		}
	}

	protected void renderScene() {
		GL11.glPushMatrix();
		{
			// move the object back to view the object
			// from default camera position
			GL11.glTranslatef(0, 0, -5);
			drawUnitRobot();
		}
		GL11.glPopMatrix();
	}

	// helper method used for setting material properties
	private void setMaterial(float shininess, float[] specular, float[] diffuse) {
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));
	}

	// helper method that aggregates cubes to make robot
	private void drawUnitRobot() {
		GL11.glPushMatrix();
		{
			drawHead();
			drawBody();
			drawLegs();
			drawHands();
		}
		GL11.glPopMatrix();
	}

	private void drawHead() {
		GL11.glPushMatrix();
		{
			// head
			// push lighting properties into stack
			GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
			GL11.glDisable(GL11.GL_LIGHTING); // disable lighting
			GL11.glEnable(GL11.GL_TEXTURE_2D); // enable texture
			GL11.glBindTexture(GL11.GL_TEXTURE_2D, angryFaceTexture.getTextureID());
			float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
			float[] diffuse = { 0.8f, 0, 0, 1f };
			setMaterial(1f, specular, diffuse);
			GL11.glTranslatef(0f, 1, 0f);
			GL11.glScalef(0.5f, 0.5f, 0.35f);
			GL11.glCallList(cubeList);
			GL11.glDisable(GL11.GL_TEXTURE_2D); // disable texture
			GL11.glPopAttrib(); // bring back lighting properties
		}
		GL11.glPopMatrix();
	}

	private void drawBody() {
		GL11.glPushMatrix();
		{
			// body
			float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
			float[] diffuse = { 0.8f, 0.5f, 0.1f, 1f };
			setMaterial(1f, specular, diffuse);
			GL11.glScalef(1, 1.5f, 0.5f);
			drawUnitCube();
		}
		GL11.glPopMatrix();
	}
	
	private void drawHands() {
		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0f, 1f, 0f);
			GL11.glPushMatrix();
			{
				// left-hand
				float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
				float[] diffuse = { 0.1f, 0.7f, 0.1f, 1f };
				setMaterial(1f, specular, diffuse);
				GL11.glRotatef(leftHandRotAngle, 1, 0, 0);
				GL11.glTranslatef(-0.5f - (0.25f / 2), -0.9f, 0f);
				GL11.glScalef(0.25f, 1.25f, 0.3f);
				GL11.glCallList(cubeList);
			}
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			{
				// right-hand
				GL11.glRotatef(rightHandRotAngle, 1, 0, 0);
				GL11.glTranslatef(0.5f + (0.25f / 2), -0.9f, 0f);
				GL11.glScalef(0.25f, 1.25f, 0.3f);
				GL11.glCallList(cubeList);
			}
			GL11.glPopMatrix();
		}
		GL11.glPopMatrix();
	}
	
	private void drawLegs() {
		GL11.glPushMatrix();
		{ // left-leg
			float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
			float[] diffuse = { 0.2f, 0.2f, 0.9f, 1f };
			setMaterial(1f, specular, diffuse);
			GL11.glRotatef(leftLegRotAngle, 1, 0, 0);
			GL11.glTranslatef(-0.35f + 0.1f, -1.25f, 0f);
			GL11.glScalef(0.35f, 1f, 0.4f);
			GL11.glCallList(cubeList);
		}
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		{ // right-leg
			float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
			float[] diffuse = { 0.2f, 0.2f, 0.9f, 1f };
			setMaterial(1f, specular, diffuse);
			GL11.glRotatef(rightLegRotAngle, 1, 0, 0);
			GL11.glTranslatef(0.35f - 0.1f, -1.25f, 0f);
			GL11.glScalef(0.35f, 1f, 0.4f);
			GL11.glCallList(cubeList);
		}
		GL11.glPopMatrix();
	}
	


	// code to draw a cube
	public void drawUnitCube() {
		float h = 0.5f;
		float d = 0.5f;
		float w = 0.5f;
		// the vertices for the cube (note that all sides have a length of 1)
		Vertex v1 = new Vertex(-w, -h, d); // bottem left
		Vertex v2 = new Vertex(-w, h, d); // top left
		Vertex v3 = new Vertex(w, h, d); // top right
		Vertex v4 = new Vertex(w, -h, d); // bottom right
		Vertex v5 = new Vertex(-w, -h, -d);
		Vertex v6 = new Vertex(-w, h, -d);
		Vertex v7 = new Vertex(w, h, -d);
		Vertex v8 = new Vertex(w, -h, -d);
		// draw the near face:
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v2.toVector(), v1.toVector(), v4.toVector()).submit();
			GL11.glTexCoord2f(1.0f, 1.0f);
			v3.submit();
			GL11.glTexCoord2f(0.0f, 1.0f);
			v2.submit();
			GL11.glTexCoord2f(0.0f, 0.0f);
			v1.submit();
			GL11.glTexCoord2f(1.0f, 0.0f);
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
	// end of code

	@Override
	protected void checkSceneInput() {
		// TODO Auto-generated method stub

	}

}
