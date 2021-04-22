package exam;

import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.glu.Cylinder;
import org.lwjgl.util.glu.GLU;
import org.lwjgl.util.glu.Sphere;
import org.newdawn.slick.opengl.Texture;

import GraphicsLab.Colour;
import GraphicsLab.FloatBuffer;
import GraphicsLab.GraphicsLab;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

public class Robot extends GraphicsLab {

	private float cameraViewPointX = 0f;
	private float cameraViewPointY = 0f;
	private float cameraViewPointZ = -15f;
	private float viewerLocX = 0f;
	private float viewerLocY = 0f;
	private float viewerLocZ = 10f;
	private float viewUpVectorX = 0f;
	private float viewUpVectorY = 1f;
	private float viewUpVectorZ = 0f;

	private float sceneRotationAngle = 0;
	private float sceneRotateSpeed = 1;

	private float leftLegRotAngle = 0f;
	private float rightLegRotAngle = 0f;

	private float leftHandRotAngle = 0f;
	private float rightHandRotAngle = 0f;

	private boolean swingRight = true;

	private int stepTaken;
	private int maxSteps = 5;
	
	private Texture angryFaceTexture;

	public static void main(String args[]) {
		new Robot().run(WINDOWED, "Pendulum", 0.1f);
	}

	protected void initScene() throws Exception {
		
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
		
		// enable the first light
		GL11.glEnable(GL11.GL_LIGHT0);
		// enable lighting calculations
		GL11.glEnable(GL11.GL_LIGHTING);
		// ensure that all normals are re-normalised after transformations automatically
		GL11.glEnable(GL11.GL_NORMALIZE);
	}

	protected void checkSceneInput() {
		if (Keyboard.isKeyDown(Keyboard.KEY_SPACE)) {
			resetAnimations();
		}
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
	}

	protected void updateScene() {
		//animation for legs and hands
		if (stepTaken < maxSteps) {	
			//swing legs and hands opposite to each other 30 degrees
			if (swingRight && leftLegRotAngle < 30) {
				leftLegRotAngle += 1 * getAnimationScale();		
				rightLegRotAngle -= 1 * getAnimationScale();
				leftHandRotAngle -= 2 * getAnimationScale();
				rightHandRotAngle += 2 * getAnimationScale();
			}
			//swing legs and hands opposite to each other 30 degrees other way
			if (!swingRight && leftLegRotAngle > -30) {
				leftLegRotAngle -= 1 * getAnimationScale();
				rightLegRotAngle += 1 * getAnimationScale();
				leftHandRotAngle += 2 * getAnimationScale();
				rightHandRotAngle -= 2 * getAnimationScale();
			}
			if (leftLegRotAngle >= 30) {
				swingRight = false;
				stepTaken++;		//increment cycle or 1 full step
			}
			if (leftLegRotAngle <= -30) {
				swingRight = true;
			}
		} else {
			//after cycle is completed
			if (leftLegRotAngle != 0) {						
				//if not at rest position
				leftLegRotAngle -= 1 * getAnimationScale();
				rightLegRotAngle += 1 * getAnimationScale(); 
				rightHandRotAngle -= 1 * getAnimationScale();
				leftHandRotAngle += 1 * getAnimationScale();
				//swing towards rest position
				rightLegRotAngle += 1 * getAnimationScale();
				if (leftLegRotAngle < 0) {					
					//if gone past rest position
					//set the angle to 0
					leftLegRotAngle = 0;
					rightLegRotAngle = 0;
					leftHandRotAngle = 0;
					rightHandRotAngle = 0;
				}
			}
		}
	}

	// --------------------------------------render-methods------------------------------------------

	protected void renderScene() {
		GL11.glRotatef(sceneRotationAngle, 0, 1, 0);

		GL11.glPushMatrix();
		{
			drawUnitRobot();
		}
		GL11.glPopMatrix();

	}
	
	private void setMaterial(float shininess, float[] specular, float[] diffuse) {
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(specular));
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(diffuse));
	}

	private void drawUnitRobot() {
		GL11.glPushMatrix();
		{
			GL11.glPushMatrix();
			{
				// head
				GL11.glPushAttrib(GL11.GL_LIGHTING_BIT);
				GL11.glDisable(GL11.GL_LIGHTING);

				GL11.glEnable(GL11.GL_TEXTURE_2D);
				GL11.glBindTexture(GL11.GL_TEXTURE_2D, angryFaceTexture.getTextureID());

				float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
				float[] diffuse = { 0.8f, 0, 0, 1f };
				setMaterial(1f, specular, diffuse);

				GL11.glTranslatef(0f, 1, 0f);
				GL11.glScalef(0.5f, 0.5f, 0.35f);
				drawUnitCube();
				GL11.glDisable(GL11.GL_TEXTURE_2D);

				GL11.glPopAttrib();
			}
			GL11.glPopMatrix();
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
					drawUnitCube();
				}
				GL11.glPopMatrix();
				GL11.glPushMatrix();
				{
					// right-hand
					GL11.glRotatef(rightHandRotAngle, 1, 0, 0);
					
					GL11.glTranslatef(0.5f + (0.25f / 2), -0.9f, 0f);
					GL11.glScalef(0.25f, 1.25f, 0.3f);
					drawUnitCube();
				}
				GL11.glPopMatrix();
			}
			GL11.glPopMatrix();

			GL11.glPushMatrix();
			{
				// left-leg
				float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
				float[] diffuse = { 0.2f, 0.2f, 0.9f, 1f };
				setMaterial(1f, specular, diffuse);

				GL11.glRotatef(leftLegRotAngle, 1, 0, 0);

				GL11.glTranslatef(-0.35f + 0.1f, -1.25f, 0f);
				GL11.glScalef(0.35f, 1f, 0.4f);
				drawUnitCube();
			}
			GL11.glPopMatrix();
			GL11.glPushMatrix();
			{
				// right-leg
				float[] specular = { 0.2f, 0.2f, 0.2f, 1f };
				float[] diffuse = { 0.2f, 0.2f, 0.9f, 1f };
				setMaterial(1f, specular, diffuse);

				GL11.glRotatef(rightLegRotAngle, 1, 0, 0);

				GL11.glTranslatef(0.35f - 0.1f, -1.25f, 0f);
				GL11.glScalef(0.35f, 1f, 0.4f);
				drawUnitCube();
			}
			GL11.glPopMatrix();

		}
		GL11.glPopMatrix();
	}

	// --------------------------------------super-methods--------------------------------------------

	protected void setSceneCamera() {
		super.setSceneCamera();
		GLU.gluLookAt(viewerLocX, viewerLocY, viewerLocZ, // viewer location
				cameraViewPointX, cameraViewPointY, cameraViewPointZ, // view point loc.
				viewUpVectorX, viewUpVectorY, viewUpVectorZ); // view-up vector
	}

	protected void cleanupScene() {
	}

	private void resetAnimations() {
		stepTaken = 0;
		swingRight = true;
	}

	// --------------------------------------mesh-methods---------------------------------------------

	public void drawUnitCube() {
		float h = 0.5f;
		float d = 0.5f;
		float w = 0.5f;
		// the vertices for the cube (note that all sides have a length of 1)
		Vertex v1 = new Vertex(-w, -h, d);	//bottem left
		Vertex v2 = new Vertex(-w, h, d);	//top left
		Vertex v3 = new Vertex(w, h, d);	//top right
		Vertex v4 = new Vertex(w, -h, d);	//bottom right
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
	
}


















