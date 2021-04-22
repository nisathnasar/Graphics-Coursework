package coursework.nisath;

import org.lwjgl.opengl.GL11;

import GraphicsLab.FloatBuffer;
import GraphicsLab.Normal;
import GraphicsLab.Vertex;

public class ManModel {

	private float shininess = 1.0f;
	private float[] skinColour = { 0.95f, 0.69f, 0.55f, 1.0f };
	private float[] shirtColour = { 0.71f, 0.35f, 0.16f, 1.0f };
	private float[] trouserColour = { 0.02f, 0.42f, 0.65f, 1.0f };
	private float[] shoeColour = { 0.5f, 0.5f, 0.5f, 1.0f };

	private float leftLegZ = 0;

	public ManModel() {
		shininess = 1.0f;
	}

	public void drawUnitMan() {
		{
			setMaterial(skinColour);
			drawHead();
		}
		{
			setMaterial(shirtColour);
			drawTorso();
		}

		drawRightHand();
		drawLeftHand();
		{
			setMaterial(trouserColour);
			drawRightLeg();
			drawLeftLeg(leftLegZ);
		}
		{
			setMaterial(shoeColour);
			drawRightFoot();

			GL11.glPushMatrix();
			{
				GL11.glTranslatef(0, 0, leftLegZ);
				drawLeftFoot();
			}
			GL11.glPopMatrix();
		}

	}

	private void setMaterial(float[] color) {
		GL11.glMaterialf(GL11.GL_FRONT, GL11.GL_SHININESS, shininess);
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_SPECULAR, FloatBuffer.wrap(color));
		GL11.glMaterial(GL11.GL_FRONT, GL11.GL_DIFFUSE, FloatBuffer.wrap(color));
	}

	private void drawHead() {
		drawHeadL1();
		drawHeadL2();
		drawHeadL3();
		drawNeckL1();
	}

	private void drawTorso() {
		drawRightShoulder();
		drawLeftShoulder();
		drawBodyFront();
		drawBodyBack();
		drawBodyRight();
		drawBodyleft();
	}

	private void drawHeadL1() {
		// Head - L1
		// left
		Vertex v1 = new Vertex(-0.95f, 1, 0);
		Vertex v4 = new Vertex(-0.63f, 0.91f, -0.87f);
		Vertex v7 = new Vertex(-0.63f, 1, 0.89f);

		// right
		Vertex v5 = new Vertex(0.95f, 1, 0);
		Vertex v6 = new Vertex(0.63f, 0.91f, -0.87f);
		Vertex v9 = new Vertex(0.63f, 1, 0.89f);

		// mid
		Vertex v2 = new Vertex(0, 1.26f, 0);
		Vertex v3 = new Vertex(0, 1, -1);
		Vertex v8 = new Vertex(0, 1, 1);

		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v4.toVector()).submit();
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v5.toVector(), v6.toVector(), v3.toVector()).submit();
			v2.submit();
			v5.submit();
			v6.submit();
			v3.submit();
		}
		GL11.glEnd();
		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v2.toVector(), v1.toVector()).submit();
			v7.submit();
			v8.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v5.toVector(), v2.toVector()).submit();
			v8.submit();
			v9.submit();
			v5.submit();
			v2.submit();
		}
		GL11.glEnd();

	}

	private void drawHeadL2() {
		// Head - L1
		// left
		Vertex v1 = new Vertex(-0.95f, 1, 0);
		Vertex v4 = new Vertex(-0.63f, 0.91f, -0.87f);
		Vertex v7 = new Vertex(-0.63f, 1, 0.89f);

		// right
		Vertex v5 = new Vertex(0.95f, 1, 0);
		Vertex v6 = new Vertex(0.63f, 0.91f, -0.87f);
		Vertex v9 = new Vertex(0.63f, 1, 0.89f);

		// mid
		//Vertex v2 = new Vertex(0, 1.26f, 0);
		Vertex v3 = new Vertex(0, 1, -1);
		Vertex v8 = new Vertex(0, 1, 1);

		// Head L2
		// left
		Vertex v10 = new Vertex(-0.84f, 0, 1);
		Vertex v16 = new Vertex(-0.89f, 0, -0.81f);
		Vertex v17 = new Vertex(-1, 0, 0);
		// mid
		Vertex v11 = new Vertex(0, 0, 1.24f);
		Vertex v15 = new Vertex(0, 0, -1);
		// right
		Vertex v12 = new Vertex(0.84f, 0, 1);
		Vertex v13 = new Vertex(1, 0, 0);
		Vertex v14 = new Vertex(0.89f, 0, -0.81f);

		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v11.toVector(), v8.toVector(), v7.toVector()).submit();
			v10.submit();
			v11.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v9.toVector(), v8.toVector()).submit();
			v11.submit();
			v12.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();
		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v12.toVector(), v13.toVector(), v5.toVector(), v9.toVector()).submit();
			v12.submit();
			v13.submit();
			v5.submit();
			v9.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v6.toVector(), v5.toVector()).submit();
			v13.submit();
			v14.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v3.toVector(), v6.toVector()).submit();
			v14.submit();
			v15.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v15.toVector(), v16.toVector(), v4.toVector(), v3.toVector()).submit();
			v15.submit();
			v16.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// 7
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v16.toVector(), v17.toVector(), v1.toVector(), v4.toVector()).submit();
			v16.submit();
			v17.submit();
			v1.submit();
			v4.submit();
		}
		GL11.glEnd();
		// 8
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v17.toVector(), v10.toVector(), v7.toVector(), v1.toVector()).submit();
			v17.submit();
			v10.submit();
			v7.submit();
			v1.submit();
		}
		GL11.glEnd();
	}

	private void drawHeadL3() {
		// Head L2
		// left
		Vertex v10 = new Vertex(-0.84f, 0, 1);
		Vertex v16 = new Vertex(-0.89f, 0, -0.81f);
		Vertex v17 = new Vertex(-1, 0, 0);
		// mid
		Vertex v11 = new Vertex(0, 0, 1.24f);
		Vertex v15 = new Vertex(0, 0, -1);
		// right
		Vertex v12 = new Vertex(0.84f, 0, 1);
		Vertex v13 = new Vertex(1, 0, 0);
		Vertex v14 = new Vertex(0.89f, 0, -0.81f);

		// Head L3
		// left
		Vertex v1 = new Vertex(-0.58f, -1, 0.72f);
		Vertex v7 = new Vertex(-0.58f, -1, -0.57f);
		Vertex v8 = new Vertex(-0.8f, -1, 0);
		// mid
		Vertex v2 = new Vertex(0, -1, 1);
		Vertex v6 = new Vertex(0, -1, -0.84f);
		// right
		Vertex v3 = new Vertex(0.58f, -1, 0.72f);
		Vertex v4 = new Vertex(0.8f, -1, 0);
		Vertex v5 = new Vertex(0.58f, -1, -0.57f);
		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v11.toVector(), v10.toVector()).submit();
			v1.submit();
			v2.submit();
			v11.submit();
			v10.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v12.toVector(), v11.toVector()).submit();
			v2.submit();
			v3.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();
		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v4.toVector(), v13.toVector(), v12.toVector()).submit();
			v3.submit();
			v4.submit();
			v13.submit();
			v12.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v14.toVector(), v13.toVector()).submit();
			v4.submit();
			v5.submit();
			v14.submit();
			v13.submit();
		}
		GL11.glEnd();
		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v15.toVector(), v14.toVector()).submit();
			v5.submit();
			v6.submit();
			v15.submit();
			v14.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v7.toVector(), v16.toVector(), v15.toVector()).submit();
			v6.submit();
			v7.submit();
			v16.submit();
			v15.submit();
		}
		GL11.glEnd();
		// 7
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v17.toVector(), v16.toVector()).submit();
			v7.submit();
			v8.submit();
			v17.submit();
			v16.submit();
		}
		GL11.glEnd();
		// 8
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v1.toVector(), v10.toVector(), v17.toVector()).submit();
			v8.submit();
			v1.submit();
			v10.submit();
			v17.submit();
		}
		GL11.glEnd();
	}

	private void drawNeckL1() {
		// Head L3
		// left
		Vertex v1 = new Vertex(-0.58f, -1, 0.72f);
		Vertex v7 = new Vertex(-0.58f, -1, -0.57f);
		Vertex v8 = new Vertex(-0.8f, -1, 0);
		// mid
		Vertex v2 = new Vertex(0, -1, 1);
		Vertex v6 = new Vertex(0, -1, -0.84f);
		// right
		Vertex v3 = new Vertex(0.58f, -1, 0.72f);
		Vertex v4 = new Vertex(0.8f, -1, 0);
		Vertex v5 = new Vertex(0.58f, -1, -0.57f);

		// Neck L1
		// left
		Vertex v11 = new Vertex(-0.41f, -1.74f, 0.72f);
		Vertex v17 = new Vertex(-0.41f, -1.74f, -0.57f);
		Vertex v18 = new Vertex(-0.63f, -1.74f, 0);
		// mid
		Vertex v12 = new Vertex(0, -1.74f, 0.72f);
		Vertex v16 = new Vertex(0, -1.74f, -0.55f);
		// right
		Vertex v13 = new Vertex(0.41f, -1.74f, 0.72f);
		Vertex v14 = new Vertex(0.63f, -1.74f, 0);
		Vertex v15 = new Vertex(0.41f, -1.74f, -0.57f);
		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v2.toVector(), v1.toVector()).submit();
			v11.submit();
			v12.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v12.toVector(), v13.toVector(), v3.toVector(), v2.toVector()).submit();
			v12.submit();
			v13.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();
		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v4.toVector(), v3.toVector()).submit();
			v13.submit();
			v14.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v5.toVector(), v4.toVector()).submit();
			v14.submit();
			v15.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v15.toVector(), v16.toVector(), v6.toVector(), v5.toVector()).submit();
			v15.submit();
			v16.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v16.toVector(), v17.toVector(), v7.toVector(), v6.toVector()).submit();
			v16.submit();
			v17.submit();
			v7.submit();
			v6.submit();
		}
		GL11.glEnd();
		// 7
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v17.toVector(), v18.toVector(), v8.toVector(), v7.toVector()).submit();
			v17.submit();
			v18.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// 8
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v18.toVector(), v11.toVector(), v1.toVector(), v8.toVector()).submit();
			v18.submit();
			v11.submit();
			v1.submit();
			v8.submit();
		}
		GL11.glEnd();

	}

	private void drawRightShoulder() {
		Vertex v1 = new Vertex(0.41f, -1.74f, 0.72f);
		Vertex v2 = new Vertex(1.4f, -1.74f, 0.66f);
		Vertex v3 = new Vertex(1.78f, -1.74f, -0.06f);
		Vertex v4 = new Vertex(1.4f, -1.74f, -0.63f);
		Vertex v5 = new Vertex(0.41f, -1.74f, -0.57f);
		Vertex v6 = new Vertex(0.63f, -1.74f, 0);
		// front
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v6.toVector()).submit();
			v1.submit();
			v2.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();
		// back
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v3.toVector(), v4.toVector(), v6.toVector()).submit();
			v6.submit();
			v3.submit();
			v4.submit();
			v5.submit();
		}
		GL11.glEnd();
	}

	private void drawLeftShoulder() {
		Vertex v1 = new Vertex(-0.41f, -1.74f, 0.72f);
		Vertex v2 = new Vertex(-1.4f, -1.74f, 0.66f);
		Vertex v3 = new Vertex(-1.78f, -1.74f, -0.06f);
		Vertex v4 = new Vertex(-1.4f, -1.74f, -0.63f);
		Vertex v5 = new Vertex(-0.41f, -1.74f, -0.57f);
		Vertex v6 = new Vertex(-0.63f, -1.74f, 0);
		// front
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v3.toVector(), v2.toVector(), v1.toVector()).submit();
			v6.submit();
			v3.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// back
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v4.toVector(), v3.toVector(), v6.toVector()).submit();
			v5.submit();
			v4.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();
	}

	private void drawBodyFront() {
		Vertex v1 = new Vertex(-1.4f, -4.82f, 0.66f);
		Vertex v2 = new Vertex(1.4f, -4.82f, 0.66f);
		Vertex v3 = new Vertex(1.4f, -1.74f, 0.66f);
		Vertex v4 = new Vertex(-1.4f, -1.74f, 0.66f);
		Vertex v5 = new Vertex(-1.01f, -6.56f, 0.66f);
		Vertex v6 = new Vertex(1.01f, -6.56f, 0.66f);
		// top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v4.toVector()).submit();
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();
		}
		GL11.glEnd();
		// bottom
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v2.toVector(), v1.toVector()).submit();
			v5.submit();
			v6.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
	}

	private void drawBodyBack() {
		Vertex v1 = new Vertex(-1.4f, -4.82f, -0.63f);
		Vertex v2 = new Vertex(1.4f, -4.82f, -0.63f);
		Vertex v3 = new Vertex(1.4f, -1.74f, -0.63f);
		Vertex v4 = new Vertex(-1.4f, -1.74f, -0.63f);
		Vertex v5 = new Vertex(-1.01f, -6.56f, -0.63f);
		Vertex v6 = new Vertex(1.01f, -6.56f, -0.63f);
		// top
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v3.toVector(), v2.toVector(), v1.toVector()).submit();
			v4.submit();
			v3.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// bottom
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v6.toVector(), v5.toVector()).submit();
			v1.submit();
			v2.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
	}

	private void drawBodyRight() {
		// top row
		Vertex v1 = new Vertex(1.4f, -2.78f, 0.66f);
		Vertex v2 = new Vertex(1.78f, -2.78f, -0.06f);
		Vertex v3 = new Vertex(1.4f, -2.78f, -0.63f);
		// mid row
		Vertex v4 = new Vertex(1.4f, -4.82f, 0.66f);
		Vertex v5 = new Vertex(1.78f, -4.82f, -0.06f);
		Vertex v6 = new Vertex(1.4f, -4.82f, -0.63f);
		// last row
		Vertex v7 = new Vertex(1.01f, -6.56f, 0.66f);
		Vertex v8 = new Vertex(1.39f, -6.56f, -0.06f);
		Vertex v9 = new Vertex(1.01f, -6.56f, -0.63f);

		// top left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v2.toVector(), v1.toVector()).submit();
			v4.submit();
			v5.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// top right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v3.toVector(), v2.toVector()).submit();
			v5.submit();
			v6.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();
		// bottom left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v5.toVector(), v4.toVector()).submit();
			v7.submit();
			v8.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// bottom right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v6.toVector(), v5.toVector()).submit();
			v8.submit();
			v9.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
	}

	private void drawBodyleft() {
		// top row
		Vertex v1 = new Vertex(-1.4f, -2.78f, 0.66f);
		Vertex v2 = new Vertex(-1.78f, -2.78f, -0.06f);
		Vertex v3 = new Vertex(-1.4f, -2.78f, -0.63f);
		// mid row
		Vertex v4 = new Vertex(-1.4f, -4.82f, 0.66f);
		Vertex v5 = new Vertex(-1.78f, -4.82f, -0.06f);
		Vertex v6 = new Vertex(-1.4f, -4.82f, -0.63f);
		// last row
		Vertex v7 = new Vertex(-1.01f, -6.56f, 0.66f);
		Vertex v8 = new Vertex(-1.39f, -6.56f, -0.06f);
		Vertex v9 = new Vertex(-1.01f, -6.56f, -0.63f);

		// top left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v5.toVector(), v4.toVector()).submit();
			v1.submit();
			v2.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// top right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v6.toVector(), v5.toVector()).submit();
			v2.submit();
			v3.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		// bottom left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v8.toVector(), v7.toVector()).submit();
			v4.submit();
			v5.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// bottom right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v9.toVector(), v8.toVector()).submit();
			v5.submit();
			v6.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();
	}

	private void drawRightHand() {
		drawRightHandFront();
		drawRightHandBack();
		drawRightHandOuter();
		drawRightHandInner();
		drawRightHandBottom();
	}

	private void drawRightHandFront() {
		Vertex v1 = new Vertex(1.4f, -2.78f, 0.66f); // row 1
		Vertex v2 = new Vertex(1.4f, -1.74f, 0.66f);
		Vertex v3 = new Vertex(2.16f, -4.14f, 0.45f); // row 2 (elbow)
		Vertex v4 = new Vertex(2.86f, -4f, 0.45f);
		Vertex v5 = new Vertex(2.51f, -5.9f, 0.45f); // row 3
		Vertex v6 = new Vertex(3.2f, -5.76f, 0.45f);
		Vertex v7 = new Vertex(2.72f, -6.46f, 0.31f); // row 4
		Vertex v8 = new Vertex(3.21f, -6.36f, 0.31f);
		Vertex v9 = new Vertex(2.63f, -6.8f, 0.52f); // row 5
		Vertex v10 = new Vertex(3.43f, -6.63f, 0.52f);
		Vertex v11 = new Vertex(2.8f, -7.1f, 0.37f); // row 6
		Vertex v12 = new Vertex(3.38f, -7f, 0.37f);

		setMaterial(shirtColour);
		
		// L1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v4.toVector(), v2.toVector(), v1.toVector()).submit();
			v3.submit();
			v4.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// L2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v4.toVector(), v3.toVector()).submit();
			v5.submit();
			v6.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// L3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v6.toVector(), v5.toVector()).submit();
			v7.submit();
			v8.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		setMaterial(skinColour);

		// L4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v9.toVector(), v10.toVector(), v8.toVector(), v7.toVector()).submit();
			v9.submit();
			v10.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// L5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v10.toVector(), v9.toVector()).submit();
			v11.submit();
			v12.submit();
			v10.submit();
			v9.submit();
		}
		GL11.glEnd();

	}

	private void drawRightHandBack() {
		Vertex v1 = new Vertex(1.4f, -2.78f, -0.63f); // row 1
		Vertex v2 = new Vertex(1.4f, -1.74f, -0.63f);
		Vertex v3 = new Vertex(2.16f, -4.14f, -0.43f); // row 2 (elbow)
		Vertex v4 = new Vertex(2.86f, -4f, -0.43f);
		Vertex v5 = new Vertex(2.51f, -5.9f, -0.43f); // row 3
		Vertex v6 = new Vertex(3.2f, -5.76f, -0.43f);
		Vertex v7 = new Vertex(2.72f, -6.46f, -0.31f); // row 4
		Vertex v8 = new Vertex(3.21f, -6.36f, -0.31f);
		Vertex v9 = new Vertex(2.63f, -6.8f, -0.5f); // row 5
		Vertex v10 = new Vertex(3.43f, -6.63f, -0.5f);
		Vertex v11 = new Vertex(2.8f, -7.1f, -0.36f); // row 6
		Vertex v12 = new Vertex(3.38f, -7f, -0.36f);

		setMaterial(shirtColour);

		// L1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v4.toVector(), v3.toVector()).submit();
			v1.submit();
			v2.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// L2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v4.toVector(), v6.toVector(), v5.toVector()).submit();
			v3.submit();
			v4.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		// L3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v8.toVector(), v7.toVector()).submit();
			v5.submit();
			v6.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();

		setMaterial(skinColour);
		// L4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v10.toVector(), v9.toVector()).submit();
			v7.submit();
			v8.submit();
			v10.submit();
			v9.submit();
		}
		GL11.glEnd();
		// L5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v9.toVector(), v10.toVector(), v12.toVector(), v11.toVector()).submit();
			v9.submit();
			v10.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();
	}

	private void drawRightHandOuter() {
		Vertex v1 = new Vertex(1.4f, -1.74f, 0.66f);
		Vertex v2 = new Vertex(1.78f, -1.74f, -0.06f);
		Vertex v3 = new Vertex(1.4f, -1.74f, -0.63f);
		Vertex v4 = new Vertex(2.86f, -4f, 0.45f);
		Vertex v5 = new Vertex(2.86f, -4f, -0.05f);
		Vertex v6 = new Vertex(2.86f, -4f, -0.43f);

		setMaterial(shirtColour);
		// L1 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v2.toVector(), v1.toVector()).submit();
			v4.submit();
			v5.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// L1 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v3.toVector(), v2.toVector()).submit();
			v5.submit();
			v6.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();

		Vertex v7 = new Vertex(3.2f, -5.76f, 0.45f);
		Vertex v8 = new Vertex(3.2f, -5.76f, 0.05f);
		Vertex v9 = new Vertex(3.2f, -5.76f, -0.43f);

		// L2 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v5.toVector(), v4.toVector()).submit();
			v7.submit();
			v8.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// L2 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v6.toVector(), v5.toVector()).submit();
			v8.submit();
			v9.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		Vertex v10 = new Vertex(3.21f, -6.36f, 0.31f);
		Vertex v11 = new Vertex(3.21f, -6.36f, -0.04f);
		Vertex v12 = new Vertex(3.21f, -6.4f, -0.31f);

		// L3 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v11.toVector(), v8.toVector(), v7.toVector()).submit();
			v10.submit();
			v11.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// L3 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v9.toVector(), v8.toVector()).submit();
			v11.submit();
			v12.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();

		Vertex v13 = new Vertex(3.43f, -6.63f, 0.52f);
		Vertex v14 = new Vertex(3.43f, -6.63f, -0.05f);
		Vertex v15 = new Vertex(3.43f, -6.63f, -0.5f);
		setMaterial(skinColour);
		// L4 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v11.toVector(), v10.toVector()).submit();
			v13.submit();
			v14.submit();
			v11.submit();
			v10.submit();
		}
		GL11.glEnd();
		// L4 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v12.toVector(), v11.toVector()).submit();
			v14.submit();
			v15.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();

		Vertex v16 = new Vertex(3.38f, -7f, 0.37f);
		Vertex v17 = new Vertex(3.38f, -7f, -0.04f);
		Vertex v18 = new Vertex(3.38f, -7f, -0.36f);

		// L5 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v16.toVector(), v17.toVector(), v14.toVector(), v13.toVector()).submit();
			v16.submit();
			v17.submit();
			v14.submit();
			v13.submit();
		}
		GL11.glEnd();
		// L5 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v17.toVector(), v18.toVector(), v15.toVector(), v14.toVector()).submit();
			v17.submit();
			v18.submit();
			v15.submit();
			v14.submit();
		}
		GL11.glEnd();
	}

	private void drawRightHandInner() {
		Vertex v1 = new Vertex(1.4f, -2.78f, -0.63f);
		Vertex v2 = new Vertex(1.78f, -2.78f, -0.06f);
		Vertex v3 = new Vertex(1.4f, -2.78f, 0.66f);
		Vertex v4 = new Vertex(2.16f, -4.14f, -0.43f);
		Vertex v5 = new Vertex(2.16f, -4.14f, -0.05f);
		Vertex v6 = new Vertex(2.16f, -4.14f, 0.45f);

		setMaterial(shirtColour);
		// L1 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v2.toVector(), v1.toVector()).submit();
			v4.submit();
			v5.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// L1 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v3.toVector(), v2.toVector()).submit();
			v5.submit();
			v6.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();

		Vertex v7 = new Vertex(2.51f, -5.9f, -0.43f);
		Vertex v8 = new Vertex(2.51f, -5.9f, -0.05f);
		Vertex v9 = new Vertex(2.51f, -5.9f, 0.45f);

		// L2 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v5.toVector(), v4.toVector()).submit();
			v7.submit();
			v8.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// L2 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v6.toVector(), v5.toVector()).submit();
			v8.submit();
			v9.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		Vertex v10 = new Vertex(2.72f, -6.46f, -0.31f);
		Vertex v11 = new Vertex(2.72f, -6.46f, -0.04f);
		Vertex v12 = new Vertex(2.72f, -6.46f, 0.31f);

		// L3 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v11.toVector(), v8.toVector(), v7.toVector()).submit();
			v10.submit();
			v11.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// L3 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v9.toVector(), v8.toVector()).submit();
			v11.submit();
			v12.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();

		Vertex v13 = new Vertex(2.63f, -6.79f, -0.5f);
		Vertex v14 = new Vertex(2.63f, -6.79f, -0.05f);
		Vertex v15 = new Vertex(2.6f, -6.79f, 0.52f);
		setMaterial(skinColour);
		// L4 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v11.toVector(), v10.toVector()).submit();
			v13.submit();
			v14.submit();
			v11.submit();
			v10.submit();
		}
		GL11.glEnd();
		// L4 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v12.toVector(), v11.toVector()).submit();
			v14.submit();
			v15.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();

		Vertex v16 = new Vertex(2.8f, -7.1f, -0.36f);
		Vertex v17 = new Vertex(2.8f, -7.1f, -0.04f);
		Vertex v18 = new Vertex(2.8f, -7.1f, 0.37f);

		// L5 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v16.toVector(), v17.toVector(), v14.toVector(), v13.toVector()).submit();
			v16.submit();
			v17.submit();
			v14.submit();
			v13.submit();
		}
		GL11.glEnd();
		// L5 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v17.toVector(), v18.toVector(), v15.toVector(), v14.toVector()).submit();
			v17.submit();
			v18.submit();
			v15.submit();
			v14.submit();
		}
		GL11.glEnd();
	}

	private void drawRightHandBottom() {
		Vertex v1 = new Vertex(2.8f, -7.1f, -0.36f);
		Vertex v2 = new Vertex(3.38f, -7f, -0.36f);
		Vertex v3 = new Vertex(3.38f, -7f, 0.37f);
		Vertex v4 = new Vertex(2.8f, -7.1f, 0.37f);

		setMaterial(skinColour);
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v4.toVector()).submit();
			v1.submit();
			v2.submit();
			v3.submit();
			v4.submit();
		}
		GL11.glEnd();
	}

	private void drawRightLeg() {
		Vertex v1 = new Vertex(0f, -6.56f, 0.66f);
		Vertex v2 = new Vertex(0.41f, -6.56f, 0.66f);
		Vertex v3 = new Vertex(1.01f, -6.56f, 0.66f);
		Vertex v4 = new Vertex(1.39f, -6.56f, -0.06f);
		Vertex v5 = new Vertex(1.01f, -6.56f, -0.63f);
		Vertex v6 = new Vertex(0.41f, -6.56f, -0.57f);
		Vertex v7 = new Vertex(0f, -6.56f, -0.55f);
		Vertex v8 = new Vertex(0f, -6.56f, 0f);

		Vertex v11 = new Vertex(0.84f, -10.23f, 0.64f);
		Vertex v12 = new Vertex(1.11f, -10.23f, 0.72f);
		Vertex v13 = new Vertex(1.59f, -10.23f, 0.58f);
		Vertex v14 = new Vertex(1.69f, -10.23f, -0.06f);
		Vertex v15 = new Vertex(1.58f, -10.23f, -0.48f);
		Vertex v16 = new Vertex(1.1f, -10.23f, -0.57f);
		Vertex v17 = new Vertex(0.83f, -10.23f, -0.4f);
		Vertex v18 = new Vertex(0.7f, -10.23f, 0f);

		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v2.toVector(), v1.toVector()).submit();
			v11.submit();
			v12.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v12.toVector(), v13.toVector(), v3.toVector(), v2.toVector()).submit();
			v12.submit();
			v13.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();
		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v4.toVector(), v3.toVector()).submit();
			v13.submit();
			v14.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v5.toVector(), v4.toVector()).submit();
			v14.submit();
			v15.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v15.toVector(), v16.toVector(), v6.toVector(), v5.toVector()).submit();
			v15.submit();
			v16.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v16.toVector(), v17.toVector(), v7.toVector(), v6.toVector()).submit();
			v16.submit();
			v17.submit();
			v7.submit();
			v6.submit();
		}
		GL11.glEnd();
		// 7
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v17.toVector(), v18.toVector(), v8.toVector(), v7.toVector()).submit();
			v17.submit();
			v18.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// 8
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v18.toVector(), v11.toVector(), v1.toVector(), v8.toVector()).submit();
			v18.submit();
			v11.submit();
			v1.submit();
			v8.submit();
		}
		GL11.glEnd();

	}

	private void drawRightFoot() {
		Vertex v1 = new Vertex(0.84f, -10.23f, 1.56f);
		Vertex v2 = new Vertex(1.11f, -10.23f, 1.64f);
		Vertex v3 = new Vertex(1.59f, -10.23f, 1.49f);
		Vertex v4 = new Vertex(1.59f, -10.23f, 0.58f);
		Vertex v5 = new Vertex(1.69f, -10.23f, 0.06f);
		Vertex v6 = new Vertex(1.59f, -10.23f, -0.48f);
		Vertex v7 = new Vertex(1.11f, -10.23f, -0.57f);
		Vertex v8 = new Vertex(0.83f, -10.23f, -0.4f);
		Vertex v9 = new Vertex(0.7f, -10.23f, 0);
		Vertex v10 = new Vertex(0.84f, -10.23f, 0.64f);

		Vertex v11 = new Vertex(0.84f, -10.7f, 1.56f);
		Vertex v12 = new Vertex(1.11f, -10.7f, 1.64f);
		Vertex v13 = new Vertex(1.59f, -10.7f, 1.49f);
		Vertex v14 = new Vertex(1.59f, -10.7f, 0.58f);
		Vertex v15 = new Vertex(1.69f, -10.7f, 0.06f);
		Vertex v16 = new Vertex(1.59f, -10.7f, -0.48f);
		Vertex v17 = new Vertex(1.11f, -10.7f, -0.57f);
		Vertex v18 = new Vertex(0.83f, -10.7f, -0.4f);
		Vertex v19 = new Vertex(0.7f, -10.7f, 0);
		Vertex v20 = new Vertex(0.84f, -10.7f, 0.64f);

		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v2.toVector(), v1.toVector()).submit();
			v11.submit();
			v12.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v12.toVector(), v13.toVector(), v3.toVector(), v2.toVector()).submit();
			v12.submit();
			v13.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();
		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v4.toVector(), v3.toVector()).submit();
			v13.submit();
			v14.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v5.toVector(), v4.toVector()).submit();
			v14.submit();
			v15.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v15.toVector(), v16.toVector(), v6.toVector(), v5.toVector()).submit();
			v15.submit();
			v16.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v16.toVector(), v17.toVector(), v7.toVector(), v6.toVector()).submit();
			v16.submit();
			v17.submit();
			v7.submit();
			v6.submit();
		}
		GL11.glEnd();
		// 7
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v17.toVector(), v18.toVector(), v8.toVector(), v7.toVector()).submit();
			v17.submit();
			v18.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// 8
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v18.toVector(), v19.toVector(), v9.toVector(), v8.toVector()).submit();
			v18.submit();
			v19.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();
		// 9
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v19.toVector(), v20.toVector(), v10.toVector(), v9.toVector()).submit();
			v19.submit();
			v20.submit();
			v10.submit();
			v9.submit();
		}
		GL11.glEnd();
		// 10
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v20.toVector(), v11.toVector(), v1.toVector(), v10.toVector()).submit();
			v20.submit();
			v11.submit();
			v1.submit();
			v10.submit();
		}
		GL11.glEnd();

		drawRightFootTop();
		drawRightFootBottom();
	}

	private void drawRightFootTop() {
		Vertex v1 = new Vertex(0.84f, -10.23f, 0.64f);
		Vertex v2 = new Vertex(0.84f, -10.23f, 1.56f);
		Vertex v3 = new Vertex(1.11f, -10.23f, 1.64f);
		Vertex v4 = new Vertex(1.59f, -10.23f, 1.5f);
		Vertex v5 = new Vertex(1.59f, -10.23f, 0.58f);
		Vertex v6 = new Vertex(1.11f, -10.23f, 0.72f);
		// front
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v3.toVector(), v6.toVector()).submit();
			v1.submit();
			v2.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();
		// back
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v3.toVector(), v4.toVector(), v6.toVector()).submit();
			v6.submit();
			v3.submit();
			v4.submit();
			v5.submit();
		}
		GL11.glEnd();
	}

	private void drawRightFootBottom() {
		Vertex v1 = new Vertex(0.84f, -10.7f, 1.56f);
		Vertex v2 = new Vertex(1.11f, -10.7f, 1.64f);
		Vertex v3 = new Vertex(1.59f, -10.7f, 1.49f);
		Vertex v4 = new Vertex(0.84f, -10.7f, 0.64f);
		Vertex v5 = new Vertex(1.11f, -10.7f, 0.72f);
		Vertex v6 = new Vertex(1.59f, -10.7f, 0.58f);

		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v2.toVector(), v1.toVector()).submit();
			v4.submit();
			v5.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v3.toVector(), v2.toVector()).submit();
			v5.submit();
			v6.submit();
			v3.submit();
			v2.submit();
		}
		GL11.glEnd();

		Vertex v7 = new Vertex(0.7f, -10.7f, 0);
		Vertex v8 = new Vertex(1.33f, -10.7f, 0);
		Vertex v9 = new Vertex(1.69f, -10.7f, 0.06f);

		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v5.toVector(), v4.toVector()).submit();
			v7.submit();
			v8.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v6.toVector(), v5.toVector()).submit();
			v8.submit();
			v9.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		Vertex v10 = new Vertex(0.83f, -10.7f, -0.4f);
		Vertex v11 = new Vertex(1.11f, -10.7f, -0.57f);
		Vertex v12 = new Vertex(1.59f, -10.7f, -0.48f);

		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v11.toVector(), v8.toVector(), v7.toVector()).submit();
			v10.submit();
			v11.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v9.toVector(), v8.toVector()).submit();
			v11.submit();
			v12.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();
	}

	private void drawLeftHand() {
		drawLeftHandFront();
		drawLeftHandBack();
		drawLeftHandOuter();
		drawLeftHandInner();
		drawLeftHandBottom();
	}

	private void drawLeftHandFront() {
		Vertex v1 = new Vertex(-1.4f, -2.78f, 0.66f); // row 1
		Vertex v2 = new Vertex(-1.4f, -1.74f, 0.66f);
		Vertex v3 = new Vertex(-2.16f, -4.14f, 0.45f); // row 2 (elbow)
		Vertex v4 = new Vertex(-2.86f, -4f, 0.45f);
		Vertex v5 = new Vertex(-2.51f, -5.9f, 0.45f); // row 3
		Vertex v6 = new Vertex(-3.2f, -5.76f, 0.45f);
		Vertex v7 = new Vertex(-2.72f, -6.46f, 0.31f); // row 4
		Vertex v8 = new Vertex(-3.21f, -6.36f, 0.31f);
		Vertex v9 = new Vertex(-2.63f, -6.8f, 0.52f); // row 5
		Vertex v10 = new Vertex(-3.43f, -6.63f, 0.52f);
		Vertex v11 = new Vertex(-2.8f, -7.1f, 0.37f); // row 6
		Vertex v12 = new Vertex(-3.38f, -7f, 0.37f);
		setMaterial(shirtColour);
		// L1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v4.toVector(), v3.toVector()).submit();
			v1.submit();
			v2.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// L2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v4.toVector(), v6.toVector(), v5.toVector()).submit();
			v3.submit();
			v4.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		// L3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v8.toVector(), v7.toVector()).submit();
			v5.submit();
			v6.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		setMaterial(skinColour);
		// L4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v10.toVector(), v9.toVector()).submit();
			v7.submit();
			v8.submit();
			v10.submit();
			v9.submit();
		}
		GL11.glEnd();
		// L5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v9.toVector(), v10.toVector(), v12.toVector(), v11.toVector()).submit();
			v9.submit();
			v10.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();

	}

	private void drawLeftHandBack() {
		Vertex v1 = new Vertex(-1.4f, -2.78f, -0.63f); // row 1
		Vertex v2 = new Vertex(-1.4f, -1.74f, -0.63f);
		Vertex v3 = new Vertex(-2.16f, -4.14f, -0.43f); // row 2 (elbow)
		Vertex v4 = new Vertex(-2.86f, -4f, -0.43f);
		Vertex v5 = new Vertex(-2.51f, -5.9f, -0.43f); // row 3
		Vertex v6 = new Vertex(-3.2f, -5.76f, -0.43f);
		Vertex v7 = new Vertex(-2.72f, -6.46f, -0.31f); // row 4
		Vertex v8 = new Vertex(-3.21f, -6.36f, -0.31f);
		Vertex v9 = new Vertex(-2.63f, -6.8f, -0.5f); // row 5
		Vertex v10 = new Vertex(-3.43f, -6.63f, -0.5f);
		Vertex v11 = new Vertex(-2.8f, -7.1f, -0.36f); // row 6
		Vertex v12 = new Vertex(-3.38f, -7f, -0.36f);
		setMaterial(shirtColour);
		// L1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v4.toVector(), v2.toVector(), v1.toVector()).submit();
			v3.submit();
			v4.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// L2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v4.toVector(), v3.toVector()).submit();
			v5.submit();
			v6.submit();
			v4.submit();
			v3.submit();
		}
		GL11.glEnd();
		// L3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v6.toVector(), v5.toVector()).submit();
			v7.submit();
			v8.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();
		setMaterial(skinColour);
		// L4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v9.toVector(), v10.toVector(), v8.toVector(), v7.toVector()).submit();
			v9.submit();
			v10.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// L5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v10.toVector(), v9.toVector()).submit();
			v11.submit();
			v12.submit();
			v10.submit();
			v9.submit();
		}
		GL11.glEnd();

	}

	private void drawLeftHandOuter() {
		Vertex v1 = new Vertex(-1.4f, -1.74f, 0.66f);
		Vertex v2 = new Vertex(-1.78f, -1.74f, -0.06f);
		Vertex v3 = new Vertex(-1.4f, -1.74f, -0.63f);
		Vertex v4 = new Vertex(-2.86f, -4f, 0.45f);
		Vertex v5 = new Vertex(-2.86f, -4f, -0.05f);
		Vertex v6 = new Vertex(-2.86f, -4f, -0.43f);
		setMaterial(shirtColour);
		// L1 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v5.toVector(), v4.toVector()).submit();
			v1.submit();
			v2.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// L1 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v6.toVector(), v5.toVector()).submit();
			v2.submit();
			v3.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		Vertex v7 = new Vertex(-3.2f, -5.76f, 0.45f);
		Vertex v8 = new Vertex(-3.2f, -5.76f, 0.05f);
		Vertex v9 = new Vertex(-3.2f, -5.76f, -0.43f);

		// L2 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v8.toVector(), v7.toVector()).submit();
			v4.submit();
			v5.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// L2 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v9.toVector(), v8.toVector()).submit();
			v5.submit();
			v6.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();

		Vertex v10 = new Vertex(-3.21f, -6.36f, 0.31f);
		Vertex v11 = new Vertex(-3.21f, -6.36f, -0.04f);
		Vertex v12 = new Vertex(-3.21f, -6.4f, -0.31f);

		// L3 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v11.toVector(), v10.toVector()).submit();
			v7.submit();
			v8.submit();
			v11.submit();
			v10.submit();
		}
		GL11.glEnd();
		// L3 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v12.toVector(), v11.toVector()).submit();
			v8.submit();
			v9.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();

		Vertex v13 = new Vertex(-3.43f, -6.63f, 0.52f);
		Vertex v14 = new Vertex(-3.43f, -6.63f, -0.05f);
		Vertex v15 = new Vertex(-3.43f, -6.63f, -0.5f);
		setMaterial(skinColour);
		// L4 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v11.toVector(), v14.toVector(), v13.toVector()).submit();
			v10.submit();
			v11.submit();
			v14.submit();
			v13.submit();
		}
		GL11.glEnd();
		// L4 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v15.toVector(), v14.toVector()).submit();
			v11.submit();
			v12.submit();
			v15.submit();
			v14.submit();
		}
		GL11.glEnd();

		Vertex v16 = new Vertex(-3.38f, -7f, 0.37f);
		Vertex v17 = new Vertex(-3.38f, -7f, -0.04f);
		Vertex v18 = new Vertex(-3.38f, -7f, -0.36f);

		// L5 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v17.toVector(), v16.toVector()).submit();
			v13.submit();
			v14.submit();
			v17.submit();
			v16.submit();
		}
		GL11.glEnd();
		// L5 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v18.toVector(), v17.toVector()).submit();
			v14.submit();
			v15.submit();
			v18.submit();
			v17.submit();
		}
		GL11.glEnd();

	}

	private void drawLeftHandInner() {
		Vertex v1 = new Vertex(-1.4f, -2.78f, -0.63f);
		Vertex v2 = new Vertex(-1.78f, -2.78f, -0.06f);
		Vertex v3 = new Vertex(-1.4f, -2.78f, 0.66f);
		Vertex v4 = new Vertex(-2.16f, -4.14f, -0.43f);
		Vertex v5 = new Vertex(-2.16f, -4.14f, -0.05f);
		Vertex v6 = new Vertex(-2.16f, -4.14f, 0.45f);
		setMaterial(shirtColour);
		// L1 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v5.toVector(), v4.toVector()).submit();
			v1.submit();
			v2.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// L1 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v6.toVector(), v5.toVector()).submit();
			v2.submit();
			v3.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		Vertex v7 = new Vertex(-2.51f, -5.9f, -0.43f);
		Vertex v8 = new Vertex(-2.51f, -5.9f, -0.05f);
		Vertex v9 = new Vertex(-2.51f, -5.9f, 0.45f);

		// L2 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v8.toVector(), v7.toVector()).submit();
			v4.submit();
			v5.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// L2 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v9.toVector(), v8.toVector()).submit();
			v5.submit();
			v6.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();

		Vertex v10 = new Vertex(-2.72f, -6.46f, -0.31f);
		Vertex v11 = new Vertex(-2.72f, -6.46f, -0.04f);
		Vertex v12 = new Vertex(-2.72f, -6.46f, 0.31f);

		// L3 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v11.toVector(), v10.toVector()).submit();
			v7.submit();
			v8.submit();
			v11.submit();
			v10.submit();
		}
		GL11.glEnd();
		// L3 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v12.toVector(), v11.toVector()).submit();
			v8.submit();
			v9.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();

		Vertex v13 = new Vertex(-2.63f, -6.79f, -0.5f);
		Vertex v14 = new Vertex(-2.63f, -6.79f, -0.05f);
		Vertex v15 = new Vertex(-2.6f, -6.79f, 0.52f);
		setMaterial(skinColour);
		// L4 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v11.toVector(), v14.toVector(), v13.toVector()).submit();
			v10.submit();
			v11.submit();
			v14.submit();
			v13.submit();
		}
		GL11.glEnd();
		// L4 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v11.toVector(), v12.toVector(), v15.toVector(), v14.toVector()).submit();
			v11.submit();
			v12.submit();
			v15.submit();
			v14.submit();
		}
		GL11.glEnd();

		Vertex v16 = new Vertex(-2.8f, -7.1f, -0.36f);
		Vertex v17 = new Vertex(-2.8f, -7.1f, -0.04f);
		Vertex v18 = new Vertex(-2.8f, -7.1f, 0.37f);
		// L5 left
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v13.toVector(), v14.toVector(), v17.toVector(), v16.toVector()).submit();
			v13.submit();
			v14.submit();
			v17.submit();
			v16.submit();
		}
		GL11.glEnd();
		// L5 right
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v14.toVector(), v15.toVector(), v18.toVector(), v17.toVector()).submit();
			v14.submit();
			v15.submit();
			v18.submit();
			v17.submit();
		}
		GL11.glEnd();

	}

	private void drawLeftHandBottom() {
		Vertex v1 = new Vertex(-2.8f, -7.1f, -0.36f);
		Vertex v2 = new Vertex(-3.38f, -7f, -0.36f);
		Vertex v3 = new Vertex(-3.38f, -7f, 0.37f);
		Vertex v4 = new Vertex(-2.8f, -7.1f, 0.37f);
		setMaterial(skinColour);
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v3.toVector(), v2.toVector(), v1.toVector()).submit();
			v4.submit();
			v3.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();

	}

	private void drawLeftLeg(float leftLegZ) {
		Vertex v1 = new Vertex(0f, -6.56f, 0.66f);
		Vertex v2 = new Vertex(-0.41f, -6.56f, 0.66f);
		Vertex v3 = new Vertex(-1.01f, -6.56f, 0.66f);
		Vertex v4 = new Vertex(-1.39f, -6.56f, -0.06f);
		Vertex v5 = new Vertex(-1.01f, -6.56f, -0.63f);
		Vertex v6 = new Vertex(-0.41f, -6.56f, -0.57f);
		Vertex v7 = new Vertex(0f, -6.56f, -0.55f);
		Vertex v8 = new Vertex(0f, -6.56f, 0f);

		Vertex v11 = new Vertex(-0.84f, -10.23f, 0.64f);
		Vertex v12 = new Vertex(-1.11f, -10.23f, 0.72f);
		Vertex v13 = new Vertex(-1.59f, -10.23f, 0.58f);
		Vertex v14 = new Vertex(-1.69f, -10.23f, -0.06f);
		Vertex v15 = new Vertex(-1.58f, -10.23f, -0.48f);
		Vertex v16 = new Vertex(-1.1f, -10.23f, -0.57f);
		Vertex v17 = new Vertex(-0.83f, -10.23f, -0.4f);
		Vertex v18 = new Vertex(-0.7f, -10.23f, 0f);

		GL11.glPushMatrix();
		{
			GL11.glTranslatef(0, 0, this.leftLegZ);
			// 1
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v1.toVector(), v2.toVector(), v12.toVector(), v11.toVector()).submit();
				v1.submit();
				v2.submit();
				v12.submit();
				v11.submit();
			}
			GL11.glEnd();
			// 2
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v2.toVector(), v3.toVector(), v13.toVector(), v12.toVector()).submit();
				v2.submit();
				v3.submit();
				v13.submit();
				v12.submit();
			}
			GL11.glEnd();
			// 3
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v3.toVector(), v4.toVector(), v14.toVector(), v13.toVector()).submit();
				v3.submit();
				v4.submit();
				v14.submit();
				v13.submit();
			}
			GL11.glEnd();
			// 4
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v4.toVector(), v5.toVector(), v15.toVector(), v14.toVector()).submit();
				v4.submit();
				v5.submit();
				v15.submit();
				v14.submit();
			}
			GL11.glEnd();
			// 5
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v5.toVector(), v6.toVector(), v16.toVector(), v15.toVector()).submit();
				v5.submit();
				v6.submit();
				v16.submit();
				v15.submit();
			}
			GL11.glEnd();
			// 6
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v6.toVector(), v7.toVector(), v17.toVector(), v16.toVector()).submit();
				v6.submit();
				v7.submit();
				v17.submit();
				v16.submit();
			}
			GL11.glEnd();
			// 7
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v7.toVector(), v8.toVector(), v18.toVector(), v17.toVector()).submit();
				v7.submit();
				v8.submit();
				v18.submit();
				v17.submit();
			}
			GL11.glEnd();
			// 8
			GL11.glBegin(GL11.GL_POLYGON);
			{
				new Normal(v8.toVector(), v1.toVector(), v11.toVector(), v18.toVector()).submit();
				v8.submit();
				v1.submit();
				v11.submit();
				v18.submit();
			}
			GL11.glEnd();
		}
		GL11.glPopMatrix();

	}

	private void drawLeftFoot() {

		Vertex v1 = new Vertex(-0.84f, -10.23f, 1.56f);
		Vertex v2 = new Vertex(-1.11f, -10.23f, 1.64f);
		Vertex v3 = new Vertex(-1.59f, -10.23f, 1.49f);
		Vertex v4 = new Vertex(-1.59f, -10.23f, 0.58f);
		Vertex v5 = new Vertex(-1.69f, -10.23f, 0.06f);
		Vertex v6 = new Vertex(-1.59f, -10.23f, -0.48f);
		Vertex v7 = new Vertex(-1.11f, -10.23f, -0.57f);
		Vertex v8 = new Vertex(-0.83f, -10.23f, -0.4f);
		Vertex v9 = new Vertex(-0.7f, -10.23f, 0);
		Vertex v10 = new Vertex(-0.84f, -10.23f, 0.64f);

		Vertex v11 = new Vertex(-0.84f, -10.7f, 1.56f);
		Vertex v12 = new Vertex(-1.11f, -10.7f, 1.64f);
		Vertex v13 = new Vertex(-1.59f, -10.7f, 1.49f);
		Vertex v14 = new Vertex(-1.59f, -10.7f, 0.58f);
		Vertex v15 = new Vertex(-1.69f, -10.7f, 0.06f);
		Vertex v16 = new Vertex(-1.59f, -10.7f, -0.48f);
		Vertex v17 = new Vertex(-1.11f, -10.7f, -0.57f);
		Vertex v18 = new Vertex(-0.83f, -10.7f, -0.4f);
		Vertex v19 = new Vertex(-0.7f, -10.7f, 0);
		Vertex v20 = new Vertex(-0.84f, -10.7f, 0.64f);

		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v12.toVector(), v11.toVector()).submit();
			v1.submit();
			v2.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v13.toVector(), v12.toVector()).submit();
			v2.submit();
			v3.submit();
			v13.submit();
			v12.submit();
		}
		GL11.glEnd();
		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v3.toVector(), v4.toVector(), v14.toVector(), v13.toVector()).submit();
			v3.submit();
			v4.submit();
			v14.submit();
			v13.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v15.toVector(), v14.toVector()).submit();
			v4.submit();
			v5.submit();
			v15.submit();
			v14.submit();
		}
		GL11.glEnd();
		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v16.toVector(), v15.toVector()).submit();
			v5.submit();
			v6.submit();
			v16.submit();
			v15.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v7.toVector(), v17.toVector(), v16.toVector()).submit();
			v6.submit();
			v7.submit();
			v17.submit();
			v16.submit();
		}
		GL11.glEnd();
		// 7
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v18.toVector(), v17.toVector()).submit();
			v7.submit();
			v8.submit();
			v18.submit();
			v17.submit();
		}
		GL11.glEnd();
		// 8
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v1.toVector(), v11.toVector(), v18.toVector()).submit();
			v8.submit();
			v1.submit();
			v11.submit();
			v18.submit();
		}
		GL11.glEnd();

		// 9
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v9.toVector(), v10.toVector(), v20.toVector(), v19.toVector()).submit();
			v9.submit();
			v10.submit();
			v20.submit();
			v19.submit();
		}
		GL11.glEnd();
		// 10
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v10.toVector(), v1.toVector(), v11.toVector(), v20.toVector()).submit();
			v10.submit();
			v1.submit();
			v11.submit();
			v20.submit();
		}
		GL11.glEnd();

		drawLeftFootTop();
		drawLeftFootBottom();
	}

	private void drawLeftFootTop() {
		Vertex v1 = new Vertex(-0.84f, -10.23f, 0.64f);
		Vertex v2 = new Vertex(-0.84f, -10.23f, 1.56f);
		Vertex v3 = new Vertex(-1.11f, -10.23f, 1.64f);
		Vertex v4 = new Vertex(-1.59f, -10.23f, 1.5f);
		Vertex v5 = new Vertex(-1.59f, -10.23f, 0.58f);
		Vertex v6 = new Vertex(-1.11f, -10.23f, 0.72f);

		// front
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v6.toVector(), v3.toVector(), v2.toVector(), v1.toVector()).submit();
			v6.submit();
			v3.submit();
			v2.submit();
			v1.submit();
		}
		GL11.glEnd();
		// back
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v4.toVector(), v3.toVector(), v6.toVector()).submit();
			v5.submit();
			v4.submit();
			v3.submit();
			v6.submit();
		}
		GL11.glEnd();
	}

	private void drawLeftFootBottom() {
		Vertex v1 = new Vertex(-0.84f, -10.7f, 1.56f);
		Vertex v2 = new Vertex(-1.11f, -10.7f, 1.64f);
		Vertex v3 = new Vertex(-1.59f, -10.7f, 1.49f);
		Vertex v4 = new Vertex(-0.84f, -10.7f, 0.64f);
		Vertex v5 = new Vertex(-1.11f, -10.7f, 0.72f);
		Vertex v6 = new Vertex(-1.59f, -10.7f, 0.58f);

		// 1
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v1.toVector(), v2.toVector(), v5.toVector(), v4.toVector()).submit();
			v1.submit();
			v2.submit();
			v5.submit();
			v4.submit();
		}
		GL11.glEnd();
		// 2
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v2.toVector(), v3.toVector(), v6.toVector(), v5.toVector()).submit();
			v2.submit();
			v3.submit();
			v6.submit();
			v5.submit();
		}
		GL11.glEnd();

		Vertex v7 = new Vertex(-0.7f, -10.7f, 0);
		Vertex v8 = new Vertex(-1.33f, -10.7f, 0);
		Vertex v9 = new Vertex(-1.69f, -10.7f, 0.06f);

		// 3
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v4.toVector(), v5.toVector(), v8.toVector(), v7.toVector()).submit();
			v4.submit();
			v5.submit();
			v8.submit();
			v7.submit();
		}
		GL11.glEnd();
		// 4
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v5.toVector(), v6.toVector(), v9.toVector(), v8.toVector()).submit();
			v5.submit();
			v6.submit();
			v9.submit();
			v8.submit();
		}
		GL11.glEnd();

		Vertex v10 = new Vertex(-0.83f, -10.7f, -0.4f);
		Vertex v11 = new Vertex(-1.11f, -10.7f, -0.57f);
		Vertex v12 = new Vertex(-1.59f, -10.7f, -0.48f);

		// 5
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v7.toVector(), v8.toVector(), v11.toVector(), v10.toVector()).submit();
			v7.submit();
			v8.submit();
			v11.submit();
			v10.submit();
		}
		GL11.glEnd();
		// 6
		GL11.glBegin(GL11.GL_POLYGON);
		{
			new Normal(v8.toVector(), v9.toVector(), v12.toVector(), v11.toVector()).submit();
			v8.submit();
			v9.submit();
			v12.submit();
			v11.submit();
		}
		GL11.glEnd();
	}

}
